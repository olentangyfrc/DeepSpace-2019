package org.usfirst.frc.team4611.robot.commands.arm;

import org.usfirst.frc.team4611.robot.Robot;
import org.usfirst.frc.team4611.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MoveMaxUp extends Command {
	private double pos;
    public MoveMaxUp(double pos) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	this.pos = pos;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.arm.movePotPos(pos);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	double potValue1 = RobotMap.linearActuatorPot.get();
		double potValue2 = RobotMap.linearActuatorPot2.get();
    	if((pos + 0.05 > potValue1 && pos - 0.05 < potValue1) && (pos + 0.05 > potValue2 && pos - 0.05 < potValue2)) {
    		return true;
    	}
    	else {
    		return false;
    	}
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
