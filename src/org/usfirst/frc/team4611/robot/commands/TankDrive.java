package org.usfirst.frc.team4611.robot.commands;

import org.usfirst.frc.team4611.robot.Robot;
import org.usfirst.frc.team4611.robot.RobotMap;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.command.Command;

public class TankDrive extends Command{
	
	public TankDrive(){
		this.requires(Robot.tankDrive); //This command uses this subsystem
	}
	
	protected void execute() { //execute is called every 20 miliseconds
		double [] values = new double [2];
		values = calculateVals(values);
	    Robot.tankDrive.move(values[0], values[1]); //Then pass that double to the method "move" in tankDrive
	  }
	
	protected double [] calculateVals(double [] vals)
	{
		if(RobotMap.useXbox)
		{
			vals[0] = Robot.oi.filter(Robot.oi.controller.getY(GenericHID.Hand.kLeft));
			vals[1] = Robot.oi.filter(Robot.oi.controller.getY(GenericHID.Hand.kRight));
		}
		else
		{
			vals[0] = Robot.oi.filter(Robot.oi.leftJoy.getY());
			vals[1] = Robot.oi.filter(Robot.oi.rightJoy.getY());
		}
		return vals;
	}
	
	@Override
	protected boolean isFinished() {
		return false; //Don't stop running this command 
	}

}
