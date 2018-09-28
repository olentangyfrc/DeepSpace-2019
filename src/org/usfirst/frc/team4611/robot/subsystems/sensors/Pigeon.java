package org.usfirst.frc.team4611.robot.subsystems.sensors;

import com.ctre.phoenix.sensors.PigeonIMU;
import com.ctre.phoenix.sensors.PigeonIMU.FusionStatus;

public class Pigeon {

	private static final int PIGEON_PORT = 21;
	
	private static PigeonIMU pigeon = new PigeonIMU(PIGEON_PORT);
	
	public static void resetAngle() {
		pigeon.setFusedHeading(0, 0);
	}
	/**
	 * @return The current angle of the gyro on the pigeon (range from -infinity to infinity)
	 */
	public static double getCurrentAngle() {
		FusionStatus status = new FusionStatus();
		pigeon.getFusedHeading(status);
		return status.heading;
	}
	
	/**
	 * @return The current angle of the gyro adjusted to the range of -360 to 360 
	 */
	public static double getCurrentRelativeAngle() {
		return getCurrentAngle()%360;
	}
	
	/**
	 * @return The current angle of the gyro adjusted to the range of 0 to 360
	 */
	public static double getCurrentAbsoluteAngle() {
		double angle = getCurrentRelativeAngle();
		if(angle < 0) {
			angle += 360;
		}
		return angle;
	}
	
	/**
	 * Ex: comAngle = 10, current Angle = 20, returns -10 (10-20)
	 * @param comAngle The angle to compare to the current angle
	 * @return The distance between the given angle and the current angle of the gyro
	 */
	public static double getAngleError(double comAngle) {
		return comAngle-getCurrentAngle();
	}
	
	/**
	 * @param comAngle The angle to compate to the current angle
	 * @return The absolute distance between the given angle and the current angle of the gyro
	 */
	public static double getAbolsuteAngleError(double comAngle) {
		return Math.abs(getAngleError(comAngle));
	}
	
}
