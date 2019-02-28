/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4611.robot.subsystems.driverfeedback;

import java.util.logging.Logger;

import org.usfirst.frc.team4611.robot.subsystems.SubsystemFactory;
import org.usfirst.frc.team4611.robot.subsystems.Intake.Intake;
import org.usfirst.frc.team4611.robot.subsystems.IntakeAdjuster.IntakeAdjuster;
import org.usfirst.frc.team4611.robot.subsystems.Roller.Roller;
import org.usfirst.frc.team4611.robot.subsystems.WheelIntake.WheelIntake;
import org.usfirst.frc.team4611.robot.subsystems.driverfeedback.commands.DriverFeedbackDefault;
import org.usfirst.frc.team4611.robot.subsystems.elevator.Elevator;
import org.usfirst.frc.team4611.robot.subsystems.kicker.Kicker;
import org.usfirst.frc.team4611.robot.subsystems.navigation.Navigation;
import org.usfirst.frc.team4611.robot.subsystems.navigation.sensors.LineTracker;
import org.usfirst.frc.team4611.robot.subsystems.petal.Petal;
import org.usfirst.frc.team4611.robot.subsystems.pixyCam.PixyCam;
import org.usfirst.frc.team4611.robot.subsystems.spatula.Spatula;
import org.usfirst.frc.team4611.robot.subsystems.stick.Stick;
import org.usfirst.frc.team4611.robot.subsystems.trianglehatch.TriangleHatch;
import org.usfirst.frc.team4611.robot.subsystems.vision.Vision;

import edu.wpi.cscore.HttpCamera;
import edu.wpi.cscore.VideoSource;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.shuffleboard.ComplexWidget;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;

/**
 * Faux subsystem that simply creates a DriverFeedback shuffleboard tab and updates
 * with various subsystems' data to present in aggregate.
 */
public class DriverFeedback extends Subsystem {
  private static Logger logger = Logger.getLogger(DriverFeedback.class.getName());

    private Petal petal; 
    private Navigation nav;
    private TriangleHatch triangleHatch;
    private Stick stick;
    private Spatula spatula;
    private Kicker kicker;
    private Vision vision;
    private WheelIntake wheelIntake;
    private Elevator elevator;
    private double doubleWheel;
    private LineTracker lineTracker;
    private Roller roller;
    private Intake shooterIntake;
    private IntakeAdjuster intakeAdjuster;
    private PixyCam pixyCam;

    ShuffleboardTab tab;
    private NetworkTableEntry   leftSideSquare, rightSideSquare, frontSquare, frontCentered;
    private NetworkTableEntry   elevatorPos1, elevatorPos2, elevatorPos3, elevatorPos4, elevatorPos5, elevatorPos6, elevatorPos7;
    private NetworkTableEntry   lineTrackerLeft, lineTrackerRight;
    private ComplexWidget       videoEntry;

  public void init() {

    petal = SubsystemFactory.getInstance().getPetal();
    nav = SubsystemFactory.getInstance().getNavigation();
    triangleHatch = SubsystemFactory.getInstance().getTriangleHatch();
    stick= SubsystemFactory.getInstance().getStick();
    spatula= SubsystemFactory.getInstance().getSpatula();
    kicker= SubsystemFactory.getInstance().getKicker();
    vision= SubsystemFactory.getInstance().getVision();
    wheelIntake= SubsystemFactory.getInstance().getWheelIntake();
    elevator= SubsystemFactory.getInstance().getElevator();
    lineTracker= SubsystemFactory.getInstance().getLineTracker();
    roller= SubsystemFactory.getInstance().getRoller();
    shooterIntake= SubsystemFactory.getInstance().getShooterIntake();
    intakeAdjuster= SubsystemFactory.getInstance().getIntakeAdjuster();
    pixyCam= SubsystemFactory.getInstance().getPixyCam();

    tab = Shuffleboard.getTab("DriverFeedback");
    Shuffleboard.selectTab("DriverFeedback");

    // Elevator Entries
    elevatorPos1    = tab.add("Elevator 1", false).withSize(1,1).withPosition(0,0).getEntry();
    elevatorPos2    = tab.add("Elevator 2", false).withSize(1,1).withPosition(0,1).getEntry();
    elevatorPos3    = tab.add("Elevator 3", false).withSize(1,1).withPosition(0,2).getEntry();
    elevatorPos4    = tab.add("Elevator 4", false).withSize(1,1).withPosition(0,3).getEntry();
    elevatorPos5    = tab.add("Elevator 5", false).withSize(1,1).withPosition(0,4).getEntry();
    elevatorPos6    = tab.add("Elevator 6", false).withSize(1,1).withPosition(0,5).getEntry();
    elevatorPos7    = tab.add("Elevator 7", false).withSize(1,1).withPosition(0,6).getEntry();

    frontSquare     = tab.add("Front\nSquare", false)
                              .withSize(1,1).withPosition(4,0).getEntry();

    frontCentered     = tab.add("Front\nCentered", false)
                              .withSize(1,1).withPosition(5,0).getEntry();

    lineTrackerLeft = tab.add("Center Left ", false).withSize(1,1).withPosition(2,1).getEntry();

    leftSideSquare     = tab.add("Left Side\nSquare", false)
                              .withSize(1,1).withPosition(2,2).getEntry();

    lineTrackerRight = tab.add("Center Right", false).withSize(1,1).withPosition(7,1).getEntry();

    rightSideSquare     = tab.add("Right Side\nSquare", false)
                              .withSize(1,1).withPosition(7,2).getEntry();

    // Vision Entries
      HttpCamera httpCamera = new HttpCamera("rPi Vision", "http://frcvision.local:1181/stream.mjpg");
      CameraServer.getInstance().addCamera(httpCamera);
      videoEntry = tab.add(httpCamera).withSize(4,3).withPosition(3,1);
  }

  public void updateDriverFeedback() {

    if (petal != null) {

    }
    if (nav != null) {
      leftSideSquare.setBoolean(nav.isLeftSideSquare());
      rightSideSquare.setBoolean(nav.isRightSideSquare());

    }
    if (triangleHatch != null) {
      
    }
    if (stick != null) {

    }
    if (spatula != null) {
      
    }
    if (kicker != null) {
      
    }
    if (vision != null) {
      frontCentered.setBoolean(vision.isCentered());
      frontSquare.setBoolean(vision.isSquare());
    }
    if (wheelIntake != null) {
      
    }
    if (elevator != null) {
      elevatorPos1.setBoolean(elevator.isAtPosition(Elevator.HappyPosition.LEVEL_1));
      elevatorPos1.setBoolean(elevator.isAtPosition(Elevator.HappyPosition.LEVEL_2));
      elevatorPos1.setBoolean(elevator.isAtPosition(Elevator.HappyPosition.LEVEL_3));
      elevatorPos1.setBoolean(elevator.isAtPosition(Elevator.HappyPosition.LEVEL_4));
      elevatorPos1.setBoolean(elevator.isAtPosition(Elevator.HappyPosition.LEVEL_5));
      elevatorPos1.setBoolean(elevator.isAtPosition(Elevator.HappyPosition.LEVEL_6));
      elevatorPos1.setBoolean(elevator.isAtPosition(Elevator.HappyPosition.LEVEL_7));
    }
      
    if (lineTracker != null) {
        lineTrackerLeft.setBoolean(lineTracker.isOnLeft());
        lineTrackerRight.setBoolean(lineTracker.isOnRight());
    }
    if (roller != null) {
      
    }
    if (shooterIntake != null) {
      
    }
    if (intakeAdjuster != null) {
      
    }
    if (pixyCam != null) {
      
    }
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new DriverFeedbackDefault());
  }
}