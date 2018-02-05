package org.usfirst.frc.team4611.robot;

import org.usfirst.frc.team4611.robot.commands.MoveElevator;

import org.usfirst.frc.team4611.robot.commands.auton.AutoGrab;
import org.usfirst.frc.team4611.robot.commands.drive.MoveBackward;
import org.usfirst.frc.team4611.robot.commands.drive.MoveForward;
import org.usfirst.frc.team4611.robot.commands.drive.StrafeLeft;
import org.usfirst.frc.team4611.robot.commands.drive.StrafeRight;
import org.usfirst.frc.team4611.robot.potentiometer.MovePotDown;
import org.usfirst.frc.team4611.robot.potentiometer.MovePotUp;
import org.usfirst.frc.team4611.robot.potentiometer.StopPot;
import org.usfirst.frc.team4611.robot.commands.solenoid.ExtendSolenoid;
import org.usfirst.frc.team4611.robot.commands.solenoid.RetractSolenoid;
import org.usfirst.frc.team4611.robot.commands.solenoid.ToggleSolenoid;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;


/**
 * This is where we create all of out buttons and joysticks and 
 * set up the "UI" of the bot for the drivers. You're gonna end up 
 * here a lot when people complain about buttons needing to be changed
 */

public class OI {
	
	//Joysticks
	public static Joystick leftJoy;
	public static Joystick rightJoy;
	public static Joystick thirdJoy;
	
	//Buttons
	public Button strafeLeft;
	public Button strafeRight;
	public Button moveForward;
	public Button moveBackward;
	public Button strafeLeft2;
	public Button strafeRight2;
	public Button linearActuatorUp;
	public Button linearActuatorDown;
	public Button linearActuatorUp2;
	public Button linearActuatorDown2;
	public Button autoGrabBox;
	public Button autoGrabBox2;
	public Button solToggle;
	public Button solExtend;
	public Button solRetract;
	public Button moveElevatorD;
	public Button moveElevatorU;
	public Button openClaw;
	public Button closeClaw;
	public Button MoveForward;
	public Button MoveBackward;
	

	public OI (){
		
		//Joystick
		leftJoy = new Joystick(RobotMap.leftJoyPort); //The left joystick exists on this port in robot map
		rightJoy = new Joystick(RobotMap.rightJoyPort); //The right joystick exists on this port in robot map
		thirdJoy = new Joystick(RobotMap.thirdJoyPort);
		
		//Buttons
		moveForward = new JoystickButton(thirdJoy, 3);
		moveBackward = new JoystickButton(thirdJoy,2);
		strafeLeft= new JoystickButton(leftJoy, 4);
		strafeRight= new JoystickButton(leftJoy, 5);
		autoGrabBox = new JoystickButton(leftJoy, 14);
		linearActuatorUp = new JoystickButton(leftJoy, 3);
		linearActuatorDown = new JoystickButton(leftJoy, 2);
		linearActuatorUp2 = new JoystickButton(thirdJoy, 6);
		linearActuatorDown2 = new JoystickButton(thirdJoy, 7);
		strafeLeft2 = new JoystickButton(thirdJoy, 4);
		strafeRight2 = new JoystickButton(thirdJoy, 5);
		autoGrabBox = new JoystickButton(rightJoy, 3);
		autoGrabBox2 = new JoystickButton(thirdJoy, 11);
		solToggle = new JoystickButton(leftJoy, 1);
		solExtend = new JoystickButton(leftJoy, 7);//close claw
		solRetract = new JoystickButton(leftJoy, 6);//open claw
		MoveForward = new JoystickButton(thirdJoy, 3);
		MoveBackward = new JoystickButton(thirdJoy, 2);
	
		//Sends the starting values of the joysticks to the Shuffleboard
		RobotMap.updateValue(RobotMap.joyStickSubTable, RobotMap.leftJoyXID, leftJoy.getX());
		RobotMap.updateValue(RobotMap.joyStickSubTable, RobotMap.leftJoyYID, leftJoy.getY());
		RobotMap.updateValue(RobotMap.joyStickSubTable, RobotMap.leftJoyZID, leftJoy.getZ());
		RobotMap.updateValue(RobotMap.joyStickSubTable, RobotMap.rightJoyXID, rightJoy.getX());
		RobotMap.updateValue(RobotMap.joyStickSubTable, RobotMap.rightJoyYID, rightJoy.getY());
		RobotMap.updateValue(RobotMap.joyStickSubTable, RobotMap.rightJoyZID, rightJoy.getZ());

		//Button Commands
		
		//Strafe Commands
		moveForward.whileHeld(new MoveForward(0.3));
		moveBackward.whileHeld(new MoveBackward(-0.3));
		
		strafeRight.whileHeld(new StrafeRight((double)RobotMap.getValue(RobotMap.mecanumSubTable, RobotMap.strafePowerID)));
		strafeLeft.whileHeld(new StrafeLeft((double)RobotMap.getValue(RobotMap.mecanumSubTable, RobotMap.strafePowerID)));
		
		strafeRight2.whileHeld(new StrafeRight((double)RobotMap.getValue(RobotMap.mecanumSubTable, RobotMap.strafePowerID)));
		strafeLeft2.whileHeld(new StrafeLeft((double)RobotMap.getValue(RobotMap.mecanumSubTable, RobotMap.strafePowerID)));
		
		//Linear actuator commands

		//linearActuatorUp.whileHeld(new MovePotUp((double)RobotMap.getValue(RobotMap.linearActuatorSubTable, RobotMap.LASpeedID)));
		linearActuatorUp.whileHeld(new MovePotUp());
		linearActuatorUp.whenReleased(new StopPot());
		
		//linearActuatorDown.whileHeld(new MovePotDown(-(double)RobotMap.getValue(RobotMap.linearActuatorSubTable, RobotMap.LASpeedID)));
		linearActuatorDown.whileHeld(new MovePotDown());
		linearActuatorDown.whenReleased(new StopPot());
		
		
		//linearActuatorSwitch.whileHeld(new MovePotSwitch((double)RobotMap.getValue(RobotMap.linearActuatorSubTable, RobotMap.LASpeedID)));
		//linearActuatorSwitch.whenReleased(new StopPot());
		
		MoveForward.whileHeld(new org.usfirst.frc.team4611.robot.commands.drive.MoveForward(.3));
		MoveBackward.whileHeld(new org.usfirst.frc.team4611.robot.commands.drive.MoveBackward(-.3));

		
		//Solenoid Commands
		solToggle.whenPressed(new ToggleSolenoid());
		solExtend.whileHeld(new ExtendSolenoid());
		solRetract.whileHeld(new RetractSolenoid());
		autoGrabBox.whenPressed(new AutoGrab());
		autoGrabBox2.whenPressed(new AutoGrab());
	}
	
	public double filter(double raw) //We pass joystick values through the filter here and edit the raw value
    {
        if (Math.abs(raw) < (double)RobotMap.getValue(RobotMap.mecanumSubTable, RobotMap.deadZoneID)) {
            return 0; //If the value passed is less than 15% ignore it. This is reffered to as a deadzone
        } else {
            return  raw * (double)RobotMap.getValue(RobotMap.mecanumSubTable, RobotMap.motorPowerID); //Set the output to a ceratin percent of of the input
        }
    }
	
	public double strafeFilter(double raw) {
		if (Math.abs(raw) < (double)RobotMap.getValue(RobotMap.mecanumSubTable, RobotMap.deadZoneID)) {
            return 0; //If the value passed is less than 15% ignore it. This is reffered to as a deadzone
        } else {
            return  raw * Math.min((double)RobotMap.getValue(RobotMap.mecanumSubTable, RobotMap.strafePowerID) * 2, 1); //Set the output to a ceratin percent of of the input
        }
	}	
	
	public double rotateFilter(double raw) {
		if (Math.abs(raw) < (double)RobotMap.getValue(RobotMap.mecanumSubTable, RobotMap.deadZoneID)) {
            return 0; //If the value passed is less than 15% ignore it. This is reffered to as a deadzone
        } else {
            return  raw * Math.min((double)RobotMap.getValue(RobotMap.mecanumSubTable, RobotMap.rotateFilterID), 1); //Set the output to a ceratin percent of of the input
        }
	}
}