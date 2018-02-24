package org.usfirst.frc.team4611.robot.commands.auton;

import org.usfirst.frc.team4611.robot.RobotMap;
import org.usfirst.frc.team4611.robot.commands.elevator.MoveElevatorToPos;
import org.usfirst.frc.team4611.robot.commands.solenoid.ReleaseBox;
import org.usfirst.frc.team4611.robot.commands.solenoid.GrabBox;
import org.usfirst.frc.team4611.robot.commands.solenoid.PushBox;
import org.usfirst.frc.team4611.robot.subsystems.Elevator;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class StartCenterScaleLeft extends CommandGroup {

	public StartCenterScaleLeft() {
		// TODO Auto-generated constructor stub
		addSequential(new GrabBox());
		addSequential(new StopAndRepositionTalons());
		addSequential(new AutonForward(RobotMap.HALFWAY));
		addSequential(new StopAndRepositionTalons());
		addSequential(new AutonStrafeLeft(RobotMap.WAY * 2));
		addSequential(new StopAndRepositionTalons());
		addParallel(new AutonForward(RobotMap.WAY));
		addParallel(new MoveElevatorToPos(Elevator.ELEVATOR_TOP));
		addSequential(new AutonStrafeRight(RobotMap.crossToScale));
		addSequential(new ReleaseBox());
		addSequential(new PushBox());
	}
}
