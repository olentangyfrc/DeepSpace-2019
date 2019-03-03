/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4611.robot.subsystems.systemstatus;

import java.util.logging.Logger;

import org.usfirst.frc.team4611.robot.networktables.NetTableManager;
import org.usfirst.frc.team4611.robot.subsystems.SubsystemFactory;
import org.usfirst.frc.team4611.robot.subsystems.elevator.Elevator;
import org.usfirst.frc.team4611.robot.subsystems.systemstatus.commands.SystemStatusDefault;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.shuffleboard.ComplexWidget;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;

/**
 * Faux subsystem that simply creates a SystemStatus shuffleboard tab and updates
 * with various subsystems' data to present in aggregate.
 * Initial implementation will just to create a shuffleboard tab. hardware data can be dragged on
 * to it.
 */
public class SystemStatus extends Subsystem {
  private static Logger logger = Logger.getLogger(SystemStatus.class.getName());
  private static ShuffleboardTab tab = Shuffleboard.getTab("SystemStatus");

   boolean inited = false;

  public SystemStatus () {

  }

  public void init() {
		
    inited = true;
  }

  public void updateSystemStatus() {
    if (!inited) return;
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new SystemStatusDefault());
  }
}