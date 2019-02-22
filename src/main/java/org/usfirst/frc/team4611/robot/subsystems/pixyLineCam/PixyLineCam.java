package org.usfirst.frc.team4611.robot.subsystems.pixyLineCam;

import java.util.logging.Logger;

import org.usfirst.frc.team4611.robot.networktables.NetTableManager;
import org.usfirst.frc.team4611.robot.subsystems.pixyLineCam.commands.PollPixyLine;
//import org.usfirst.frc.team4611.robot.subsystems.vision.commands.PollNetworkTable;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import io.github.pseudoresonance.pixy2api.Pixy2;
import io.github.pseudoresonance.pixy2api.Pixy2Line;

public class PixyLineCam extends Subsystem{

    private Pixy2 pixy;
    private Pixy2Line line;


    Logger logger = Logger.getLogger(PixyLineCam.class.getName());
    

    private ShuffleboardTab tab;
    private NetworkTableEntry x0;
    private NetworkTableEntry y0;
    private NetworkTableEntry x1;
    private NetworkTableEntry y1;
    private NetworkTableEntry index;
    private NetworkTableEntry flags;
    private NetworkTableEntry arrayNumber;
    private NetworkTableEntry slope;
    private NetworkTableEntry angle;
    private NetworkTableEntry leftStatus;
    private NetworkTableEntry middleStatus;
    private NetworkTableEntry rightStatus;
    
    




    public void PixyLineCam() {

        
    }

    public void resetPixyLine() {
        x0.setNumber(0);
        y0.setNumber(0);
        x1.setNumber(0);
        y1.setNumber(0);
        index.setNumber(0);
        flags.setNumber(0);
        arrayNumber.setNumber(0);
        slope.setNumber(0);
        angle.setNumber(0);
        leftStatus.setBoolean(false);
        middleStatus.setBoolean(false);
        rightStatus.setBoolean(false);


    }

    public void init() {
        logger.info("initializing");
        tab = Shuffleboard.getTab("PixyLineCam");
        logger.info("Creating Pixy with link type of SPI");
        pixy = Pixy2.createInstance(Pixy2.LinkType.SPI);
        pixy.init(0);
        logger.info("Here comes the version! :) " + pixy.getVersionInfo().toString());
        line = pixy.getLine();

        NetTableManager.updateValue("Health Map", "PixyLineInitialize", false);

        x0 = tab.add("Pixy x0", 0).getEntry();
        y0 = tab.add("Pixy y0", 0).getEntry();
        x1 = tab.add("Pixy x1", 0).getEntry();
        y1 = tab.add("Pixy y1", 0).getEntry();
        flags = tab.add("Pixy Flags", 0).getEntry();
        index = tab.add("Pixy Index", 0).getEntry();
        arrayNumber = tab.add("Number of Verticals", 0).getEntry();
        slope = tab.add("Vector Slope", 0).getEntry();
        angle = tab.add("Vector Angle", 0).getEntry();
        leftStatus = tab.add("Left", false).getEntry();
        middleStatus = tab.add("MIDDLE !!!!!!!", false).getEntry();
        rightStatus = tab.add("Right", false).getEntry();
  
      }


    public Pixy2Line getLine() {
        return this.line;
    }



    public void writeLine(Pixy2Line.Vector line, int number) {
        x0.setNumber(line.getX0());
        y0.setNumber(line.getY0());
        x1.setNumber(line.getX1());
        y1.setNumber(line.getY1());
        index.setNumber(line.getIndex());
        flags.setNumber(line.getFlags());
        arrayNumber.setNumber(number);

        //calculating slope
        int vectorSlope = (line.getY0()-line.getY1())/(line.getX0()-line.getX1());
        slope.setNumber(vectorSlope);

        //calculating angles
        double ratio = (double) (line.getX1()-line.getX0())/(line.getY1()-line.getY0());//Math.toDegrees(Math.atan((line.getX0()-line.getX1())/(line.getY0()-line.getY1())));
        double vectorAngle = Math.toDegrees(Math.atan(ratio));
        angle.setDouble(vectorAngle);

        //identifying relative location
        int averageX = (line.getX0() + line.getX1())/2;
        int leftX = 37;
        int rightX = 41;
        boolean left = false;
        boolean middle = false;
        boolean right = false;
        if (averageX < leftX || averageX < leftX) {
            left = true;
        } else if (averageX > rightX || averageX > rightX) {
            right = true;
        } else {
            middle = true;
        }
        leftStatus.setBoolean(left);
        rightStatus.setBoolean(right);
        middleStatus.setBoolean(middle);

    }

    protected void initDefaultCommand() {
        setDefaultCommand(new PollPixyLine());
    } 

}