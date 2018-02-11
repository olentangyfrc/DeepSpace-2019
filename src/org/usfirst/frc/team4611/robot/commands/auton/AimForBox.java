package org.usfirst.frc.team4611.robot.commands.auton;

import org.usfirst.frc.team4611.robot.RobotMap;
import org.usfirst.frc.team4611.robot.commands.drive.MecanumDrive;
import org.usfirst.frc.team4611.robot.commands.drive.UltraDrive;
import org.usfirst.frc.team4611.robot.commands.drive.VisionDrive;
import org.usfirst.frc.team4611.robot.commands.solenoid.RetractSolenoid;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class AimForBox extends CommandGroup{
	
	/**
	 * Drives forward until a certain distance from a surface
	 */
	public AimForBox(){
		addSequential(new VisionDrive(),3);
		
	}
}
