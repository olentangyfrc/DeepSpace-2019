package org.usfirst.frc.team4611.robot.commands;

import org.usfirst.frc.team4611.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SetDefault extends Command{

	//The subtable the value is saved on
	private String subTable;
		
	//The key the value is paired with
	private String key;
		
	public SetDefault(String name, String key) {
		this.subTable = name;
		this.key = key;
		
		//Sets the name that is viewed on the Shuffleboard
		this.setName(subTable + " " + key + " reset");
				
		//Sends the command to Shuffleboard to be used
		SmartDashboard.putData(this);
	}
	
	/**
	 * Called when the command button is pressed on the Shuffleboard
	 */
	public void execute() {
		//The new value that is retrieved from the Shuffleboard
		double newval = (double)RobotMap.getValue(subTable, key);
		
		//Overrides the currently saved value with the value retrieved from the Shuffleboard
		RobotMap.defaults.updateProperty(subTable, key, "" + newval);
	}
	
	@Override
	protected boolean isFinished() {
		return true;
	}
	
	
}
