package org.usfirst.frc.team4611.robot.commands.drive;

import org.usfirst.frc.team4611.robot.commands.auton.StopAndRepositionTalons;
import org.usfirst.frc.team4611.robot.commands.auton.Wait;
import org.usfirst.frc.team4611.robot.commands.pigeon.PigeonAdjust2;
import org.usfirst.frc.team4611.robot.commands.pigeon.PigeonAdjustVision2;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class FindBox extends CommandGroup {

	public FindBox() {
		//addSequential(new StopAndRepositionTalons());
		//addSequential(new PigeonAdjust2(180));
		//addSequential(new Wait(1));
		//addSequential(new StopAndRepositionTalons());
		//addSequential(new VisionHorizontalDrive3());
		addSequential(new StopAndRepositionTalons());
		addSequential(new PigeonAdjustVision2());
		//addSequential(new StopAndRepositionTalons()); 
		
	}
}
