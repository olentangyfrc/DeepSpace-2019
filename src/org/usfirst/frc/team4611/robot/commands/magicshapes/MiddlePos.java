package org.usfirst.frc.team4611.robot.commands.magicshapes;

import org.usfirst.frc.team4611.robot.commands.elevator.MoveElevatorToPos;
import org.usfirst.frc.team4611.robot.potentiometer.MovePotPos;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class MiddlePos extends CommandGroup{
//may need to rename class once we find
//what position/purpose this is for 
//Right now: it's a position in between switchPos
//and scalePos
	public MiddlePos() {
		addSequential(new MoveElevatorToPos(-87696)); //Must check position 
		addParallel(new MovePotPos(0.7)); //Must check position
	}
}
