package org.usfirst.frc.team4611.robot.commands.auton;

import org.usfirst.frc.team4611.robot.Robot;
import org.usfirst.frc.team4611.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutonStrafe extends Command {
	
	private double inches;
	private double targetPosition;
	private double encoderPositionAverage;
	public double converter = 206.243;
	
    public AutonStrafe(double inches) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	this.inches = inches;
    	targetPosition = inches * converter;
    	requires(Robot.mecanum);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	System.out.println(this.getClass().getName() + " initializing");
    	RobotMap.driveTrainBL_Talon.setSelectedSensorPosition(0, 0, 0);
		RobotMap.driveTrainBR_Talon.setSelectedSensorPosition(0, 0, 0);
		RobotMap.driveTrainFL_Talon.setSelectedSensorPosition(0, 0, 0);
		RobotMap.driveTrainFR_Talon.setSelectedSensorPosition(0, 0, 0);
		System.out.println(this.getClass().getName() + " Sensors Set to Zero");
		RobotMap.driveTrainBR_Talon.config_kP(0, 1, 0);
    	RobotMap.driveTrainBL_Talon.config_kP(0, 1, 0);
    	RobotMap.driveTrainFR_Talon.config_kP(0, 1, 0);
    	RobotMap.driveTrainFL_Talon.config_kP(0, 1, 0);
    	RobotMap.driveTrainBR_Talon.configOpenloopRamp(5, 0);
    	RobotMap.driveTrainBL_Talon.configOpenloopRamp(5, 0);
    	RobotMap.driveTrainFR_Talon.configOpenloopRamp(5, 0);
    	RobotMap.driveTrainFL_Talon.configOpenloopRamp(5, 0);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	encoderPositionAverage = (Math.abs(RobotMap.driveTrainBL_Talon.getSelectedSensorPosition(0)) +
    	Math.abs(RobotMap.driveTrainBR_Talon.getSelectedSensorPosition(0)) +
       	Math.abs(RobotMap.driveTrainFL_Talon.getSelectedSensorPosition(0)) +
       	Math.abs(RobotMap.driveTrainFR_Talon.getSelectedSensorPosition(0))) / 4;
    	Robot.mecanum.motionMagicStrafe(targetPosition);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	
    	StringBuffer s = null;
    	try {
    		s.toString();
    	} catch (Exception e) {
    		e.printStackTrace(System.out);
    	}
    	
    	System.out.println("Target Pos [" + targetPosition + "] Current Pos [" + encoderPositionAverage + "]");
    	if(targetPosition - 200 >= encoderPositionAverage)
        	return false;
        else {
        	System.out.println("ENDING AUTON STRAFE");
        	return true;
        }
    }

    // Called once after isFinished returns true
    protected void end() {
    	RobotMap.driveTrainBR_Talon.config_kP(0, .65, 0);
    	RobotMap.driveTrainBL_Talon.config_kP(0, .65, 0);
    	RobotMap.driveTrainFR_Talon.config_kP(0, .65, 0);
    	RobotMap.driveTrainFL_Talon.config_kP(0, .65, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
