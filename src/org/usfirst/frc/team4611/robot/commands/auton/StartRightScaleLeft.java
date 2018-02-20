package org.usfirst.frc.team4611.robot.commands.auton;

import org.usfirst.frc.team4611.robot.commands.elevator.MoveElevatorToPos;
import org.usfirst.frc.team4611.robot.commands.elevator.ResetElevator;
import org.usfirst.frc.team4611.robot.commands.solenoid.ExtendSolenoid;
import org.usfirst.frc.team4611.robot.subsystems.Elevator;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class StartRightScaleLeft extends CommandGroup {

	public StartRightScaleLeft() {
		// TODO Auto-generated constructor stub
		addSequential(new ResetElevator());
		addSequential(new StopAndRepositionTalons());
		addSequential(new AutonForward(13*12));
		addSequential(new StopAndRepositionTalons());
		addSequential(new AutonStrafeLeft(16*12));
		addSequential(new StopAndRepositionTalons());
		addParallel(new AutonForward(30));
		addParallel(new MoveElevatorToPos(Elevator.ELEVATOR_TOP));
		addSequential(new ExtendSolenoid());
	}
}
