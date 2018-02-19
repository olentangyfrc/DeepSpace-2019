package org.usfirst.frc.team4611.robot.commands.magicshapes;

import org.usfirst.frc.team4611.robot.commands.elevator.MoveElevatorToPos;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AttackPos extends CommandGroup{
	public AttackPos() {
		addSequential(new MoveElevatorToPos(-3000));	
	}

}
