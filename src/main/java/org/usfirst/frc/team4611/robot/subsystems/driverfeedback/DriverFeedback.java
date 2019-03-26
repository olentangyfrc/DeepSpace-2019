/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4611.robot.subsystems.driverfeedback;

import java.util.logging.Logger;

import org.usfirst.frc.team4611.robot.networktables.NetTableManager;
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
import org.usfirst.frc.team4611.robot.subsystems.trianglehatch.TriangleHatch;
import org.usfirst.frc.team4611.robot.subsystems.vision.Vision;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;

/**
 * Faux subsystem that simply creates a DriverFeedback shuffleboard tab and updates
 * with various subsystems' data to present in aggregate.
 */
public class DriverFeedback extends Subsystem {
  private static Logger logger = Logger.getLogger(DriverFeedback.class.getName());
  private boolean inited = false;
    private Navigation nav;
    private Vision vision;
    private WheelIntake wheelIntake;
    private Elevator elevator;
    private LineTracker lineTracker;
    private Roller roller;
    private Intake shooterIntake;
    private IntakeAdjuster intakeAdjuster;
    private PixyCam pixyCam;

    ShuffleboardTab tab;
    private NetworkTableEntry   leftSideSquare, rightSideSquare, frontSquare, frontCentered;
    private NetworkTableEntry   lineTrackerLeft, lineTrackerRight;
    private NetworkTableEntry selectedCamera;

  public void init() {

    nav = SubsystemFactory.getInstance().getNavigation();
    vision= SubsystemFactory.getInstance().getVision();
    wheelIntake= SubsystemFactory.getInstance().getWheelIntake();
    elevator= SubsystemFactory.getInstance().getElevator();
    lineTracker= SubsystemFactory.getInstance().getLineTracker();
    roller= SubsystemFactory.getInstance().getRoller();
    shooterIntake= SubsystemFactory.getInstance().getShooterIntake();
    intakeAdjuster= SubsystemFactory.getInstance().getIntakeAdjuster();

    tab = Shuffleboard.getTab("DriverFeedback");

    selectedCamera = tab.add("Selected Camera", "NONE").withSize(2, 1).withPosition(4, 4).getEntry();

    if (vision != null) {
      frontSquare     = tab.add("Front\nSquare", false)
                                .withSize(1,1).withPosition(4,0).getEntry();

      frontCentered     = tab.add("Front\nCentered", false)
                                .withSize(1,1).withPosition(5,0).getEntry();
    }

    lineTrackerLeft = tab.add("Center Left ", false).withSize(1,1).withPosition(2,1).getEntry();

    leftSideSquare     = tab.add("Left Side\nSquare", false)
                              .withSize(1,1).withPosition(2,2).getEntry();

    lineTrackerRight = tab.add("Center Right", false).withSize(1,1).withPosition(11,1).getEntry();

    rightSideSquare     = tab.add("Right Side\nSquare", false)
                              .withSize(1,1).withPosition(11,2).getEntry();

  }

  private String currentCamera = "NONE";

  public void updateDriverFeedback() {
    if (!inited) return;

    currentCamera = (String)NetTableManager.getValue("Vision", "selected_camera", currentCamera);
    selectedCamera.setString(currentCamera);

    if (nav != null) {
      leftSideSquare.setBoolean(nav.isLeftSideSquare());
      rightSideSquare.setBoolean(nav.isRightSideSquare());

    }
    if (vision != null) {
      frontCentered.setBoolean(vision.isCentered());
      frontSquare.setBoolean(vision.isSquare());
    }
    if (wheelIntake != null) {
      
    }
    if (elevator != null) {
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