package org.usfirst.frc.team4611.robot.commands.auton;

import org.usfirst.frc.team4611.robot.commands.solenoid.ExtendSolenoid;
import org.usfirst.frc.team4611.robot.commands.solenoid.RetractSolenoid;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class TestBlock extends CommandGroup {
	public TestBlock() {
		//addSequential(new ExtendSolenoid());
		addSequential(new AutonForward(50));
		addSequential(new AutonForward(75));
		addSequential(new AutonForward(10));
		addSequential(new StopAndRepositionTalons());
		//addSequential(new RetractSolenoid());
		addSequential(new StopAndRepositionTalons());
		addSequential(new AutonBackward(100 + 75 + 50));
		//addSequential(new ExtendSolenoid());
	}

}
