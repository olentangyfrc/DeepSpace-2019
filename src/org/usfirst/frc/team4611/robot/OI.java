package org.usfirst.frc.team4611.robot;

import org.usfirst.frc.team4611.robot.commands.ActivateButtonExample;

import edu.wpi.first.wpilibj.GenericHID;
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
	public XboxController controller;
	
	public Button buttonExample;
	
	public OI (){
		if(RobotMap.useXbox)
			controller = new XboxController(RobotMap.controllerPort); //The Xbox controller exists on this port in robot map
		else
		{
			leftJoy = new Joystick(RobotMap.leftJoyPort); //The left joystick exists on this port in robot map
			rightJoy = new Joystick(RobotMap.rightJoyPort); //The right joystick exists on this port in robot map
		}
		
		
		
		buttonExample = new JoystickButton(determinedDrive('l'), 1);
		this.buttonExample.whileHeld(new ActivateButtonExample());
	}
	
	public double filter(double raw) //We pass joystick values through the filter here and edit the raw value
    {
        if (Math.abs(raw) < .15) {
            return 0; //If the value passed is less than 15% ignore it. This is reffered to as a deadzone
        } else {
            return  raw * 0.7; //Set the output to a ceratin percent of of the input
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
}
