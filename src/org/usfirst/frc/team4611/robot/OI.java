
package org.usfirst.frc.team4611.robot;

import org.usfirst.frc.team4611.robot.commands.ActivateButtonExample;

import edu.wpi.first.wpilibj.GenericHID;
import org.usfirst.frc.team4611.robot.commands.drive.StrafeLeft;
import org.usfirst.frc.team4611.robot.commands.drive.StrafeRight;
import org.usfirst.frc.team4611.robot.commands.solenoid.ExtendSolenoid;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;


/**
 * This is where we create all of out buttons and joysticks and 
 * set up the "UI" of the bot for the drivers. You're gonna end up 
 * here a lot when people complain about buttons needing to be changed
 */

public class OI {
	public Joystick leftJoy;
	public Joystick rightJoy;
	public Joystick armJoy;
	public XboxController controller;
	
	public Button buttonExample;
	public Button strafeLeft;
	public Button strafeRight;
	public Button grabber;

	public OI (){
		/*if(RobotMap.useXbox)
			controller = new XboxController(0); //The Xbox controller exists on this port in robot map
		else
		{*/
			leftJoy = new Joystick(RobotMap.leftJoyPort); //The left joystick exists on this port in robot map
			rightJoy = new Joystick(RobotMap.rightJoyPort); //The right joystick exists on this port in robot map
			armJoy = new Joystick(RobotMap.armJoyPort);
			//}
		buttonExample = new JoystickButton(determinedDrive('l'), 1);
		this.buttonExample.whileHeld(new ActivateButtonExample());
		grabber= new JoystickButton(armJoy, 1);
		this.grabber.toggleWhenPressed(new ExtendSolenoid());
		
		leftJoy = new Joystick(RobotMap.leftJoyPort); //The left joystick exists on this port in robot map
		rightJoy = new Joystick(RobotMap.rightJoyPort); //The right joystick exists on this port in robot map
		strafeLeft= new JoystickButton(rightJoy, 4);
		strafeRight= new JoystickButton(rightJoy, 5);
	
		//Sends the starting values of the joysticks to the Shuffleboard
		RobotMap.updateValue(RobotMap.joyStickSubTable, RobotMap.leftJoyXID, leftJoy.getX());
		RobotMap.updateValue(RobotMap.joyStickSubTable, RobotMap.leftJoyYID, leftJoy.getY());
		RobotMap.updateValue(RobotMap.joyStickSubTable, RobotMap.leftJoyZID, leftJoy.getZ());
		RobotMap.updateValue(RobotMap.joyStickSubTable, RobotMap.rightJoyXID, rightJoy.getX());
		RobotMap.updateValue(RobotMap.joyStickSubTable, RobotMap.rightJoyYID, rightJoy.getY());
		RobotMap.updateValue(RobotMap.joyStickSubTable, RobotMap.rightJoyZID, rightJoy.getZ());

		this.strafeRight.whileHeld(new StrafeRight((double)RobotMap.getValue(RobotMap.mecanumSubTable, RobotMap.strafePowerID)));
		this.strafeLeft.whileHeld(new StrafeLeft((double)RobotMap.getValue(RobotMap.mecanumSubTable, RobotMap.strafePowerID)));
		this.strafeRight.whileHeld(new StrafeRight((double)RobotMap.getValue(RobotMap.mecanumSubTable, RobotMap.strafePowerID)));
		this.strafeLeft.whileHeld(new StrafeLeft((double)RobotMap.getValue(RobotMap.mecanumSubTable, RobotMap.strafePowerID)));
	}
	
	public double filter(double raw) //We pass joystick values through the filter here and edit the raw value
    {
        if (Math.abs(raw) < (double)RobotMap.getValue(RobotMap.mecanumSubTable, RobotMap.deadZoneID)) {
            return 0; //If the value passed is less than 15% ignore it. This is reffered to as a deadzone
        } else {
            return  raw * (double)RobotMap.getValue(RobotMap.mecanumSubTable, RobotMap.motorPowerID); //Set the output to a ceratin percent of of the input
        }
    }
	
	public GenericHID determinedDrive(char joystick)
	{
		if(RobotMap.useXbox)
			return controller;
		else if(joystick == 'l')
			return leftJoy;
		return rightJoy;
	}
	public double strafeFilter(double raw) {
		if (Math.abs(raw) < (double)RobotMap.getValue(RobotMap.mecanumSubTable, RobotMap.deadZoneID)) {
            return 0; //If the value passed is less than 15% ignore it. This is reffered to as a deadzone
        } else {
            return  raw * Math.min((double)RobotMap.getValue(RobotMap.mecanumSubTable, RobotMap.motorPowerID) * 2, 1); //Set the output to a ceratin percent of of the input
        }
	}	
}
