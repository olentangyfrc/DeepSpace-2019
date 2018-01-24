package org.usfirst.frc.team4611.robot.subsystems;

import org.usfirst.frc.team4611.robot.RobotMap;
import org.usfirst.frc.team4611.robot.commands.drive.MecanumDrive;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveTrain extends Subsystem {

	public void move(double y, double x, double z) { //Grabs the left and right values that get passed by "TankDrive"
		 RobotMap.driveTrain.driveCartesian(y, x, z); //Use those values for the method "tankDrive" which calls for joystick values
	}
	
	public void moveGyro (double y, double x, double z, double gyroAngle) {
		RobotMap.driveTrain.driveCartesian(y, x, z, gyroAngle);
	}
	
	public void movePolar (double mag, double angle, double z) {
		RobotMap.driveTrain.drivePolar(mag, angle, z);
	}
	
	public void motionMagic(int positionUnits) {
		RobotMap.driveTrainBL_Talon.set(ControlMode.MotionMagic, positionUnits);
		RobotMap.driveTrainBR_Talon.set(ControlMode.MotionMagic, positionUnits);
		RobotMap.driveTrainFL_Talon.set(ControlMode.MotionMagic, positionUnits);
		RobotMap.driveTrainFR_Talon.set(ControlMode.MotionMagic, positionUnits);
	}
	
	
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new MecanumDrive()); //This subsystem will automatically run this command 
	}
}
