package org.usfirst.frc.team4611.robot.subsystems;

import org.usfirst.frc.team4611.robot.RobotMap;
import org.usfirst.frc.team4611.robot.commands.MoveElevator;
import org.usfirst.frc.team4611.robot.commands.drive.MecanumDrive;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Elevator extends Subsystem{
	
	public void move(double speed) {
		RobotMap.elevator_Talon.set(ControlMode.PercentOutput, speed);
	}
	
	public boolean isSwitchSet() {
		return RobotMap.limitSwitch.get() > 0;
	}
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		setDefaultCommand(new MoveElevator());
	}

}
