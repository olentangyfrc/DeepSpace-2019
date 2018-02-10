package org.usfirst.frc.team4611.robot.subsystems;

import org.usfirst.frc.team4611.robot.RobotMap;
import org.usfirst.frc.team4611.robot.commands.elevator.MoveElevator;
import org.usfirst.frc.team4611.robot.commands.drive.MecanumDrive;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Elevator extends Subsystem{
	
	public void move(double speed) {
		RobotMap.elevator_Talon.set(ControlMode.PercentOutput, speed);
	}
	/*
	 * The first step for Motion Magic is sett
	 * ing up your position PIDF values 
	 * as normal (see the Soft. Ref. Manual). You then use two additional calls 
	 * (setMotionMagicAcceleration(double) and setMotionMagicCruiseVelocity(double)) 
	 * to set the desired acceleration and cruise velocity parameters.
	Once this has been done, you can use set() calls as normal to set the position 
	that Talon will move to, and the motion will follow a trapezoidal motion profile 
	(as described in the motion profiling documentation).
	 */
	public void moveToPos(double position) {
		RobotMap.elevator_Talon.config_kP(0, 0.001, 0);
		RobotMap.elevator_Talon.config_kI(0, 0, 0);
		RobotMap.elevator_Talon.config_kD(0, 0, 0);
		RobotMap.elevator_Talon.config_kF(0, 0, 0);
		
		RobotMap.updateValue(RobotMap.elevatorSubtable, RobotMap.SensorVelocityID, 4096);
		RobotMap.elevator_Talon.configMotionCruiseVelocity((int)((double)RobotMap.getValue(RobotMap.elevatorSubtable, RobotMap.SensorVelocityID)), 0);
		RobotMap.updateValue(RobotMap.elevatorSubtable, RobotMap.SensorAccelID, 4096);
		RobotMap.elevator_Talon.configMotionAcceleration((int)((double)RobotMap.getValue(RobotMap.elevatorSubtable, RobotMap.SensorAccelID)), 0);

		RobotMap.elevator_Talon.set(ControlMode.MotionMagic, position);
	}
	
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		setDefaultCommand(new MoveElevator());
	}
	
	public boolean isSwitchSet() {
		return RobotMap.limitSwitch.get() > 0;
	}

}
