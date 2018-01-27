package org.usfirst.frc.team4611.robot.subsystems;

import org.usfirst.frc.team4611.robot.RobotMap;
import org.usfirst.frc.team4611.robot.commands.TankDrive;

import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveTrain extends Subsystem {

	public void move(double left, double right) { //Grabs the left and right values that get passed by "TankDrive"
		 RobotMap.driveTrain.tankDrive(-left, -right); //Use those values for the method "tankDrive" which calls for joystick values
	}
	
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new TankDrive()); //This subsystem will automatically run this command 
	}
}
