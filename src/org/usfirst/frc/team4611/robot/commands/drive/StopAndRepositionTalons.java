package org.usfirst.frc.team4611.robot.commands.drive;

import org.usfirst.frc.team4611.robot.Robot;
import org.usfirst.frc.team4611.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class StopAndRepositionTalons extends Command {
	
    public StopAndRepositionTalons() {
    	requires(Robot.mecanum);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    /**
     * Stop motors and set position to zero.
     */
    protected void execute() {
    	Robot.mecanum.resetEncoders();
    	Robot.mecanum.velocityDrive(0, 0, 0, 0);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	double blSpeed, brSpeed, flSpeed, frSpeed;
    	double blPosition, brPosition, flPosition, frPosition;
    	
    	blSpeed	= RobotMap.driveTrainBL_Talon.get();
    	brSpeed	= RobotMap.driveTrainBR_Talon.get();
    	flSpeed	= RobotMap.driveTrainFL_Talon.get();
    	frSpeed	= RobotMap.driveTrainFR_Talon.get();

    	System.out.println(this.getClass().getName() + "isFinished() : motorSpeeds [bl, br, fl, fr] ["
    																			+ blSpeed + ", "
    																			+ brSpeed + ", "
    																			+ flSpeed + ", "
    																			+ frSpeed + ']');
    	
    	blPosition	= RobotMap.driveTrainBL_Talon.getSelectedSensorPosition(0);
    	brPosition	= RobotMap.driveTrainBR_Talon.getSelectedSensorPosition(0);
    	flPosition	= RobotMap.driveTrainFL_Talon.getSelectedSensorPosition(0);
    	frPosition	= RobotMap.driveTrainFR_Talon.getSelectedSensorPosition(0);
      	System.out.println(this.getClass().getName() + "isFinished() : motorPositions [bl, br, fl, fr] ["
				+ blPosition + ", "
				+ brPosition + ", "
				+ flPosition + ", "
				+ frPosition + ']');
      	
    	if ((blSpeed > 0 || brSpeed > 0 || flSpeed > 0 || frSpeed > 0)
    			|| (Math.abs(blPosition) > 30
	    			|| Math.abs(brPosition) > 30
	    			|| Math.abs(flPosition) > 30
	    			|| Math.abs(frPosition) > 30)) {
    		return false;
    	} else {
        	RobotMap.driveTrainBL_Talon.setSelectedSensorPosition(0, 0, 0);
    		RobotMap.driveTrainBR_Talon.setSelectedSensorPosition(0, 0, 0);
    		RobotMap.driveTrainFL_Talon.setSelectedSensorPosition(0, 0, 0);
    		RobotMap.driveTrainFR_Talon.setSelectedSensorPosition(0, 0, 0);
    		return true;
    	}
    	
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	System.out.println(this.getClass().getName() + "IS INTERRUPTED");
    }
}

