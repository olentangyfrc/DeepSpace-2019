package org.usfirst.frc.team4611.robot.commands.drive;

import org.usfirst.frc.team4611.robot.Robot;
import org.usfirst.frc.team4611.robot.RobotMap;
import org.usfirst.frc.team4611.robot.logging.Logger;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutonForward extends Command {
	private double targetPosition;
	//public double converter = 206.243;
	//public double converter = 215.910640625;
	private double encoderPositionAverage;
	private double startingAngle;
	private double currentAngle;
	private double error;
	private double threshhold = .5;
	private int velo;
	
    public AutonForward(double inches) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	double converter = (double)RobotMap.getValue(RobotMap.autonSubTable, RobotMap.inchPuMultipler);
    	targetPosition = inches * converter;
    	requires(Robot.mecanum);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	RobotMap.pigeon.setFusedHeading(0, 0);
    	RobotMap.driveTrainBL_Talon.setSelectedSensorPosition(0, 0, 0);
		RobotMap.driveTrainBR_Talon.setSelectedSensorPosition(0, 0, 0);
		RobotMap.driveTrainFL_Talon.setSelectedSensorPosition(0, 0, 0);
		RobotMap.driveTrainFR_Talon.setSelectedSensorPosition(0, 0, 0);
		Robot.mecanum.setRampRate(0);
		startingAngle = RobotMap.pigeon.getFusedHeading();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.mecanum.logSpeed();
    	Robot.mecanum.logPosition();
    	
    	currentAngle = RobotMap.pigeon.getFusedHeading();
    	double pVal = 35;
    	error = startingAngle - Math.abs(currentAngle);
      	Robot.mecanum.logVelocities();
    	Logger.log("Starting Angle [" + startingAngle + "] Current Angle [" + currentAngle + "]", "Angles");
    	
    	
      	encoderPositionAverage = (Math.abs(RobotMap.driveTrainBL_Talon.getSelectedSensorPosition(0)) +
    	Math.abs(RobotMap.driveTrainBR_Talon.getSelectedSensorPosition(0)) +
    	Math.abs(RobotMap.driveTrainFL_Talon.getSelectedSensorPosition(0)) +
    	Math.abs(RobotMap.driveTrainFR_Talon.getSelectedSensorPosition(0))) / 4;
    	
      
      	int adjustedVelo = (int)(RobotMap.motionmagicCruiseVelocity + (error * pVal));
      	velo = Math.min(3000, adjustedVelo);
      	
      	if(currentAngle > startingAngle && error > threshhold) {//drifted right
      		Robot.mecanum.setMotionMagicVelocity(RobotMap.motionmagicCruiseVelocity, velo);//left then right
      		//Robot.mecanum.setMotionMagicAcceleration(accel, (int)(accel + (error * pVal)));
      	}
      	else if(currentAngle < startingAngle && error < threshhold) {//drifted left
      		Robot.mecanum.setMotionMagicVelocity(velo, RobotMap.motionmagicCruiseVelocity);//left then right
      		//Robot.mecanum.setMotionMagicAcceleration((int)(accel + (error * pVal)), accel);
      	}
      	else {
      		Robot.mecanum.setMotionMagicValuesToDefault();
      		Logger.log("Did not need to adjust", "AutonForward2");
      	}
      	
    	Robot.mecanum.motionMagicStraight(targetPosition);
    	//accel = Math.min(2 * accel, RobotMap.magicValuesAccel);
    	
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
