package org.usfirst.frc.team4611.robot.commands.auton;

import org.usfirst.frc.team4611.robot.RobotMap;
import org.usfirst.frc.team4611.robot.commands.elevator.ResetElevator;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class StartRightNullZoneRightside extends CommandGroup {

	public StartRightNullZoneRightside() {
		// TODO Auto-generated constructor stub
		addSequential(new ResetElevator());
		addSequential(new StopAndRepositionTalons());
		addSequential(new AutonForward(RobotMap.MOREWAY));
		addSequential(new StopAndRepositionTalons());
	}
	protected void initialize() {
		RobotMap.log(RobotMap.autonSubTable, "SRNZR initialized");
	}
}
