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
	
	public MecanumDrive(){
		this.requires(Robot.mecanum); //This command uses this subsystem
	}
	
	protected void initialize() {
		RobotMap.updateValue(RobotMap.mecanumSubTable, "velocityInvert1" , velocityInvert1);
		RobotMap.updateValue(RobotMap.mecanumSubTable, "velocityInvert2" , velocityInvert2);
		RobotMap.updateValue(RobotMap.mecanumSubTable, "velocityInvert3" , velocityInvert3);
		RobotMap.updateValue(RobotMap.mecanumSubTable, "velocityInvert4" , velocityInvert4);		
	}
	
	protected void execute() {
		
		super.execute();
	}
	
	@Override
	public void executeTalon() {
		double YVal = Robot.oi.filter(Robot.oi.leftJoy.getY()); //Grab the Y value of the joystick and pass 
		double XVal = Robot.oi.strafeFilter(Robot.oi.leftJoy.getX());//it through the filter
		double ZVal = Robot.oi.filter(Robot.oi.rightJoy.getX());
		double velocity1;
		double velocity2;
		double velocity3;
		double velocity4;
		double YValScaler1 = 1;
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
		double ZValScaler4 = 1;	

		RobotMap.updateValue(RobotMap.joyStickSubTable, RobotMap.leftJoyXID, Robot.oi.leftJoy.getY());
		RobotMap.updateValue(RobotMap.joyStickSubTable, RobotMap.leftJoyYID, Robot.oi.leftJoy.getY());
		RobotMap.updateValue(RobotMap.joyStickSubTable, RobotMap.leftJoyZID, Robot.oi.leftJoy.getZ());
		RobotMap.updateValue(RobotMap.joyStickSubTable, RobotMap.rightJoyXID, Robot.oi.rightJoy.getX());
		RobotMap.updateValue(RobotMap.joyStickSubTable, RobotMap.rightJoyYID, Robot.oi.rightJoy.getY());
		RobotMap.updateValue(RobotMap.joyStickSubTable, RobotMap.rightJoyZID, Robot.oi.rightJoy.getZ());
		
		velocityInvert1 = ((Double) RobotMap.getValue(RobotMap.mecanumSubTable, "velocityInvert1")).intValue();
		velocityInvert2 = ((Double) RobotMap.getValue(RobotMap.mecanumSubTable, "velocityInvert2")).intValue();
		velocityInvert3 = ((Double) RobotMap.getValue(RobotMap.mecanumSubTable, "velocityInvert3")).intValue();
		velocityInvert4 = ((Double) RobotMap.getValue(RobotMap.mecanumSubTable, "velocityInvert4")).intValue();
		
		//Blaine and Halter's magic math
		velocity1 = 1100 * (YVal * YValScaler1 + XVal * XValScaler1 + ZVal * ZValScaler1) * (velocityInvert1); //Multiplier COULD be 1250 in the perfect world but load is a thing
		velocity2 = 1100 * (YVal * YValScaler2 - XVal * XValScaler2 - ZVal * ZValScaler2) * (velocityInvert2); //I think these (2 and 3) might have to be negative
		velocity3 = 1100 * (YVal * YValScaler3 + XVal * XValScaler3 - ZVal * ZValScaler3) * (velocityInvert3);// ^ Says 1am Seth 
		velocity4 = 1100 * (YVal * YValScaler4 - XVal * XValScaler4 + ZVal * ZValScaler4) * (velocityInvert4);//Maybe switch the sensor phase???
		
		if (YVal > 0 && XVal == 0) {
			velocity1 = -Math.abs(velocity1);
			velocity2 = Math.abs(velocity2);
			velocity3 = Math.abs(velocity3);
			velocity4 = -Math.abs(velocity4);
		}
		
		else if (YVal < 0 && XVal == 0) {
			velocity1 = Math.abs(velocity1);
			velocity2 = -Math.abs(velocity2);
			velocity3 = -Math.abs(velocity3);
			velocity4 = Math.abs(velocity4);
		}
		
		
		RobotMap.updateValue(RobotMap.mecanumSubTable, "Velocity 1", velocity1);
		RobotMap.updateValue(RobotMap.mecanumSubTable, "Velocity 2", velocity2);
		RobotMap.updateValue(RobotMap.mecanumSubTable, "Velocity 3", velocity3);
		RobotMap.updateValue(RobotMap.mecanumSubTable, "Velocity 4", velocity4);
		
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
