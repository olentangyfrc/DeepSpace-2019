package org.usfirst.frc.team4611.robot.subsystems;

import org.usfirst.frc.team4611.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

public class BoxPusher extends Subsystem{

	/** Moves the pusher to set direction
	 * 
	 * @param dir Desired position based on [DoubleSolenoid.Value]
	 */
	public void move(Value  dir) {
		RobotMap.boxPusher.set(dir);
	}
	
	@Override
	protected void initDefaultCommand() {
		
	}
	
}
