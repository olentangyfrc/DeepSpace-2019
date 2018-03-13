package org.usfirst.frc.team4611.robot.subsystems;

import org.usfirst.frc.team4611.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Climber extends Subsystem {

	/** Moves the climber at set speed 
	 * 
	 * @param speed Desired speed in position units per 100 ms.
	 */
	public void move(double speed) {	
		RobotMap.climber_Talon.config_kP(0, .5, 0);
		RobotMap.climber_Talon.config_kI(0, 0, 0);
		RobotMap.climber_Talon.config_kD(0, 0, 0);
		
		RobotMap.climber_Talon2.config_kP(0, .5, 0);
		RobotMap.climber_Talon2.config_kI(0, 0, 0);
		RobotMap.climber_Talon2.config_kD(0, 0, 0);
		
		RobotMap.climber_Talon.setInverted(false);
		RobotMap.climber_Talon2.setInverted(true);
		
		RobotMap.climber_Talon.set(ControlMode.Velocity, speed);
		RobotMap.climber_Talon2.set(ControlMode.Follower, 31);		
	}
	
	/** Moves the climber to desired position
	 * 
	 * @param position Desired position in native encoder units
	 */
	public void moveToPos(double position) {
		RobotMap.climber_Talon.config_kP(0, 5, 0);
		RobotMap.climber_Talon.config_kI(0, 0, 0);
		RobotMap.climber_Talon.config_kD(0, 0, 0);
		
		RobotMap.climber_Talon2.config_kP(0, 5, 0);
		RobotMap.climber_Talon2.config_kI(0, 0, 0);
		RobotMap.climber_Talon2.config_kD(0, 0, 0);
		
		RobotMap.climber_Talon2.configMotionAcceleration(100000, 0);
		RobotMap.climber_Talon2.configMotionCruiseVelocity(100000, 0);
		
		RobotMap.climber_Talon2.set(ControlMode.MotionMagic, position);
		RobotMap.climber_Talon.set(ControlMode.Follower, 32);
		RobotMap.climber_Talon.setInverted(true);
	}
	
	@Override
	protected void initDefaultCommand() {
		
	}

}
