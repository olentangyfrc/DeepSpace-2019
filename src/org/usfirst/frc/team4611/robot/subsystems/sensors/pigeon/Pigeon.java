package org.usfirst.frc.team4611.robot.subsystems.sensors.pigeon;

import com.ctre.phoenix.sensors.PigeonIMU;
import com.ctre.phoenix.sensors.PigeonIMU.FusionStatus;

public class Pigeon {

	private PigeonIMU pigeon;
	
	//Used to tell when to report in degrees or radians
	private AngleReporting report = AngleReporting.DEGREES;
	
	public Pigeon(int pigeonPort) {
		pigeon = new PigeonIMU(pigeonPort);
		pigeon.setFusedHeading(0, 0);
	}
	
	public Pigeon(int pigeonPort, AngleReporting report) {
		pigeon = new PigeonIMU(pigeonPort);
		this.report = report;
	}
	
	/**
	 * @return The current angle of the gyro on the pigeon (range from -infinity to infinity)
	 */
	public double getCurrentAngle() {
		FusionStatus status = new FusionStatus();
		pigeon.getFusedHeading(status);
		return report == AngleReporting.DEGREES ? status.heading : this.toRadians(status.heading);
	}
	
	/**
	 * @return The current angle of the gyro adjusted to the range of -360/-2PI to 360/2PI 
	 */
	public double getCurrentRelativeAngle() {
		return report == AngleReporting.DEGREES ? this.getCurrentAngle()%360 : this.getCurrentAngle()%(2*Math.PI);
	}
	
	/**
	 * @return The current angle of the gyro adjusted to the range of 0 to 360/2PI
	 */
	public double getCurrentAbsoluteAngle() {
		double angle = this.getCurrentAbsoluteAngle();
		if(angle < 0) {
			angle += report == AngleReporting.DEGREES ? 360 : 2*Math.PI;
		}
		return angle;
	}
	
	/**
	 * Ex: comAngle = 10, current Angle = 20, returns -10 (10-20)
	 * @param comAngle The angle to compare to the current angle
	 * @return The distance between the given angle and the current angle of the gyro
	 */
	public double getAngleError(double comAngle) {
		return comAngle-getCurrentAngle();
	}
	
	/**
	 * @param comAngle The angle to compate to the current angle
	 * @return The absolute distance between the given angle and the current angle of the gyro
	 */
	public double getAbolsuteAngleError(double comAngle) {
		return Math.abs(comAngle-getCurrentAngle());
	}
	
	/**
	 * @param value of the angle in degrees
	 * @return The angle in radians
	 */
	public double toRadians(double val) {
		return val*2*Math.PI/360;
	}
	
	/**
	 * @param value of the angle in radians
	 * @return the angle in degrees
	 */
	public double toDegrees(double val) {
		return val*360/(2*Math.PI);
	}
	
}
