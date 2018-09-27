package org.usfirst.frc.team4611.robot.commands.blocks;

import org.usfirst.frc.team4611.robot.commands.auton.StopAndRepositionTalons;
import org.usfirst.frc.team4611.robot.commands.auton.pigeon.TurnLeft;
import org.usfirst.frc.team4611.robot.commands.auton.pigeon.TurnRight;
import org.usfirst.frc.team4611.robot.subsystems.baseclasses.MecanumBase;
import org.usfirst.frc.team4611.robot.subsystems.sensors.Pigeon;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class Box extends CommandGroup{
	public Box(MecanumBase base, Pigeon pigeon) {
		addSequential(new StopAndRepositionTalons());
		addSequential(new TurnRight(base, pigeon, 180));
		addSequential(new StopAndRepositionTalons());
		addSequential(new TurnLeft(base, pigeon, 180));
		addSequential(new StopAndRepositionTalons());
	}
}
