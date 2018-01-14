package org.usfirst.frc.team4611.robot.subsystems;

import org.usfirst.frc.team4611.robot.RobotMap;
import org.usfirst.frc.team4611.robot.commands.DriveMotor;

import edu.wpi.first.wpilibj.command.Subsystem;

public class MotorDriver extends Subsystem{

	private String subTable = "NetworkTable";
	
	public MotorDriver() {
		RobotMap.updateValue(subTable, "motorSpeed", RobotMap.motorSpeed);
		RobotMap.updateValue(subTable, "motor_enabled", true);
	}
	
	public void runMotor() {
		if((boolean)RobotMap.getValue(subTable, "motor_enabled")) {
			System.out.println((double)RobotMap.getValue(subTable, "motorSpeed"));
			RobotMap.motor.set((double)RobotMap.getValue(subTable, "motorSpeed"));
		}else {
			System.out.print("Setting motor to zero");
			RobotMap.motor.set(0);
		}
	}
	
	@Override
	protected void initDefaultCommand() {
		this.setDefaultCommand(new DriveMotor());
	}
}
