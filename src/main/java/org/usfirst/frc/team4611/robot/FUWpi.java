/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4611.robot;

import java.util.logging.Logger;

import org.usfirst.frc.team4611.robot.subsystems.SubsystemFactory;

import edu.wpi.first.wpilibj.command.Command;

public class FUWpi extends Command {
  private static Logger logger = Logger.getLogger(FUWpi.class.getName());
  public FUWpi() {
    requires (SubsystemFactory.getInstance().getElevator());
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
   logger.severe("FU Wpi execute");
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
   logger.severe("isFinished");
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
   logger.severe("end");
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
   logger.severe("interrupted");
  }
}
