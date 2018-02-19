package org.usfirst.frc.team4611.robot.subsystems;

import org.usfirst.frc.team4611.robot.Robot;

import edu.wpi.first.wpilibj.command.Subsystem;

public class FancyLights extends Subsystem{
	/**
	 *  See page 14 http://www.revrobotics.com/content/docs/REV-11-1105-UM.pdf to see all spark values
	 */

	protected void initDefaultCommand() {
	}
	
	public void makeRed(){
		Robot.lightController.set(0.61);
	}
	
	public void makeYellow(){
		Robot.lightController.set(0.69);
	}
	
	public void makeGreen(){
		Robot.lightController.set(0.77);
	}
	
	public void makeCyan(){
		Robot.lightController.set(0.79);
	}
	
	public void makeBlue(){
		Robot.lightController.set(0.87);
	}
	
	public void makePurple(){
		Robot.lightController.set(0.91);
	}
	
	
	public void makeWhite(){
		Robot.lightController.set(0.93);
	}
	
	public void makeOff(){
		//Robot.lights1.disable();
	}

}
