package org.usfirst.frc.team4611.robot.commands.drive;

import org.usfirst.frc.team4611.robot.Robot;
import org.usfirst.frc.team4611.robot.RobotMap;
import org.usfirst.frc.team4611.robot.logging.Logger;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutonForward extends Command {
	private double inches;
	private double targetPosition;
	//public double converter = 206.243;
	//public double converter = 215.910640625;
	private double encoderPositionAverage;
    public AutonForward(double inches) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	this.inches = inches;
    	double converter = (double)RobotMap.getValue(RobotMap.autonSubTable, RobotMap.inchPuMultipler);
    	targetPosition = inches * converter;
    	requires(Robot.mecanum);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	RobotMap.driveTrainBL_Talon.setSelectedSensorPosition(0, 0, 0);
		RobotMap.driveTrainBR_Talon.setSelectedSensorPosition(0, 0, 0);
		RobotMap.driveTrainFL_Talon.setSelectedSensorPosition(0, 0, 0);
		RobotMap.driveTrainFR_Talon.setSelectedSensorPosition(0, 0, 0);
		Robot.mecanum.setRampRate(0);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.mecanum.logSpeed();
    	Robot.mecanum.logPosition();
      	
    	encoderPositionAverage = (Math.abs(RobotMap.driveTrainBL_Talon.getSelectedSensorPosition(0)) +
    	Math.abs(RobotMap.driveTrainBR_Talon.getSelectedSensorPosition(0)) +
    	Math.abs(RobotMap.driveTrainFL_Talon.getSelectedSensorPosition(0)) +
    	Math.abs(RobotMap.driveTrainFR_Talon.getSelectedSensorPosition(0))) / 4;
    	
    	Robot.mecanum.motionMagicStraight(targetPosition);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	Logger.log(" targetPosition [" + targetPosition + "] encodePositionAverage [" + encoderPositionAverage + "]", "AutonForward");

        return (targetPosition < encoderPositionAverage);
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.mecanum.resetRampRate();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
