package org.usfirst.frc.team4611.robot.commands.auton;

import org.usfirst.frc.team4611.robot.commands.elevator.ResetElevator;
import org.usfirst.frc.team4611.robot.commands.pigeon.PigeonAdjust;
import org.usfirst.frc.team4611.robot.commands.solenoid.ExtendSolenoid;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class StartRightSwitchLeft extends CommandGroup {

	public StartRightSwitchLeft() {
		// TODO Auto-generated constructor stub
		addSequential(new ResetElevator());
		addSequential(new StopAndRepositionTalons());
		addSequential(new AutonForward(20*12));
		addSequential(new StopAndRepositionTalons());
		addSequential(new PigeonAdjust(180));
		addSequential(new AutonStrafeRight(15*12));
		addSequential(new StopAndRepositionTalons());
		//addSequential(new AutonForward());
		addSequential(new ExtendSolenoid());
		addSequential(new StopAndRepositionTalons());
	}
}
