package org.usfirst.frc.team4611.robot.networking;

import org.usfirst.frc.team4611.robot.RobotMap;

import edu.wpi.first.networktables.EntryListenerFlags;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;

public class NetworkTableManager {

	private static NetworkTableInstance instance;
	private static NetworkTable table;
	
	public NetworkTableManager() {
		instance = NetworkTableInstance.getDefault();
		instance.setServer(RobotMap.networkTableServerAddress, RobotMap.teamID);
		
		instance.startServer();
		NetworkTableEntryListener manager = new NetworkTableEntryListener();
		
		table = instance.getTable(RobotMap.networkTableID);
		table.addEntryListener(manager, EntryListenerFlags.kUpdate);
	}
	public boolean updateValue(String subTable, String key, Object value) {
		boolean succ = table.getSubTable(subTable).getEntry(key).setValue(value);
		if(succ) {
			instance.flush();
		}
		return succ;
	}
	
	public Object getValue(String subTable, String key) {
		return table.getSubTable(subTable).getEntry(key).getValue().getValue();
	}
	
	public boolean alreadyHasKey(String subTable, String key) {
		return table.getSubTable(subTable).containsKey(key);
	}
	
	
}
