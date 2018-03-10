package org.usfirst.frc.team4611.robot.commands.auton;

import org.usfirst.frc.team4611.robot.RobotMap;
import org.usfirst.frc.team4611.robot.commands.elevator.MoveElevatorToPos;
import org.usfirst.frc.team4611.robot.commands.elevator.ResetElevator;
import org.usfirst.frc.team4611.robot.commands.pigeon.PigeonAdjust;
import org.usfirst.frc.team4611.robot.commands.solenoid.ReleaseBox;
import org.usfirst.frc.team4611.robot.logging.Logger;
import org.usfirst.frc.team4611.robot.commands.solenoid.GrabBox;
import org.usfirst.frc.team4611.robot.commands.solenoid.PushBox;
import org.usfirst.frc.team4611.robot.subsystems.Elevator;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class StartCenterScaleRight extends CommandGroup {

	public StartCenterScaleRight() {
		// TODO Auto-generated constructor stub
		addSequential(new ResetElevator());
		addSequential(new GrabBox());
		addSequential(new StopAndRepositionTalons());
		addSequential(new AutonForward(RobotMap.HALFWAY));
		addSequential(new StopAndRepositionTalons());
		addSequential(new PigeonAdjust(-RobotMap.turnAngle1));
		addSequential(new StopAndRepositionTalons());
		addSequential(new AutonForward(RobotMap.WAY));
		addSequential(new StopAndRepositionTalons());
		addSequential(new PigeonAdjust(RobotMap.turnAngle1));
		addSequential(new AutonForward(RobotMap.MOREWAY));
		addParallel(new MoveElevatorToPos(Elevator.ELEVATOR_TOP));
		//addSequential(new PushBox());
		addSequential(new ReleaseBox());
	}
	protected void initialize() {
		Logger.log("initialized", this.getClass().getName());
	}
}
