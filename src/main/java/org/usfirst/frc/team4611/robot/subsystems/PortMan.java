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

    public static final String pcm0_label = "PCM0";
	public static final String pcm1_label = "PCM1";
    public static final String pcm2_label = "PCM2";
    public static final String pcm3_label = "PCM3";
    public static final String pcm4_label = "PCM4";
    public static final String pcm5_label = "PCM5";
    public static final String pcm6_label = "PCM6";
    public static final String pcm7_label = "PCM7";


    public static final String can_10_label = "CAN10";
    public static final String can_11_label = "CAN11";
    public static final String can_12_label = "CAN12";
    public static final String can_13_label = "CAN13";
    public static final String can_14_label = "CAN14";
    public static final String can_15_label = "CAN15";
    public static final String can_16_label = "CAN16";
    public static final String can_17_label = "CAN17";
    public static final String can_18_label = "CAN18";
    public static final String can_19_label = "CAN19";
    public static final String can_20_label = "CAN20";
    public static final String can_21_label = "CAN21";
    public static final String can_22_label = "CAN22";
    public static final String can_23_label = "CAN23";
    public static final String can_24_label = "CAN24";
    public static final String can_25_label = "CAN25";
    public static final String can_26_label = "CAN26";
    public static final String can_27_label = "CAN27";
    public static final String can_28_label = "CAN28";
    public static final String can_29_label = "CAN29";
    public static final String can_30_label = "CAN30";
    public static final String can_31_label = "CAN31";
    public static final String can_32_label = "CAN32";
    public static final String can_33_label = "CAN33";
    public static final String can_34_label = "CAN34";
    public static final String can_35_label = "CAN35";
    public static final String can_36_label = "CAN36";
    public static final String can_37_label = "CAN37";
    public static final String can_38_label = "CAN38";
    public static final String can_39_label = "CAN39";
    public static final String can_40_label = "CAN40";
    public static final String can_41_label = "CAN41";
    public static final String can_42_label = "CAN42";
    public static final String can_43_label = "CAN43";
    public static final String can_44_label = "CAN44";
    public static final String can_45_label = "CAN45";
    public static final String can_46_label = "CAN46";
    public static final String can_47_label = "CAN47";
    public static final String can_48_label = "CAN48";
    public static final String can_49_label = "CAN49";

    public PortMan() {
        allocatedPorts  = new HashMap<String, String> ();
    }

    public int acquirePort(String label, String requestedDevice) throws Exception {
        String device   = allocatedPorts.get(label);

        if (device != null) {
            throw new Exception ("Port [" + label + "] already allocated to device [" + device + "]");
        }
        // remember that we allocated it
        allocatedPorts.put(label, requestedDevice);

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

            case pcm0_label: return 0;
            case pcm1_label: return 1;
            case pcm2_label: return 2;
            case pcm3_label: return 3;
            case pcm4_label: return 4;
            case pcm5_label: return 5;
            case pcm6_label: return 6;
            case pcm7_label: return 7;

            case can_10_label: return 10;
            case can_11_label: return 11;
            case can_12_label: return 12;
            case can_13_label: return 13;
            case can_21_label: return 21;
            case can_17_label: return 17;

        }

        throw new Exception ("Unknown port identifier [" + label + "]") ;
    }
}