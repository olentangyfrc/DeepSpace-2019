package org.usfirst.frc.team4611.robot.networking;

import org.usfirst.frc.team4611.robot.RobotMap;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableValue;
import edu.wpi.first.networktables.TableEntryListener;

public class NetworkTableEntryListener implements TableEntryListener{
	
	@Override
	public void valueChanged(NetworkTable table, String key, NetworkTableEntry entry, NetworkTableValue value,
			int flags) {
		if(RobotMap.watchedValues.containsKey(key)) {
			RobotMap.watchedValues.get(key).onValueChanged(key, value);
		}	
	}

	
	
}
