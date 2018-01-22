package org.usfirst.frc.team4611.robot.commands;

import org.usfirst.frc.team4611.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SetDefault extends Command{

	private String name;
	private String key;
	public SetDefault(String name, String key) {
		this.name = name;
		this.key = key;
		this.setName(name + " " + key + " set default");
		SmartDashboard.putData(this);
	}
	
	public void execute() {
		double newval = (double)RobotMap.getValue(name, key);
		RobotMap.defaults.updateProperty(name, key, "" + newval);
	}
	
	@Override
	protected boolean isFinished() {
		return true;
	}
	
	
}
