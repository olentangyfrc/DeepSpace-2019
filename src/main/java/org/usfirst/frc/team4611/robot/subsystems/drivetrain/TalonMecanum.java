package org.usfirst.frc.team4611.robot.subsystems.drivetrain;

import java.util.HashMap;
import java.util.logging.Logger;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import org.usfirst.frc.team4611.robot.OI;
import org.usfirst.frc.team4611.robot.OzoneJavaLogger.LogTest;
import org.usfirst.frc.team4611.robot.Robot;
import org.usfirst.frc.team4611.robot.networktables.NetTableManager;
import org.usfirst.frc.team4611.robot.subsystems.drivetrain.commands.Move;
import org.usfirst.frc.team4611.robot.subsystems.drivetrain.interfaces.DriveTrain;

import edu.wpi.first.wpilibj.Timer;

public class TalonMecanum extends DriveTrain {

	private int maxRPM = 400; // Reduced from 1200

	private WPI_TalonSRX frontLeft = new WPI_TalonSRX(frontLeftTalonPort);
	private WPI_TalonSRX frontRight = new WPI_TalonSRX(frontRightTalonPort);
	private WPI_TalonSRX backLeft = new WPI_TalonSRX(backLeftTalonPort);
	private WPI_TalonSRX backRight = new WPI_TalonSRX(backRightTalonPort);

	public double pVal = .65;
	public int interval = 10;

	private double velocity1;
	private double velocity2;
	private double velocity3;
	private double velocity4;

	private double YValScaler1 = 1;
	private double XValScaler1 = 1;
	private double YValScaler2 = 1;
	private double XValScaler2 = 1;
	private double ZValScaler = 1;

	private int velocityInvert1 = -1;
	private int velocityInvert2 = -1;
	private int velocityInvert3 = 1;
	private int velocityInvert4 = 1;

	private String mecanumSubtable = "Mecanum";

	private String velocity1ID = "Velocity1";
	private String velocity2ID = "Velocity2";
	private String velocity3ID = "Velocity3";
	private String velocity4ID = "Velocity4";

	/**
	 * private DistanceFollower left; private DistanceFollower right;
	 * 
	 * private TankModifier modifier;
	 */
	private Timer driveT;

	private double wheelBase = .59;// IN METERS

	public TalonMecanum() {
		setupTalons();
	}

	public void setupTalons() {
		driveT = new Timer();
		driveT.start();
		driveT.reset();

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

		/**
		 * left = new DistanceFollower(); right = new DistanceFollower();
		 * left.configurePIDVA(1, 0, 1, 1, 0); right.configurePIDVA(1, 0, 1, 1, 0);
		 */
	}

	public void setTrajectorySpeeds() {

		/**
		 * double leftSpeed = left.calculate(getMetersTraveled()); double rightSpeed =
		 * right.calculate(getMetersTraveled());
		 * 
		 * double velocity1 = 4 * (maxRPM * leftSpeed * velocityInvert1); double
		 * velocity2 = 4 * (maxRPM * rightSpeed * velocityInvert2); double velocity3 = 4
		 * * (maxRPM * rightSpeed * velocityInvert3); double velocity4 = 4 * (maxRPM *
		 * leftSpeed * velocityInvert4);
		 */
		frontLeft.set(ControlMode.Velocity, velocity1);
		frontRight.set(ControlMode.Velocity, velocity2);
		backLeft.set(ControlMode.Velocity, velocity4);
		backRight.set(ControlMode.Velocity, velocity3);

		/**
		 * logger.info("Left: " + leftSpeed); logger.info("Right: " + rightSpeed);
		 */
	}

	/**
	 * public void followTrajectory(Trajectory traj) { resetEncoders(); modifier =
	 * new TankModifier(traj); modifier.modify(wheelBase);
	 * left.setTrajectory(modifier.getLeftTrajectory());
	 * right.setTrajectory(modifier.getRightTrajectory()); }
	 */

	public void moveLateralInches(double inches) {
		// Converts the given inches to position units for the encoders to understand
		double pu = inches * INCH_PU_MULT;// probably needs to change to metric system

		frontLeft.set(ControlMode.MotionMagic, pu);
		frontRight.set(ControlMode.MotionMagic, -pu);
		backLeft.set(ControlMode.MotionMagic, pu);
		backRight.set(ControlMode.MotionMagic, -pu);
	}

	public void moveHorizontalInches(double inches) {
		// Converts the given inches to position units for the encoders to understand
		double pu = inches * INCH_PU_MULT;// probably needs to change to metric system

		frontLeft.set(ControlMode.MotionMagic, pu);
		frontRight.set(ControlMode.MotionMagic, -pu);
		backLeft.set(ControlMode.MotionMagic, pu);
		backRight.set(ControlMode.MotionMagic, -pu);
	}

	public void moveLateral(double speed) {

	}

	public void moveHorizontal(double speed) {

	}

	public void resetEncoders() {
		frontLeft.setSelectedSensorPosition(0, 0, 0);
		frontRight.setSelectedSensorPosition(0, 0, 0);
		backLeft.setSelectedSensorPosition(0, 0, 0);
		backRight.setSelectedSensorPosition(0, 0, 0);
	}

	public int getAverageSensorPos() {
		return (Math.abs(frontLeft.getSelectedSensorPosition(0)) + Math.abs(frontRight.getSelectedSensorPosition(0))
				+ Math.abs(backLeft.getSelectedSensorPosition(0)) + Math.abs(backRight.getSelectedSensorPosition(0)))
				/ 4;
	}

	/**
	 * Converts the average sensor position from position units to meters based of
	 * experimented {@value #METER_PU_MULT}
	 * 
	 * @return The approximate average distance traveled in meters
	 */
	public double getMetersTraveled() {
		return getAverageSensorPos() / METER_PU_MULT;

	}

	public void moveVelocityAuton(double speed) {
		double velocity1 = 4 * (maxRPM * speed * velocityInvert1);
		double velocity2 = 4 * (maxRPM * speed * velocityInvert2);
		double velocity3 = 4 * (maxRPM * speed * velocityInvert3);
		double velocity4 = 4 * (maxRPM * speed * velocityInvert4);

		frontLeft.set(ControlMode.Velocity, velocity1);
		frontRight.set(ControlMode.Velocity, velocity2);
		backLeft.set(ControlMode.Velocity, velocity4);
		backRight.set(ControlMode.Velocity, velocity3);
	}

	public void move() {
		double YVal = OI.generalJoystickFilter(OI.leftJoy.getY());
		double XVal = -OI.generalJoystickFilter(OI.leftJoy.getX());
		double ZVal = OI.generalJoystickFilter(OI.rightJoy.getX());

		velocity1 = 4 * (maxRPM * (XVal * YValScaler1 + ZVal * XValScaler1 + YVal * ZValScaler) * (velocityInvert1));
		velocity2 = 4 * (maxRPM * (XVal * YValScaler2 - ZVal * XValScaler2 - YVal * ZValScaler) * (velocityInvert2));
		velocity3 = 4 * (maxRPM * (XVal * YValScaler2 + ZVal * XValScaler2 - YVal * ZValScaler) * (velocityInvert3));
		velocity4 = 4 * (maxRPM * (XVal * YValScaler1 - ZVal * XValScaler1 + YVal * ZValScaler) * (velocityInvert4));

		System.out.println(velocity1 + "," + velocity2 + "," + velocity3 + "," + velocity4);
		frontLeft.set(ControlMode.Velocity, velocity1);
		frontRight.set(ControlMode.Velocity, velocity2);
		backLeft.set(ControlMode.Velocity, velocity4);
		backRight.set(ControlMode.Velocity, velocity3);

		HashMap<String, Object> values = new HashMap<String, Object>();
		values.put(velocity1ID, velocity1);
		values.put(velocity2ID, velocity2);
		values.put(velocity3ID, velocity3);
		values.put(velocity4ID, velocity4);
		NetTableManager.updateValues(mecanumSubtable, values);
		logger.info("" + (frontRight.getSelectedSensorVelocity(0)));
		logger.fine("" + frontRight.getBusVoltage());
	}

	public void moveAtSpeed(double speed1, double speed2, double speed3, double speed4) {

		frontLeft.set(ControlMode.Velocity, speed1);
		frontRight.set(ControlMode.Velocity, speed2);
		backLeft.set(ControlMode.Velocity, speed4);
		backRight.set(ControlMode.Velocity, speed3);

	}

	public void rotate(double velocity) {
		frontLeft.set(ControlMode.Velocity, velocity);
		frontRight.set(ControlMode.Velocity, velocity);
		backLeft.set(ControlMode.Velocity, velocity);
		backRight.set(ControlMode.Velocity, velocity);
	}

	/**
	 * Defines the command to start when the bot is enabled. For TalonMecanum, this
	 * begins the command for normal driving procedure
	 */
	protected void initDefaultCommand() {
		this.setDefaultCommand(new Move());
	}

	public double getVelocity() {
		return getMetersTraveled() / (driveT.get() / 1000);
	}

	@Override
	public void activateTurbo() {

	}

	@Override
	public void deactivateTurbo() {

	}

}