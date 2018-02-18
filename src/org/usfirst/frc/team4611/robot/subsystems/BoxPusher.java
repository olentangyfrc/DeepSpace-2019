package org.usfirst.frc.team4611.robot.subsystems;

import org.usfirst.frc.team4611.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

public class BoxPusher extends Subsystem{

	public BoxPusher() {
		
	}

	public void move(Value v) {
		RobotMap.boxPusher.set(v);
	}
	
	@Override
	protected void initDefaultCommand() {
		
	}
	
}