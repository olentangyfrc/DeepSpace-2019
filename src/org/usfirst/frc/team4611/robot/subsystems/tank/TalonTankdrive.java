package org.usfirst.frc.team4611.robot.subsystems.tank;

import java.util.HashMap;
import java.util.logging.Logger;

import org.usfirst.frc.team4611.robot.OI;
import org.usfirst.frc.team4611.robot.Robot;
import org.usfirst.frc.team4611.robot.OzoneJavaLogger.LogTest;
import org.usfirst.frc.team4611.robot.commands.teleop.drive.Move;
import org.usfirst.frc.team4611.robot.networktables.NetTableManager;
import org.usfirst.frc.team4611.robot.subsystems.baseclasses.TankdriveBase;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.Timer;
import jaci.pathfinder.followers.DistanceFollower;

public class TalonTankdrive extends TankdriveBase {
	
	final Logger	logger	= Logger.getLogger(LogTest.class.getName());
	
	private int maxRPM = 400; //Reduced from 1200
	
	private DigitalOutput sol = new DigitalOutput(0);
	
	private WPI_TalonSRX frontLeft = new WPI_TalonSRX(0);
	private WPI_TalonSRX frontRight = new WPI_TalonSRX(1);
	private WPI_TalonSRX backLeft = new WPI_TalonSRX(2);
	private WPI_TalonSRX backRight = new WPI_TalonSRX(3);
	private WPI_TalonSRX powerFrontRight = new WPI_TalonSRX(4);
	private WPI_TalonSRX powerFrontLeft = new WPI_TalonSRX(5);
	
	private double pVal = .65;
	private int interval = 10;
	
	public final double INCH_PU_MULT = 215.910640625;
	
	public final double METER_PU_MULT = 39.3701 * 215.910640625;
	
	private double velocity1;
	private double velocity2;
	private double velocity3;
	private double velocity4;
	
	private double YValScaler1 = 1;
	private double XValScaler1 = 1;
	private double YValScaler2 = 1;
	private double XValScaler2 = 1;
	private double ZValScaler = 1;
	
	private int velocityInvert1 = 1;
	private int velocityInvert2 = -1;
	private int velocityInvert3 = -1;
	private int velocityInvert4 = 1;
	
	private String mecanumSubtable = "Mecanum";
	
	private String velocity1ID = "Velocity1";
	private String velocity2ID = "Velocity2";
	private String velocity3ID = "Velocity3";
	private String velocity4ID = "Velocity4";
	
	public TalonTankdrive() {
		setupTalons();
	}
	
	public void setupTalons() {		
		frontLeft.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
		frontRight.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
		backLeft.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
		backRight.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
		
		frontLeft.setSelectedSensorPosition(0, 0, 0);
		frontRight.setSelectedSensorPosition(0, 0, 0);
		backLeft.setSelectedSensorPosition(0, 0, 0);
		backRight.setSelectedSensorPosition(0, 0, 0);
		
		frontLeft.config_kP(0, pVal, interval);
		frontRight.config_kP(0, pVal, interval);
		backLeft.config_kP(0, pVal, interval);
		backRight.config_kP(0, pVal, interval);
			
		frontLeft.config_kI(0, 0.000, 0);
		frontRight.config_kI(0, 0.000, 0);
		backLeft.config_kI(0, 0.000, 0);
		backRight.config_kI(0, 0.000, 0);
			
		frontLeft.config_kD(0, 0, 0);
		frontRight.config_kD(0, 0, 0);
		backLeft.config_kD(0, 0, 0);
		backRight.config_kD(0, 0, 0);
		
		frontLeft.setSensorPhase(true);
		frontRight.setSensorPhase(true);
		backLeft.setSensorPhase(true);
		backRight.setSensorPhase(true);

	}
	
	@Override
	public void move() {
		// TODO Auto-generated method stub
		double LYVal = -OI.generalJoystickFilter(OI.leftJoy.getY()); 
		double RYVal = -OI.generalJoystickFilter(OI.rightJoy.getY());
	
		velocity1 = 4*(maxRPM * (LYVal * YValScaler1) * (velocityInvert1));
		velocity2 = 4*(maxRPM * (RYVal * YValScaler2) * (velocityInvert2)); 

		if(OI.leftJoy.getTrigger())
			sol.set(true);
		else
			sol.set(false);
		
		backLeft.follow(frontLeft);
		backRight.follow(frontRight);
			
		frontLeft.set(ControlMode.Velocity, velocity1);
		frontRight.set(ControlMode.Velocity, velocity2);
		
		
		
		
		HashMap<String, Object> values = new HashMap<String, Object>();
		values.put(velocity1ID, velocity1);
		values.put(velocity2ID, velocity2);
		values.put(velocity3ID, velocity3);
		values.put(velocity4ID, velocity4);
		NetTableManager.updateValues(mecanumSubtable, values);
		logger.info(""+(frontRight.getSelectedSensorVelocity()*600/4092));
		logger.fine(""+frontRight.getBusVoltage());
	}

	@Override
	public void moveForward(int d) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void moveBackward(int d) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getAverageSensorPos() {
		// TODO Auto-generated method stub
		return (Math.abs(frontLeft.getSelectedSensorPosition(0)) + Math.abs(
				frontRight.getSelectedSensorPosition(0)) + Math.abs(
				backLeft.getSelectedSensorPosition(0)) + Math.abs(
				backRight.getSelectedSensorPosition(0)))/4;
	}

	@Override
	public void resetEncoders() {
		// TODO Auto-generated method stub
		frontLeft.setSelectedSensorPosition(0, 0, 0);
		frontRight.setSelectedSensorPosition(0, 0, 0);
		backLeft.setSelectedSensorPosition(0, 0, 0);
		backRight.setSelectedSensorPosition(0, 0, 0);
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		Robot.driveTrain.setDefaultCommand(new Move(this));
	}

	public void moveVelocityAuton(double speed) {
		// TODO Auto-generated method stub
		
	}

	public void rotate(double d) {
		// TODO Auto-generated method stub
		
	}

}
