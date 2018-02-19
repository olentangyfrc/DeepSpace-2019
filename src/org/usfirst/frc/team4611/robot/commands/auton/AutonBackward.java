package org.usfirst.frc.team4611.robot.commands.auton;

import org.usfirst.frc.team4611.robot.Robot;
import org.usfirst.frc.team4611.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutonBackward extends Command {
	private double inches;
	private double targetPosition;
	public double converter = 206.243;
	private double encoderPositionAverage;
    public AutonBackward(double inches) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	this.inches = inches;
    	targetPosition = -inches * converter;
    	requires(Robot.mecanum);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	RobotMap.driveTrainBL_Talon.setSelectedSensorPosition(0, 0, 0);
		RobotMap.driveTrainBR_Talon.setSelectedSensorPosition(0, 0, 0);
		RobotMap.driveTrainFL_Talon.setSelectedSensorPosition(0, 0, 0);
		RobotMap.driveTrainFR_Talon.setSelectedSensorPosition(0, 0, 0);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	encoderPositionAverage = (Math.abs(RobotMap.driveTrainBL_Talon.getSelectedSensorPosition(0)) +
    	Math.abs(RobotMap.driveTrainBR_Talon.getSelectedSensorPosition(0)) +
    	Math.abs(RobotMap.driveTrainFL_Talon.getSelectedSensorPosition(0)) +
    	Math.abs(RobotMap.driveTrainFR_Talon.getSelectedSensorPosition(0))) / 4;
    	System.out.println(this.getClass().getName() + "Encoder Position Av: " + encoderPositionAverage);
    	System.out.println(this.getClass().getName() + "Target Pos {" + targetPosition + "}");
    	Robot.mecanum.motionMagicStraight(targetPosition);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        if(targetPosition + 200 <= -encoderPositionAverage )
        	return false;
        else 
        	return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
