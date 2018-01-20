package org.usfirst.frc.team4611.robot;

import org.usfirst.frc.team4611.robot.commands.ExtendSolenoid;
import org.usfirst.frc.team4611.robot.commands.RetractSolenoid;
import org.usfirst.frc.team4611.robot.commands.StrafeLeft;
import org.usfirst.frc.team4611.robot.commands.StrafeRight;

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
	public Button strafeLeft;
	public Button strafeRight;
	public Button but;

	public OI (){
		
		RobotMap.updateValue("Mecanum", "StrafePower", 0.65);
		RobotMap.updateValue("Mecanum", "motorPower", 0.65);
		
		leftJoy = new Joystick(RobotMap.leftJoyPort); //The left joystick exists on this port in robot map
		rightJoy = new Joystick(RobotMap.rightJoyPort); //The right joystick exists on this port in robot map
		strafeLeft= new JoystickButton(rightJoy, 4);
		strafeRight= new JoystickButton(rightJoy, 5);
		this.strafeRight.whileHeld(new StrafeRight((double)RobotMap.getValue("Mecanum", "StrafePower")));
		this.strafeLeft.whileHeld(new StrafeLeft((double)RobotMap.getValue("Mecanum", "StrafePower")));

		but = new JoystickButton(leftJoy, RobotMap.joyButtonPort);
		but.whileHeld(new ExtendSolenoid());
		
		
	}
	
	public double filter(double raw) //We pass joystick values through the filter here and edit the raw value
    {
        if (Math.abs(raw) < .15) {
            return 0; //If the value passed is less than 15% ignore it. This is reffered to as a deadzone
        } else {
            return  raw * (double)RobotMap.getValue("Mecanum", "motorPower"); //Set the output to a ceratin percent of of the input
        }
    }
	public double yFilter(double raw)
	{
		if (Math.abs(raw)< 0.25) {
			return 0;
		}
		else {
			return raw*0.65;
		}
	
	}
}
