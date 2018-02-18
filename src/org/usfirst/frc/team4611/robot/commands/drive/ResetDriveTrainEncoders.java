package org.usfirst.frc.team4611.robot.commands.drive;

import org.usfirst.frc.team4611.robot.Robot;
import org.usfirst.frc.team4611.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class ResetDriveTrainEncoders extends Command {

	public ResetDriveTrainEncoders() {
		this.requires(Robot.mecanum);
	}
	
	protected void execute() {
		Robot.mecanum.resetEncoders();
		System.out.println(this.getClass().getName() + " Sensors Set to Zero");
		
		System.out.println(Math.abs(RobotMap.driveTrainBL_Talon.getSelectedSensorPosition(0)));
		System.out.println(Math.abs(RobotMap.driveTrainBR_Talon.getSelectedSensorPosition(0)));
		System.out.println(Math.abs(RobotMap.driveTrainFL_Talon.getSelectedSensorPosition(0)));
		System.out.println(Math.abs(RobotMap.driveTrainFR_Talon.getSelectedSensorPosition(0)));

	}
	
	@Override
	protected boolean isFinished() {
		return true;
	}
	

}
