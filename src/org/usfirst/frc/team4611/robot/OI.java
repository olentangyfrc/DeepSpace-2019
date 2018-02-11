package org.usfirst.frc.team4611.robot;

import org.usfirst.frc.team4611.robot.commands.auton.AimForBox;
import org.usfirst.frc.team4611.robot.commands.auton.AutoGrab;
import org.usfirst.frc.team4611.robot.commands.drive.StrafeLeft;
import org.usfirst.frc.team4611.robot.commands.drive.StrafeRight;
import org.usfirst.frc.team4611.robot.commands.drive.VisionDrive;
import org.usfirst.frc.team4611.robot.commands.solenoid.ExtendSolenoid;
import org.usfirst.frc.team4611.robot.commands.solenoid.PushBox;
import org.usfirst.frc.team4611.robot.commands.solenoid.RetractSolenoid;
import org.usfirst.frc.team4611.robot.commands.solenoid.ToggleSolenoid;
import org.usfirst.frc.team4611.robot.potentiometer.MovePotDown;
import org.usfirst.frc.team4611.robot.potentiometer.MovePotUp;
import org.usfirst.frc.team4611.robot.potentiometer.StopPot;

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
	public static Joystick auxJoy;
	
	//Movement Buttons
	public Button strafeLeft;
	public Button strafeRight;
	public Button MoveForward;
	public Button MoveBackward;
	public Button autoGrabBox;
	public Button autoGrabBox2;
	public Button aimBox;
	
	//Solenoid Buttons
	public Button solToggle;
	public Button solExtend;
	public Button solRetract;	
	
	//LA Buttons
	public Button linearActuatorUp;
	public Button linearActuatorDown;
	public Button linearActuatorUp2;
	public Button linearActuatorDown2;
	public Button linearActuatorUp3;
	public Button linearActuatorDown3;
	public Button linearActuatorSwitch;
	
	//Elevator Buttons
	public Button moveElevatorD;
	public Button moveElevatorU;
	
	//Claw Buttons
	public Button openClaw;
	public Button closeClaw;
	public Button pushBox;

	public OI (){
		
		//Joystick
		leftJoy = new Joystick(RobotMap.leftJoyPort); //The left joystick exists on this port in robot map
		rightJoy = new Joystick(RobotMap.rightJoyPort); //The right joystick exists on this port in robot map
		auxJoy = new Joystick(RobotMap.thirdJoyPort);
		
		//Movement Buttons
		strafeLeft= new JoystickButton(leftJoy, 4);
		strafeRight = new JoystickButton(leftJoy, 5);
		linearActuatorUp = new JoystickButton(rightJoy, 3);
		linearActuatorDown = new JoystickButton(rightJoy, 2);
		linearActuatorUp2 = new JoystickButton(auxJoy, 3);
		linearActuatorDown2 = new JoystickButton(auxJoy, 2);
		linearActuatorUp3 = new JoystickButton(auxJoy, 6);
		linearActuatorDown3 = new JoystickButton(auxJoy, 7);
		autoGrabBox = new JoystickButton(leftJoy, 3);
		autoGrabBox2 = new JoystickButton(auxJoy, 11);
		
		//Solenoid Buttons
		solToggle = new JoystickButton(leftJoy, 1);
		solExtend = new JoystickButton(leftJoy, 7);//close claw
		solRetract = new JoystickButton(leftJoy, 6);//open claw
		pushBox = new JoystickButton(rightJoy, 1);
		aimBox = new JoystickButton(auxJoy, 10);
		
		//Sends the starting values of the joysticks to the Shuffleboard
		RobotMap.updateValue(RobotMap.joyStickSubTable, RobotMap.leftJoyXID, leftJoy.getX());
		RobotMap.updateValue(RobotMap.joyStickSubTable, RobotMap.leftJoyYID, leftJoy.getY());
		RobotMap.updateValue(RobotMap.joyStickSubTable, RobotMap.leftJoyZID, leftJoy.getZ());
		RobotMap.updateValue(RobotMap.joyStickSubTable, RobotMap.rightJoyXID, rightJoy.getX());
		RobotMap.updateValue(RobotMap.joyStickSubTable, RobotMap.rightJoyYID, rightJoy.getY());
		RobotMap.updateValue(RobotMap.joyStickSubTable, RobotMap.rightJoyZID, rightJoy.getZ());

		//Strafe Commands	
		strafeRight.whileHeld(new StrafeRight((double)RobotMap.getValue(RobotMap.mecanumSubTable, RobotMap.strafePowerID)));
		strafeLeft.whileHeld(new StrafeLeft((double)RobotMap.getValue(RobotMap.mecanumSubTable, RobotMap.strafePowerID)));
		
		//Linear actuator commands
		//linearActuatorUp.whileHeld(new MovePotUp((double)RobotMap.getValue(RobotMap.linearActuatorSubTable, RobotMap.LASpeedID)));
		linearActuatorUp.whileHeld(new MovePotUp());
		linearActuatorUp.whenReleased(new StopPot());
		linearActuatorUp2.whileHeld(new MovePotUp());
		linearActuatorUp2.whenReleased(new StopPot());		
		linearActuatorUp3.whileHeld(new MovePotUp());
		linearActuatorUp3.whenReleased(new StopPot());		
		//linearActuatorDown.whileHeld(new MovePotDown(-(double)RobotMap.getValue(RobotMap.linearActuatorSubTable, RobotMap.LASpeedID)));
		linearActuatorDown.whileHeld(new MovePotDown());
		linearActuatorDown.whenReleased(new StopPot());
		linearActuatorDown2.whileHeld(new MovePotDown());
		linearActuatorDown2.whenReleased(new StopPot());
		linearActuatorDown3.whileHeld(new MovePotDown());
		linearActuatorDown3.whenReleased(new StopPot());
		//linearActuatorSwitch.whileHeld(new MovePotSwitch((double)RobotMap.getValue(RobotMap.linearActuatorSubTable, RobotMap.LASpeedID)));
		//linearActuatorSwitch.whenReleased(new StopPot());	
		
		//Claw Commands
		solToggle.whenPressed(new ToggleSolenoid());
		solExtend.whileHeld(new ExtendSolenoid());
		solRetract.whileHeld(new RetractSolenoid());
		pushBox.whenPressed(new PushBox());
		autoGrabBox.whenPressed(new AutoGrab());
		
		//Vison Comomands
		aimBox.whenPressed(new AimForBox());
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