package org.usfirst.frc.team4611.robot.subsystems;

import org.usfirst.frc.team4611.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Climber extends Subsystem {

	public void moveToPos(double position) {
		RobotMap.climber_Talon.config_kP(0, 0, 0);
		RobotMap.climber_Talon.config_kI(0, 0, 0);
		RobotMap.climber_Talon.config_kD(0, 0, 0);
		RobotMap.climber_Talon2.config_kP(0, 0, 0);
		RobotMap.climber_Talon2.config_kI(0, 0, 0);
		RobotMap.climber_Talon2.config_kD(0, 0, 0);
		
		RobotMap.climber_Talon.set(ControlMode.MotionMagic, position);
		RobotMap.climber_Talon2.set(ControlMode.MotionMagic, position);
	}
	
	@Override
	protected void initDefaultCommand() {
		
	}

}
