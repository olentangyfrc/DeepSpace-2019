package org.usfirst.frc.team4611.robot.commands.magicshapes;

import org.usfirst.frc.team4611.robot.commands.elevator.MoveElevatorToPos;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class SwitchPos extends CommandGroup{
	public SwitchPos() {
		addSequential(new MoveElevatorToPos(-39556)); //need to check position
		
	}
}