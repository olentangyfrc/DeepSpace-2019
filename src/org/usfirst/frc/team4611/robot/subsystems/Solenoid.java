package org.usfirst.frc.team4611.robot.subsystems;

import org.usfirst.frc.team4611.robot.RobotMap;
import org.usfirst.frc.team4611.robot.commands.solenoid.RetractSolenoid;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Solenoid extends Subsystem{

	@Override
	protected void initDefaultCommand() {
		this.setDefaultCommand(new RetractSolenoid());
	}
	
	public void move(Value v) {
		RobotMap.sol.set(v);
	}
}
