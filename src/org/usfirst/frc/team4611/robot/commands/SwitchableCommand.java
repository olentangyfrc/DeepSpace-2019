package org.usfirst.frc.team4611.robot.commands;

import org.usfirst.frc.team4611.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public abstract class SwitchableCommand extends Command{

	protected void execute() {
		//Checks to see the current value on the Shuffleboard
		if(!(boolean)RobotMap.getValue(RobotMap.switcherSubTable, RobotMap.switcherID)) {
			//If it's false, then it runs the victor-specific code
			this.executeVictor();
		}else {
			//If it's not equal to 0 (talon == 1), then it runs talon-specific code
			this.executeTalon();
		}
	}
	
	/**Used to implements talon-specific code*/
	public abstract void executeTalon();
	
	/**Used to implement victor-specific code*/
	public abstract void executeVictor();
	
}
