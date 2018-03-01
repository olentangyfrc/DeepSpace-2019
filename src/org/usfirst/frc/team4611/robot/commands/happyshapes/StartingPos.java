package org.usfirst.frc.team4611.robot.commands.happyshapes;

import org.usfirst.frc.team4611.robot.commands.elevator.MoveElevatorToPos;
import org.usfirst.frc.team4611.robot.commands.elevator.ResetElevator;
import org.usfirst.frc.team4611.robot.commands.solenoid.GrabBox;
import org.usfirst.frc.team4611.robot.commands.solenoid.ReleaseBox;
import org.usfirst.frc.team4611.robot.potentiometer.MovePotPos;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class StartingPos extends CommandGroup {
	public StartingPos() {
		addSequential(new ResetElevator());
		addSequential(new MoveElevatorToPos(0));
		addSequential(new MovePotPos(.83));
		addSequential(new GrabBox());
	}
}
