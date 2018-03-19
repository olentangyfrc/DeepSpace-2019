package org.usfirst.frc.team4611.robot.commands.arm;

import org.usfirst.frc.team4611.robot.Robot;
import org.usfirst.frc.team4611.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class MovePot extends Command{
	
	private double varianceLimit = (double)RobotMap.getValue(RobotMap.potentiometerSubTable, RobotMap.varianceLimitID);
	
	public MovePot() {
		this.requires(Robot.arm);
	}
	
	protected void execute() {
		double y = Robot.oi.LAFilter(Robot.oi.auxJoy.getY());
		
		if (y > 0) {
			Robot.arm.moveArmUp(y, y);
		}
		else {
			Robot.arm.moveArmDown(-y, -y);
		}
	}
	
	

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}
