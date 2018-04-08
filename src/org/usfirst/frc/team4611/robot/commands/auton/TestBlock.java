package org.usfirst.frc.team4611.robot.commands.auton;

import org.usfirst.frc.team4611.robot.commands.drive.AutonForward2;
import org.usfirst.frc.team4611.robot.commands.drive.StopAndRepositionTalons;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class TestBlock extends CommandGroup {
	public TestBlock() {
		addSequential(new StopAndRepositionTalons());
		addSequential(new AutonForward2(35 * 12));
	}
}

