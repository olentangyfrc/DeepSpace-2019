package org.usfirst.frc.team4611.robot.commands.teleop.drive;

import org.usfirst.frc.team4611.robot.Robot;
import org.usfirst.frc.team4611.robot.subsystems.baseclasses.Drivetrain;
import org.usfirst.frc.team4611.robot.subsystems.baseclasses.MecanumBase;
import org.usfirst.frc.team4611.robot.subsystems.tank.TalonTankdrive;

import edu.wpi.first.wpilibj.command.Command;

public class Move extends Command {
		
	private Drivetrain drive;
	
	public Move(MecanumBase mecanum) {
		this.drive = mecanum;
		this.requires(Robot.driveTrain);
	}
	
	public Move(TalonTankdrive talonTankdrive) {
		// TODO Auto-generated constructor stub
		this.drive = talonTankdrive;
		this.requires(Robot.driveTrain);
	}

	protected void execute() {
		drive.move();
	}

	@Override
	protected boolean isFinished() {
		return false;
	}
}