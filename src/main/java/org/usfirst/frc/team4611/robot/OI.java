package org.usfirst.frc.team4611.robot;

import org.usfirst.frc.team4611.robot.subsystems.drivetrain.commands.ActivateTurbo;
import org.usfirst.frc.team4611.robot.subsystems.drivetrain.commands.DeactivateTurbo;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This is where we create all of out buttons and joysticks and set up the "UI"
 * of the bot for the drivers. You're gonna end up here a lot when people
 * complain about buttons needing to be changed
 */

public class OI {
    public static Joystick leftJoy;
    public static Joystick rightJoy;

    public OI() {
        leftJoy = new Joystick(0); // The left joystick exists on this port in robot map
        rightJoy = new Joystick(1); // The right joystick exists on this port in robot map

        Button turboToggle = new JoystickButton(leftJoy, 1);
        turboToggle.whenPressed(new ActivateTurbo());
        turboToggle.whenReleased(new DeactivateTurbo());
    }

    public static double generalJoystickFilter(double raw) // We pass joystick values through the filter here and edit
                                                           // the raw value
    {
        if (Math.abs(raw) < .15) {
            return 0; // If the value passed is less than 15% ignore it. This is reffered to as a
                      // deadzone
        } else {
            return raw * (1); // Set the output to a ceratin percent of of the input
        }
    }

}
