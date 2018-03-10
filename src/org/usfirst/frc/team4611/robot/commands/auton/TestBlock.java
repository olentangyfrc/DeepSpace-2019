package org.usfirst.frc.team4611.robot.commands.auton;

import org.usfirst.frc.team4611.robot.commands.pigeon.PigeonAdjust2;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class TestBlock extends CommandGroup {
	public TestBlock() {
		addSequential(new PigeonAdjust2(88));
	}
}

