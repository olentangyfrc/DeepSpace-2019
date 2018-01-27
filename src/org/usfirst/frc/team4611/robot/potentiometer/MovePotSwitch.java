package org.usfirst.frc.team4611.robot.potentiometer;

import org.usfirst.frc.team4611.robot.OI;
import org.usfirst.frc.team4611.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.Command;

public class MovePotSwitch extends Command{
	
	private double speed;
	
	public MovePotSwitch(double speed) {
		// TODO Auto-generated constructor stub
		this.speed = speed;
	}

	protected void execute() {
		//System.out.println("LA speed: " + -speed);
		if(RobotMap.linearActuatorPot.get() < RobotMap.potSwitch) {
			//RobotMap.linearActuator.set(RobotMap.linearActuatorControl, speed);
			RobotMap.linearActuator.set(speed);
		}
		else {
			//RobotMap.linearActuator.set(RobotMap.linearActuatorControl, 0);
			RobotMap.linearActuator.set(0);
		}
		
	}
	
	
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}
