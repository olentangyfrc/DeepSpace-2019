package org.usfirst.frc.team4611.robot.commands.teleop.drive;

import org.usfirst.frc.team4611.robot.Robot;
import org.usfirst.frc.team4611.robot.subsystems.baseclasses.MecanumBase;
import org.usfirst.frc.team4611.robot.subsystems.tank.TalonTankdrive;

import edu.wpi.first.wpilibj.command.Command;

public class Move extends Command {
		
	private MecanumBase mecanum;
	
	public Move(MecanumBase mecanum) {
		this.mecanum = mecanum;
		this.requires(Robot.driveTrain);
	}
	
	public Move(TalonTankdrive talonTankdrive) {
		// TODO Auto-generated constructor stub
		this.mecanum = mecanum;
		this.requires(Robot.driveTrain);
	}

	protected void execute() {
		mecanum.move();
	}

	@Override
	protected boolean isFinished() {
		return false;
	}
}