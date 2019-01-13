package org.usfirst.frc.team4611.robot.subsystems;

import java.util.HashMap;

/**
 * Portman provides a control mechanism for subsystems to acquire ports for their
 * devices. It does it my keeping track of ports that have been allocated and throws
 * an exception if a port is requested more than once. It help to track down invalid port assignments
 * 
 * Usage of this would look something like the following in a hypothetical mecanum:
 * 
 * public class Mecanum {
 * TalonSRX frontRight;
 * 
 * public init() throws Exception {
 *  frontRight  = new TalonSRX(portMan.acquirePort(PortMan.digital0_label, "Mecanum.frontRight"));
 *
 */

public class PortMan {
    public static String pcm0_label;

	public static String pcm1_label;

	private HashMap <String, String> allocatedPorts;

    public static final String digital0_label   = "DIGITAL0";
    public static final String digital1_label   = "DIGITAL1";
    public static final String digital2_label   = "DIGITAL2";    
    public static final String digital3_label   = "DIGITAL3";
    public static final String digital4_label   = "DIGITAL4";
    public static final String digital5_label   = "DIGITAL5";
    public static final String digital6_label   = "DIGITAL6";
    public static final String digital7_label   = "DIGITAL7";
    public static final String digital8_label   = "DIGITAL8";
    public static final String digital9_label   = "DIGITAL9";

    public static final String analog0_label = "ANALOG0";
    public static final String analog1_label = "ANALOG1";
    public static final String analog2_label = "ANALOG2";
    public static final String analog3_label = "ANALOG3";

    public static final String relay0_label = "RELAY0";
    public static final String relay1_label = "RELAY1";
    public static final String relay2_label = "RELAY2";
    public static final String relay3_label = "RELAY3";

    public static final String pwm0_label = "PWM0";
    public static final String pwm1_label = "PWM1";
    public static final String pwm2_label = "PWM2";
    public static final String pwm3_label = "PWM3";
    public static final String pwm4_label = "PWM4";
    public static final String pwm5_label = "PWM5";
    public static final String pwm6_label = "PWM6";
    public static final String pwm7_label = "PWM7";
    public static final String pwm8_label = "PWM8";
    public static final String pwm9_label = "PWM9";

    public static final String can_rotation_pigeon_angle = "CAN21";
    public static final String can_kicker = "CAN17";

    public PortMan() {
        allocatedPorts  = new HashMap<String, String> ();
    }

    public int acquirePort(String label, String requestedDevice) throws Exception {
        String device   = allocatedPorts.get(label);

        if (device != null) {
            throw new Exception ("Port [" + label + "] already allocated to device [" + device + "]");
        }
        // remember that we allocated it
        allocatedPorts.put(label, device);

        switch (label) {
            case digital0_label: return 0;
            case digital1_label: return 1;
            case digital2_label: return 2;
            case digital3_label: return 3;
            case digital4_label: return 4;
            case digital5_label: return 5;
            case digital6_label: return 6;
            case digital7_label: return 7;
            case digital8_label: return 8;
            case digital9_label: return 9;

            case analog0_label: return 0;
            case analog1_label: return 1;
            case analog2_label: return 2;
            case analog3_label: return 3;

            case relay0_label: return 0;
            case relay1_label: return 1;
            case relay2_label: return 2;
            case relay3_label: return 3;

            case pwm0_label: return 0;
            case pwm1_label: return 1;
            case pwm2_label: return 2;
            case pwm3_label: return 3;
            case pwm4_label: return 4;
            case pwm5_label: return 5;
            case pwm6_label: return 6;
            case pwm7_label: return 7;
            case pwm8_label: return 8;
            case pwm9_label: return 9;

            case can_rotation_pigeon_angle: return 21;
            case can_kicker: return 17;
        }

        throw new Exception ("Unknown port identifier [" + label + "]") ;
    }
}