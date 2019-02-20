package org.usfirst.frc.team4611.robot.subsystems.elevator;

import java.util.logging.Logger;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import org.usfirst.frc.team4611.robot.networktables.NetTableManager;
import org.usfirst.frc.team4611.robot.subsystems.PortMan;
import org.usfirst.frc.team4611.robot.subsystems.navigation.sensors.Potentiometer;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;

public class Elevator extends Subsystem {

    private final Logger logger = Logger.getLogger(Elevator.class.getName());

    private ShuffleboardTab tab;
    private NetworkTableEntry isLogging;
    private NetworkTableEntry elevatorPercentUp;
    private NetworkTableEntry elevatorPercentDown;
    private NetworkTableEntry elevatorPosition1;
    private NetworkTableEntry elevatorPosition2;
    private NetworkTableEntry elevatorPosition3;
    private NetworkTableEntry elevatorPosition4;
    private NetworkTableEntry elevatorPosition5;
    private NetworkTableEntry elevatorPosition6;
    private NetworkTableEntry elevatorPosition7;
    private NetworkTableEntry potPosition;

    public static double maxRPM = 6000;

    private double power = .75;

    private boolean logging = false;

    private WPI_TalonSRX elevatorLeftTalon;
    private WPI_TalonSRX elevatorRightTalon;
    private DigitalInput hardLimitTop;
    private DigitalInput softLimitTop;
    private DigitalInput softLimitBottom;
    private DigitalInput hardLimitBottom;

    private double potTop = .85;
    private double potBot = .11;

    private Potentiometer pot;

    private boolean upperSoftLimitToggle = false;
    private boolean lowerSoftLimitToggle = false;

    public Elevator(){

    }

    public void init(PortMan pm) throws Exception {
    
        if(logging) logger.info("initializing");

        tab = Shuffleboard.getTab("Elevator");
        NetTableManager.updateValue("Elevator", "ElevatorInitialize", true);

        isLogging = tab.add("Elevator Logging", false).getEntry();
        elevatorPercentUp = tab.add("Elevator Percent Up", power).getEntry();
        elevatorPercentDown = tab.add("Elevator Percent Down", power/8).getEntry();
        elevatorPosition1 = tab.add("Elevator Position1", 0.0539).getEntry();
        elevatorPosition2 = tab.add("Elevator Position2", 0.40).getEntry();
        elevatorPosition3 = tab.add("Elevator Position3", 0.5335).getEntry();
        elevatorPosition4 = tab.add("Elevator Position4", 0.8742).getEntry();
        elevatorPosition5 = tab.add("Elevator Position5", 0.9842).getEntry();
        elevatorPosition6 = tab.add("Elevator Position6", 0.9689 ).getEntry();
        elevatorPosition7 = tab.add("Elevator Position7", 0.723).getEntry();
        potPosition = tab.add("Elevator Pot Position", 0).getEntry();

        elevatorLeftTalon = new WPI_TalonSRX(pm.acquirePort(PortMan.can_15_label, "Elevator.elevatorLeftTalon"));
        elevatorRightTalon = new WPI_TalonSRX(pm.acquirePort(PortMan.can_16_label, "Elevator.elevatorRightTalon"));
        hardLimitTop = new DigitalInput(pm.acquirePort(PortMan.digital10_label, "Elevator.hardLimitTop"));
        softLimitTop = new DigitalInput(pm.acquirePort(PortMan.digital11_label, "Elevator.softLimitTop"));
        softLimitBottom = new DigitalInput(pm.acquirePort(PortMan.digital12_label, "Elevator.softLimitBottom"));
        hardLimitBottom = new DigitalInput(pm.acquirePort(PortMan.digital13_label, "Elevator.hardLimitBottom"));

        elevatorLeftTalon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
        elevatorRightTalon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);

        elevatorLeftTalon.config_kP(0, .5, 0);
        elevatorLeftTalon.config_kI(0, 0, 0);
        elevatorLeftTalon.config_kD(0, 0, 0);
        elevatorLeftTalon.config_kF(0, 0, 0);

        elevatorRightTalon.config_kP(0, .5, 0);
        elevatorRightTalon.config_kI(0, 0, 0);
        elevatorRightTalon.config_kD(0, 0, 0);
        elevatorRightTalon.config_kF(0, 0, 0);

        this.resetEncoders();

        elevatorRightTalon.follow(elevatorLeftTalon);
        elevatorRightTalon.setInverted(false);


        pot = new Potentiometer(pm.acquirePort(PortMan.analog0_label, "Elevator Pot"),potBot,potTop);
    }

    public boolean isLogging(){
        logging = isLogging.getBoolean(false);
        return logging;
    }

    public void stop() {
        elevatorLeftTalon.set(ControlMode.Velocity, 0);
    }

    public void move(boolean moveUp) {
        double speed;
        if(moveUp) {
            speed = (int)(maxRPM*elevatorPercentUp.getDouble(power));
        }
        else {
            speed = (int)(maxRPM*-elevatorPercentDown.getDouble(power));
        } 

        if(!softLimitBottom.get()) {
            lowerSoftLimitToggle = speed < 0;
            if(logging)
                logger.info("Soft Limit Bottom");
        }

        if(!softLimitTop.get()) {
            upperSoftLimitToggle = speed > 0;
            if(logging)
                logger.info("Soft Limit Top");
        }
        
        if(!hardLimitTop.get()) {
            if(speed >= 0) {
                speed = 0;
                if(logging)
                    logger.info("Hard Limit Top");
            }
        }
        if(upperSoftLimitToggle) {
            if(speed >= 0) {
                speed = speed/2;//for non changed soft upward movement
                if(logging)
                    logger.info("Soft Limit Top");
            }
        }
        if(lowerSoftLimitToggle) {
            if(speed <= 0) {
                speed = (speed/2)*.7;
                if(logging)
                    logger.info("Soft Limit Bottom");
            }
        }
        if(!hardLimitBottom.get()) {
            if(speed <= 0) {
                speed = 0;
                if(logging)
                    logger.info("Hard Limit Bottom");
            }
        }

        potPosition.setDouble(pot.getValue());
        elevatorLeftTalon.set(ControlMode.Velocity, speed);
    }

    public void stopElevator() {
        elevatorLeftTalon.stopMotor();
    }

    public static enum HappyPosition {LEVEL_1, LEVEL_2, LEVEL_3, LEVEL_4, LEVEL_5, LEVEL_6, LEVEL_7};

    public boolean moveToPos(HappyPosition level) {
        double finalTarget  = 0;
        
        switch (level) {
            case LEVEL_1:
                finalTarget = elevatorPosition1.getDouble(.5);
                break;
            case LEVEL_2:
                finalTarget = elevatorPosition2.getDouble(.5);
                break;
            case LEVEL_3:
                finalTarget = elevatorPosition3.getDouble(.5);
                break;
            case LEVEL_4:
                finalTarget = elevatorPosition4.getDouble(.5);
                break;
            case LEVEL_5:
                finalTarget = elevatorPosition5.getDouble(.5);
                break;
            case LEVEL_6:
                finalTarget = elevatorPosition6.getDouble(.5);
                break;
            case LEVEL_7:
                finalTarget = elevatorPosition7.getDouble(.5);
                break;
            default:
                return false;
        }
        
        boolean stop = false;


        if(finalTarget - pot.getValue() < -.03) {
            this.move(false);
        }
        else if(finalTarget - pot.getValue() > .03) {
            this.move(true);
        }
        else{
            this.stopElevator();
            endTime = 0;
            this.keepInPlaceForTime();
            stop = true;
        }
        return stop;
    }

    public boolean isAtPosition(HappyPosition level) {
        double finalTarget;
        
        switch (level) {
            case LEVEL_1:
                finalTarget = elevatorPosition1.getDouble(.5);
                break;
            case LEVEL_2:
                finalTarget = elevatorPosition2.getDouble(.5);
                break;
            case LEVEL_3:
                finalTarget = elevatorPosition3.getDouble(.5);
                break;
            case LEVEL_4:
                finalTarget = elevatorPosition4.getDouble(.5);
                break;
            case LEVEL_5:
                finalTarget = elevatorPosition5.getDouble(.5);
                break;
            case LEVEL_6:
                finalTarget = elevatorPosition6.getDouble(.5);
                break;
            case LEVEL_7:
                finalTarget = elevatorPosition7.getDouble(.5);
                break;
            default:
                return false;
        }

        if(finalTarget - pot.getValue() < -.05) {
            return false;
        }
        else if(finalTarget - pot.getValue() > .05) {
            return true;
        }
        else{
            return true;
        }
    }

    public void resetEncoders() {
        elevatorLeftTalon.setSelectedSensorPosition(0, 0 ,0);
        elevatorRightTalon.setSelectedSensorPosition(0, 0, 0);
    }

    public void keepInPlace() {
        elevatorLeftTalon.set(ControlMode.PercentOutput, .08);
        potPosition.setDouble(pot.getValue());
    }
    
    private long currentTime;
    private long endTime = 0;

    public boolean keepInPlaceForTime() {
        if(logging)
            logger.info("keeping in place");
        boolean done = false;;
        potPosition.setDouble(pot.getValue());
        currentTime = System.currentTimeMillis();
        
        if(endTime == 0) {
            endTime = currentTime + 100;
            if(logging)
                logger.info("setting");
        }
                                                                        
        if(currentTime <= endTime) {
            elevatorLeftTalon.set(ControlMode.PercentOutput, .08);
            if(logging)
                logger.info("stalling");
        }
        else {
            if(logging)
                logger.info("finishing");
            done = true;
            endTime = 0;
            this.stopElevator();
        }
        return done;
    }

    @Override
    protected void initDefaultCommand() {
        //this.setDefaultCommand(new KeepElevatorInPlace());
    }

    public void writeToShuffleboard() {

    }

    public void log() {

    }
}