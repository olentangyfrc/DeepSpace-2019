package org.usfirst.frc.team4611.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;

/**
 * This is where we create all of out buttons and joysticks and set up the "UI"
 * of the bot for the drivers. You're gonna end up here a lot when people
 * complain about buttons needing to be changed
 */

public class OI {
    private static OI   me;

    private Joystick leftJoy;
    private Joystick rightJoy;

    private double  deadzone    = 0.15;
    private double  scaleFactor = 1.0;

    private OI() {
        // private constructor to enforce Singleton pattern
    }

    static public OI getInstance() {
        if (me == null) {
            me= new OI();
        }

        return me;
    }

    public static final int LeftJoyButton1  = 1;
    public static final int LeftJoyButton2  = 2;
    public static final int LeftJoyButton3  = 3;
    public static final int LeftJoyButton4  = 4;
    public static final int LeftJoyButton5  = 5;
    public static final int LeftJoyButton6  = 6;
    public static final int LeftJoyButton7  = 7;
    public static final int LeftJoyButton8  = 8;
    public static final int LeftJoyButton9  = 9;
    public static final int LeftJoyButton10  = 10;
    public static final int LeftJoyButton11 = 11;

    public static final int RightJoyButton1  = 12;
    public static final int RightJoyButton2  = 13;
    public static final int RightJoyButton3  = 14;
    public static final int RightJoyButton4  = 15;
    public static final int RightJoyButton5  = 16;
    public static final int RightJoyButton6  = 17;
    public static final int RightJoyButton7  = 18;
    public static final int RightJoyButton8  = 19;
    public static final int RightJoyButton9  = 20;
    public static final int RightJoyButton10  = 21;
    public static final int RightJoyButton11  = 22;

    public static final int WhenPressed         = 1;
    public static final int WhenReleased        = 2;
    public static final int WhileHeld           = 3;
    public static final int ToggleWhenPressed   = 4;
    public static final int CancelWhenPressed   = 5;

    public void init() {
       leftJoy = new Joystick(0); // The left joystick exists on this port in robot map
       rightJoy = new Joystick(1); // The right joystick exists on this port in robot map
    }

    public double getLeftJoystickXValue() {
        return getFilteredValue (leftJoy.getX());
    }

    public double getLeftJoystickYValue() {
        return getFilteredValue (leftJoy.getY());
    }

    public double getRightJoystickXValue() {
        return getFilteredValue (rightJoy.getX());
    }

    public double getRightJoystickYValue() {
        return getFilteredValue (rightJoy.getY());
    }

    public void rumbleJoystick(int j) {

        System.out.print("Trying to rumble");
        switch (j) {
            case 0:
                leftJoy.setRumble(RumbleType.kLeftRumble , 0.5);
                break;
            case 1:
                rightJoy.setRumble(RumbleType.kRightRumble, 0.5 );
                break;
            default:
                System.err.println("Invalid Joystick id [" + j + "]");
        }
    }

    /**
     * this method binds a Command to a Joystick button for an action
     * @param c - the Command
     * @param button - which Joystick button to bind
     * @param action - the button action that invokes the Command
     */
    public void bind(Command c, int button, int action) throws Exception {
        Joystick    j;
        System.out.println("Binding command to " + button + " with action " + action);
        // see constants in this file LeftJoyButton1  = 1;
        // see constants in this file RightJoyButton1  = 11;
        // Joystick button values 1-10 are for left joystick
        // Joystick button values 11-20 are for righ joystick
        if (button >= 1 && button <= 11) {
            j   = leftJoy;
        } else if (button >= 12 && button <= 21 ) {
            j   = rightJoy;
            button  -= 11; // adjust the actual button. joystick button ids start at 1
        } else {
            throw new Exception ("Unrecognized joystick button [" + button + "]");
        }

        Button  b = new JoystickButton(j, button);
        // TODO: consider remembering the button for troubleshooting
        
        switch (action) {
            case OI.WhenPressed:
                b.whenPressed(c);
                return;
            case OI.WhenReleased:
                b.whenReleased(c);
                return;
            case OI.WhileHeld:
                b.whileHeld(c);
                return;
            case ToggleWhenPressed:
                b.toggleWhenPressed(c);
                return;
            case OI.CancelWhenPressed:
                b.cancelWhenPressed(c);
                return;
            default:
                throw new Exception ("Unrecognized Button Action [" + action + "]");
        }
    }

    /**
     * filters and scales a joytick value.
     * if value is too small, joystick will seem too sensitive.
     * apply a scaling factor to be able to adjust values
     * @param raw
     * @return
     */
    public double getFilteredValue(double raw)
    {
        if (Math.abs(raw) < deadzone) {
            return 0; 
        } else {
            return raw * (scaleFactor); // Set the output to a ceratin percent of of the input
        }
    }

}
