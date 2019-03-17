/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4611.robot.subsystems.hook.commands;

import java.util.logging.Logger;

import org.usfirst.frc.team4611.robot.subsystems.SubsystemFactory;
import org.usfirst.frc.team4611.robot.subsystems.hook.Hook;

import edu.wpi.first.wpilibj.command.Command;

public class MoveHook extends Command {
  private static Logger logger = Logger.getLogger(MoveHook.class.getName());
  private Hook hook;

  private boolean up;

  public MoveHook(boolean u) {
    up = u;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    hook = SubsystemFactory.getInstance().getHook();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    hook.move(up);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return true;
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
