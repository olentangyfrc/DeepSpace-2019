package org.usfirst.frc.team4611.robot.subsystems;

import org.usfirst.frc.team4611.robot.RobotMap;
import org.usfirst.frc.team4611.robot.commands.drive.MecanumDrive;
import org.usfirst.frc.team4611.robot.logging.Logger;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveTrain extends Subsystem {

	public void move(double y, double x, double z) { 
		 RobotMap.driveTrain.driveCartesian(y, x, z); 
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
	public void setRampRate(double rate) {
		RobotMap.driveTrainBL_Talon.configClosedloopRamp(rate, 0);
		RobotMap.driveTrainBR_Talon.configClosedloopRamp(rate, 0);
		RobotMap.driveTrainFL_Talon.configClosedloopRamp(rate, 0);
		RobotMap.driveTrainFR_Talon.configClosedloopRamp(rate, 0);
	}
	
	public void rotate(double velocity) {
		RobotMap.driveTrainBL_Talon.set(ControlMode.Velocity, velocity);
		RobotMap.driveTrainBR_Talon.set(ControlMode.Velocity, velocity);
		RobotMap.driveTrainFL_Talon.set(ControlMode.Velocity, velocity);
		RobotMap.driveTrainFR_Talon.set(ControlMode.Velocity, velocity);

	}
	
	public void config_kP(double p) {
		RobotMap.driveTrainBL_Talon.config_kP(0, p, 0);
		RobotMap.driveTrainBR_Talon.config_kP(0, p, 0);
		RobotMap.driveTrainFL_Talon.config_kP(0, p, 0);
		RobotMap.driveTrainFR_Talon.config_kP(0, p, 0);
	}
	
	public void config_kI(double i) {
		RobotMap.driveTrainBL_Talon.config_kI(0, i, 0);
		RobotMap.driveTrainBR_Talon.config_kI(0, i, 0);
		RobotMap.driveTrainFL_Talon.config_kI(0, i, 0);
		RobotMap.driveTrainFR_Talon.config_kI(0, i, 0);
	}
	
	public void config_kD(double d) {
		RobotMap.driveTrainBL_Talon.config_kD(0, d, 0);
		RobotMap.driveTrainBR_Talon.config_kD(0, d, 0);
		RobotMap.driveTrainFL_Talon.config_kD(0, d, 0);
		RobotMap.driveTrainFR_Talon.config_kD(0, d, 0);
	}
	
	public void logSpeed() {
	  	double blSpeed, brSpeed, flSpeed, frSpeed;
    	
    	blSpeed	= RobotMap.driveTrainBL_Talon.getMotorOutputPercent();
    	brSpeed	= RobotMap.driveTrainBR_Talon.getMotorOutputPercent();
    	flSpeed	= RobotMap.driveTrainFL_Talon.getMotorOutputPercent();
    	frSpeed	= RobotMap.driveTrainFR_Talon.getMotorOutputPercent();

    	Logger.log("motorSpeeds [bl, br, fl, fr] ["
													+ blSpeed + ", "
													+ brSpeed + ", "
													+ flSpeed + ", "
													+ frSpeed + ']', "DriveTrain");
	}
	
	public void logPosition() {
    	double blPosition, brPosition, flPosition, frPosition;
    	
    	blPosition	= RobotMap.driveTrainBL_Talon.getSelectedSensorPosition(0);
    	brPosition	= RobotMap.driveTrainBR_Talon.getSelectedSensorPosition(0);
    	flPosition	= RobotMap.driveTrainFL_Talon.getSelectedSensorPosition(0);
    	frPosition	= RobotMap.driveTrainFR_Talon.getSelectedSensorPosition(0);
      	Logger.log("motorPositions [bl, br, fl, fr] ["
													+ blPosition + ", "
													+ brPosition + ", "
													+ flPosition + ", "
													+ frPosition + ']', "DriveTrain");
	}
	
	public double getAveragePosition() {
		double encoderPositionAverage = (Math.abs(RobotMap.driveTrainBL_Talon.getSelectedSensorPosition(0)) +
		    	Math.abs(RobotMap.driveTrainBR_Talon.getSelectedSensorPosition(0)) +
		       	Math.abs(RobotMap.driveTrainFL_Talon.getSelectedSensorPosition(0)) +
		       	Math.abs(RobotMap.driveTrainFR_Talon.getSelectedSensorPosition(0))) / 4;
		return encoderPositionAverage;
	}
	
	public double getAverageSpeed() {
		double encoderSpeedAverage = (Math.abs(RobotMap.driveTrainBL_Talon.getSelectedSensorVelocity(0)) +
		    	Math.abs(RobotMap.driveTrainBR_Talon.getSelectedSensorVelocity(0)) +
		       	Math.abs(RobotMap.driveTrainFL_Talon.getSelectedSensorVelocity(0)) +
		       	Math.abs(RobotMap.driveTrainFR_Talon.getSelectedSensorVelocity(0))) / 4;
		return encoderSpeedAverage;
	}
	
	/**
	   * Returns 0.0 if the given value is within the specified range around zero. The remaining range
	   * between the deadband and 1.0 is scaled from 0.0 to 1.0. 
	   *
	   * @param value    value to clip
	   * @param deadband range around zero
	   */
	protected double applyDeadband(double value, double deadband) {
	    if (Math.abs(value) > deadband) {
	      if (value > 0.0) {
	        return (value - deadband) / (1.0 - deadband);
	      } else {
	        return (value + deadband) / (1.0 - deadband);
	      }
	    } else {
	      return 0.0;
	    }
	}
	
	public boolean isTargetSpeedWithinThreshold(double speed){
		if(Math.abs(speed) > 200) {
			return true;
		}
		
		else {
			return false;
		}
	}
	public void setMotionMagicAcceleration(int leftAccel, int rightAccel) {
		RobotMap.driveTrainFL_Talon.configMotionAcceleration(leftAccel, 0);
		RobotMap.driveTrainFR_Talon.configMotionAcceleration(rightAccel, 0);
		RobotMap.driveTrainBL_Talon.configMotionAcceleration(leftAccel, 0);
		RobotMap.driveTrainBR_Talon.configMotionAcceleration(rightAccel, 0);
	}
	public void setMotionMagicVelocity(int leftVel, int rightVel) {
		RobotMap.driveTrainFL_Talon.configMotionCruiseVelocity(leftVel, 0);
		RobotMap.driveTrainFR_Talon.configMotionCruiseVelocity(rightVel, 0);
		RobotMap.driveTrainBL_Talon.configMotionCruiseVelocity(leftVel, 0);
		RobotMap.driveTrainBR_Talon.configMotionCruiseVelocity(rightVel, 0);
	}
	public void setMotionMagicValuesToDefault() {
		setMotionMagicVelocity(RobotMap.motionmagicCruiseVelocity, RobotMap.motionmagicCruiseVelocity);
		setMotionMagicAcceleration(RobotMap.magicValuesAccel, RobotMap.magicValuesAccel);
	}
	
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new MecanumDrive());
	}
}