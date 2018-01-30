package org.usfirst.frc.team4611.robot;

import org.usfirst.frc.team4611.robot.commands.auton.AutoGrab;
import org.usfirst.frc.team4611.robot.commands.drive.StrafeLeft;
import org.usfirst.frc.team4611.robot.commands.drive.StrafeRight;
import org.usfirst.frc.team4611.robot.potentiometer.MovePotDown;
import org.usfirst.frc.team4611.robot.potentiometer.MovePotSwitch;
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
	
	//Buttons
	public Button strafeLeft;
	public Button strafeRight;
	public Button linearActuatorUp;
	public Button linearActuatorDown;
	public Button linearActuatorSwitch;
	public Button autoGrabBox;
	public Button solToggle;
	public Button solExtend;
	public Button solRetract;

	public OI (){
		
		//Joystick
		leftJoy = new Joystick(RobotMap.leftJoyPort); //The left joystick exists on this port in robot map
		rightJoy = new Joystick(RobotMap.rightJoyPort); //The right joystick exists on this port in robot map
		
		//Buttons
		strafeLeft= new JoystickButton(rightJoy, 4);
		strafeRight= new JoystickButton(rightJoy, 5);
		autoGrabBox = new JoystickButton(leftJoy, 14);
		solToggle = new JoystickButton(leftJoy, RobotMap.solTogglePort);
		solExtend = new JoystickButton(leftJoy, RobotMap.solExtendPort);
		solRetract = new JoystickButton(leftJoy, RobotMap.solRetractPort);
		linearActuatorUp = new JoystickButton(leftJoy, 3);
		linearActuatorDown = new JoystickButton(leftJoy, 2);
		linearActuatorSwitch = new JoystickButton(rightJoy, 3);
	
		//Sends the starting values of the joysticks to the Shuffleboard
		RobotMap.updateValue(RobotMap.joyStickSubTable, RobotMap.leftJoyXID, leftJoy.getX());
		RobotMap.updateValue(RobotMap.joyStickSubTable, RobotMap.leftJoyYID, leftJoy.getY());
		RobotMap.updateValue(RobotMap.joyStickSubTable, RobotMap.leftJoyZID, leftJoy.getZ());
		RobotMap.updateValue(RobotMap.joyStickSubTable, RobotMap.rightJoyXID, rightJoy.getX());
		RobotMap.updateValue(RobotMap.joyStickSubTable, RobotMap.rightJoyYID, rightJoy.getY());
		RobotMap.updateValue(RobotMap.joyStickSubTable, RobotMap.rightJoyZID, rightJoy.getZ());

		//Button Commands
		//Strafe Commands
		strafeRight.whileHeld(new StrafeRight((double)RobotMap.getValue(RobotMap.mecanumSubTable, RobotMap.strafePowerID)));
		strafeLeft.whileHeld(new StrafeLeft((double)RobotMap.getValue(RobotMap.mecanumSubTable, RobotMap.strafePowerID)));
		
		//Linear actuator commands
		linearActuatorUp.whileHeld(new MovePotUp((double)RobotMap.getValue(RobotMap.linearActuatorSubTable, RobotMap.LASpeedID)));
		linearActuatorUp.whenReleased(new StopPot());
		
		linearActuatorDown.whileHeld(new MovePotDown(-(double)RobotMap.getValue(RobotMap.linearActuatorSubTable, RobotMap.LASpeedID)));
		linearActuatorDown.whenReleased(new StopPot());
		
		linearActuatorSwitch.whileHeld(new MovePotSwitch((double)RobotMap.getValue(RobotMap.linearActuatorSubTable, RobotMap.LASpeedID)));
		linearActuatorSwitch.whenReleased(new StopPot());
		
		//Solenoid Commands
		solToggle.whenPressed(new ToggleSolenoid());
		solExtend.whenPressed(new ExtendSolenoid());
		solRetract.whenPressed(new RetractSolenoid());
		autoGrabBox.whenPressed(new AutoGrab());
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
            return  raw * Math.min((double)RobotMap.getValue(RobotMap.mecanumSubTable, RobotMap.motorPowerID) * 2, 1); //Set the output to a ceratin percent of of the input
        }
	}	
}