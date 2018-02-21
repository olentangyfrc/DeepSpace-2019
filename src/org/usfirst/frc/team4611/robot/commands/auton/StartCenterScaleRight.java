package org.usfirst.frc.team4611.robot.commands.auton;

import org.usfirst.frc.team4611.robot.RobotMap;
import org.usfirst.frc.team4611.robot.commands.elevator.MoveElevatorToPos;
import org.usfirst.frc.team4611.robot.commands.solenoid.ExtendSolenoid;
import org.usfirst.frc.team4611.robot.commands.solenoid.RetractSolenoid;
import org.usfirst.frc.team4611.robot.subsystems.Elevator;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class StartCenterScaleRight extends CommandGroup {

	public StartCenterScaleRight() {
		// TODO Auto-generated constructor stub
		addSequential(new RetractSolenoid());
		addSequential(new StopAndRepositionTalons());
		addSequential(new AutonForward(RobotMap.HALFWAY));
		addSequential(new StopAndRepositionTalons());
		addSequential(new AutonStrafeRight(RobotMap.HALFWAY));
		addSequential(new StopAndRepositionTalons());
		addParallel(new AutonForward(RobotMap.WAY));
		addParallel(new MoveElevatorToPos(Elevator.ELEVATOR_TOP));
		addSequential(new ExtendSolenoid());
	}
}
