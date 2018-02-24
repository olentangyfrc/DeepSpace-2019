package org.usfirst.frc.team4611.robot.commands.auton;

import org.usfirst.frc.team4611.robot.commands.solenoid.ReleaseBox;
import org.usfirst.frc.team4611.robot.commands.solenoid.GrabBox;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonForwardRight extends CommandGroup {

	public AutonForwardRight() {	
		addSequential(new ReleaseBox());
		addSequential(new AutonForward(135));
		addSequential(new StopAndRepositionTalons());
		addSequential(new AutonStrafeRight(50 * 1.6));
		addSequential(new GrabBox());
	}
}
