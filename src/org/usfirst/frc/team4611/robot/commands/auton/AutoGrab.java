package org.usfirst.frc.team4611.robot.commands.auton;

import org.usfirst.frc.team4611.robot.RobotMap;
import org.usfirst.frc.team4611.robot.commands.drive.UltraDrive;
import org.usfirst.frc.team4611.robot.commands.drive.VisionDrive;
import org.usfirst.frc.team4611.robot.commands.solenoid.RetractSolenoid;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoGrab extends CommandGroup{
	
	/**
	 * Drives forward until a certain distance from a surface
	 */
	public AutoGrab(){
		addSequential(new VisionDrive(),2);
		//addSequential(new VisionDrive(),2);
		addSequential(new UltraDrive(),2);
		//addSequential(new RetractSolenoid() ,0.2);
		
	}
}
