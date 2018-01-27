package org.usfirst.frc.team4611.robot.commands.drive;

import org.usfirst.frc.team4611.robot.Robot;
import org.usfirst.frc.team4611.robot.RobotMap;
import org.usfirst.frc.team4611.robot.commands.SwitchableCommand;

import edu.wpi.first.wpilibj.command.Command;

public class MecanumDrive extends SwitchableCommand{
	
	public MecanumDrive(){
		this.requires(Robot.mecanum); //This command uses this subsystem
	}
	
	protected void execute() {
		double YVal = Robot.oi.filter(Robot.oi.leftJoy.getY()); //Grab the Y value of the joystick and pass 
		double XVal = Robot.oi.strafeFilter(Robot.oi.leftJoy.getX());//it through the filter
		double ZVal = Robot.oi.filter(Robot.oi.rightJoy.getX());

		//Sends the current values of the two joysticks to the Shuffleboard
		RobotMap.updateValue(RobotMap.joyStickSubTable, RobotMap.leftJoyXID, Robot.oi.leftJoy.getY());
		RobotMap.updateValue(RobotMap.joyStickSubTable, RobotMap.leftJoyYID, Robot.oi.leftJoy.getY());
		RobotMap.updateValue(RobotMap.joyStickSubTable, RobotMap.leftJoyZID, Robot.oi.leftJoy.getZ());
		RobotMap.updateValue(RobotMap.joyStickSubTable, RobotMap.rightJoyXID, Robot.oi.rightJoy.getX());
		RobotMap.updateValue(RobotMap.joyStickSubTable, RobotMap.rightJoyYID, Robot.oi.rightJoy.getY());
		RobotMap.updateValue(RobotMap.joyStickSubTable, RobotMap.rightJoyZID, Robot.oi.rightJoy.getZ());
		
	    Robot.mecanum.move(-YVal, XVal, ZVal); 	
		
	    //Runs the execute in SwitchableCommand to run talon or victor-specific code
	    super.execute();
	}
	
	/**
	 * Talon-specific code for MecanumDrive
	 */
	@Override
	public void executeTalon() {
	    System.out.println("BL Sensor: " + RobotMap.driveTrainBL_Talon.getSelectedSensorPosition(0));
	    RobotMap.log("Talon", "" + RobotMap.driveTrainBL_Talon.getMotorOutputVoltage());
	}

	/**
	 * Victor-specific code for MecanumDrive
	 */
	@Override
	public void executeVictor() {}
	
	@Override
	protected boolean isFinished() {
		return false; //Don't stop running this command 
	}

	

}
