package org.usfirst.frc.team4611.robot.commands.auton;

import org.usfirst.frc.team4611.robot.commands.elevator.MoveElevatorToPos;
import org.usfirst.frc.team4611.robot.commands.elevator.ResetElevator;
import org.usfirst.frc.team4611.robot.commands.solenoid.ExtendSolenoid;
import org.usfirst.frc.team4611.robot.commands.solenoid.PushBox;
import org.usfirst.frc.team4611.robot.commands.solenoid.RetractSolenoid;
import org.usfirst.frc.team4611.robot.potentiometer.MovePotPos;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class TestBlock extends CommandGroup {
	public TestBlock() {
		addSequential(new ExtendSolenoid());
		addParallel(new ResetElevator());
		addSequential(new MoveElevatorToPos(-5000));
		addSequential(new MovePotPos(.1));
		addSequential(new RetractSolenoid());
		addSequential(new MoveElevatorToPos(-50000));
		addSequential(new MovePotPos(.6));
		addSequential(new ExtendSolenoid());
		addSequential(new PushBox());
	}
}

