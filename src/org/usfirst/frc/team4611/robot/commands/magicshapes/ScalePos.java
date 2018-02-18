package org.usfirst.frc.team4611.robot.commands.magicshapes;

import org.usfirst.frc.team4611.robot.commands.elevator.MoveElevatorToPos;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ScalePos extends CommandGroup {
	public ScalePos() {
		addSequential(new MoveElevatorToPos(-116300)); //need to check position values
		
		
	}

}
