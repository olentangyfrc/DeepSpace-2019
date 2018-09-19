package org.usfirst.frc.team4611.robot.subsystems;

import java.util.HashMap;

import org.usfirst.frc.team4611.robot.OI;
import org.usfirst.frc.team4611.robot.Robot;
import org.usfirst.frc.team4611.robot.commands.Move;
import org.usfirst.frc.team4611.robot.networktables.NetTableManager;
import org.usfirst.frc.team4611.robot.subsystems.baseclasses.MecanumBase;
import org.usfirst.frc.team4611.robot.subsystems.sensors.pigeon.Pigeon;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class TalonMecanum extends MecanumBase {
	
	private int maxRPM = 1200;
	
	private WPI_TalonSRX frontLeft = new WPI_TalonSRX(0);
	private WPI_TalonSRX frontRight = new WPI_TalonSRX(1);
	private WPI_TalonSRX backLeft = new WPI_TalonSRX(2);
	private WPI_TalonSRX backRight = new WPI_TalonSRX(3);
	
	
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
	
	//private Pigeon pigeon;
	
	public TalonMecanum() {
		setupTalons();
		//this.pigeon = pigeon;
	}
	
	public void setupTalons() {
		frontLeft.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
		frontRight.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
		backLeft.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
		backRight.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
		
	/**	frontLeft.config_kP(0, .65, 0);
		frontRight.config_kP(0, .65, 0);
		backLeft.config_kP(0, .65, 0);
		backRight.config_kP(0, .65, 0);
			
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
		backRight.setSensorPhase(true);*/
	}
	
	public void moveBackward(double speed) {
		
	}
	

	public void moveForward(double speed) {
		
	}

	public void move() {
		double YVal = -OI.generalJoystickFilter(OI.leftJoy.getY()); 
		double XVal = OI.generalJoystickFilter(OI.leftJoy.getX());
		double ZVal = OI.generalJoystickFilter(OI.rightJoy.getX());
	
		velocity1 = 4*(maxRPM * (YVal * YValScaler1 + XVal * XValScaler1 + ZVal * ZValScaler) * (velocityInvert1));
		velocity2 = 4*(maxRPM * (YVal * YValScaler2 - XVal * XValScaler2 - ZVal * ZValScaler) * (velocityInvert2)); 
		velocity3 = 4*(maxRPM * (YVal * YValScaler2 + XVal * XValScaler2 - ZVal * ZValScaler) * (velocityInvert3));
		velocity4 = 4*(maxRPM * (YVal * YValScaler1 - XVal * XValScaler1 + ZVal * ZValScaler) * (velocityInvert4));
		
		frontLeft.set(ControlMode.Velocity, velocity1);
		frontRight.set(ControlMode.Velocity, velocity2);
		backLeft.set(ControlMode.Velocity, velocity4);
		backRight.set(ControlMode.Velocity, velocity3);
		
		
		HashMap<String, Object> values = new HashMap<String, Object>();
		values.put(velocity1ID, velocity1);
		values.put(velocity2ID, velocity2);
		values.put(velocity3ID, velocity3);
		values.put(velocity4ID, velocity4);
	//	values.put("pigeon-angle", pigeon.getCurrentAngle());
	//	values.put("pigeon-wrapped-angle", pigeon.getCurrentRelativeAngle());
	//	values.put("pigeon-0-360-angle", pigeon.getCurrentAbsoluteAngle());
		NetTableManager.updateValues(mecanumSubtable, values);
				
	}
	
	protected void initDefaultCommand() {
		Robot.mecanum.setDefaultCommand(new Move(this));
	}
	

}