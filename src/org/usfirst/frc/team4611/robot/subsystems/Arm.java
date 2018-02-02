package org.usfirst.frc.team4611.robot.subsystems;

import org.usfirst.frc.team4611.robot.RobotMap;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Arm extends Subsystem {
	
	public void stopPot() {
		RobotMap.linearActuator.set(0);
		RobotMap.linearActuator2.set(0);
	}
	
	public void movePotUp(double speed, double speed2) {
		if((RobotMap.linearActuatorPot.get() < (double)RobotMap.getValue(RobotMap.potentiometerSubTable, RobotMap.potMaxID))) {
			//RobotMap.linearActuator.set(RobotMap.linearActuatorControl, speed);
			RobotMap.linearActuator.set(speed);
		}
		else {
			//RobotMap.linearActuator.set(RobotMap.linearActuatorControl, 0);
			RobotMap.linearActuator.set(0);
			
		}
		if((RobotMap.linearActuatorPot2.get() < (double)RobotMap.getValue(RobotMap.potentiometerSubTable, RobotMap.potMax2ID))) {
			RobotMap.linearActuator2.set(speed2);
		}
		else {
			RobotMap.linearActuator2.set(0);
		}
	}
	
	public void movePotSwitch(double speed, double speed2) {
		if((RobotMap.linearActuatorPot.get() < (double)RobotMap.getValue(RobotMap.potentiometerSubTable, RobotMap.potSwitch2ID))) {
			//RobotMap.linearActuator.set(RobotMap.linearActuatorControl, speed);
			RobotMap.linearActuator.set(speed);
			
		}
		else {
			//RobotMap.linearActuator.set(RobotMap.linearActuatorControl, 0);
			RobotMap.linearActuator.set(0);
			
		}
		if((RobotMap.linearActuatorPot2.get() < (double)RobotMap.getValue(RobotMap.potentiometerSubTable, RobotMap.potSwitchID))) {
			RobotMap.linearActuator2.set(speed2);
		}
		else {
			RobotMap.linearActuator2.set(0);
		}
	}
	
	public void movePotDown(double speed, double speed2) {
		if((RobotMap.linearActuatorPot.get() > (double)RobotMap.getValue(RobotMap.potentiometerSubTable, RobotMap.potMinID))) {
			//talon and victor code
			//RobotMap.linearActuator.set(RobotMap.linearActuatorControl, speed);
			RobotMap.linearActuator.set(-speed);
		}
		else {
			//talon and victor code
			//RobotMap.linearActuator.set(RobotMap.linearActuatorControl, 0);
			RobotMap.linearActuator.set(0);
		}
		if(RobotMap.linearActuatorPot2.get() > (double)RobotMap.getValue(RobotMap.potentiometerSubTable, RobotMap.potMin2ID)) {
				RobotMap.linearActuator2.set(-speed2);
		}
		else {
			RobotMap.linearActuator2.set(0);
		}
	}

	@Override
	protected void initDefaultCommand() {	
	}

}
