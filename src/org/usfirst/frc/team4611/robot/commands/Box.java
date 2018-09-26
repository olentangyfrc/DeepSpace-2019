package org.usfirst.frc.team4611.robot.commands;

import org.usfirst.frc.team4611.robot.commands.pigeon.TurnRight;
import org.usfirst.frc.team4611.robot.subsystems.sensors.pigeon.Pigeon;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class Box extends CommandGroup{
	public Box(Pigeon pigeon) {
		addSequential(new StopAndRepositionTalons());
		addSequential(new TurnRight(pigeon, 180));
		addSequential(new StopAndRepositionTalons());
	}
}
