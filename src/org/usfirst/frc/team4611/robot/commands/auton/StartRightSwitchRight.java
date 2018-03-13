package org.usfirst.frc.team4611.robot.commands.auton;

import org.usfirst.frc.team4611.robot.RobotMap;
import org.usfirst.frc.team4611.robot.commands.arm.MovePotPos;
import org.usfirst.frc.team4611.robot.commands.drive.AutonForward;
import org.usfirst.frc.team4611.robot.commands.drive.StopAndRepositionTalons;
import org.usfirst.frc.team4611.robot.commands.elevator.ResetElevator;
import org.usfirst.frc.team4611.robot.commands.pigeon.PigeonAdjust;
import org.usfirst.frc.team4611.robot.commands.solenoid.GrabBox;
import org.usfirst.frc.team4611.robot.commands.solenoid.ReleaseBox;
import org.usfirst.frc.team4611.robot.logging.Logger;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class StartRightSwitchRight extends CommandGroup {

	public StartRightSwitchRight() {
		// TODO Auto-generated constructor stub
		addSequential(new ResetElevator());
		addSequential(new GrabBox());
		addSequential(new StopAndRepositionTalons());
		addSequential(new AutonForward(RobotMap.WAY));
		addSequential(new PigeonAdjust(-90));
		addSequential(new StopAndRepositionTalons());
		addSequential(new AutonForward(RobotMap.TOWARDS_SWITCH), 1.5);// 1.85
		//addSequential(new MoveElevatorToPos(Elevator.ELEVATOR_TOP/2));
		addSequential(new MovePotPos(RobotMap.POTSWITCH));
		addSequential(new ReleaseBox());
		//addSequential(new PushBox());
	}
	protected void initialize() {
		Logger.log("initialized", this.getClass().getName());
	}
}
