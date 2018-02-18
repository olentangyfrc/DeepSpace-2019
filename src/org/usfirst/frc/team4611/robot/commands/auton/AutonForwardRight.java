package org.usfirst.frc.team4611.robot.commands.auton;

import org.usfirst.frc.team4611.robot.commands.drive.ResetDriveTrainEncoders;
import org.usfirst.frc.team4611.robot.commands.pigeon.PigeonAdjust;
import org.usfirst.frc.team4611.robot.commands.solenoid.ExtendSolenoid;
import org.usfirst.frc.team4611.robot.commands.solenoid.RetractSolenoid;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonForwardRight extends CommandGroup {

	public AutonForwardRight() {
		//addSequential(new ExtendSolenoid());
		addSequential(new AutonForward(135));
		addSequential(new ResetDriveTrainEncoders(), 1);
		addSequential(new AutonStrafe(50 * 1.6));
		addSequential(new RetractSolenoid());
	}
}
