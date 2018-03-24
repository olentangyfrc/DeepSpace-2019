package org.usfirst.frc.team4611.robot.commands.auton.dualTargets;

import org.usfirst.frc.team4611.robot.RobotMap;
import org.usfirst.frc.team4611.robot.commands.arm.MovePotPos;
import org.usfirst.frc.team4611.robot.commands.drive.AutonBackward;
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

public class StartLeftLeftSwitchRightScale extends CommandGroup {

	public StartLeftLeftSwitchRightScale() {
		//addSequential(new ResetElevator());
		addSequential(new GrabBox());
		addSequential(new StopAndRepositionTalons());
		//addParallel(new MoveElevatorToPos(Elevator.ELEVATOR_TOP/2));
		//addParallel(new MovePotPos(RobotMap.POTSWITCH));
		addSequential(new AutonForward(RobotMap.WAY));
		addSequential(new StopAndRepositionTalons());
		addSequential(new PigeonAdjust(RobotMap.turnAngle1));
		addSequential(new StopAndRepositionTalons());
		addSequential(new AutonForward(RobotMap.TOWARDS_SWITCH), 1.5);// 1.85
		addSequential(new StopAndRepositionTalons());
		addSequential(new ReleaseBox());
		addSequential(new AutonBackward(RobotMap.TOWARDS_SWITCH/2));
		addSequential(new StopAndRepositionTalons());
		addSequential(new PigeonAdjust(-RobotMap.turnAngle1+2));
		addSequential(new StopAndRepositionTalons());
		addSequential(new AutonForward(RobotMap.HALFWAY + 28 ));
		addSequential(new StopAndRepositionTalons());
		addSequential(new PigeonAdjust(RobotMap.turnAngle1 + 5));
		addSequential(new StopAndRepositionTalons());
		addSequential(new AutonForward(RobotMap.crossToScale - (RobotMap.TOWARDS_SWITCH/2) + 10));
		addSequential(new StopAndRepositionTalons());
		addSequential(new PigeonAdjust(RobotMap.turnAngle1));
		addSequential(new StopAndRepositionTalons());
		addSequential(new AutonForward(20), 1.5);// 1.85
		
	}

	protected void initialize() {
		Logger.log("initialized", this.getClass().getName());
	}
}
