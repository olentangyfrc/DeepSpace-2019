#!/usr/bin/env python3
#----------------------------------------------------------------------------
# Copyright (c) 2018 FIRST. All Rights Reserved.
# Open Source Software - may be modified and shared by FRC teams. The code
# must be accompanied by the FIRST BSD license file in the root directory of
# the project.

# My 2019 license: use it as much as you want. Crediting is recommended because it lets me know that I am being useful.
# Credit to Screaming Chickens 3997

# This is meant to be used in conjuction with WPILib Raspberry Pi image: https://github.com/wpilibsuite/FRCVision-pi-gen
#----------------------------------------------------------------------------
import json
import time
import sys
import argparse
from datetime import datetime


from cscore import CameraServer, VideoSource
from networktables import NetworkTablesInstance
import cv2
import numpy as np
from networktables import NetworkTables
import math

#merge

"""Start running the camera."""
def startCamera(config):
    print("Starting camera '{}' on {}".format(config.name, config.path))
    cs = CameraServer.getInstance()
    camera = cs.startAutomaticCapture(name=config.name, path=config.path)

    camera.setConfigJson(json.dumps(config.config))

    return cs, camera


"""Read configuration file."""
def readConfig():
    global team
    global server

    # parse file
    try:
        with open(configFile, "rt") as f:
            j = json.load(f)
    except OSError as err:
        print("could not open '{}': {}".format(configFile, err), file=sys.stderr)
        return False

    # top level must be an object
    if not isinstance(j, dict):
        parseError("must be JSON object")
        return False

    # team number
    try:
        team = j["team"]
    except KeyError:
        parseError("could not read team number")
        return False



#################### FRC VISION PI Image Specific #############
configFile = "/boot/frc.json"

class CameraConfig: pass

team = None
server = False
cameraConfigs = []

"""Report parse error."""
def parseError(str):
    print("config error in '" + configFile + "': " + str, file=sys.stderr)

"""Read single camera configuration."""
def readCameraConfig(config):
    cam = CameraConfig()

    # name
    try:
        cam.name = config["name"]
    except KeyError:
        parseError("could not read camera name")
        return False

    # path
    try:
        cam.path = config["path"]
    except KeyError:
        parseError("camera '{}': could not read path".format(cam.name))
        return False

    cam.config = config

    cameraConfigs.append(cam)
    return True

"""Read configuration file."""
def readConfig():
    global team
    global server

    # parse file
    try:
        with open(configFile, "rt") as f:
            j = json.load(f)
    except OSError as err:
        print("could not open '{}': {}".format(configFile, err), file=sys.stderr)
        return False

    # top level must be an object
    if not isinstance(j, dict):
        parseError("must be JSON object")
        return False

    # team number
    try:
        team = j["team"]
    except KeyError:
        parseError("could not read team number")
        return False

    # ntmode (optional)
    if "ntmode" in j:
        str = j["ntmode"]
        if str.lower() == "client":
            server = False
        elif str.lower() == "server":
            server = True
        else:
            parseError("could not understand ntmode value '{}'".format(str))

    # cameras
    try:
        cameras = j["cameras"]
    except KeyError:
        parseError("could not read cameras")
        return False
    for camera in cameras:
        if not readCameraConfig(camera):
            return False

    return True
    #merge



def picamvidopencv(image, nettable):
    # initialize the camera and grab a reference to the raw camera capture
    # camera.shutter_speed = 7300 #18000  # 0 to 31163; 0 is auto
    # rawCapture = PiRGBArray(camera, size=(640, 480))
    crosshair = [320, 240]
    toggle_rectangles = True
    image_width = 640
    image_height = 480
    minArea = 250 # ~350 pixels when 7 feet away
    maxArea = 22500 # used to hide rope
    maxDist = 3 # distance between 1 contour and another in widths

    angle = 0.0
    hypoDist = 0.0
    straightDist = 0.0
    found = False

    hsv = cv2.cvtColor(image, cv2.COLOR_BGR2HSV)

    # Adjust image
    #mask = cv2.morphologyEx(image, cv2.MORPH_OPEN, kernel)
    mask = cv2.erode(hsv, None, iterations=2)
    mask = cv2.dilate(mask, None, iterations=2)
    mask = cv2.inRange(mask, np.array([50, 50, 125]), np.array([100, 255, 255]))

    phase1 = mask.copy()
    im2, contours, hierarchy = cv2.findContours(mask, cv2.RETR_LIST, cv2.CHAIN_APPROX_SIMPLE)
    leftContours = []
    rightContours = []
    contourPairs = []
    largeCrop = phase1[100:300, 100:500]

    #Center Pair and Variables
    bestContourPair = None
    centerX = None
    centerY = None
    avgHeight = None

    numContours = 0
    if len(contours) > 0:
        for contour in contours:
            rect = cv2.minAreaRect(contour)
            
            rotAngle = abs(rect[2]) #returns between 0 & 90
            if rotAngle < 45:
                x, y = rect[0] # center[0] = xPos,   center[1] = yPos 
                w, h = rect[1]   # size[0] = xSize     size[1] = ySize
            else:
                x, y = rect[0]
                h, w = rect[1]

            if (w * h) > minArea: #if the area is greater than minArea, used to remove single pixels
                if (w * h) < maxArea: #used to remove the rope
                    if (h / w) > (5.5 / 2) - 1 and (h / w) < (5.5 / 2) + 1: # ideally h = 5.5 and w = 2

                        #Draw
                        box = cv2.boxPoints(rect)  # cv2.boxPoints(rect) for OpenCV 3.x
                        box = np.int0(box)

                        if options.show:
                            cv2.drawContours(image,[box],0,(0,0,255),2)

                        if rotAngle > 75.5 - 7 and rotAngle < 75.5 + 7:
                            leftContours.append({'x': x, 'y': y, 'w': w, 'h': h, 'rotAngle': rotAngle, 'verticalHeight': box[0][1] - box[2][1]})
                        elif (rotAngle > 14.5 - 10 and rotAngle < 14.5 + 10):
                            rightContours.append({'x': x, 'y': y, 'w': w, 'h': h, 'rotAngle': rotAngle, 'verticalHeight': box[0][1] - box[2][1]})


        #Match Contours
        for leftContour in leftContours:
            for rightContour in rightContours:
                if leftContour['x'] < rightContour['x'] and abs(rightContour['y'] - leftContour['y']) < 0.5 * leftContour['y'] \
                    and (rightContour['x'] - leftContour['x']) < (2.5 * leftContour['h']) + .5 * leftContour['h']:
                    contourPairs.append({'Left': leftContour, 'Right': rightContour})


        #Find best pair
        closestDistAway = 999
        for contourPair in contourPairs:
            if abs(((contourPair['Left']['x'] + contourPair['Right']['x']) / 2) - (640/2)) < closestDistAway:
                closestDistAway = abs(((contourPair['Left']['x'] + contourPair['Right']['x']) / 2) - (640/2))
                bestContourPair = contourPair

        #For best pair
        if bestContourPair is not None:
            centerX = (bestContourPair['Left']['x'] + bestContourPair['Right']['x']) / 2
            centerY = (bestContourPair['Left']['y'] + bestContourPair['Right']['y']) / 2
            avgHeight = (bestContourPair['Left']['h'] + bestContourPair['Right']['h']) / 2  #all in pixels
            verticalHeightPixels = (bestContourPair['Left']['verticalHeight'] + bestContourPair['Right']['verticalHeight']) / 2 

            #Find angle and distance
            offset = centerX - 320
            angle = offset * (62.2 / 640) #Horizontal angle

            verticalHeightTape = 2 * math.sin(math.radians(14.5)) + 5.5 * math.cos(math.radians(14.5)) # in inches

            heightTape = 5.5 # in inches
            fieldOfView = math.radians(48.8/2)

            #perpDist = ((heightTape * 480/2)/avgHeight)/(math.tan(fieldOfView)) #Perpendicular Distance
            #hypoDist = perpDist/math.cos(abs(math.radians(angle)))              #Hypotenuse Distance

            straightDist = ((480/2) * (verticalHeightTape / verticalHeightPixels)) / (math.tan(fieldOfView))
            hypoDist = straightDist/math.cos(abs(math.radians(angle)))

            found = True


    # Publish Angle & Distance
    
    nettable.putNumber('rPi last update', datetime.utcnow().timestamp())
    nettable.putNumber('angle', float(angle))
    nettable.putNumber('distance', float(hypoDist))
    nettable.putBoolean('found', found)

    leftBox = False
    middleBox = False
    rightBox = False

    if found:
        if abs(angle) < 5:
            middleBox = True
        elif angle > 0:
            leftBox = True
        else:
            rightBox = True

    nettable.putBoolean('leftBox', leftBox)
    nettable.putBoolean('middleBox', middleBox)
    nettable.putBoolean('rightBox', rightBox)

    # show the frame
    if options.show:
        if bestContourPair is not None:
            cv2.circle(image, (int(centerX), int(centerY)), 5, (255, 0, 255), 3)

        cv2.putText(image, "LeftContours: " + str(len(leftContours)), (200, 380), cv2.FONT_HERSHEY_PLAIN, 2, (255, 255, 255), 3, 8)
        cv2.putText(image, "RightContours: " + str(len(rightContours)), (200, 360), cv2.FONT_HERSHEY_PLAIN, 2, (255, 255, 255), 3, 8)
        cv2.putText(image, "Angle: " + str(angle), (200, 400), cv2.FONT_HERSHEY_PLAIN, 2, (255, 255, 255), 3, 8)
        cv2.putText(image, "Distance: " + str(hypoDist), (200, 420), cv2.FONT_HERSHEY_PLAIN, 2, (255, 255, 255), 3, 8)
        cv2.putText(image, str(found), (200, 440), cv2.FONT_HERSHEY_PLAIN, 2, (255, 255, 255), 3, 8)

        cv2.putText(image, "Contours:" + str(len(contours)), (0, 20), cv2.FONT_HERSHEY_PLAIN, 1, (255, 255, 255), 1, 8)
        #cv2.putText(image, "(S)hutter: " + str(camera.shutter_speed), (0, 40), cv2.FONT_HERSHEY_PLAIN, 1, (255, 255, 255), 1, 8)
        cv2.line(image, (crosshair[0]-10, crosshair[1]), (crosshair[0]+10, crosshair[1]), (255, 255, 255), thickness=1)
        cv2.line(image, (crosshair[0], crosshair[1]-10), (crosshair[0], crosshair[1]+10), (255, 255, 255), thickness=1)
        cv2.putText(image, "HSV: " + str(hsv[crosshair[0], crosshair[1]]), (0, 60), cv2.FONT_HERSHEY_PLAIN, 1, (255, 255, 255), 1, 8)

        # show the frame
        #cv2.imshow("Image", image)
        #cv2.imshow("Mask", phase1)

    # clear the stream in preparation for the next frame
    #rawCapture.truncate(0)

    return image



def main():

    readConfig()
     # start cameras
    image_width = 640
    image_height = 480
    cameras = []
    streams = []
    for cameraConfig in cameraConfigs:
        cs, cameraCapture = startCamera(cameraConfig)
        streams.append(cs)
        cameras.append(cameraCapture)
    #Get the first camera
    cameraServer = streams[0]
    # Get a CvSink. This will capture images from the camera
    cvSink = cameraServer.getVideo()

    # (optional) Setup a CvSource. This will send images back to the Dashboard
    outputStream = cameraServer.putVideo("stream", image_width, image_height)
    # Allocating new images is very expensive, always try to preallocate
    img = np.zeros(shape=(image_height, image_width, 3), dtype=np.uint8)

    # initialize network tables
    # start NetworkTables
    ntinst = NetworkTablesInstance.getDefault()
    if server:
        print("Setting up NetworkTables server")
        ntinst.startServer()
    else:
        print("Setting up NetworkTables client for team {}".format(team))
        ntinst.startClientTeam(team)

    nettable = ntinst.getTable("Shuffleboard").getSubTable('Vision')
    nettable.getEntry('connected').setValue('true')

    # allow the camera to warmup
    time.sleep(0.1)

    # loop forever
    while True:
        # Tell the CvSink to grab a frame from the camera and put it
        # in the source image.  If there is an error notify the output.
        timestamp, img = cvSink.grabFrame(img)
        #frame = flipImage(img)
        if timestamp == 0:
            # Send the output the error.
            outputStream.notifyError(cvSink.getError());
            # skip the rest of the current iteration
            continue

        frame = picamvidopencv(img, nettable)
        # (optional) send some image back to the dashboard
        outputStream.putFrame(frame)



if __name__ == '__main__':
    parser = argparse.ArgumentParser()
    parser.add_argument('--show', action='store_true', dest='show', default=True)
    parser.add_argument('--usekb', action='store_true', dest='usekb', default=False)
    options = parser.parse_args()
    main()
