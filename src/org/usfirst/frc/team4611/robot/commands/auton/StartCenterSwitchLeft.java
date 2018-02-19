package org.usfirst.frc.team4611.robot.commands.auton;

import org.usfirst.frc.team4611.robot.commands.elevator.MoveElevatorToPos;
import org.usfirst.frc.team4611.robot.commands.solenoid.ExtendSolenoid;
import org.usfirst.frc.team4611.robot.commands.solenoid.RetractSolenoid;
import org.usfirst.frc.team4611.robot.potentiometer.MovePotPos;
import org.usfirst.frc.team4611.robot.subsystems.Elevator;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class StartCenterSwitchLeft extends CommandGroup {

	public StartCenterSwitchLeft() {
		// TODO Auto-generated constructor stub
		addSequential(new RetractSolenoid());
		addSequential(new StopAndRepositionTalons());
		addSequential(new AutonForward(50));
		addSequential(new StopAndRepositionTalons());
		addSequential(new AutonStrafeLeft(30));
		addSequential(new StopAndRepositionTalons());
		addParallel(new AutonForward(70));
		addParallel(new MoveElevatorToPos(Elevator.ELEVATOR_TOP/2));
		addParallel(new MovePotPos(.5));
		addSequential(new ExtendSolenoid());
	}
}
