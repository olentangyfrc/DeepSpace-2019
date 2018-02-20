package org.usfirst.frc.team4611.robot.commands.auton;

import org.usfirst.frc.team4611.robot.commands.elevator.ResetElevator;
import org.usfirst.frc.team4611.robot.commands.solenoid.ExtendSolenoid;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class StartRightSwitchRight extends CommandGroup {

	public StartRightSwitchRight() {
		// TODO Auto-generated constructor stub
		addSequential(new ResetElevator());
		addSequential(new StopAndRepositionTalons());
		addSequential(new AutonForward(120));
		addSequential(new ExtendSolenoid());
	}
}
