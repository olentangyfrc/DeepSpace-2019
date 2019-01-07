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

    /** @TODO: add all the other ports */

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
        }

        throw new Exception ("Unknown port identifier [" + label + "]") ;
    }
}