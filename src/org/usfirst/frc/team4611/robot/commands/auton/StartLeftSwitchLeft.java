package org.usfirst.frc.team4611.robot.commands.auton;

import org.usfirst.frc.team4611.robot.RobotMap;
import org.usfirst.frc.team4611.robot.commands.elevator.MoveElevatorToPos;
import org.usfirst.frc.team4611.robot.commands.elevator.ResetElevator;
import org.usfirst.frc.team4611.robot.commands.pigeon.PigeonAdjust;
import org.usfirst.frc.team4611.robot.commands.solenoid.PushBox;
import org.usfirst.frc.team4611.robot.commands.solenoid.ReleaseBox;
import org.usfirst.frc.team4611.robot.potentiometer.MovePotPos;
import org.usfirst.frc.team4611.robot.subsystems.Elevator;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class StartLeftSwitchLeft extends CommandGroup {

	public StartLeftSwitchLeft() {
		addSequential(new ResetElevator());
		addSequential(new StopAndRepositionTalons());
		addParallel(new AutonForward(RobotMap.WAY));
		addSequential(new PigeonAdjust(90));
		addSequential(new StopAndRepositionTalons());
		addSequential(new AutonForward(RobotMap.TOWARDS_SWITCH), 1.5);
		//addParallel(new MoveElevatorToPos(Elevator.ELEVATOR_TOP/2));
		addParallel(new MovePotPos(RobotMap.POTSWITCH));
		addSequential(new ReleaseBox());
		//addSequential(new PushBox());
	}
}
