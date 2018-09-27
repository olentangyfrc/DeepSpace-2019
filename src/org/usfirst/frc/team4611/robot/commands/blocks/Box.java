package org.usfirst.frc.team4611.robot.commands.blocks;

import org.usfirst.frc.team4611.robot.commands.auton.StopAndRepositionTalons;
import org.usfirst.frc.team4611.robot.commands.auton.pigeon.TurnLeft;
import org.usfirst.frc.team4611.robot.commands.auton.pigeon.TurnRight;
import org.usfirst.frc.team4611.robot.subsystems.sensors.Pigeon;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class Box extends CommandGroup{
	public Box(Pigeon pigeon) {
		addSequential(new StopAndRepositionTalons());
		addSequential(new TurnRight(pigeon, 180));
		addSequential(new StopAndRepositionTalons());
		addSequential(new TurnLeft(pigeon, 180));
		addSequential(new StopAndRepositionTalons());
	}
}
