
package org.usfirst.frc.team4611.robot.subsystems;

import org.usfirst.frc.team4611.robot.RobotMap;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Arm extends Subsystem {
	
	private double varianceLimit = (double)RobotMap.getValue(RobotMap.potentiometerSubTable, RobotMap.varianceLimitID);
	
	public void stopPot() {
		RobotMap.linearActuator.set(0);
		RobotMap.linearActuator2.set(0);
	}
	
	public void movePotUp(double speed, double speed2) {		
		double max1 = (double)RobotMap.getValue(RobotMap.potentiometerSubTable, RobotMap.potMaxID);
		double min1 = (double)RobotMap.getValue(RobotMap.potentiometerSubTable, RobotMap.potMinID);
		double max2 = (double)RobotMap.getValue(RobotMap.potentiometerSubTable, RobotMap.potMax2ID);
		double min2 = (double)RobotMap.getValue(RobotMap.potentiometerSubTable, RobotMap.potMin2ID);
		double potValue1 = RobotMap.linearActuatorPot.get();
		double potValue2 = RobotMap.linearActuatorPot2.get();
		double pos1 = (potValue1 - min1) / (max1 - min1);
		double pos2 = (potValue2 - min2) / (max2 - min2);
		
		if(potValue1 < max1 && pos1 <= pos2 + varianceLimit) {
			RobotMap.linearActuator.set(speed);
		}
		else {
			RobotMap.linearActuator.set(0);
			
		}
		if(potValue2 < max2 && pos2 <= pos1 + varianceLimit) {
			RobotMap.linearActuator2.set(speed2);
		}
		else {
			RobotMap.linearActuator2.set(0);
		}
	}
	
	
	
	public void movePotDown(double speed, double speed2) {
		double max1 = (double)RobotMap.getValue(RobotMap.potentiometerSubTable, RobotMap.potMaxID);
		double min1 = (double)RobotMap.getValue(RobotMap.potentiometerSubTable, RobotMap.potMinID);
		double max2 = (double)RobotMap.getValue(RobotMap.potentiometerSubTable, RobotMap.potMax2ID);
		double min2 = (double)RobotMap.getValue(RobotMap.potentiometerSubTable, RobotMap.potMin2ID);
		double potValue1 = RobotMap.linearActuatorPot.get();
		double potValue2 = RobotMap.linearActuatorPot2.get();
		double pos1 = (potValue1 - min1) / (max1 - min1);
		double pos2 = (potValue2 - min2) / (max2 - min2);
		
		if((potValue1 > min1 && pos1 >= pos2 - varianceLimit)) {
			RobotMap.linearActuator.set(-speed);
		}
		else {
			RobotMap.linearActuator.set(0);
		}
		if(potValue2 > min2 && pos2 >= pos1 - varianceLimit) {
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
