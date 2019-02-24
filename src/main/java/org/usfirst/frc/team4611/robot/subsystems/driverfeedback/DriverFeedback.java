/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4611.robot.subsystems.driverfeedback;

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

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;

/**
 * Faux subsystem that simply creates a DriverFeedback shuffleboard tab and updates
 * with various subsystems' data to present in aggregate.
 */
public class DriverFeedback extends Subsystem {

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
    private NetworkTableEntry   leftSideSquare;
    private NetworkTableEntry   elevatorPos1, elevatorPos2, elevatorPos3, elevatorPos4, elevatorPos5, elevatorPos6, elevatorPos7;
    private NetworkTableEntry   lineTrackerLeft, lineTrackerRight;

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

    // Navigation Entries
    leftSideSquare     = tab.add("Left Side\nSquare", nav.isLeftSideSquare()).getEntry();

    // Elevator Entries
    elevatorPos1    = tab.add("Elevator 1", false).getEntry();
    elevatorPos2    = tab.add("Elevator 2", false).getEntry();
    elevatorPos3    = tab.add("Elevator 3", false).getEntry();
    elevatorPos4    = tab.add("Elevator 4", false).getEntry();
    elevatorPos5    = tab.add("Elevator 5", false).getEntry();
    elevatorPos6    = tab.add("Elevator 6", false).getEntry();
    elevatorPos7    = tab.add("Elevator 7", false).getEntry();

    // Linetracker Entries
    lineTrackerLeft = tab.add("Linetracker Left", false).getEntry();
    lineTrackerRight = tab.add("Linetracker Right", false).getEntry();
  }

  public void updateDriverFeedback() {

    if (petal != null) {

    }
    if (nav != null) {
      leftSideSquare.setBoolean(nav.isLeftSideSquare());

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