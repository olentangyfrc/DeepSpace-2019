package org.usfirst.frc.team4611.robot.commands.auton.blocks;

import org.usfirst.frc.team4611.robot.commands.auton.StopAndRepositionTalons;
import org.usfirst.frc.team4611.robot.commands.auton.drive.MoveForward;
import org.usfirst.frc.team4611.robot.subsystems.sensors.Pigeon;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class Box extends CommandGroup{
	public Box() {
		addSequential(new StopAndRepositionTalons());
		addSequential(new MoveForward(30));
		addSequential(new StopAndRepositionTalons());
		addSequential(new MoveForward(30));
		addSequential(new StopAndRepositionTalons());
	}
}
