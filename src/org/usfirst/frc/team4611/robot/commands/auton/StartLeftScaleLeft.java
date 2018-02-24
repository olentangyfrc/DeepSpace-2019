package org.usfirst.frc.team4611.robot.commands.auton;

import org.usfirst.frc.team4611.robot.RobotMap;
import org.usfirst.frc.team4611.robot.commands.elevator.MoveElevatorToPos;
import org.usfirst.frc.team4611.robot.commands.elevator.ResetElevator;
import org.usfirst.frc.team4611.robot.commands.solenoid.PushBox;
import org.usfirst.frc.team4611.robot.commands.solenoid.ReleaseBox;
import org.usfirst.frc.team4611.robot.subsystems.Elevator;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class StartLeftScaleLeft extends CommandGroup {

	public StartLeftScaleLeft() {
		// TODO Auto-generated constructor stub
		addSequential(new ResetElevator());
		addSequential(new StopAndRepositionTalons());
		addSequential(new AutonForward(RobotMap.MOREWAY));
		addSequential(new StopAndRepositionTalons());
		addSequential(new AutonStrafeRight(RobotMap.strafeToCloseTarget));
		addSequential(new StopAndRepositionTalons());
		addSequential(new MoveElevatorToPos(Elevator.ELEVATOR_TOP));
		addSequential(new AutonForward(RobotMap.HALFWAY/2));
		addSequential(new PushBox());
		addSequential(new ReleaseBox());
	}
}
