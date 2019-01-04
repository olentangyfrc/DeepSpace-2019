package org.usfirst.frc.team4611.robot.subsystems;


import edu.wpi.first.wpilibj.command.Subsystem;

class SubsystemFactory {
    private Subsystem   s;
    private static SubsystemFactory    me;
    private static String   botMacAddress;  // value of environment variable for MAC Address
    private static String   bot1MacAddress   = "themacaddressofbot1";
    private static String   bot2MacAddress   = "themacaddressofbot2";
    private static String   bot3MacAddress   = "themacaddressofbot3";
    
    private SubsystemFactory() {
        // private constructor to enforce Singleton pattern
    }

    public static SubsystemFactory getInstance() {
        if (me == null) {
            botMacAddress   = System.getenv("macAddress");
            // this should throw Exception there is a null value
            if (botMacAddress != null) {
                me  = new SubsystemFactory();
                me.init();
            }
        }

        return me;
    }

    private void init() {
        
        initCommon();

        if (botMacAddress.equals(bot1MacAddress)) {
            initBot1();
        } else if (botMacAddress.equals(bot2MacAddress)) {
            initBot2();
        } if (botMacAddress.equals(bot3MacAddress)) {
            initBot3();
        } else {}
            System.err.println("Unrecognized MAC Address [" + botMacAddress + "]");
                // should throw an Exception here
        }

    /**
     * init subsystems that are common to all bots
     */
    private void initCommon() {

    }

    /**
     * init subsytems specific to Bot1
     */
    private void initBot1() {

    }

    /**
     * init subsystems specific to Bot2
     */
    private void initBot2() {

    }

    /**
     * init subsystems specific to Bot3
     */
    private void initBot3() {
    }

}