package org.usfirst.frc.team4611.robot.commands.magicshapes;

import org.usfirst.frc.team4611.robot.commands.elevator.MoveElevatorToPos;
import org.usfirst.frc.team4611.robot.potentiometer.MovePotPos;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class BottomPos extends CommandGroup{
	public BottomPos() {
		addParallel(new MovePotPos(0.2));
		addParallel(new MoveElevatorToPos(0));
		
	}

}
