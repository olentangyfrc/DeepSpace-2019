package org.usfirst.frc.team4611.robot.commands.auton;

import org.usfirst.frc.team4611.robot.commands.pigeon.PigeonAdjust;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class TestBlock extends CommandGroup {
	public TestBlock() {
		addSequential(new PigeonAdjust(90));
		addSequential(new PigeonAdjust(90));
		addSequential(new PigeonAdjust(90));
		addSequential(new PigeonAdjust(90));
	}
}

