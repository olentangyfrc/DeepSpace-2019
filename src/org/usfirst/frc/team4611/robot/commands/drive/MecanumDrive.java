package org.usfirst.frc.team4611.robot.commands.drive;

import org.usfirst.frc.team4611.robot.Robot;
import org.usfirst.frc.team4611.robot.RobotMap;
import org.usfirst.frc.team4611.robot.commands.SwitchableCommand;

import com.ctre.phoenix.motorcontrol.ControlMode;

public class MecanumDrive extends SwitchableCommand{
	
	public MecanumDrive(){
		this.requires(Robot.mecanum); //This command uses this subsystem
	}
	
	protected void execute() {
		double YVal = Robot.oi.filter(Robot.oi.leftJoy.getY()); //Grab the Y value of the joystick and pass 
		double XVal = Robot.oi.strafeFilter(Robot.oi.leftJoy.getX());//it through the filter
		double ZVal = Robot.oi.filter(Robot.oi.rightJoy.getX());
		double YAbs = Math.abs(YVal);
		double ZAbs = Math.abs(ZVal);
		double XAbs = Math.abs(XVal);
		double maxJoyChange = 0.1;

		RobotMap.updateValue(RobotMap.joyStickSubTable, RobotMap.leftJoyXID, Robot.oi.leftJoy.getY());
		RobotMap.updateValue(RobotMap.joyStickSubTable, RobotMap.leftJoyYID, Robot.oi.leftJoy.getY());
		RobotMap.updateValue(RobotMap.joyStickSubTable, RobotMap.leftJoyZID, Robot.oi.leftJoy.getZ());
		RobotMap.updateValue(RobotMap.joyStickSubTable, RobotMap.rightJoyXID, Robot.oi.rightJoy.getX());
		RobotMap.updateValue(RobotMap.joyStickSubTable, RobotMap.rightJoyYID, Robot.oi.rightJoy.getY());
		RobotMap.updateValue(RobotMap.joyStickSubTable, RobotMap.rightJoyZID, Robot.oi.rightJoy.getZ());
		
		if ((boolean) RobotMap.getValue(RobotMap.mecanumSubTable, RobotMap.switcherID)) {
		if (YAbs >= maxJoyChange && XAbs <= maxJoyChange && ZAbs <= maxJoyChange) {
			
			RobotMap.driveTrainBL_Talon.set(ControlMode.Follower, RobotMap.driveTrainFL_Talon.getDeviceID());
			RobotMap.driveTrainBL_Talon.setInverted(false);
			RobotMap.driveTrainBR_Talon.set(ControlMode.Follower, RobotMap.driveTrainFR_Talon.getDeviceID());
			RobotMap.driveTrainBR_Talon.setInverted(false);
			RobotMap.driveTrainFR_Talon.set(ControlMode.PercentOutput, YVal);
			RobotMap.driveTrainFL_Talon.set(ControlMode.PercentOutput, -YVal);

		}
		
		else if (XAbs >= maxJoyChange && YAbs <= maxJoyChange && ZAbs <= maxJoyChange) {
			RobotMap.driveTrainBL_Talon.set(ControlMode.Follower, RobotMap.driveTrainFL_Talon.getDeviceID());
			RobotMap.driveTrainBL_Talon.setInverted(true);
			RobotMap.driveTrainBR_Talon.set(ControlMode.Follower, RobotMap.driveTrainFR_Talon.getDeviceID());
			RobotMap.driveTrainBR_Talon.setInverted(true);
			RobotMap.driveTrainFR_Talon.set(ControlMode.PercentOutput, XVal);
			RobotMap.driveTrainFL_Talon.set(ControlMode.PercentOutput, XVal);
		}
		
		else if (ZAbs >= maxJoyChange && YAbs <= maxJoyChange && XAbs <= maxJoyChange) {
			RobotMap.driveTrainBL_Talon.set(ControlMode.Follower, RobotMap.driveTrainFL_Talon.getDeviceID());
			RobotMap.driveTrainBL_Talon.setInverted(false);
			RobotMap.driveTrainBR_Talon.set(ControlMode.Follower, RobotMap.driveTrainFR_Talon.getDeviceID());
			RobotMap.driveTrainBR_Talon.setInverted(false);
			RobotMap.driveTrainFR_Talon.set(ControlMode.PercentOutput, ZVal);
			RobotMap.driveTrainFL_Talon.set(ControlMode.PercentOutput, ZVal);
		}
		
		else {
			RobotMap.driveTrainBL_Talon.set(ControlMode.PercentOutput, 0);
			RobotMap.driveTrainBR_Talon.set(ControlMode.PercentOutput, 0);
			RobotMap.driveTrainFL_Talon.set(ControlMode.PercentOutput, 0);
			RobotMap.driveTrainFR_Talon.set(ControlMode.PercentOutput, 0);
			RobotMap.driveTrainBL_Talon.setInverted(false);
			RobotMap.driveTrainBR_Talon.setInverted(false);
			RobotMap.driveTrainFL_Talon.setInverted(false);
			RobotMap.driveTrainFR_Talon.setInverted(false);
			Robot.mecanum.move(-YVal, XVal, ZVal); 	
		}
	}
		else {
			Robot.mecanum.move(-YVal, XVal, ZVal);
		}
		
		super.execute();
	}
	
	@Override
	public void executeTalon() {
	    System.out.println("BL Sensor: " + RobotMap.driveTrainBL_Talon.getSelectedSensorPosition(0));
	    RobotMap.log("Talon", "" + RobotMap.driveTrainBL_Talon.getMotorOutputVoltage());
	}

	@Override
	public void executeVictor() {}
	
	@Override
	protected boolean isFinished() {
		return false; //Don't stop running this command 
	}

	

}
