package org.usfirst.frc.team4611.robot.commands;

import org.usfirst.frc.team4611.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class laserDistance extends Command {

	public int distance;
	
    public laserDistance() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.opt);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	//Robot.opt.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//distance = Robot.opt.getInt(); 
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        
    	return !(distance == -1);
    }

    // Called once after isFinished returns true
    protected void end() {
    	System.out.println("Laser distance: " + distance);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
