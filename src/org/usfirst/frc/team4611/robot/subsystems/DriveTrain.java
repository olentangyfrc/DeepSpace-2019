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
	
	public void motionMagicStraight(double positionUnits) {
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
	
	public void resetEncoders() {
		RobotMap.driveTrainBL_Talon.setSelectedSensorPosition(0, 0, 0);
		RobotMap.driveTrainBR_Talon.setSelectedSensorPosition(0, 0, 0);
		RobotMap.driveTrainFL_Talon.setSelectedSensorPosition(0, 0, 0);
		RobotMap.driveTrainFR_Talon.setSelectedSensorPosition(0, 0, 0);
	}
	//reset to default 
	public void velocityDrive(double velocity1, double velocity2, double velocity3, double velocity4) {
		RobotMap.driveTrainBL_Talon.set(ControlMode.Velocity, velocity4);
		RobotMap.driveTrainBR_Talon.set(ControlMode.Velocity, velocity3);
		RobotMap.driveTrainFL_Talon.set(ControlMode.Velocity, velocity1);
		RobotMap.driveTrainFR_Talon.set(ControlMode.Velocity, velocity2);		
	}
	//reset to 0 before every motion magic command
	public void resetRampRate() {
		RobotMap.driveTrainBL_Talon.configClosedloopRamp(0, 0);
		RobotMap.driveTrainBR_Talon.configClosedloopRamp(0, 0);
		RobotMap.driveTrainFL_Talon.configClosedloopRamp(0, 0);
		RobotMap.driveTrainFR_Talon.configClosedloopRamp(0, 0);
	}
	//set back to default Ramp Rate
	public void setRampRate() {
		double rampSeconds = (double)RobotMap.getValue(RobotMap.mecanumSubTable, RobotMap.rampTime);
		RobotMap.driveTrainBL_Talon.configClosedloopRamp(rampSeconds, 0);
		RobotMap.driveTrainBR_Talon.configClosedloopRamp(rampSeconds, 0);
		RobotMap.driveTrainFL_Talon.configClosedloopRamp(rampSeconds, 0);
		RobotMap.driveTrainFR_Talon.configClosedloopRamp(rampSeconds, 0);
	}
	
	public void rotate(double velocity) {
		RobotMap.driveTrainBL_Talon.set(ControlMode.Velocity, velocity);
		RobotMap.driveTrainBR_Talon.set(ControlMode.Velocity, velocity);
		RobotMap.driveTrainFL_Talon.set(ControlMode.Velocity, velocity);
		RobotMap.driveTrainFR_Talon.set(ControlMode.Velocity, velocity);

	}
	
	public void logSpeed() {
	  	double blSpeed, brSpeed, flSpeed, frSpeed;
    	
    	blSpeed	= RobotMap.driveTrainBL_Talon.get();
    	brSpeed	= RobotMap.driveTrainBR_Talon.get();
    	flSpeed	= RobotMap.driveTrainFL_Talon.get();
    	frSpeed	= RobotMap.driveTrainFR_Talon.get();

    	System.out.println(this.getClass().getName() + "isFinished() : motorSpeeds [bl, br, fl, fr] ["
    																			+ blSpeed + ", "
    																			+ brSpeed + ", "
    																			+ flSpeed + ", "
    																			+ frSpeed + ']');
	}
	
	public void logPosition() {
    	double blPosition, brPosition, flPosition, frPosition;
    	
    	blPosition	= RobotMap.driveTrainBL_Talon.getSelectedSensorPosition(0);
    	brPosition	= RobotMap.driveTrainBR_Talon.getSelectedSensorPosition(0);
    	flPosition	= RobotMap.driveTrainFL_Talon.getSelectedSensorPosition(0);
    	frPosition	= RobotMap.driveTrainFR_Talon.getSelectedSensorPosition(0);
      	System.out.println(this.getClass().getName() + "isFinished() : motorPositions [bl, br, fl, fr] ["
      																			+ blPosition + ", "
      																			+ brPosition + ", "
      																			+ flPosition + ", "
      																			+ frPosition + ']');
	}
	
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new MecanumDrive()); //This subsystem will automatically run this command 
	}
}