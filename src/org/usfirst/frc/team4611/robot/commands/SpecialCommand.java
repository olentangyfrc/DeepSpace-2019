package org.usfirst.frc.team4611.robot.commands;

import org.usfirst.frc.team4611.robot.RobotMap;
import org.usfirst.frc.team4611.robot.networking.INetworkTable;

import edu.wpi.first.wpilibj.command.Command;

public abstract class SpecialCommand extends Command implements INetworkTable{

	public void addValueToNetworkTable(String key, Object value) {
		if(RobotMap.networkManager.updateValue("NetworkTable", key, value)) {
			RobotMap.watchedValues.put(key, this);
		}
	}
	
	public void updateValue(String key, Object value) {
		RobotMap.networkManager.updateValue("NetworkTable", key, value);
	}
	public void onValueChanged(String key, Object newvalue) {
		
	}
}

