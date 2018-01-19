package org.usfirst.frc.team4611.robot.subsystems;

import org.usfirst.frc.team4611.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Elevator extends Subsystem {
	
	public static void genericMovement(double speed) {
		RobotMap.elevatorMotor.set(speed);
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}

}
