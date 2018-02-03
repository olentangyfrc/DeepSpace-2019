package org.usfirst.frc.team4611.robot.subsystems;

import org.usfirst.frc.team4611.robot.RobotMap;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Arm extends Subsystem {
	
	public void stopPot() {
		RobotMap.linearActuator.set(0);
	}
	
	public void movePotUp(double speed) {
		System.out.println("Pot reading: "+ RobotMap.linearActuatorPot.get());
		System.out.println("Value: "+ RobotMap.getValue(RobotMap.potentiometerSubTable, RobotMap.potMaxID));
		if(RobotMap.linearActuatorPot.get() < (double)RobotMap.getValue(RobotMap.potentiometerSubTable, RobotMap.potMaxID)) {
			//RobotMap.linearActuator.set(RobotMap.linearActuatorControl, speed);
			RobotMap.linearActuator.set(speed);
		}
		else {
			//RobotMap.linearActuator.set(RobotMap.linearActuatorControl, 0);
			RobotMap.linearActuator.set(0);
		}		
	}
	
	public void movePotSwitch(double speed) {
		if(RobotMap.linearActuatorPot.get() < (double)RobotMap.getValue(RobotMap.potentiometerSubTable, RobotMap.potSwitchID)) {
			//RobotMap.linearActuator.set(RobotMap.linearActuatorControl, speed);
			RobotMap.linearActuator.set(speed);
		}
		else {
			//RobotMap.linearActuator.set(RobotMap.linearActuatorControl, 0);
			RobotMap.linearActuator.set(0);
		}
	}
	
	public void movePotDown(double speed) {
		System.out.println("Pot reading: "+ RobotMap.linearActuatorPot.get());
		System.out.println("Value: "+ RobotMap.getValue(RobotMap.potentiometerSubTable, RobotMap.potMinID));
		if(RobotMap.linearActuatorPot.get() > (double)RobotMap.getValue(RobotMap.potentiometerSubTable, RobotMap.potMinID)) {
			//talon and victor code
			//RobotMap.linearActuator.set(RobotMap.linearActuatorControl, speed);
			RobotMap.linearActuator.set(-speed);
		}
		else {
			//talon and victor code
			//RobotMap.linearActuator.set(RobotMap.linearActuatorControl, 0);
			RobotMap.linearActuator.set(0);
		}
	}

	@Override
	protected void initDefaultCommand() {	
	}

}
