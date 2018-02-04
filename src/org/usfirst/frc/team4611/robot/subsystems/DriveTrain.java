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
	
	public void motionMagicStraight(int positionUnits) {
		RobotMap.driveTrainBL_Talon.set(ControlMode.MotionMagic, positionUnits);
		RobotMap.driveTrainBR_Talon.set(ControlMode.MotionMagic, -positionUnits);
		RobotMap.driveTrainFL_Talon.set(ControlMode.MotionMagic, positionUnits);
		RobotMap.driveTrainFR_Talon.set(ControlMode.MotionMagic, -positionUnits);
	}
	
	public void motionMagicStrafe(int positionUnits) {
		RobotMap.driveTrainBL_Talon.set(ControlMode.MotionMagic, -positionUnits);
		RobotMap.driveTrainBR_Talon.set(ControlMode.MotionMagic, -positionUnits);
		RobotMap.driveTrainFL_Talon.set(ControlMode.MotionMagic, positionUnits);
		RobotMap.driveTrainFR_Talon.set(ControlMode.MotionMagic, positionUnits);
	}
	
	public void velocityDrive(double velocity1, double velocity2, double velocity3, double velocity4) {
		RobotMap.driveTrainBL_Talon.set(ControlMode.Velocity, velocity4);
		RobotMap.driveTrainBR_Talon.set(ControlMode.Velocity, velocity3);
		RobotMap.driveTrainFL_Talon.set(ControlMode.Velocity, velocity1);
		RobotMap.driveTrainFR_Talon.set(ControlMode.Velocity, velocity2);
		
		/* I'm a good 75% sure we don't need this but if im not moving throw in a P
		RobotMap.driveTrainBL_Talon.config_kP(0, 1, 0);
		RobotMap.driveTrainBL_Talon.config_kP(0, 1, 0);
		RobotMap.driveTrainBL_Talon.config_kP(0, 1, 0);
		RobotMap.driveTrainBL_Talon.config_kP(0, 1, 0);
		*/ 
		 
		/* Just for when everything is broken and I give up		
		System.out.println("BL Set Velocity: " + velocity4);
		System.out.println("BR Set Velocity: " + velocity3);
		System.out.println("FL Set Velocity: " + velocity1);
		System.out.println("FR Set Velocity: " + velocity2);
		
		System.out.println("BL Actual Velocity: " + RobotMap.driveTrainBL_Talon.getSelectedSensorVelocity(0));
		System.out.println("BR Actual Velocity: " + RobotMap.driveTrainBR_Talon.getSelectedSensorVelocity(0));
		System.out.println("FL Actual Velocity: " + RobotMap.driveTrainFL_Talon.getSelectedSensorVelocity(0));
		System.out.println("FR Actual Velocity: " + RobotMap.driveTrainFR_Talon.getSelectedSensorVelocity(0));*/
	}
	
	
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new MecanumDrive()); //This subsystem will automatically run this command 
	}
}