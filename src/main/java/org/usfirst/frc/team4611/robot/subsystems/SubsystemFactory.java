package org.usfirst.frc.team4611.robot.subsystems;

import java.util.logging.Logger;

import org.usfirst.frc.team4611.robot.OI;
import org.usfirst.frc.team4611.robot.OzoneException;
import org.usfirst.frc.team4611.robot.subsystems.PortMan;
//import org.usfirst.frc.team4611.robot.subsystems.doublewheel.DoubleWheel;
import org.usfirst.frc.team4611.robot.subsystems.drivetrain.TalonMecanum;
import org.usfirst.frc.team4611.robot.subsystems.drivetrain.TurboTankDrive;
import org.usfirst.frc.team4611.robot.subsystems.drivetrain.interfaces.DriveTrain;

import org.usfirst.frc.team4611.robot.subsystems.elevator.Elevator;
import org.usfirst.frc.team4611.robot.subsystems.elevator.commands.MoveElevatorUp;
import org.usfirst.frc.team4611.robot.subsystems.elevator.commands.MoveElevatorDown;

import org.usfirst.frc.team4611.robot.subsystems.claw.Claw;
import org.usfirst.frc.team4611.robot.subsystems.claw.commands.GrabBox;
import org.usfirst.frc.team4611.robot.subsystems.claw.commands.ReleaseBox;

import org.usfirst.frc.team4611.robot.subsystems.arm.Arm;
import org.usfirst.frc.team4611.robot.subsystems.arm.commands.MovePotDown;
import org.usfirst.frc.team4611.robot.subsystems.arm.commands.MovePotUp;

/*
import org.usfirst.frc.team4611.robot.subsystems.kicker.Kicker;
import org.usfirst.frc.team4611.robot.subsystems.petal.Petal;
import org.usfirst.frc.team4611.robot.subsystems.spatula.Spatula;
import org.usfirst.frc.team4611.robot.subsystems.navigation.Navigation;
import org.usfirst.frc.team4611.robot.subsystems.navigation.sensors.LineTracker;
import org.usfirst.frc.team4611.robot.subsystems.trianglehatch.TriangleHatch;
import org.usfirst.frc.team4611.robot.subsystems.stick.Stick;
import org.usfirst.frc.team4611.robot.subsystems.vision.Vision;
import org.usfirst.frc.team4611.robot.subsystems.WheelIntake.WheelIntake;
import org.usfirst.frc.team4611.robot.subsystems.WheelIntake.commands.EjectBall;
import org.usfirst.frc.team4611.robot.subsystems.WheelIntake.commands.IntakeGroup;
import org.usfirst.frc.team4611.robot.subsystems.WheelIntake.commands.TopLoader;
import org.usfirst.frc.team4611.robot.subsystems.WheelIntake.commands.StopWheelIntake;
import org.usfirst.frc.team4611.robot.subsystems.WheelIntake.commands.TakeInBall;
import org.usfirst.frc.team4611.robot.subsystems.vision.commands.RumbleJoystick;
import org.usfirst.frc.team4611.robot.subsystems.vision.commands.StrafeVision;
import org.usfirst.frc.team4611.robot.subsystems.elevator.Elevator;

import org.usfirst.frc.team4611.robot.subsystems.elevator.commands.MoveElevator;
import org.usfirst.frc.team4611.robot.subsystems.elevator.commands.StopElevator;
*/

public class SubsystemFactory {
    private static SubsystemFactory    me;
    static Logger logger = Logger.getLogger(SubsystemFactory.class.getName());

    private static String   botMacAddress;  // value of environment variable for MAC Address
    
    private String   jankyMacAddress    = "00:80:2F:17:F8:3F";   
    private String   wonkyMacAddress    = "00:80:2F:27:1D:E9";
    private String   zippyMacAddress    = "00:80:2F:25:B4:CA";
    private String   turboMacAddress    = "00:80:2F:27:04:C6";
    private String   footballMacAddress = "00:80:2F:17:D7:4B";

    private OI oi;

    private PortMan portMan  = new PortMan();

    private DriveTrain driveTrain;
    private Elevator elevator;
    private Claw claw;
    private Arm arm;

    /*
    private Petal petal; 
    private Navigation nav;
    private TriangleHatch triangleHatch;
    private Stick stick;
    private Spatula spatula;
    private Kicker kicker;
    private Vision vision;
    private WheelIntake intake;
    private Elevator elevator;
    private DoubleWheel doubleWheel;
    private LineTracker lineTracker;
    */

    private SubsystemFactory() {
        // private constructor to enforce Singleton pattern
    }

    public static SubsystemFactory getInstance() {
        if (me == null) {
            me  = new SubsystemFactory();
        }
        return me;
    }

    public void init() throws Exception {

        logger.info("intializing");
        
        botMacAddress   = System.getenv("MAC_ADDRESS");
        if (botMacAddress == null) {
            throw new OzoneException("Could not find MAC Address for this bot. Make sure /home/lvuser/.bash_profile is correct");
        }

        try {
            oi  = OI.getInstance();
            oi.init();

            // subsystems common to every bot
            initCommon();
            System.out.println("["+botMacAddress+"]");
            if (botMacAddress.equals(jankyMacAddress)) {
                initJanky();
            } else if (botMacAddress.equals(footballMacAddress)) {
                initFootball();
            } else if (botMacAddress.equals(wonkyMacAddress)) {
                initWonky();
            } else if (botMacAddress.equals(zippyMacAddress)) {
                initZippy();
            } else if ( botMacAddress.equals(turboMacAddress)) {
                initTurbo();
            } else {
                logger.severe("Unrecognized MAC Address [" + botMacAddress + "]");
            } 
        } catch (Exception e) {
            throw new OzoneException(e.getMessage());
        }
    }

    /**
     * init subsystems that are common to all bots
     */
    private void initCommon() {
    }

    /**
     * init subsytems specific to Janky
     */
    private void initJanky() throws Exception{
    }
    
    /**
     * init subsytems specific to Wonky
     */

    
    private void initWonky() throws Exception {
    } 

    /**
     * init subsytems specific to Zippy
     */
    private void initZippy() throws Exception {
        logger.info("initalizing Zippy");

        driveTrain = new TalonMecanum();
        driveTrain.init(portMan);

        elevator = new Elevator();
        elevator.init(portMan);

        claw = new Claw();
        claw.init(portMan);

        arm = new Arm();
        arm.init(portMan);
        
        //vision = new Vision();
        //vision.init();

        //nav = new Navigation();
        //nav.init(portMan);
        
        oi.bind(new MoveElevatorUp(), OI.LeftJoyButton3, OI.WhileHeld);
        oi.bind(new MoveElevatorDown(), OI.LeftJoyButton2, OI.WhileHeld);
        //oi.bind(new StrafeVision(), OI.LeftJoyButton1, OI.WhenPressed);

        oi.bind(new GrabBox(), OI.LeftJoyButton4, OI.WhenPressed);
        oi.bind(new ReleaseBox(), OI.LeftJoyButton5, OI.WhenPressed);

        oi.bind(new MovePotUp(), OI.RightJoyButton3, OI.WhileHeld);
        oi.bind(new MovePotDown(), OI.RightJoyButton2, OI.WhileHeld);        
    
        oi.bind(new MoveElevatorUp(), OI.AuxJoyButton6, OI.WhileHeld);
        oi.bind(new MoveElevatorDown(), OI.AuxJoyButton7, OI.WhileHeld);
    }
    
    /**
     * init subsytems specific to Turbo
     */
    private void initTurbo() throws Exception {
    }

    /**
     * init subsystems specific to Football
     */
    private void initFootball() throws Exception {
    }

    public DriveTrain getDriveTrain(){
        return driveTrain;
    }

    public Elevator getElevator(){
        return elevator;
    }

    public Claw getClaw(){
        return claw;
    }

    public Arm getArm(){
        return arm;
    }
}
