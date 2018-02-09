package org.usfirst.frc.team4611.robot.subsystems;

import org.usfirst.frc.team4611.robot.Robot;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Subsystem;

public class FancyLights extends Subsystem{
	
	/*	kOff - Turns both relay outputs off
	kForward - Sets the relay to forward (M+ @ 12V, M- @ GND)
	kReverse - Sets the relay to reverse (M+ @ GND, M- @ 12V)
	kOn - Sets both relay outputs on (M+ @ 12V, M- @ 12V).
	 */
	
	/**
	 *  M- for fancyLights1 must be powered for any lights to turn on
	 *  Each color has a specific pin to be grounded to turn on
	 *  Colors are red, green, blue
	 *  If all 3 are grounded lights = white
	 *  The colors that need to be off should be powered
	 *  
	 *  M+ on fancyLights1 is Green
	 *  M- on fancyLights2 is Red
	 *  M+ on fancyLights2 is Blue
	 */

	protected void initDefaultCommand() {
	}
	
	public void makeRed(){
		Robot.lights1.set(Relay.Value.kOn);
		Robot.lights2.set(Relay.Value.kForward);
	}
	
	public void makeYellow(){
		Robot.lights1.set(Relay.Value.kReverse);
		Robot.lights2.set(Relay.Value.kForward);
	}
	
	public void makeGreen(){
		Robot.lights1.set(Relay.Value.kReverse);
		Robot.lights2.set(Relay.Value.kOn);
	}
	
	public void makeCyan(){
		Robot.lights1.set(Relay.Value.kReverse);
		Robot.lights2.set(Relay.Value.kReverse);
	}
	
	public void makeBlue(){
		Robot.lights1.set(Relay.Value.kOn);
		Robot.lights2.set(Relay.Value.kReverse);
	}
	
	public void makePurple(){
		Robot.lights1.set(Relay.Value.kOn);
		Robot.lights2.set(Relay.Value.kOff);
	}
	
	
	public void makeWhite(){
		Robot.lights1.set(Relay.Value.kReverse);
		Robot.lights2.set(Relay.Value.kOff);
	}
	
	public void makeOff(){
		Robot.lights1.set(Relay.Value.kOff);
		Robot.lights2.set(Relay.Value.kOff);
		
}

}
