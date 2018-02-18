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
	public void motionMagicStraight(double inches) {
		double positionUnits = inches * 206.243;
		//first convert inches to position units (double)


		RobotMap.driveTrainBL_Talon.set(ControlMode.MotionMagic, positionUnits);
		RobotMap.driveTrainBR_Talon.set(ControlMode.MotionMagic, -positionUnits);
		RobotMap.driveTrainFL_Talon.set(ControlMode.MotionMagic, positionUnits);
		RobotMap.driveTrainFR_Talon.set(ControlMode.MotionMagic, -positionUnits);
	}
	
	public void motionMagicStrafe(double positionUnits) {
		RobotMap.driveTrainFR_Talon.set(ControlMode.MotionMagic, positionUnits);
		RobotMap.driveTrainBR_Talon.set(ControlMode.MotionMagic, -positionUnits);
		RobotMap.driveTrainFL_Talon.set(ControlMode.MotionMagic, positionUnits);
		RobotMap.driveTrainBL_Talon.set(ControlMode.MotionMagic, -positionUnits);
	}
	
	public void velocityDrive(double velocity1, double velocity2, double velocity3, double velocity4) {
		RobotMap.driveTrainBL_Talon.set(ControlMode.Velocity, velocity4);
		RobotMap.driveTrainBR_Talon.set(ControlMode.Velocity, velocity3);
		RobotMap.driveTrainFL_Talon.set(ControlMode.Velocity, velocity1);
		RobotMap.driveTrainFR_Talon.set(ControlMode.Velocity, velocity2);
		
	}
	
	
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new MecanumDrive()); //This subsystem will automatically run this command 
	}
}