package org.usfirst.frc.team4611.robot.commands;

import org.usfirst.frc.team4611.robot.Robot;
import org.usfirst.frc.team4611.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class MecanumDrive extends Command{
	
	public MecanumDrive(){
		this.requires(Robot.tankDrive); //This command uses this subsystem
	}
	
	protected void execute() { //execute is called every 20 miliseconds
		double YVal = Robot.oi.filter(Robot.oi.leftJoy.getY()); //Grab the Y value of the joystick and pass 
		double XVal = Robot.oi.filter(Robot.oi.leftJoy.getX());//it through the filter
		double ZVal = Robot.oi.filter(Robot.oi.rightJoy.getX());
		
		RobotMap.updateValue("Joysticks", RobotMap.leftJoyXID, Robot.oi.leftJoy.getY());
		RobotMap.updateValue("Joysticks", RobotMap.leftJoyYID, Robot.oi.leftJoy.getY());
		RobotMap.updateValue("Joysticks", RobotMap.leftJoyZID, Robot.oi.leftJoy.getZ());
		RobotMap.updateValue("Joysticks", RobotMap.rightJoyXID, Robot.oi.rightJoy.getX());
		RobotMap.updateValue("Joysticks", RobotMap.rightJoyYID, Robot.oi.rightJoy.getY());
		RobotMap.updateValue("Joysticks", RobotMap.rightJoyZID, Robot.oi.rightJoy.getZ());
		
		double finalZVal= ZVal* 180;
		//Boosts the strafe to be equal to the speed of going forward or backwards
		if(XVal < 0) {
			XVal = Math.min(-XVal*2, 1);
		}else if(XVal > 0){
			XVal = Math.min(XVal*2, -1);
		}
	    Robot.tankDrive.move(YVal, XVal, -ZVal); 
	    System.out.println("YVal: "+YVal);
	    System.out.println("XVal: "+XVal);
	    System.out.println("ZVal: "+ZVal);
	    //Then pass that double to the method "move" in tankDrive
	   // Robot.tankDrive.movePolar(XVal,finalZVal, YVal);
	
	}
	
	@Override
	protected boolean isFinished() {
		return false; //Don't stop running this command 
	}

}
