package org.usfirst.frc.team4611.robot.commands.auton;

import org.usfirst.frc.team4611.robot.commands.pigeon.PigeonAdjust;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class PigeonAuton extends CommandGroup{

	public PigeonAuton() {
		this.addSequential(new PigeonAdjust(45));
		this.addSequential(new PigeonAdjust(-45));
	}
}
