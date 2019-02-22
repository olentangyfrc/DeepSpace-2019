package org.usfirst.frc.team4611.robot;

import java.util.logging.Logger;

import org.usfirst.frc.team4611.robot.subsystems.SubsystemFactory;
import org.usfirst.frc.team4611.robot.subsystems.elevator.commands.MoveElevatorDown;

import java.util.HashMap;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;

/**
 * This is where we create all of out buttons and joysticks and set up the "UI"
 * of the bot for the drivers. You're gonna end up here a lot when people
 * complain about buttons needing to be changed
 */

public class OI {
    private static OI   me;

    private Joystick leftJoy;
    private Joystick rightJoy;
    private Joystick auxJoy;
    private Joystick buttons;
    private XboxController xbox;  

    static Logger logger = Logger.getLogger(SubsystemFactory.class.getName());
    private static ShuffleboardTab tab = Shuffleboard.getTab("OI");
    
    private double  deadzone    = 0.15;
    private double  scaleFactor = 1.0;

    private HashMap<Integer, String> allocatedJoyButtons = new HashMap<Integer, String>();

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

    public static final int AuxJoyButton1  = 23;
    public static final int AuxJoyButton2  = 24;
    public static final int AuxJoyButton3  = 25;
    public static final int AuxJoyButton4  = 26;
    public static final int AuxJoyButton5  = 27;
    public static final int AuxJoyButton6  = 28;
    public static final int AuxJoyButton7  = 29;
    public static final int AuxJoyButton8  = 30;
    public static final int AuxJoyButton9  = 31;
    public static final int AuxJoyButton10  = 32;
    public static final int AuxJoyButton11  = 33;

    public static final int button1 = 34;
    public static final int button2 = 35;
    public static final int button3 = 36; 
    public static final int button4 = 37; 
    public static final int button5 = 38; 
    public static final int button6 = 39; 
    public static final int button7 = 40; 
    public static final int button8 = 41;
    public static final int button9 = 42; 
    public static final int button10 = 43; 

    public static final int WhenPressed         = 1;
    public static final int WhenReleased        = 2;
    public static final int WhileHeld           = 3;
    public static final int ToggleWhenPressed   = 4;
    public static final int CancelWhenPressed   = 5;

    public void init() {
       leftJoy = new Joystick(0); // The left joystick exists on this port in robot map
       rightJoy = new Joystick(1); // The right joystick exists on this port in robot map
       auxJoy = new Joystick(2);
       buttons = new Joystick(4);
       xbox = new XboxController(3);
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

    public double getLeftXboxYValue(){
        return getFilteredValue(xbox.getY(Hand.kLeft));
    }
    public double getRightXboxYValue(){
        return getFilteredValue(xbox.getY(Hand.kRight));
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
                logger.severe("Invalid Joystick id [" + j + "]");
        }
    }

    /**
     * this method binds a Command to a Joystick button for an action
     * @param c - the Command
     * @param button - which Joystick button to bind
     * @param action - the button action that invokes the Command
     */
    public void bind(Command c, int button, int action) throws OzoneException {
        Joystick    j;
        logger.info("Binding command to " + button + " with action " + action);
        // see constants in this file LeftJoyButton1  = 1;
        // see constants in this file RightJoyButton1  = 11;
        // Joystick button values 1-10 are for left joystick
        // Joystick button values 11-20 are for righ joystick
        
        if(allocatedJoyButtons.get(button) != null) {
            if(action == 2) {
                logger.info("ONLY OK BECAUSE THIS IS A WHEN RELEASED COMMAND");
            }
            else {
                throw new OzoneException((button >= 1 && button <= 11 ? "Left" : (button >= 12 && button <= 22) ? "Right" : (button >= 23 && button <= 33) ? "Aux" : "Buttons") +
                    " Joystick Button [" + (button >= 12 && button <= 21 ? (button-11) : button >= 23 && button <= 33 ? (button-22) : button >= 34 && button <= 44 ? (button-33) : button) + 
                    "] is already taken by [" + allocatedJoyButtons.get(button) + 
                    "] when asked for by [ " + c.getClass().getName() + "]");
                    //logger.log("MULTI BUTTON LINKAGE");
            }
        }
        
        allocatedJoyButtons.put(button, c.getClass().getName());

        if (button >= 1 && button <= 11) {
            j   = leftJoy;
        } else if (button >= 12 && button <= 22 ) {
            j   = rightJoy;
            button  -= 11; // adjust the actual button. joystick button ids start at 1
        } else if (button >= 23 && button <= 33) {
            j = auxJoy;
            button -= 22;
        }
        else if(button >= 34 && button <= 43){
            j = buttons;
            button -= 33;
        } else {
            throw new OzoneException ("Unrecognized joystick button [" + button + "]");
        }

        tab.add("joy[" + j + "] b[" + button + "]", c.getClass().getName());

        Button  b = new JoystickButton(j, button);
        
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
                throw new OzoneException ("Unrecognized Button Action [" + action + "]");
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
