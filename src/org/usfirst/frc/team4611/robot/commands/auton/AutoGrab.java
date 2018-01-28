package org.usfirst.frc.team4611.robot.commands.auton;

import org.usfirst.frc.team4611.robot.RobotMap;
import org.usfirst.frc.team4611.robot.commands.drive.UltraDrive;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoGrab extends CommandGroup{
	
	/**
	 * Drives forward until a certain distance from a surface
	 */
	public AutoGrab(){
		//DriveTrain.defaultCommand.
		addSequential(new UltraDrive(RobotMap.UD_DISTANCE),5);
		//TODO SETH *SCREAMS* add roll forward a small distance code
		//addSequential(new RetractSolenoid() ,2);
		
	}
}
