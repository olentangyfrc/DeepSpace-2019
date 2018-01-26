package org.usfirst.frc.team4611.robot.commands;

import org.usfirst.frc.team4611.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ResetDefault extends Command{

	//The subtable the value is saved on
	private String subTable;
	
	//The key the value is paired with
	private String key;
	
	public ResetDefault(String sub, String k) {
		subTable = sub;
		key = k;
		
		//Sets the name that is viewed on the Shuffleboard
		this.setName(subTable + " " + key + " reset");
		
		//Sends the command to Shuffleboard to be used
		SmartDashboard.putData(this);
	}
	
	/**
	 * Called when the command button is pressed on the Shuffleboard.
	 * Resets the value to the currently saved value on the RoboRio
	 */
	protected void execute() {
		//The value that is currently saved on the Rio, or zero if it doesn't exist
		double val = RobotMap.defaults.getDoubleDefaultValue(subTable, key, 0);
		
		//Updates the value on the networktable with the saved value
		RobotMap.updateValue(subTable, key, val);
	}
	@Override
	protected boolean isFinished() {
		return true;
	}

}
