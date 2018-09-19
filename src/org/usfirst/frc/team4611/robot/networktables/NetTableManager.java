package org.usfirst.frc.team4611.robot.networktables;

import java.util.HashMap;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;

public class NetTableManager {
	
	private static NetworkTableInstance instance;
	private static NetworkTable table;
	
	private static final int teamID = 4611;
	private static final String netTableServerAdd = "10.46.11.2";
	private static final String netTableID = "Ozone Values";
	
	public static void startNetworkTables() {
		instance = NetworkTableInstance.getDefault();
		
		instance.setServer(netTableServerAdd, teamID);
		instance.startServer();
		
		table = instance.getTable(netTableID);
	}
	
	/**
	 * Updates a value in the NetworkTable. If the value doesn't exists, it creates
	 * the value in the specified subtable
	 * @param subTable The subtable it will be set in (WILL BE DETERMINED BY LOGGERTYPE IN THE FUTURE)
	 * @param key The key that the value will be identified as
	 * @param value The value to be saved/binded with the key
	 * @return If it was successful in updating/creating the value
	 */
	public static void updateValue(String subTable, String key, Object value) {		
		//Updates the value and returns a boolean that tells if it was successful
		if(table.getSubTable(subTable).getEntry(key).setValue(value)) {
			System.out.println("Successfully sent value: " + value + " to Shuffleboard with SubTable: " + subTable + " and key: " + key);
		}else {
			System.out.println("Unable to send value: " + value + " to Shuffleboard with SubTable: " + subTable + " and key: " + key);

		}
	}
	
	public static void updateValues(String subTable, HashMap<String, Object> objects) {
		for(Object key : objects.keySet().toArray()) {
			updateValue(subTable, (String)key, objects.get(key));
		}
	}
	
	/**
	 * Gets a value that is linked with the given key
	 * @param subTable The subtable the value is located on
	 * @param key The identifier that is linked with the desired value
	 * @return The object that is linked with the given key
	 */
	public static Object getValue(String subTable, String key, Object defaultVal) {
		if(table.getSubTable(subTable).getEntry(key).getValue().getValue() == null) {
			updateValue(subTable, key, defaultVal);
			return defaultVal;
		}
		return table.getSubTable(subTable).getEntry(key).getValue().getValue();
	}
	
	/**
	 * Checks to see if a given key in a subtable already exists
	 * @param subTable The subtable to be checked
	 * @param key the key to be checked
	 * @return if the key in the given subtable already exists
	 */
	public static boolean alreadyHasKey(String subTable, String key) {
		return table.getSubTable(subTable).containsKey(key);
	}
}
