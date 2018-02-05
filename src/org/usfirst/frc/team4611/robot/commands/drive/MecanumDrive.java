package org.usfirst.frc.team4611.robot.commands.drive;

import org.usfirst.frc.team4611.robot.Robot;
import org.usfirst.frc.team4611.robot.RobotMap;
import org.usfirst.frc.team4611.robot.commands.SwitchableCommand;

import com.ctre.phoenix.motorcontrol.ControlMode;

public class MecanumDrive extends SwitchableCommand{
	
	int velocityInvert1 = 1;
	int velocityInvert2 = -1;
	int velocityInvert3 = -1;
	int velocityInvert4 = 1;
	int maxRPM = 1500;
	
	public MecanumDrive(){
		this.requires(Robot.mecanum); //This command uses this subsystem
	}
	
	protected void initialize() {
		RobotMap.updateValue(RobotMap.mecanumSubTable, "velocityInvert1" , velocityInvert1);
		RobotMap.updateValue(RobotMap.mecanumSubTable, "velocityInvert2" , velocityInvert2);
		RobotMap.updateValue(RobotMap.mecanumSubTable, "velocityInvert3" , velocityInvert3);
		RobotMap.updateValue(RobotMap.mecanumSubTable, "velocityInvert4" , velocityInvert4);
		RobotMap.updateValue(RobotMap.mecanumSubTable, "Max RPM", maxRPM);
	}
	
	protected void execute() {
		
		super.execute();
	}
	
	@Override
	public void executeTalon() {
		double YVal = -Robot.oi.filter(Robot.oi.leftJoy.getY()); //Grab the Y value of the joystick and pass 
		double XVal = Robot.oi.strafeFilter(Robot.oi.leftJoy.getX());//it through the filter
		double ZVal = Robot.oi.rotateFilter(Robot.oi.rightJoy.getX());
		double velocity1;
		double velocity2;
		double velocity3;
		double velocity4;
		/*double YValScaler1 = 1;
		double XValScaler1 = 1;
		double ZValScaler1 = 1;
		double YValScaler2 = 1;
		double XValScaler2 = 1;
		double ZValScaler2 = 1;
		double YValScaler3 = 1;
		double XValScaler3 = 1;
		double ZValScaler3 = 1;
		double YValScaler4 = 1;
		double XValScaler4 = 1;
		double ZValScaler4 = 1;	*/
		double YValScaler1 = 1;
		double XValScaler1 = 1;
		double YValScaler2 = 1;
		double XValScaler2 = 1;
		double ZValScaler = 1;

		RobotMap.updateValue(RobotMap.joyStickSubTable, RobotMap.leftJoyXID, XVal);
		RobotMap.updateValue(RobotMap.joyStickSubTable, RobotMap.leftJoyYID, YVal);
		RobotMap.updateValue(RobotMap.joyStickSubTable, RobotMap.rightJoyXID, ZVal);
		/*RobotMap.updateValue(RobotMap.mecanumSubTable, "YVal Scaler1: ", YValScaler1);	
		RobotMap.updateValue(RobotMap.mecanumSubTable, "YVal Scaler2: ", YValScaler2);	
		RobotMap.updateValue(RobotMap.mecanumSubTable, "YVal Scaler3: ", YValScaler3);	
		RobotMap.updateValue(RobotMap.mecanumSubTable, "YVal Scaler4: ", YValScaler4);	
		RobotMap.updateValue(RobotMap.mecanumSubTable, "XVal Scaler1: ", XValScaler1);	
		RobotMap.updateValue(RobotMap.mecanumSubTable, "XVal Scaler2: ", XValScaler2);	
		RobotMap.updateValue(RobotMap.mecanumSubTable, "XVal Scaler3: ", XValScaler3);
		RobotMap.updateValue(RobotMap.mecanumSubTable, "YVal Scaler4: ", XValScaler4);
		RobotMap.updateValue(RobotMap.mecanumSubTable, "ZVal Scaler1: ", ZValScaler1);	
		RobotMap.updateValue(RobotMap.mecanumSubTable, "ZVal Scaler2: ", ZValScaler2);	
		RobotMap.updateValue(RobotMap.mecanumSubTable, "ZVal Scaler3: ", ZValScaler3);
		RobotMap.updateValue(RobotMap.mecanumSubTable, "YVal Scaler4: ", ZValScaler4);*/
		RobotMap.updateValue(RobotMap.mecanumSubTable, "YVal Scaler Left: ", YValScaler1);
		RobotMap.updateValue(RobotMap.mecanumSubTable, "XVal Scaler Left: ", XValScaler1);
		RobotMap.updateValue(RobotMap.mecanumSubTable, "ZVal Scaler: ", ZValScaler);
		RobotMap.updateValue(RobotMap.mecanumSubTable, "YVal Scaler Right: ", YValScaler2);
		RobotMap.updateValue(RobotMap.mecanumSubTable, "XVal Scaler Right: ", XValScaler2);
		
		//Blaine and Halter's magic math
		velocity1 = ((Double) RobotMap.getValue(RobotMap.mecanumSubTable, "Max RPM")).doubleValue() * (YVal * YValScaler1 + XVal * XValScaler1 + ZVal * ZValScaler) * (velocityInvert1);
		velocity2 = ((Double) RobotMap.getValue(RobotMap.mecanumSubTable, "Max RPM")).doubleValue() * (YVal * YValScaler2 - XVal * XValScaler2 - ZVal * ZValScaler) * (velocityInvert2); 
		velocity3 = ((Double) RobotMap.getValue(RobotMap.mecanumSubTable, "Max RPM")).doubleValue() * (YVal * YValScaler2 + XVal * XValScaler2 - ZVal * ZValScaler) * (velocityInvert3);
		velocity4 = ((Double) RobotMap.getValue(RobotMap.mecanumSubTable, "Max RPM")).doubleValue() * (YVal * YValScaler1 - XVal * XValScaler1 + ZVal * ZValScaler) * (velocityInvert4);
		if (velocity1 > 0 || velocity2 > 0|| velocity3 > 0|| velocity4 > 0) {
		RobotMap.updateValue(RobotMap.mecanumSubTable, "Velocity 1", velocity1);
		RobotMap.updateValue(RobotMap.mecanumSubTable, "Velocity 2", velocity2);
		RobotMap.updateValue(RobotMap.mecanumSubTable, "Velocity 3", velocity3);
		RobotMap.updateValue(RobotMap.mecanumSubTable, "Velocity 4", velocity4);
		}
		
		Robot.mecanum.velocityDrive(velocity1, velocity2, velocity3, velocity4);
		
	}

	@Override
	public void executeVictor() {
		double YVal = Robot.oi.filter(Robot.oi.leftJoy.getY()); //Grab the Y value of the joystick and pass 
		double XVal = Robot.oi.strafeFilter(Robot.oi.leftJoy.getX());//it through the filter
		double ZVal = Robot.oi.filter(Robot.oi.rightJoy.getX());
		Robot.mecanum.move(YVal,-XVal,-ZVal);
	}
	
	@Override
	protected boolean isFinished() {
		return false; //Don't stop running this command 
	}

	

}
