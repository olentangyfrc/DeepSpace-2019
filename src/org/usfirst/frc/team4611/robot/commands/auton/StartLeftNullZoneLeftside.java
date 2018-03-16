package org.usfirst.frc.team4611.robot.commands.auton;

import org.usfirst.frc.team4611.robot.RobotMap;
import org.usfirst.frc.team4611.robot.commands.drive.AutonForward;
import org.usfirst.frc.team4611.robot.commands.drive.StopAndRepositionTalons;
import org.usfirst.frc.team4611.robot.commands.elevator.ResetElevator;
import org.usfirst.frc.team4611.robot.logging.Logger;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class StartLeftNullZoneLeftside extends CommandGroup {

	public StartLeftNullZoneLeftside() {
		// TODO Auto-generated constructor stub
		addSequential(new ResetElevator());
		addSequential(new StopAndRepositionTalons());
		addSequential(new AutonForward(RobotMap.MOREWAY));
		addSequential(new StopAndRepositionTalons());
	}
	protected void initialize() {
		Logger.log("initialized", this.getClass().getName());
	}
}
