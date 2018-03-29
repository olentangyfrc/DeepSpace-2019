
package org.usfirst.frc.team4611.robot.subsystems;

import org.usfirst.frc.team4611.robot.RobotMap;
import org.usfirst.frc.team4611.robot.commands.arm.MovePot;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Arm extends Subsystem {
	
	private double varianceLimit = (double)RobotMap.getValue(RobotMap.potentiometerSubTable, RobotMap.varianceLimitID);
	
	public void stopPot() {
		RobotMap.linearActuator.set(0);
		RobotMap.linearActuator2.set(0);
	}
	
	/** Percent power passed to the linear actuators
	 * 
	 * <p> Negative values = Lowering LAs. Positive values = Raising LAs
	 * 
	 * @param speed  Speed passed to linear actuator 1 based in percent voltage
	 * @param speed2 Speed passed to linear actuator 1 based in percent voltage
	 */
	public void moveArmUp(double speed, double speed2) {		
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
	
	
	/** Percent power passed to the linear actuators. Included for logic discontinuity.
	 *  
	 *  <p> Negative values = Lowering LAs. Positive values = Raising LAs
	 * 
	 * @param speed  Speed passed to linear actuator 1 based in percent voltage
	 * @param speed2 Speed passed to linear actuator 1 based in percent voltage
	 */
	public void moveArmDown(double speed, double speed2) {
		double max1 = (double)RobotMap.getValue(RobotMap.potentiometerSubTable, RobotMap.potMaxID);
		double min1 = (double)RobotMap.getValue(RobotMap.potentiometerSubTable, RobotMap.potMinID);
		double max2 = (double)RobotMap.getValue(RobotMap.potentiometerSubTable, RobotMap.potMaxID);
		double min2 = (double)RobotMap.getValue(RobotMap.potentiometerSubTable, RobotMap.potMinID);
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
	
	/** Moves LAs to posiiton passed
	 * 
	 * @param pos The position desired. Pos is measured in [0.0-1.0] with limits placed on LAs
	 */
	public void movePotPos(double pos) {
		double max1 = (double)RobotMap.getValue(RobotMap.potentiometerSubTable, RobotMap.potMaxID);
		double min1 = (double)RobotMap.getValue(RobotMap.potentiometerSubTable, RobotMap.potMinID);
		double max2 = (double)RobotMap.getValue(RobotMap.potentiometerSubTable, RobotMap.potMax2ID);
		double min2 = (double)RobotMap.getValue(RobotMap.potentiometerSubTable, RobotMap.potMin2ID);
		double potValue1 = RobotMap.linearActuatorPot.get();
		double potValue2 = RobotMap.linearActuatorPot2.get();
		double pos1 = (potValue1 - min1) / (max1 - min1);
		double pos2 = (potValue2 - min2) / (max2 - min2);
		
		if(RobotMap.linearActuatorPot.get() > pos) {
			if((potValue1 > min1 && pos1 >= pos2 - varianceLimit)) {
				RobotMap.linearActuator.set(-.7);
			}
			else {
				RobotMap.linearActuator.set(0);
			}
			if(potValue2 > min2 && pos2 >= pos1 - varianceLimit) {
					RobotMap.linearActuator2.set(-.7);
			}
			else {
				RobotMap.linearActuator2.set(0);
			}
		}
		
		else if (RobotMap.linearActuatorPot.get() < pos) {
			if(potValue1 < max1 && pos1 <= pos2 + varianceLimit) {
				RobotMap.linearActuator.set(.7);
			}
			else {
				RobotMap.linearActuator.set(0);
			}
			if(potValue2 < max2 && pos2 <= pos1 + varianceLimit) {
				RobotMap.linearActuator2.set(.7);
			}
			else {
				RobotMap.linearActuator2.set(0);
			}
		}
		
		else {
			RobotMap.linearActuator.set(0);
			RobotMap.linearActuator2.set(0);
		}
		
		}
	
	@Override
	protected void initDefaultCommand() {	
		setDefaultCommand(new MovePot());
	}

}
