package org.usfirst.frc.team4611.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

import org.usfirst.frc.team4611.robot.OI;
import org.usfirst.frc.team4611.robot.subsystems.PortMan;
import org.usfirst.frc.team4611.robot.subsystems.drivetrain.TalonMecanum;
import org.usfirst.frc.team4611.robot.subsystems.drivetrain.interfaces.DriveTrain;
import org.usfirst.frc.team4611.robot.subsystems.petal.Petal;
import org.usfirst.frc.team4611.robot.subsystems.spatula.Spatula;
import org.usfirst.frc.team4611.robot.subsystems.navigation.Navigation;
import org.usfirst.frc.team4611.robot.subsystems.trianglehatch.TriangleHatch;

public class SubsystemFactory {
    private Subsystem   s;
    private static SubsystemFactory    me;
    private static String   botMacAddress;  // value of environment variable for MAC Address
    
    private String   bot1MacAddress   = "themacaddressofbot1";
    private String   bot2MacAddress   = "themacaddressofbot2";
    private String   bot3MacAddress   = "themacaddressofbot3";

    private OI oi;

    private PortMan portMan  = new PortMan();

    private DriveTrain driveTrain;
    private Petal petal;
    private Navigation nav;
    private TriangleHatch triangleHatch;
    private Spatula spatula;

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
        oi = new OI();
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
        driveTrain = new TalonMecanum();
        //nav = new Navigation();
    }

    /**
     * init subsystems specific to Bot3
     */
    private void initBot3() {
    }

    public DriveTrain getDriveTrain(){
        return driveTrain;
    }

    public Petal getPetal(){
        return petal;
    }
    
    public Navigation getNavigation(){
        return nav;
    }

    public TriangleHatch getTriangleHatch(){
        return triangleHatch;
    }

    public Spatula getSpatula(){
        return spatula;
    }
}