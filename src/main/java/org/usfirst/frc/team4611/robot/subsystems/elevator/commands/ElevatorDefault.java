/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4611.robot.subsystems.elevator.commands;

import java.util.logging.Logger;

import org.usfirst.frc.team4611.robot.OI;
import org.usfirst.frc.team4611.robot.subsystems.SubsystemFactory;
import org.usfirst.frc.team4611.robot.subsystems.elevator.Elevator;

import edu.wpi.first.wpilibj.command.Command;

public class ElevatorDefault extends Command {
    private final Logger logger = Logger.getLogger(MoveElevatorDown.class.getName());
    
    private Elevator elevator;
    private boolean moving = false;
    private boolean wasMoving = false;

  public ElevatorDefault() {
        elevator = SubsystemFactory.getInstance().getElevator();
        this.requires(elevator);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    
    if(OI.getInstance().getAuxJoystickYValue() > 0.5) {
      elevator.move(true);
      moving = true;
    } else if (OI.getInstance().getAuxJoystickYValue() < -0.5) {
      elevator.move(false);
      moving = true;
    } else {
      moving = false;
    }
   
    if (!moving && wasMoving) {
      elevator.stop();
      wasMoving = false;
    }
    if (moving) {
      wasMoving = true;
    }

    elevator.updateValues();
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
