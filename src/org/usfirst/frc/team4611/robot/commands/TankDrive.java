package org.usfirst.frc.team4611.robot.commands;

import org.usfirst.frc.team4611.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class TankDrive extends Command{
	
	public TankDrive(){
		this.requires(Robot.tankDrive); //This command uses this subsystem
	}
	
	protected void execute() { //execute is called every 20 miliseconds
		double rightJoyVal = Robot.oi.filter(Robot.oi.rightJoy.getY()); //Grab the Y value of the joystick and pass 
		double leftJoyVal = Robot.oi.filter(Robot.oi.leftJoy.getY());; //it through the filter 
	    Robot.tankDrive.move(leftJoyVal, rightJoyVal); //Then pass that double to the method "move" in tankDrive
	  }
	
	@Override
	protected boolean isFinished() {
		return false; //Don't stop running this command 
	}

}
