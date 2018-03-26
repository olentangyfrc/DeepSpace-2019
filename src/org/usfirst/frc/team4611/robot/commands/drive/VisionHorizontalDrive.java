package org.usfirst.frc.team4611.robot.commands.drive;

import org.usfirst.frc.team4611.robot.Robot;
import org.usfirst.frc.team4611.robot.RobotMap;
import org.usfirst.frc.team4611.robot.logging.Logger;

import edu.wpi.first.wpilibj.command.Command;

public class VisionHorizontalDrive extends Command{
	/* the horizontal distance is constantly updating
	 * can cause oscillation
	 */
	double horizontalDistance = -1;
	double angle;
	private double converter;
	private double horizontalThreshhold = 200.0;
	private boolean inTolerence = false;
	private double error = 0;
	public VisionHorizontalDrive(){
    	requires(Robot.mecanum);
	}
	
	public void initialize() {
		horizontalDistance = -1;
		converter = 206.243 * (double)RobotMap.getValue(RobotMap.autonSubTable, RobotMap.converterID);
		angle = (double)RobotMap.networkManager.getVisionValue(RobotMap.angleID);
		Robot.mecanum.resetEncoders();
    	Robot.mecanum.config_kP(1);
    	Robot.mecanum.resetRampRate();
    	System.out.println("Initilizing Vision drive");
    	
    	Logger.log(" H Dist [" + horizontalDistance +"] "
				+ " Converter [" + converter +"] "
				+ " HDist*converter [" + (horizontalDistance * converter) +"] "
				+ " Avg Position [" + Robot.mecanum.getAveragePosition() + "]", "VisionHorizontalDrive3");
		
	}
	
	public void execute(){
		
		Logger.log(" H Dist [" + horizontalDistance +"] "
				+ " Target [" + RobotMap.driveTrainBL_Talon.getClosedLoopTarget(0) + "]"
				+ " Avg Position [" + Robot.mecanum.getAveragePosition() + "]"
				+ " Avg Speed: [" + Robot.mecanum.getAverageSpeed() + "]"
				+ " Found: [" + ((boolean)RobotMap.networkManager.getVisionValue(RobotMap.foundID)) + "] "
				,"VisionHorizontalDrive3 Execute");
		if(horizontalDistance == -1 && (boolean)RobotMap.networkManager.getVisionValue(RobotMap.foundID)) {	
			horizontalDistance = (double)RobotMap.networkManager.getVisionValue(RobotMap.horizontalDistanceID); 
		}
		else {
			return;
		}
		if(horizontalDistance <= 1.5) {
			inTolerence = true;
			return;
		}
		
		else {
			inTolerence = false;
		}
		if(angle > 0) {
			Robot.mecanum.motionMagicStrafe(-horizontalDistance * converter);
		} else {
			Robot.mecanum.motionMagicStrafe(horizontalDistance * converter);
		}
		error = Math.abs(RobotMap.driveTrainBL_Talon.getClosedLoopTarget(0) - Robot.mecanum.getAveragePosition());
		
	}
	
	protected boolean isFinished() {
		Logger.log(" inTolerence [" + inTolerence +"] "
				+ " error [" + error + "]"
				+ " Avg Position [" + Robot.mecanum.getAveragePosition() + "]"
				+ " Avg Speed: [" + Robot.mecanum.getAverageSpeed() + "]"
				+ " Found: [" + ((boolean)RobotMap.networkManager.getVisionValue(RobotMap.foundID)) + "] "
				,"VisionHorizontalDrive3 isFinished");
		if(inTolerence || error < 100) {
			return true;
		}
		
		if(Robot.mecanum.getAveragePosition() > 1000) {
			if(Robot.mecanum.getAverageSpeed() <= 100) {
				return true;
			}
			else
			{
				return false;
			}
		}
		return false;

    }
	
	private boolean horizontalGood() {
		if (horizontalDistance == -1)
				return false;
		
		if ((horizontalDistance * converter) - horizontalThreshhold <= Robot.mecanum.getAveragePosition()) {
			return true;
		} else {
			return false;
		}
	}
	
	protected void end( ) {
		Robot.mecanum.config_kP(.65);
	}
}
