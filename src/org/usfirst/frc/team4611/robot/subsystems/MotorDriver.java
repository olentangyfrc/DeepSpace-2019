package org.usfirst.frc.team4611.robot.subsystems;

import org.usfirst.frc.team4611.robot.RobotMap;
import org.usfirst.frc.team4611.robot.commands.DriveMotor;

public class MotorDriver extends SpecialSubsystem{

	public MotorDriver() {
		this.updateValue("motorSpeed", RobotMap.motorSpeed);
		this.updateValue("motor_enabled", true);
	}
	
	public void runMotor() {
		if((boolean)this.getValue("motor_enabled")) {
			System.out.println((double)this.getValue("motorSpeed"));
			RobotMap.motor.set((double)this.getValue("motorSpeed"));
		}else {
			System.out.print("Setting motor to zero");
			RobotMap.motor.set(0);
		}
	}
	
	@Override
	protected void initDefaultCommand() {
		this.setDefaultCommand(new DriveMotor());
	}
	
	public void onValueChanged(String key, Object newvalue) {
		if(key.equals("motorSpeed") && newvalue instanceof Double) {
			RobotMap.motorSpeed = (double)newvalue;
		}
	}
}
