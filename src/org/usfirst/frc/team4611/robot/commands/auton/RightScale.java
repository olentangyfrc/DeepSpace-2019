package org.usfirst.frc.team4611.robot.commands.auton;

import org.usfirst.frc.team4611.robot.commands.solenoid.RetractSolenoid;
import org.usfirst.frc.team4611.robot.commands.solenoid.ExtendSolenoid;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class RightScale extends CommandGroup {

	public RightScale() {	
		addSequential(new ExtendSolenoid());
		addSequential(new AutonForward(26 * 12));
		addSequential(new StopAndRepositionTalons());
		addSequential(new AutonForward(26 * 12 /2));
		addSequential(new StopAndRepositionTalons());
		//addSequential(new AutonStrafe(50 * 1.6));
		addSequential(new RetractSolenoid());	
	}
}

