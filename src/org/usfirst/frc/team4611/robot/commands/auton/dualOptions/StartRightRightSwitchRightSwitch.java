package org.usfirst.frc.team4611.robot.commands.auton.dualOptions;

import org.usfirst.frc.team4611.robot.RobotMap;
import org.usfirst.frc.team4611.robot.commands.arm.MovePotPos;
import org.usfirst.frc.team4611.robot.commands.auton.Sleep;
import org.usfirst.frc.team4611.robot.commands.auton.Wait;
import org.usfirst.frc.team4611.robot.commands.drive.AutonBackward;
import org.usfirst.frc.team4611.robot.commands.drive.AutonForward;
import org.usfirst.frc.team4611.robot.commands.drive.AutonStrafeRight;
import org.usfirst.frc.team4611.robot.commands.drive.FindBox;
import org.usfirst.frc.team4611.robot.commands.drive.StopAndRepositionTalons;
import org.usfirst.frc.team4611.robot.commands.elevator.MoveElevatorToPos;
import org.usfirst.frc.team4611.robot.commands.elevator.ResetElevator;
import org.usfirst.frc.team4611.robot.commands.pigeon.PigeonAdjust;
import org.usfirst.frc.team4611.robot.commands.solenoid.GrabBox;
import org.usfirst.frc.team4611.robot.commands.solenoid.PushBox;
import org.usfirst.frc.team4611.robot.commands.solenoid.ReleaseBox;
import org.usfirst.frc.team4611.robot.subsystems.Elevator;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class StartRightRightSwitchRightSwitch extends CommandGroup {

	public StartRightRightSwitchRightSwitch() {
		addSequential(new ResetElevator());
		addSequential(new GrabBox());
		addSequential(new StopAndRepositionTalons());
		addSequential(new AutonForward(RobotMap.WAY));
		addParallel(new MoveElevatorToPos(Elevator.ELEVATOR_TOP/2));
		addParallel(new MovePotPos(RobotMap.POTSWITCH));
		addSequential(new StopAndRepositionTalons());
		addSequential(new PigeonAdjust(-RobotMap.turnAngle1));
		addSequential(new StopAndRepositionTalons());
		addSequential(new AutonForward(RobotMap.TOWARDS_SWITCH), 1.5);// 1.85
		addSequential(new ReleaseBox());
		addSequential(new AutonBackward(RobotMap.TOWARDS_SWITCH));
		addSequential(new StopAndRepositionTalons());
		addSequential(new PigeonAdjust(RobotMap.turnAngle1));
		addSequential(new StopAndRepositionTalons());
		addSequential(new AutonForward(RobotMap.HALFWAY + 18));
		addSequential(new StopAndRepositionTalons());
		addSequential(new PigeonAdjust(-RobotMap.turnAngle2));
		addSequential(new StopAndRepositionTalons());
		addSequential(new AutonStrafeRight(20));
		addSequential(new StopAndRepositionTalons());
		addSequential(new Sleep(500)); // give Vision a chance to focus
		addSequential(new FindBox());
		addSequential(new GrabBox());
		addSequential(new Wait(2));
		addSequential(new PigeonAdjust(-RobotMap.turnAngle1));
		addSequential(new StopAndRepositionTalons());
		addParallel(new MoveElevatorToPos(Elevator.ELEVATOR_TOP/2));
		addParallel(new MovePotPos(RobotMap.POTSWITCH));
		addSequential(new Wait(.1));
		addSequential(new AutonForward(RobotMap.TOWARDS_SWITCH));
		addSequential(new ReleaseBox());
	}

}
