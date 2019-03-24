package org.usfirst.frc.team4611.robot.subsystems;

import java.util.logging.Logger;

import org.usfirst.frc.team4611.robot.FUWpi;
import org.usfirst.frc.team4611.robot.OI;
import org.usfirst.frc.team4611.robot.OzoneException;
import org.usfirst.frc.team4611.robot.subsystems.PortMan;
import org.usfirst.frc.team4611.robot.subsystems.Intake.Intake;
import org.usfirst.frc.team4611.robot.subsystems.Intake.commands.IntakeBackward;
import org.usfirst.frc.team4611.robot.subsystems.Intake.commands.IntakeBackwardSlower;
import org.usfirst.frc.team4611.robot.subsystems.Intake.commands.IntakeForward;
import org.usfirst.frc.team4611.robot.subsystems.IntakeAdjuster.IntakeAdjuster;
import org.usfirst.frc.team4611.robot.subsystems.IntakeAdjuster.commands.MoveAdjusterToPos;
import org.usfirst.frc.team4611.robot.subsystems.IntakeAdjuster.commands.MoveIntakeAdjusterBackward;
import org.usfirst.frc.team4611.robot.subsystems.IntakeAdjuster.commands.MoveIntakeAdjusterForward;
import org.usfirst.frc.team4611.robot.subsystems.Roller.Roller;
import org.usfirst.frc.team4611.robot.subsystems.Roller.commands.MoveRollerBackward;
import org.usfirst.frc.team4611.robot.subsystems.Roller.commands.MoveRollerForward;
import org.usfirst.frc.team4611.robot.subsystems.Roller.commands.MoveRollerSlowForward;
import org.usfirst.frc.team4611.robot.subsystems.doublewheel.DoubleWheel;
import org.usfirst.frc.team4611.robot.subsystems.doublewheel.commands.IntakeBall;
import org.usfirst.frc.team4611.robot.subsystems.doublewheel.commands.OutTakeBall;
import org.usfirst.frc.team4611.robot.subsystems.driverfeedback.DriverFeedback;
import org.usfirst.frc.team4611.robot.subsystems.drivetrain.SparkMecanum;
import org.usfirst.frc.team4611.robot.subsystems.drivetrain.TalonMecanum;
import org.usfirst.frc.team4611.robot.subsystems.drivetrain.TankDrive;
import org.usfirst.frc.team4611.robot.subsystems.drivetrain.TurboTankDrive;
import org.usfirst.frc.team4611.robot.subsystems.drivetrain.interfaces.DriveTrain;
import org.usfirst.frc.team4611.robot.subsystems.kicker.Kicker;
import org.usfirst.frc.team4611.robot.subsystems.petal.Petal;
import org.usfirst.frc.team4611.robot.subsystems.spatula.Spatula;
import org.usfirst.frc.team4611.robot.subsystems.navigation.Navigation;
import org.usfirst.frc.team4611.robot.subsystems.navigation.sensors.LineTracker;
import org.usfirst.frc.team4611.robot.subsystems.trianglehatch.TriangleHatch;
import org.usfirst.frc.team4611.robot.subsystems.unicornHorn.UnicornHorn;
import org.usfirst.frc.team4611.robot.subsystems.unicornHorn.UnicornHorn;
import org.usfirst.frc.team4611.robot.subsystems.hook.Hook;
import org.usfirst.frc.team4611.robot.subsystems.hook.commands.MoveHook;
import org.usfirst.frc.team4611.robot.subsystems.unicornHorn.commands.Push;
import org.usfirst.frc.team4611.robot.subsystems.unicornHorn.commands.Retract;
import org.usfirst.frc.team4611.robot.subsystems.systemstatus.SystemStatus;
import org.usfirst.frc.team4611.robot.subsystems.vision.Vision;
import org.usfirst.frc.team4611.robot.subsystems.WheelIntake.WheelIntake;
import org.usfirst.frc.team4611.robot.subsystems.vision.commands.ChooseCamera;
import org.usfirst.frc.team4611.robot.subsystems.vision.commands.StrafeVision;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;

import org.usfirst.frc.team4611.robot.subsystems.elevator.Elevator;
import org.usfirst.frc.team4611.robot.subsystems.elevator.commands.KeepElevatorInPlace;
import org.usfirst.frc.team4611.robot.subsystems.elevator.commands.MoveElevatorToLevel;
import org.usfirst.frc.team4611.robot.subsystems.elevator.commands.MoveHatch;
import org.usfirst.frc.team4611.robot.subsystems.elevator.commands.MoveElevator;
import org.usfirst.frc.team4611.robot.subsystems.pixyCam.PixyCam;
import org.usfirst.frc.team4611.robot.subsystems.pixyLineCam.PixyLineCam;
import org.usfirst.frc.team4611.robot.subsystems.climber.Climber;
import org.usfirst.frc.team4611.robot.subsystems.climber.commands.SPush;
import org.usfirst.frc.team4611.robot.subsystems.climber.commands.SRetract;


public class SubsystemFactory {
    private static SubsystemFactory    me;
    static Logger logger = Logger.getLogger(SubsystemFactory.class.getName());

    private static String   botMacAddress;  // value of environment variable for MAC Address
    
    private String   protoMacAddress    = "00:80:2F:22:D7:BC";   
    private String   blueMacAddress    = "00:80:2F:27:1D:E9";
    private String   zippyMacAddress    = "00:80:2F:25:B4:CA";
    private String   turboMacAddress    = "00:80:2F:27:04:C6";
    private String   footballMacAddress = "00:80:2F:17:D7:4B";
    private String   newbieMacAddress   = "00:80:2F:17:F8:3F";

    private OI oi;

    private PortMan portMan  = new PortMan();

    /**
     * keep all available subsystem declarations here.
     */
    private DriveTrain driveTrain;
    private Petal petal; 
    private Navigation nav;
    private TriangleHatch triangleHatch;
    private UnicornHorn unicornHorn;
    private Hook hook;
    private Spatula spatula;
    private Kicker kicker;
    private Vision vision;
    private WheelIntake intake;
    private Elevator elevator;
    private DoubleWheel doubleWheel;
    private LineTracker lineTracker;
    private Roller roller;
    private Intake shooterIntake;
    private IntakeAdjuster intakeAdjuster;
    private PixyCam pixyCam;
    private PixyLineCam pixyLineCam;
    private DriverFeedback  driverFeedback;
    private org.usfirst.frc.team4611.robot.subsystems.climber.Climber climber;
    private SystemStatus systemStatus;
  
    private UsbCamera camera1;
  
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
        logger.info("["+botMacAddress+"]");
        if (botMacAddress == null) {
            throw new OzoneException("Could not find MAC Address for this bot. Make sure /home/lvuser/.bash_profile is correct");
        }

        try {
            // create SB tabs that we want to see first now
            Shuffleboard.getTab("DriverFeedback");
            Shuffleboard.selectTab("DriverFeedback");
            Shuffleboard.getTab("SystemStatus");
            Shuffleboard.getTab("Elevator");

            systemStatus = new SystemStatus();
            systemStatus.init();

            oi  = OI.getInstance();
            oi.init();

            //subsystems common to every bot
            if (botMacAddress.equals(protoMacAddress)) {
                initProto();
            } else if (botMacAddress.equals(footballMacAddress) || botMacAddress == null || botMacAddress.equals("")) {
                initFootball();
            } else if (botMacAddress.equals(blueMacAddress)) {
                initComp();
            } else if (botMacAddress.equals(zippyMacAddress)) {
                initZippy();
            } else if (botMacAddress.equals(turboMacAddress)) {
                initTurbo();
            } else if (botMacAddress.equals(newbieMacAddress)) {
                initNewbie();
            } else {
                logger.severe("Unrecognized MAC Address [" + botMacAddress + "]");
            }

            initCommon();
            // driverfeedback will create a shuffleboard tab that aggregates data from subsystems.
            driverFeedback = new DriverFeedback();
            driverFeedback.init();
        } catch (Exception e) {
            e.printStackTrace();
            throw new OzoneException(e.getMessage());
        }
    }

    /**
     * init subsystems that are common to all bots
     */
    private void initCommon() {
    }

     /**
     * init subsytems specific to Proto
     */
    private void initProto() throws Exception{
        logger.info("initalizing Proto");

        elevator = new Elevator();
        elevator.init(portMan);

        nav = new Navigation();
        nav.init(portMan);
        
        intakeAdjuster = new IntakeAdjuster();
        intakeAdjuster.init(portMan);

        lineTracker = new LineTracker();
        lineTracker.init(portMan);

        roller = new Roller();
        roller.init(portMan);

        doubleWheel = new DoubleWheel();
        doubleWheel.init(portMan);

        shooterIntake = new Intake();
        shooterIntake.init(portMan);

        driveTrain = new SparkMecanum();
        driveTrain.init(portMan);

        unicornHorn = new UnicornHorn();
        unicornHorn.init(portMan);

        hook = new Hook();
        hook.init(portMan);

        climber = new Climber();
        climber.init(portMan);

        oi.bind(new KeepElevatorInPlace(), OI.LeftJoyButton1, OI.WhileHeld);
        
        oi.bind(new MoveHatch(5), OI.LeftJoyButton3, OI.WhenPressed);
        oi.bind(new MoveHatch(-5), OI.LeftJoyButton2, OI.WhenPressed);
        oi.bind(new IntakeBackward(), OI.LeftJoyButton4, OI.ToggleWhenPressed);
        oi.bind(new IntakeBackwardSlower(), OI.LeftJoyButton5, OI.ToggleWhenPressed);

        oi.bind(new IntakeBall(), OI.RightJoyButton5, OI.WhileHeld);
        oi.bind(new OutTakeBall(), OI.RightJoyButton4, OI.ToggleWhenPressed);
        
        oi.bind(new MoveRollerBackward(), OI.RightJoyButton1, OI.WhileHeld);
        oi.bind(new MoveRollerSlowForward(), OI.RightJoyButton2, OI.WhileHeld);
        oi.bind(new MoveRollerForward(), OI.RightJoyButton3, OI.WhileHeld);
        oi.bind(new MoveIntakeAdjusterBackward(), OI.RightJoyButton10, OI.WhileHeld);
        oi.bind(new MoveIntakeAdjusterForward(), OI.RightJoyButton11, OI.WhileHeld);

        oi.bind(new MoveHook(true), OI.LeftJoyButton8, OI.WhenPressed);
        oi.bind(new MoveHook(false), OI.LeftJoyButton9, OI.WhenPressed);

        oi.bind(new MoveElevatorToLevel(Elevator.HappyPosition.LEVEL_1), OI.AuxJoyButton4, OI.WhenPressed);
        oi.bind(new MoveElevatorToLevel(Elevator.HappyPosition.LEVEL_2), OI.AuxJoyButton8, OI.WhenPressed);
        oi.bind(new MoveElevatorToLevel(Elevator.HappyPosition.LEVEL_3), OI.AuxJoyButton3, OI.WhenPressed);
        oi.bind(new MoveElevatorToLevel(Elevator.HappyPosition.LEVEL_4), OI.AuxJoyButton7, OI.WhenPressed);
        oi.bind(new MoveElevatorToLevel(Elevator.HappyPosition.LEVEL_5), OI.AuxJoyButton5, OI.WhenPressed);
        oi.bind(new MoveElevatorToLevel(Elevator.HappyPosition.LEVEL_8), OI.AuxJoyButton6, OI.WhenPressed);
        oi.bind(new MoveElevatorToLevel(Elevator.HappyPosition.LEVEL_7), OI.AuxJoyButton2, OI.WhenPressed);
        oi.bind(new MoveAdjusterToPos(IntakeAdjuster.HappyPositions.LEVEL2), OI.AuxJoyButton9, OI.WhenPressed);
        oi.bind(new MoveAdjusterToPos(IntakeAdjuster.HappyPositions.LEVEL2), OI.AuxJoyButton10, OI.WhenPressed);
        oi.bind(new MoveAdjusterToPos(IntakeAdjuster.HappyPositions.LEVEL3), OI.AuxJoyButton11, OI.WhenPressed);
        oi.bind(new ChooseCamera(), OI.AuxJoyButton1, OI.WhenPressed);

        /*oi.bind(new MoveElevator(true), OI.button1, OI.WhileHeld);
        oi.bind(new MoveElevator(false), OI.button2, OI.WhileHeld);

        oi.bind(new MoveElevatorToLevel(Elevator.HappyPosition.LEVEL_1), OI.button3, OI.WhenPressed);
        oi.bind(new MoveElevatorToLevel(Elevator.HappyPosition.LEVEL_2), OI.button4, OI.WhenPressed);
        oi.bind(new MoveElevatorToLevel(Elevator.HappyPosition.LEVEL_3), OI.button5, OI.WhenPressed);
        oi.bind(new MoveElevatorToLevel(Elevator.HappyPosition.LEVEL_4), OI.button6, OI.WhenPressed);
        oi.bind(new MoveElevatorToLevel(Elevator.HappyPosition.LEVEL_5), OI.button7, OI.WhenPressed);
        oi.bind(new MoveElevatorToLevel(Elevator.HappyPosition.LEVEL_8), OI.button8, OI.WhenPressed);
        oi.bind(new MoveElevatorToLevel(Elevator.HappyPosition.LEVEL_1), OI.button9, OI.WhenPressed);
        oi.bind(new MoveElevatorToLevel(Elevator.HappyPosition.LEVEL_7), OI.button10, OI.WhenPressed);
        oi.bind(new MoveAdjusterToPos(IntakeAdjuster.HappyPositions.LEVEL1), OI.button12, OI.WhenPressed);
        oi.bind(new MoveAdjusterToPos(IntakeAdjuster.HappyPositions.LEVEL2), OI.button13, OI.WhenPressed);
        oi.bind(new MoveAdjusterToPos(IntakeAdjuster.HappyPositions.LEVEL3), OI.button14, OI.WhenPressed);
        oi.bind(new ChooseCamera(), OI.button15, OI.WhenPressed);*/
    
        oi.bind(new Push(), OI.LeftJoyButton6, OI.WhenPressed);
        oi.bind(new Retract(), OI.LeftJoyButton7, OI.WhenPressed);
    }
    
    /**
     * init subsytems specific to Proto
     */
    private void initComp() throws Exception{
        logger.info("initalizing Blue");

        elevator = new Elevator();
        elevator.init(portMan);

        nav = new Navigation();
        nav.init(portMan);
        
        intakeAdjuster = new IntakeAdjuster();
        intakeAdjuster.init(portMan);

        lineTracker = new LineTracker();
        lineTracker.init(portMan);

        roller = new Roller();
        roller.init(portMan);

        doubleWheel = new DoubleWheel();
        doubleWheel.init(portMan);

        shooterIntake = new Intake();
        shooterIntake.init(portMan);

        driveTrain = new SparkMecanum();
        driveTrain.init(portMan);

        unicornHorn = new UnicornHorn();
        unicornHorn.init(portMan);

        climber = new Climber();
        climber.init(portMan);

        oi.bind(new KeepElevatorInPlace(), OI.LeftJoyButton1, OI.WhileHeld);

        oi.bind(new MoveElevator(true), OI.LeftJoyButton3, OI.WhileHeld);
        oi.bind(new MoveElevator(false), OI.LeftJoyButton2, OI.WhileHeld);
        oi.bind(new IntakeBackward(), OI.LeftJoyButton4, OI.ToggleWhenPressed);
        oi.bind(new IntakeBackwardSlower(), OI.LeftJoyButton5, OI.ToggleWhenPressed);

        oi.bind(new IntakeBall(), OI.RightJoyButton5, OI.WhileHeld);
        oi.bind(new OutTakeBall(), OI.RightJoyButton4, OI.ToggleWhenPressed);
        
        oi.bind(new MoveRollerBackward(), OI.RightJoyButton1, OI.WhileHeld);
        oi.bind(new MoveRollerSlowForward(), OI.RightJoyButton2, OI.WhileHeld);
        oi.bind(new MoveRollerForward(), OI.RightJoyButton3, OI.WhileHeld);
        oi.bind(new MoveIntakeAdjusterBackward(), OI.RightJoyButton10, OI.WhileHeld);
        oi.bind(new MoveIntakeAdjusterForward(), OI.RightJoyButton11, OI.WhileHeld);

        oi.bind(new Push(), OI.LeftJoyButton9, OI.WhenPressed);
        oi.bind(new Retract(), OI.LeftJoyButton8, OI.WhenPressed);

        oi.bind(new MoveElevatorToLevel(Elevator.HappyPosition.LEVEL_1), OI.AuxJoyButton4, OI.WhenPressed);
        oi.bind(new MoveElevatorToLevel(Elevator.HappyPosition.LEVEL_2), OI.AuxJoyButton8, OI.WhenPressed);
        oi.bind(new MoveElevatorToLevel(Elevator.HappyPosition.LEVEL_3), OI.AuxJoyButton3, OI.WhenPressed);
        oi.bind(new MoveElevatorToLevel(Elevator.HappyPosition.LEVEL_4), OI.AuxJoyButton7, OI.WhenPressed);
        oi.bind(new MoveElevatorToLevel(Elevator.HappyPosition.LEVEL_5), OI.AuxJoyButton5, OI.WhenPressed);
        oi.bind(new MoveElevatorToLevel(Elevator.HappyPosition.LEVEL_8), OI.AuxJoyButton6, OI.WhenPressed);
        oi.bind(new MoveElevatorToLevel(Elevator.HappyPosition.LEVEL_7), OI.AuxJoyButton2, OI.WhenPressed);
        oi.bind(new MoveAdjusterToPos(IntakeAdjuster.HappyPositions.LEVEL2), OI.AuxJoyButton9, OI.WhenPressed);
        oi.bind(new MoveAdjusterToPos(IntakeAdjuster.HappyPositions.LEVEL2), OI.AuxJoyButton10, OI.WhenPressed);
        oi.bind(new MoveAdjusterToPos(IntakeAdjuster.HappyPositions.LEVEL3), OI.AuxJoyButton11, OI.WhenPressed);
        oi.bind(new ChooseCamera(), OI.AuxJoyButton1, OI.WhenPressed);
    
        oi.bind(new SPush(), OI.LeftJoyButton6, OI.WhenPressed);
        oi.bind(new SRetract(), OI.LeftJoyButton7, OI.WhenPressed);

        //4, 6, 3, 7, 5, 8, 1
        

    }

    
    /**
     * This was the blue code as it was on stop build
     */
    
    /**
     * init subsystems specific to Newbie
     * @throws Exception
     */
    private void initNewbie() throws Exception {
       logger.info("initializing Newbie");

       driveTrain = new TankDrive();
       driveTrain.init(portMan);
       pixyLineCam = new PixyLineCam();
       pixyLineCam.init();
       
    }

    /**
     * init subsytems specific to Zippy
     */
    private void initZippy() throws Exception {
        logger.info("initalizing Zippy");
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
    }

    /**
     * init subsystems specific to Football
     */
    private void initFootball() throws Exception {
        logger.info("Initializing Football");

        elevator = new Elevator();
        elevator.initSB();

        /*
        intakeAdjuster = new IntakeAdjuster();
        intakeAdjuster.init(portMan);

        oi.bind(new MoveElevator(true), OI.button1, OI.WhileHeld);
        oi.bind(new MoveElevator(false), OI.button2, OI.WhileHeld);

        oi.bind(new MoveElevatorToLevel(Elevator.HappyPosition.LEVEL_1), OI.button3, OI.WhenPressed);
        oi.bind(new MoveElevatorToLevel(Elevator.HappyPosition.LEVEL_2), OI.button4, OI.WhenPressed);
        oi.bind(new MoveElevatorToLevel(Elevator.HappyPosition.LEVEL_3), OI.button5, OI.WhenPressed);
        oi.bind(new MoveElevatorToLevel(Elevator.HappyPosition.LEVEL_4), OI.button6, OI.WhenPressed);
        oi.bind(new MoveElevatorToLevel(Elevator.HappyPosition.LEVEL_5), OI.button7, OI.WhenPressed);
        oi.bind(new MoveElevatorToLevel(Elevator.HappyPosition.LEVEL_8), OI.button8, OI.WhenPressed);
        oi.bind(new MoveElevatorToLevel(Elevator.HappyPosition.LEVEL_1), OI.button9, OI.WhenPressed);
        oi.bind(new MoveElevatorToLevel(Elevator.HappyPosition.LEVEL_7), OI.button10, OI.WhenPressed);
        oi.bind(new MoveAdjusterToPos(IntakeAdjuster.HappyPositions.LEVEL1), OI.button12, OI.WhenPressed);
        oi.bind(new MoveAdjusterToPos(IntakeAdjuster.HappyPositions.LEVEL2), OI.button13, OI.WhenPressed);
        oi.bind(new MoveAdjusterToPos(IntakeAdjuster.HappyPositions.LEVEL3), OI.button14, OI.WhenPressed);
        oi.bind(new ChooseCamera(), OI.button15, OI.WhenPressed);
        */

        /*
        nav    = new Navigation();
        nav.initSB();
        lineTracker = new LineTracker();
        lineTracker.initSB();
        elevator = new Elevator();
        elevator.initSB();
        intakeAdjuster = new IntakeAdjuster();
        intakeAdjuster.initSB();
        //nav.init(portMan);
        //elevator = new Elevator();
        //elevator.initSB();
        */
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

    public UnicornHorn getUnicornHorn(){
        return unicornHorn;
    }

    public Climber getClimber(){
        return climber;
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

	public Intake getShooterIntake() {
		return shooterIntake;
	}

	public IntakeAdjuster getIntakeAdjuster() {
		return intakeAdjuster;
    }

    public PixyCam getPixyCam() {
        return pixyCam;
    }

    public PixyLineCam getPixyLineCam() {
        return pixyLineCam;
    }

    public DriverFeedback getDriverFeedback() {
        return driverFeedback;
    }
    public SystemStatus getSystemStatus() {
        return systemStatus;
    }

    public Hook getHook() {
        return hook;
    }
}
