package org.usfirst.frc.team4611.robot.subsystems.navigation.sensors;

import org.usfirst.frc.team4611.robot.subsystems.baseclasses.AbsoluteEncoder;

import edu.wpi.first.wpilibj.AnalogPotentiometer;

public class Potentiometer extends AbsoluteEncoder{
	
	AnalogPotentiometer pot;
	double minimum;
	double maximum;
	
	public Potentiometer(int port, double min, double max) {
		pot = new AnalogPotentiometer(port);
		minimum = min;
		maximum = max;
	}

	@Override
	public double getValue() {
		// TODO Auto-generated method stub
		if(pot.get() < .001) {
			return 0;
		}
		else {
			return (pot.get()-minimum)/(maximum-minimum);
		}
	}

	public double getRawValue() {
		if(pot.get() < .001) {
			return 0;
		}
		else {
			return pot.get();
		}
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}
	
}
