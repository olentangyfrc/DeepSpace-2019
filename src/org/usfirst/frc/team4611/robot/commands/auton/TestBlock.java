package org.usfirst.frc.team4611.robot.commands.auton;

import org.usfirst.frc.team4611.robot.commands.drive.VisionHorizontalDrive2;
import org.usfirst.frc.team4611.robot.commands.pigeon.PigeonAdjustVision2;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class TestBlock extends CommandGroup {
	public TestBlock() {
		//addSequential(new StopAndRepositionTalons());
		//addSequential(new VisionHorizontalDrive2());
		addSequential(new StopAndRepositionTalons());
		addSequential(new PigeonAdjustVision2());
	}
}

