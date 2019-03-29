package org.usfirst.frc.team4611.robot.subsystems.navigation.sensors;

import org.usfirst.frc.team4611.robot.subsystems.baseclasses.AbsoluteEncoder;

import edu.wpi.first.wpilibj.AnalogInput;

public class Potentiometer extends AbsoluteEncoder {
	
	AnalogInput input;
	double minimum;
	double maximum;
	
	public Potentiometer(int port, double min, double max) {
		input = new AnalogInput(port);
		minimum = min;
		maximum = max;
	}

	@Override
	public double getPercentOutput() {
		return (input.getVoltage()-minimum)/(maximum-minimum);
	}

	public double getRawValue() {
		return input.getVoltage();
	}

	public void setMin(double m) {
		minimum = m;
	}
	public double getMin() {
		return minimum;
	}

	public void setMax(double m) {
		maximum = m;
	}
	public double getMax() {
		return maximum;
	}

	@Override
	protected void initDefaultCommand() {
	}
	
}
