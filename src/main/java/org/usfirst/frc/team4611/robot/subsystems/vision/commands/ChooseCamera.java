/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4611.robot.subsystems.vision.commands;

import java.util.logging.Logger;

import org.usfirst.frc.team4611.robot.networktables.NetTableManager;

import edu.wpi.first.wpilibj.command.Command;


public class ChooseCamera extends Command {
public static  enum Camera {LEFT, CENTER, RIGHT};
  private static Logger logger = Logger.getLogger(ChooseCamera.class.getName());

  private String camera;

  public ChooseCamera(Camera c) {
    if (c == Camera.LEFT) {
      camera = "usb 1";
    } else if (c == Camera.CENTER) {
      camera = "usb 2";
    } else if (c == Camera.RIGHT) {
      camera = "usb 3";
    }
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    logger.info("try to select camera [" + camera + "]");
    NetTableManager.updateValue("Vision", "selected_camera", camera);
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
