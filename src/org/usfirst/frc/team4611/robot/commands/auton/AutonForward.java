package org.usfirst.frc.team4611.robot.commands.auton;

import org.usfirst.frc.team4611.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutonForward extends Command {
	private double inches;
	private double sensorUnits;
	public double converter = 206.243;
    public AutonForward(double inches) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	this.inches = inches;
    	sensorUnits = inches * converter;
    	requires(Robot.mecanum);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.mecanum.motionMagicStraight(sensorUnits);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
