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
	
	public void moveToPos(double position) {
		RobotMap.elevator_Talon.config_kP(0, 1, 0);
		RobotMap.elevator_Talon.config_kI(0, 0, 0);
		RobotMap.elevator_Talon.config_kD(0, 0, 0);
		RobotMap.elevator_Talon.config_kF(0, 0, 0);
		
		RobotMap.updateValue(RobotMap.elevatorSubtable, RobotMap.SensorVelocityID, 4096);
		RobotMap.elevator_Talon.configMotionCruiseVelocity(4096, 0);
		RobotMap.updateValue(RobotMap.elevatorSubtable, RobotMap.SensorAccelID, 4096);
		RobotMap.elevator_Talon.configMotionAcceleration(4096, 0);
		RobotMap.elevator_Talon.set(ControlMode.MotionMagic, position);
	}
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		setDefaultCommand(new MoveElevator());
	}
	
	public boolean isSwitchSet() { //if switch is pressed, returns true
		return RobotMap.limitSwitch.get();
	}

}
