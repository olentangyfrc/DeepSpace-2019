package org.usfirst.frc.team4611.robot.subsystems;

import org.usfirst.frc.team4611.robot.Robot;
import org.usfirst.frc.team4611.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class UltrasonicSensor extends Subsystem{
	
	/** The sensor measures the distance from the sensor to a surface up to 16 inches from the sensor.
	 * Inches equation was calculated 1/21/2018 with data collection.
	 * @return Returns approximate range in inches
	 */
	public double getInches(){
		double value = RobotMap.ultrasonicInput.getValue();
		double inches = value * .05140 - 0.07;
		//SmartDashboard.putNumber("Ultra Value", value);
		//SmartDashboard.putNumber("Ultra Inches", inches);
		return inches;
	}

	@Override
	protected void initDefaultCommand() {
		
	}

}
