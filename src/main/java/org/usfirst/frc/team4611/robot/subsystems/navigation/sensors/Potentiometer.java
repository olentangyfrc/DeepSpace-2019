package org.usfirst.frc.team4611.robot.subsystems.navigation.sensors;

import org.usfirst.frc.team4611.robot.subsystems.baseclasses.AbsoluteEncoder;

import edu.wpi.first.wpilibj.AnalogPotentiometer;

public class Potentiometer extends AbsoluteEncoder{
	
	AnalogPotentiometer pot;
	
	public Potentiometer(int port) {
		pot = new AnalogPotentiometer(port);
	}

	@Override
	public double getValue() {
		// TODO Auto-generated method stub
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
