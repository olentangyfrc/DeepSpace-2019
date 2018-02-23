package org.usfirst.frc.team4611.robot.commands.auton;

import org.usfirst.frc.team4611.robot.commands.elevator.MoveElevatorToPos;
import org.usfirst.frc.team4611.robot.commands.elevator.ResetElevator;
import org.usfirst.frc.team4611.robot.commands.pigeon.PigeonAdjust;
import org.usfirst.frc.team4611.robot.commands.solenoid.ExtendSolenoid;
import org.usfirst.frc.team4611.robot.potentiometer.MovePotPos;
import org.usfirst.frc.team4611.robot.subsystems.Elevator;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class StartLeftScaleRight extends CommandGroup {

	public StartLeftScaleRight() {
		// TODO Auto-generated constructor stub
		addSequential(new ResetElevator());
		addSequential(new StopAndRepositionTalons());
		addSequential(new AutonForward(13*12));
		addSequential(new StopAndRepositionTalons());
		addSequential(new PigeonAdjust(90));
		addSequential(new StopAndRepositionTalons());
		addSequential(new AutonForward(16*12));
		addSequential(new StopAndRepositionTalons());
		addSequential(new PigeonAdjust(-180));
		addParallel(new AutonForward(12));
		addParallel(new MoveElevatorToPos(Elevator.ELEVATOR_TOP));
		addParallel(new MovePotPos(.6));
		addSequential(new ExtendSolenoid());
	}
}
