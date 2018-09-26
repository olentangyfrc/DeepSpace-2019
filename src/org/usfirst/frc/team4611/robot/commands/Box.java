package org.usfirst.frc.team4611.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class Box extends CommandGroup{
	public Box() {
		addSequential(new MoveForward(20));
		addSequential(new StopAndRepositionTalons());
	}
}
