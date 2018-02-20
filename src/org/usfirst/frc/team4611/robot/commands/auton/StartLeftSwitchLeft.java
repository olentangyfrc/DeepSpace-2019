package org.usfirst.frc.team4611.robot.commands.auton;

import org.usfirst.frc.team4611.robot.commands.elevator.ResetElevator;
import org.usfirst.frc.team4611.robot.commands.solenoid.ExtendSolenoid;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class StartLeftSwitchLeft extends CommandGroup {

	public StartLeftSwitchLeft() {
		addSequential(new ResetElevator());
		addSequential(new StopAndRepositionTalons());
		addSequential(new AutonForward(120));
		addSequential(new ExtendSolenoid());
	}
}
