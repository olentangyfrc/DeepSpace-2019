package org.usfirst.frc.team4611.robot.commands.magicshapes;

import org.usfirst.frc.team4611.robot.commands.elevator.MoveElevatorToPos;
import org.usfirst.frc.team4611.robot.potentiometer.MovePotPos;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ScalePos extends CommandGroup {
	public ScalePos() {
		addSequential(new MoveElevatorToPos(-117328)); //need to check position values
		addParallel(new MovePotPos(0.2)); //need to check position values
		
	}

}
