package org.usfirst.frc.team4611.robot.commands.auton;

import org.usfirst.frc.team4611.robot.commands.elevator.MoveElevatorToPos;
import org.usfirst.frc.team4611.robot.commands.elevator.ResetElevator;
import org.usfirst.frc.team4611.robot.commands.solenoid.ReleaseBox;
import org.usfirst.frc.team4611.robot.commands.solenoid.PushBox;
import org.usfirst.frc.team4611.robot.commands.solenoid.GrabBox;
import org.usfirst.frc.team4611.robot.potentiometer.MovePotPos;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class TestBlock extends CommandGroup {
	public TestBlock() {
		addSequential(new ReleaseBox());
		addParallel(new ResetElevator());
		addSequential(new MoveElevatorToPos(-5000));
		addSequential(new MovePotPos(.1));
		addSequential(new GrabBox());
		addSequential(new MoveElevatorToPos(-50000));
		addSequential(new MovePotPos(.6));
		addSequential(new ReleaseBox());
		addSequential(new PushBox());
	}
}

