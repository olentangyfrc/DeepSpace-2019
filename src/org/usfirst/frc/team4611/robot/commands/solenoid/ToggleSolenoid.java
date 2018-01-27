package org.usfirst.frc.team4611.robot.commands.solenoid;

import org.usfirst.frc.team4611.robot.Robot;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class ToggleSolenoid extends CommandGroup{
	
	public ToggleSolenoid(){
		this.requires(Robot.sol);
		if( Robot.sol.isRetracted ) {
			addSequential( new ExtendSolenoid(), 2);
		}
		else{
			addSequential( new RetractSolenoid(), 2);
		}
	}

}
