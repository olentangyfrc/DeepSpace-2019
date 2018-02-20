package org.usfirst.frc.team4611.robot.commands.happyshapes;

import org.usfirst.frc.team4611.robot.commands.elevator.MoveElevatorToPos;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ScalePos extends CommandGroup {
	public ScalePos() {
		addSequential(new MoveElevatorToPos(-136300)); //need to check position values
		
		
	}

}
