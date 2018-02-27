package org.usfirst.frc.team4611.robot.commands.auton;

import org.usfirst.frc.team4611.robot.Robot;
import org.usfirst.frc.team4611.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.Command;

public class Rotate extends Command{
	int velocity;
	public Rotate(int vel) {
		this.requires(Robot.mecanum);
		this.velocity = vel;
	}
	
	protected void execute() {
	Robot.mecanum.rotate(velocity);
	}
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}
