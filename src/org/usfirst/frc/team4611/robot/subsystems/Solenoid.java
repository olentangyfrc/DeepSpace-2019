package org.usfirst.frc.team4611.robot.subsystems;

import org.usfirst.frc.team4611.robot.RobotMap;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Solenoid extends Subsystem{
	public boolean isRetracted;

	@Override
	protected void initDefaultCommand() {
		//this.setDefaultCommand(new RetractSolenoid());
		isRetracted = true;
	}
	
	public void move(Value v) {
		RobotMap.sol.set(v);
	}
	
}
