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
  private static Logger logger = Logger.getLogger(ChooseCamera.class.getName());

  private String camera;
  private static int cycleCount = 0;

  public ChooseCamera() {
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    if (cycleCount % 4 == 0) {
      camera = "usb 1";
    } else if (cycleCount % 4 == 1) {
      camera = "usb 2";
    } else if (cycleCount % 4 == 2) {
      camera = "usb 3";
    } else if (cycleCount % 4 == 3) {
      camera = "usb 4";
    }
    cycleCount += 1;
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
