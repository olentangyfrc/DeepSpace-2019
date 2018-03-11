package org.usfirst.frc.team4611.robot.commands.drive;

import org.usfirst.frc.team4611.robot.OI;
import org.usfirst.frc.team4611.robot.Robot;
import org.usfirst.frc.team4611.robot.RobotMap;
import org.usfirst.frc.team4611.robot.logging.Logger;

import edu.wpi.first.wpilibj.command.Command;

public class MecanumDrive extends Command{
	
	int velocityInvert1 = 1;
	int velocityInvert2 = -1;
	int velocityInvert3 = -1;
	int velocityInvert4 = 1;
	int maxRPM = (int)(double)RobotMap.getValue(RobotMap.mecanumSubTable, RobotMap.maxRPMID);
	
	public MecanumDrive(){
		this.requires(Robot.mecanum);
	}
	
	protected void initialize() {
	}
	
	@Override
	protected void execute() {
		double YVal = -Robot.oi.filter(OI.leftJoy.getY()); 
		double XVal = Robot.oi.strafeFilter(OI.leftJoy.getX());
		double ZVal = Robot.oi.rotateFilter(OI.rightJoy.getX());
		double velocity1;
		double velocity2;
		double velocity3;
		double velocity4;
		
		double YValScaler1 = 1;
		double XValScaler1 = 1;
		double YValScaler2 = 1;
		double XValScaler2 = 1;
		double ZValScaler = 1;

		
		// need to calc velocity for each wheel
		velocity1 = 4*(maxRPM * (YVal * YValScaler1 + XVal * XValScaler1 + ZVal * ZValScaler) * (velocityInvert1));
		velocity2 = 4*(maxRPM * (YVal * YValScaler2 - XVal * XValScaler2 - ZVal * ZValScaler) * (velocityInvert2)); 
		velocity3 = 4*(maxRPM * (YVal * YValScaler2 + XVal * XValScaler2 - ZVal * ZValScaler) * (velocityInvert3));
		velocity4 = 4*(maxRPM * (YVal * YValScaler1 - XVal * XValScaler1 + ZVal * ZValScaler) * (velocityInvert4));

		Robot.mecanum.setRampRate(0);
		Robot.mecanum.velocityDrive(velocity1, velocity2, velocity3, velocity4);
		
		//Logger.log("{"+ velocity1 +", " + velocity2 + ", " + velocity3 + ", " + velocity4 + "}", "MecanumDrive.capturedVelocities");
		
	}
	@Override
	protected boolean isFinished() {
		return false; //Don't stop running this command 
	}

}
