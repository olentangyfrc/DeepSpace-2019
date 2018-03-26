package org.usfirst.frc.team4611.robot.commands.auton.singleTargets;

import org.usfirst.frc.team4611.robot.RobotMap;
import org.usfirst.frc.team4611.robot.commands.arm.MovePotPos;
import org.usfirst.frc.team4611.robot.commands.drive.AutonForward;
import org.usfirst.frc.team4611.robot.commands.drive.StopAndRepositionTalons;
import org.usfirst.frc.team4611.robot.commands.elevator.MoveElevatorToPos;
import org.usfirst.frc.team4611.robot.commands.elevator.ResetElevator;
import org.usfirst.frc.team4611.robot.commands.pigeon.PigeonAdjust;
import org.usfirst.frc.team4611.robot.commands.solenoid.GrabBox;
import org.usfirst.frc.team4611.robot.commands.solenoid.ReleaseBox;
import org.usfirst.frc.team4611.robot.logging.Logger;
import org.usfirst.frc.team4611.robot.subsystems.Elevator;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class StartRightSwitchLeft extends CommandGroup {

	public StartRightSwitchLeft() {
		addSequential(new ResetElevator());
		addSequential(new GrabBox());
		addSequential(new StopAndRepositionTalons());
		addParallel(new MovePotPos(RobotMap.POTSWITCH2));
		addParallel(new MoveElevatorToPos(Elevator.ELEVATOR_TOP/2));
		addSequential(new AutonForward(RobotMap.MOREWAY));
		addSequential(new StopAndRepositionTalons());
		addSequential(new PigeonAdjust(-RobotMap.turnAngle1));
		addSequential(new StopAndRepositionTalons());
		addSequential(new AutonForward(RobotMap.crossToScale));
		addSequential(new StopAndRepositionTalons());
		addSequential(new PigeonAdjust(-RobotMap.turnAngle1));
		addSequential(new StopAndRepositionTalons());
		addSequential(new AutonForward(RobotMap.TOWARDS_SWITCH), .5);
		addSequential(new ReleaseBox());
	}
	protected void initialize() {
		Logger.log("initialized", this.getClass().getName());
	}
}
