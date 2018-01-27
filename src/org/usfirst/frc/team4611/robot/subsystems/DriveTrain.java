package org.usfirst.frc.team4611.robot.subsystems;

import org.usfirst.frc.team4611.robot.RobotMap;
import org.usfirst.frc.team4611.robot.commands.drive.MecanumDrive;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveTrain extends Subsystem {
	//public Command defaultCommand;

	public void move(double y, double x, double z) { //Grabs the left and right values that get passed by "TankDrive"
		 RobotMap.driveTrain.driveCartesian(y, x, z); //Use those values for the method "tankDrive" which calls for joystick values
	}
	
	public void moveGyro (double y, double x, double z, double gyroAngle) {
		RobotMap.driveTrain.driveCartesian(y, x, z, gyroAngle);
	}
	
	public void movePolar (double mag, double angle, double z) {
		RobotMap.driveTrain.drivePolar(mag, angle, z);
	}
	
	
	@Override
	protected void initDefaultCommand() {
		//defaultCommand = new MecanumDrive();
		setDefaultCommand(new MecanumDrive()); //This subsystem will automatically run this command 
	}
}
