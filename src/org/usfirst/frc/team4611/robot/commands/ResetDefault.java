package org.usfirst.frc.team4611.robot.commands;

import org.usfirst.frc.team4611.robot.RobotMap;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ResetDefault extends Command{

	private String subTable;
	private String key;
	public ResetDefault(String sub, String k) {
		subTable = sub;
		key = k;
		this.setName(subTable + " " + key + " reset");
		SmartDashboard.putData(this);
	}
	
	protected void execute() {
		double val = RobotMap.defaults.getDoubleDefaultValue(subTable, key, 0);
		RobotMap.updateValue(subTable, key, val);
	}
	@Override
	protected boolean isFinished() {
		return true;
	}

}
