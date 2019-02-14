package org.usfirst.frc.team4611.robot.subsystems.navigation.sensors;

import java.util.logging.Logger;

import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.DigitalInput;

public class LidarPWM {
	private Counter counter;
	
	private int printedWarningCount = 5;

    private static Logger logger = Logger.getLogger(LidarPWM.class.getName());
	
	public LidarPWM (int dio) {

		DigitalInput source = new DigitalInput(dio);
		counter = new Counter(source);
        counter.setMaxPeriod(1.0);

	    // Configure for measuring rising to falling pulses
	    counter.setSemiPeriodMode(true);
	    counter.reset();
	}

	/**
	 * Take a measurement and return the distance in cm
	 * 
	 * @return Distance in cm
	 */
	public double getDistance() {
		double cm;
		if (counter.get() < 1) {
			if (printedWarningCount-- > 0) {
				logger.info("LidarLitePWM: waiting for distance measurement");
			}
			return 0;
		}
		/* getPeriod returns time in seconds. The hardware resolution is microseconds.
		 * The LIDAR-Lite unit sends a high signal for 10 microseconds per cm of distance.
		 */
		cm = (counter.getPeriod() * 1000000.0 / 10.0);
		return cm;
	}
}