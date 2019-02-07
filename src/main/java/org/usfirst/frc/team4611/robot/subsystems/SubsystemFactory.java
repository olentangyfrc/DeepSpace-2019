package org.usfirst.frc.team4611.robot.subsystems;

import java.util.logging.Logger;

import org.usfirst.frc.team4611.robot.OI;
import org.usfirst.frc.team4611.robot.OzoneException;
import org.usfirst.frc.team4611.robot.subsystems.PortMan;
import org.usfirst.frc.team4611.robot.subsystems.Roller.Roller;
import org.usfirst.frc.team4611.robot.subsystems.Roller.commands.MoveRollerBackward;
import org.usfirst.frc.team4611.robot.subsystems.Roller.commands.MoveRollerForward;
import org.usfirst.frc.team4611.robot.subsystems.Roller.commands.StopRoller;
import org.usfirst.frc.team4611.robot.subsystems.doublewheel.DoubleWheel;
import org.usfirst.frc.team4611.robot.subsystems.doublewheel.commands.IntakeBall;
import org.usfirst.frc.team4611.robot.subsystems.doublewheel.commands.MoveIndiWheelFrontBackward;
import org.usfirst.frc.team4611.robot.subsystems.doublewheel.commands.MoveIndiWheelFrontForward;
import org.usfirst.frc.team4611.robot.subsystems.doublewheel.commands.MoveIntakeAdjusterBackward;
import org.usfirst.frc.team4611.robot.subsystems.doublewheel.commands.MoveIntakeAdjusterForward;
import org.usfirst.frc.team4611.robot.subsystems.doublewheel.commands.OutTakeBall;
import org.usfirst.frc.team4611.robot.subsystems.doublewheel.commands.StopBall;
import org.usfirst.frc.team4611.robot.subsystems.doublewheel.commands.StopIndiWheelFront;
import org.usfirst.frc.team4611.robot.subsystems.doublewheel.commands.StopIntakeAdjuster;
import org.usfirst.frc.team4611.robot.subsystems.drivetrain.TalonMecanum;
import org.usfirst.frc.team4611.robot.subsystems.drivetrain.TurboTankDrive;
import org.usfirst.frc.team4611.robot.subsystems.drivetrain.interfaces.DriveTrain;
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
import org.usfirst.frc.team4611.robot.subsystems.elevator.commands.KeepElevatorInPlace;
import org.usfirst.frc.team4611.robot.subsystems.elevator.commands.MoveElevatorDown;
import org.usfirst.frc.team4611.robot.subsystems.elevator.commands.MoveElevatorToPos;
import org.usfirst.frc.team4611.robot.subsystems.elevator.commands.MoveElevatorUp;
import org.usfirst.frc.team4611.robot.subsystems.elevator.commands.StopElevator;


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
    private Roller roller;

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
        logger.info("initalizing Janky");
        //driveTrain = new TalonMecanum();
        //driveTrain.init(portMan);

        intake = new WheelIntake();
        intake.init(portMan);

        oi.bind(new EjectBall(), OI.LeftJoyButton3, OI.WhileHeld);
        oi.bind(new TakeInBall(), OI.LeftJoyButton2, OI.WhenPressed);
        
    }
    
    /**
     * init subsytems specific to Wonky
     */

    
    private void initWonky() throws Exception {
        logger.info("initalizing Wonky");
        //driveTrain = new TalonMecanum();
        //driveTrain.init(portMan);

        elevator = new Elevator();
        elevator.init(portMan);

        roller = new Roller();
        roller.init(portMan);

        doubleWheel = new DoubleWheel();
        doubleWheel.init(portMan);

        oi.bind(new MoveElevatorUp(), OI.LeftJoyButton3, OI.WhileHeld);
        oi.bind(new MoveElevatorDown(), OI.LeftJoyButton2, OI.WhileHeld);

        oi.bind(new MoveElevatorToPos(), OI.LeftJoyButton6, OI.WhenPressed);

        oi.bind(new IntakeBall(), OI.RightJoyButton5, OI.WhileHeld);
        oi.bind(new OutTakeBall(), OI.RightJoyButton4, OI.WhileHeld);
        oi.bind(new StopBall(), OI.RightJoyButton4, OI.WhenReleased);
        oi.bind(new StopBall(), OI.RightJoyButton5, OI.WhenReleased);
        oi.bind(new MoveRollerBackward(), OI.LeftJoyButton5, OI.WhileHeld);
        oi.bind(new MoveRollerForward(), OI.LeftJoyButton4, OI.WhileHeld);
        oi.bind(new StopRoller(), OI.LeftJoyButton4, OI.WhenReleased);
        oi.bind(new StopRoller(), OI.LeftJoyButton5, OI.WhenReleased);
        oi.bind(new MoveIndiWheelFrontBackward(), OI.RightJoyButton1, OI.WhileHeld);
        oi.bind(new MoveIndiWheelFrontForward(), OI.RightJoyButton3, OI.WhileHeld);
        oi.bind(new StopIndiWheelFront(), OI.RightJoyButton3, OI.WhenReleased);
        oi.bind(new StopIndiWheelFront(), OI.RightJoyButton1, OI.WhenReleased);
        oi.bind(new MoveIntakeAdjusterBackward(), OI.RightJoyButton11, OI.WhileHeld);
        oi.bind(new MoveIntakeAdjusterForward(), OI.RightJoyButton10, OI.WhileHeld);
        oi.bind(new StopIntakeAdjuster(), OI.RightJoyButton11, OI.WhenReleased);
        oi.bind(new StopIntakeAdjuster(), OI.RightJoyButton10, OI.WhenReleased);

        oi.bind(new KeepElevatorInPlace(), OI.LeftJoyButton1, OI.WhileHeld);

       // oi.bind(new StopElevator(), OI.LeftJoyButton2, OI.WhenReleased);
        //oi.bind(new StopElevator(), OI.LeftJoyButton3, OI.WhenReleased);
    } 

    /**
     * init subsytems specific to Zippy
     */
    private void initZippy() throws Exception {
        logger.info("initalizing Zippy");
        System.out.println("initZippy");
        driveTrain = new TalonMecanum();
        driveTrain.init(portMan);
        
        vision = new Vision();
        vision.init();

        nav = new Navigation();
        nav.init(portMan);
        
        oi.bind(new StrafeVision(), OI.LeftJoyButton1, OI.WhenPressed);
    }
    
    /**
     * init subsytems specific to Turbo
     */
    private void initTurbo() throws Exception {
        logger.info("initalizing Turbo");
        driveTrain = new TurboTankDrive();
        driveTrain.init(portMan);
        intake = new WheelIntake();
        intake.init(portMan);
        oi.bind(new EjectBall(), OI.LeftJoyButton3, OI.WhenPressed);
        oi.bind(new TakeInBall(), OI.LeftJoyButton2, OI.WhileHeld);
        oi.bind(new IntakeGroup(), OI.LeftJoyButton4, OI.WhenPressed);
        oi.bind(new TopLoader(), OI.LeftJoyButton5, OI.WhenPressed);
        oi.bind(new StopWheelIntake(), OI.LeftJoyButton2, OI.WhenReleased);
    }

    /**
     * init subsystems specific to Football
     */
    private void initFootball() throws Exception {
        logger.info("initializing Football");
        // kicker = new Kicker();
        // kicker.init(portMan);

        elevator = new Elevator();
        elevator.init(portMan);

        vision  = new Vision();
        vision.init();
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

    public Stick getStick(){
        return stick;
    }

    public Kicker getKicker(){
        return kicker;
    }

    public Vision getVision() {
        return vision;
    }
  
    public WheelIntake getWheelIntake(){
        return intake;
    }
    
    public Elevator getElevator(){
        return elevator;
    }

    public DoubleWheel getDoubleWheel(){
        return doubleWheel;
    }

    public LineTracker getLineTracker() {
        return lineTracker;
    }

    public Roller getRoller() {
        return roller;
    }
}
