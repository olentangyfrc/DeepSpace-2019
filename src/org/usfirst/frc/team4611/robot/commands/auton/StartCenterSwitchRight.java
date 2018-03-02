package org.usfirst.frc.team4611.robot.commands.auton;

import org.usfirst.frc.team4611.robot.RobotMap;
import org.usfirst.frc.team4611.robot.commands.elevator.MoveElevatorToPos;
import org.usfirst.frc.team4611.robot.commands.elevator.ResetElevator;
import org.usfirst.frc.team4611.robot.commands.pigeon.PigeonAdjust;
import org.usfirst.frc.team4611.robot.commands.solenoid.ReleaseBox;
import org.usfirst.frc.team4611.robot.commands.solenoid.GrabBox;
import org.usfirst.frc.team4611.robot.commands.solenoid.PushBox;
import org.usfirst.frc.team4611.robot.potentiometer.MovePotPos;
import org.usfirst.frc.team4611.robot.subsystems.Elevator;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class StartCenterSwitchRight extends CommandGroup {

	public StartCenterSwitchRight() {
		// TODO Auto-generated constructor stub	
		addSequential(new ResetElevator());
		addSequential(new GrabBox());
		addSequential(new StopAndRepositionTalons());
		addSequential(new AutonForward(RobotMap.HALFWAY));
		addSequential(new StopAndRepositionTalons());
		addSequential(new PigeonAdjust(90));
		addSequential(new StopAndRepositionTalons());
		addSequential(new AutonForward(30), 2);
		addSequential(new StopAndRepositionTalons());
		addSequential(new PigeonAdjust(-90));
		addSequential(new StopAndRepositionTalons());
		addSequential(new MovePotPos(RobotMap.POTSWITCH));
		addSequential(new AutonForward(RobotMap.HALFWAY), 1);
		addSequential(new ReleaseBox());
	}
	protected void initialize() {
		RobotMap.log(RobotMap.autonSubTable, "SCSWR initialized");
	}
}
