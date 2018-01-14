package org.usfirst.frc.team4611.robot.subsystems;

import org.usfirst.frc.team4611.robot.RobotMap;
import org.usfirst.frc.team4611.robot.networking.INetworkTable;

import edu.wpi.first.wpilibj.command.Subsystem;

public abstract class SpecialSubsystem extends Subsystem implements INetworkTable{
	
	/**
	 * Updates or adds a new value to the NetworkTable in a subtable based
	 * on the LoggerType name
	 * @param key The identifer for the value
	 * @param value The value of the key
	 */
	public void updateValue(String key, Object value) {
		//Checks to see if this key has already been used
		if(!RobotMap.watchedValues.containsKey(key)) {
			
			//If it hasn't, it first makes sure that the NetworkTable adds this value successfully
			if(RobotMap.networkManager.updateValue("NetworkTable", key, value)) {
				
				//Once added successfully, it links this class and the key for future updates
				RobotMap.watchedValues.put(key, this);
			}else {
				
				//If it's unsuccessful, it logs that there was a problem
			}
			//If it has been used before, it ensures that it successfully updates the value
		}else if(!RobotMap.networkManager.updateValue("NetworkTable", key, value)){
			
			//If it's unsuccessful, it logs there was a problem
		}
	}
	
	/**
	 * Called by the NetworkTableEntryListener when a value that is linked with
	 * this class through the watchedValues HashMap has been changed on the NetworkTable
	 * 
	 * **MUST BE IMPLEMENTED BY EACH COMMAND AS EACH ONE WILL HAVE
	 * DIFFERENT VARIABLES TO UPDATE
	 */
	public void onValueChanged(String key, Object newvalue) {
		
	}
}

