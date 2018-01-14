package org.usfirst.frc.team4611.robot;

import org.usfirst.frc.team4611.robot.commands.DriveMotor;

import edu.wpi.first.wpilibj.Joystick;
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
	public Button armUp;
	public Button armDown;
	
	public OI (){
		leftJoy = new Joystick(RobotMap.leftJoyPort); //The left joystick exists on this port in robot map
		rightJoy = new Joystick(RobotMap.rightJoyPort); //The right joystick exists on this port in robot map
		armUp= new JoystickButton(rightJoy,11);
		armDown= new JoystickButton(leftJoy,10);
		this.armUp.whileHeld(new DriveMotor());
		this.armDown.whileHeld(new DriveMotor());
	}
	
	public double filter(double raw) //We pass joystick values through the filter here and edit the raw value
    {
        if (Math.abs(raw) < .15) {
            return 0; //If the value passed is less than 15% ignore it. This is reffered to as a deadzone
        } else {
            return  raw * 0.7; //Set the output to a ceratin percent of of the input
        }
    }
}
