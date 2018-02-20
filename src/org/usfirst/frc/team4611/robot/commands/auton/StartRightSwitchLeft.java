package org.usfirst.frc.team4611.robot.commands.auton;

import org.usfirst.frc.team4611.robot.commands.elevator.ResetElevator;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class StartRightSwitchLeft extends CommandGroup {

	public StartRightSwitchLeft() {
		// TODO Auto-generated constructor stub
		addSequential(new ResetElevator());
		addSequential(new StopAndRepositionTalons());
		addSequential(new AutonForward(60));
		addSequential(new StopAndRepositionTalons());
		addSequential(new AutonStrafeLeft(15*12));
		addSequential(new StopAndRepositionTalons());
		addSequential(new AutonForward(60));
		addSequential(new StopAndRepositionTalons());
	}
}
