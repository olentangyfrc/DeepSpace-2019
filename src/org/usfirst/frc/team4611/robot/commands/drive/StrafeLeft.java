package org.usfirst.frc.team4611.robot.commands.drive;

import org.usfirst.frc.team4611.robot.OI;
import org.usfirst.frc.team4611.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class StrafeLeft extends Command {
	private double speed;
	public StrafeLeft (double s)
	{
		speed=s;
		this.requires(Robot.mecanum);
	}
	protected void execute ()
	{
		double YVal=Robot.oi.strafeFilter(OI.leftJoy.getY());
		double ZVal=Robot.oi.filter(OI.rightJoy.getX());
		
		Robot.mecanum.move(-YVal, -speed, -ZVal);
	}
	

	@Override
	protected boolean isFinished() {
		return false;
	}

}
