package org.usfirst.frc.team4611.robot.commands.auton;

import org.usfirst.frc.team4611.robot.commands.pigeon.PigeonAdjust;
import org.usfirst.frc.team4611.robot.commands.solenoid.RetractSolenoid;
import org.usfirst.frc.team4611.robot.commands.solenoid.ExtendSolenoid;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class TestCommand extends CommandGroup {

	public TestCommand() {
		this.addSequential(new StopAndRepositionTalons());
		this.addSequential(new ExtendSolenoid());
		this.addSequential(new AutonForward(26 * 12));
		this.addSequential(new StopAndRepositionTalons());
		this.addSequential(new PigeonAdjust(-90));
		this.addSequential(new RetractSolenoid());
	}
}
