package org.usfirst.frc.team4611.robot.subsystems.elevator;

import java.util.logging.Logger;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import org.usfirst.frc.team4611.robot.subsystems.PortMan;
import org.usfirst.frc.team4611.robot.subsystems.elevator.commands.ElevatorDefault;
import org.usfirst.frc.team4611.robot.subsystems.navigation.sensors.Potentiometer;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;

public class Elevator extends Subsystem {

    private final Logger logger = Logger.getLogger(Elevator.class.getName());
    private boolean inited = false;
    private boolean logging     = false;
    static private ShuffleboardTab tab = Shuffleboard.getTab("Elevator");

    private WPI_TalonSRX    leftTalon;
    private WPI_TalonSRX    rightTalon;

    private DigitalInput    hardLimitTop;
    private DigitalInput    softLimitTop;
    private DigitalInput    softLimitBottom;
    private DigitalInput    hardLimitBottom;

    private final double elevatorInchToPUMult = 356.8;

    private Potentiometer   pot;

    private boolean mmMode  = true; // Motion Magic mode by default

    public Elevator(){
    }

    public void setMMMode(boolean on){
        mmMode = on;
    }

    private double  potTop      = 4.0;
    private double  potBot      = 0.1;

    public void init(PortMan pm) throws Exception {
        initSB();

        leftTalon = new WPI_TalonSRX(pm.acquirePort(PortMan.can_15_label, "Elevator.elevatorLeftTalon"));
        rightTalon = new WPI_TalonSRX(pm.acquirePort(PortMan.can_16_label, "Elevator.elevatorRightTalon"));

        hardLimitTop = new DigitalInput(pm.acquirePort(PortMan.digital10_label, "Elevator.hardLimitTop"));
        softLimitTop = new DigitalInput(pm.acquirePort(PortMan.digital11_label, "Elevator.softLimitTop"));
        softLimitBottom = new DigitalInput(pm.acquirePort(PortMan.digital12_label, "Elevator.softLimitBottom"));
        hardLimitBottom = new DigitalInput(pm.acquirePort(PortMan.digital13_label, "Elevator.hardLimitBottom"));

        pot = new Potentiometer(pm.acquirePort(PortMan.analog0_label, "Elevator Pot"), potBot, potTop);

        initTalonCommon();
        configTalonsForMotionMagic(true);
        inited = true;
    }

    public void stop() {
        logger.info("stopping mm[" + mmMode + "]");
        if (!mmMode) {
            leftTalon.set(ControlMode.PercentOutput, 0.0);
            leftTalon.set(ControlMode.MotionMagic, leftTalon.getSelectedSensorPosition());
        }
        else {
            // this will use MM to hold elevator at current position
            leftTalon.set(ControlMode.MotionMagic, leftTalon.getSelectedSensorPosition());
        }
    }

    boolean lastMoveWasUp = true;
    public void move(boolean moveUp) {
        movePercOutput(moveUp);

        updateValues();
    }
    
    public boolean move(double pos) {
        boolean stop = false;
        pos = pos * elevatorInchToPUMult;
        double finalTarget = leftTalon.getSelectedSensorPosition() + pos;

        if(finalTarget > maxEncoder) {
            finalTarget = maxEncoder;
        }
        else if(finalTarget < 0) {
            finalTarget = 0;
        }

        leftTalon.set(ControlMode.MotionMagic, finalTarget);

        if(Math.abs(finalTarget - leftTalon.getSelectedSensorPosition()) > positionTolerance) {
            stop = true;
        }
        return stop;
    }

    public boolean moveToLevel (HappyPosition level) {
        boolean done;
        if (mmMode) {
            done =  moveToMMPos(level);
        } else {
            done =  moveToPotPos(level);
        }
        updateValues();
        return done;
    }

    private int     maxEncoder    = 22300;

    private double mmLevel1Target  =  1370;
    private double mmLevel2Target  =  9799;
    private double mmLevel3Target  =  12764;
    private double mmLevel4Target  =  18214;
    private double mmLevel5Target  =  22300;
    private double mmLevel6Target  =  22240;
    private double mmLevel7Target  =  14971;
    private double mmLevel8Target  =  11373;
    private double mmCargoGrabTarget  =  14200;
    private double positionTolerance    = 100;

    private boolean moveToMMPos(HappyPosition level) {
        double finalTarget  = 0.0;

        switch (level) {
            case BOTTOM:
                finalTarget = 0.0;
                break;
            case LEVEL_1:
                finalTarget = mmLevel1Target;
                break;
            case LEVEL_2:
                finalTarget = mmLevel2Target;
                break;
            case LEVEL_3:
                finalTarget = mmLevel3Target;
                break;
            case LEVEL_4:
                finalTarget = mmLevel4Target;
                break;
            case LEVEL_5:
                finalTarget = mmLevel5Target;
                break;
            case LEVEL_6:
                finalTarget = mmLevel6Target;
                break;
            case LEVEL_7:
                finalTarget = mmLevel7Target;
                break;
            case LEVEL_8:
                finalTarget = mmLevel8Target;
                break;
            case CARGO_GRAB:
                finalTarget = mmCargoGrabTarget;
            default:
                return false;
        }

        //safe guard to ensure we dont go higher than our maximum
        if(finalTarget > maxEncoder) {
            finalTarget = maxEncoder;
        }

        logger.info("abs[" + Math.abs(finalTarget - leftTalon.getSelectedSensorPosition()) + "] [" + positionTolerance + "]");
        if(Math.abs(finalTarget - leftTalon.getSelectedSensorPosition()) <= positionTolerance) {
            return true;
        }

        /*
        // I think this is correct now. i added the !
        if(!hardLimitTop.get()) {
            leftTalon.set(ControlMode.MotionMagic, leftTalon.getSelectedSensorPosition());
            return true;
        }
        */

        if (finalTarget > leftTalon.getSelectedSensorPosition() && !lastMoveWasUp) {
            lastMoveWasUp = true;
            configTalonsForMotionMagic(true);
        } else if (finalTarget <= leftTalon.getSelectedSensorPosition() && lastMoveWasUp) {
            lastMoveWasUp = false;
            configTalonsForMotionMagic(false);
        }

        leftTalon.set(ControlMode.MotionMagic, finalTarget);

        return false;
    }

    private double  percOutputUp    = 0.50;
    private double  percOutputDown  = 0.15;

    public void movePercOutput(boolean moveUp) {
        boolean upperSoftLimitToggle = false;
        boolean lowerSoftLimitToggle = false;
        double output;

        output = (moveUp ? percOutputUp : (-1 * percOutputDown));

        if(!softLimitBottom.get()) {
            lowerSoftLimitToggle = output < 0;
        }

        if(!softLimitTop.get()) {
            upperSoftLimitToggle = output > 0;
        }
        
        // we need to add the potMax logic here. but we can't until we get proper mins/max
        // they are on shuffleboard, so this commented line below should be the line to be used when we can test
        //if((!hardLimitTop.get() || leftTalon.getSelectedSensorPosition() >= maxEncoder || pot.getPercentOutput() > 0.99) && output >=0 ) {
        if((!hardLimitTop.get() || leftTalon.getSelectedSensorPosition() >= maxEncoder) && output >=0 ) {
            output = 0;
        }
        if(upperSoftLimitToggle && output >= 0) {
            output = output/2;
        }
        if(lowerSoftLimitToggle && output <= 0) {
            output = (output/2);
        }
        
        // we need to add the potMax logic here. but we can't until we get proper mins/max
        // they are on shuffleboard, so this commented line below should be the line to be used when we can test
        //if((!hardLimitBottom.get() || leftTalon.getSelectedSensorPosition() <= 0 || pot.getPercentOutput() < 0.05) && output <=0 ) {
        if((!hardLimitBottom.get() || leftTalon.getSelectedSensorPosition() <= 0) && output <= 0) {
            output = 0;
        }

        leftTalon.set(ControlMode.PercentOutput, output);
    }

    public static enum HappyPosition {BOTTOM, LEVEL_1, LEVEL_2, LEVEL_3, LEVEL_4, LEVEL_5, LEVEL_6, LEVEL_7, LEVEL_8, CARGO_GRAB};

    private double potLevel1Target  =  0.20;
    private double potLevel2Target  =  0.53;
    private double potLevel3Target  =  0.60;
    private double potLevel4Target  =  0.81;
    private double potLevel5Target  =  0.89;
    private double potLevel6Target  =  0.89;
    private double potLevel7Target  =  0.7230;
    private double potLevel8Target = 0.7230;
    private double potCargoGrabTarget   = 0.5335;

    public boolean moveToPotPos(HappyPosition level) {
        double finalTarget  = 0;
        
        switch (level) {
            case BOTTOM:
                finalTarget = 0.05;
                break;
            case LEVEL_1:
                finalTarget = potLevel1Target;
                break;
            case LEVEL_2:
                finalTarget = potLevel2Target;
                break;
            case LEVEL_3:
                finalTarget = potLevel3Target;
                break;
            case LEVEL_4:
                finalTarget = potLevel4Target;
                break;
            case LEVEL_5:
                finalTarget = potLevel5Target;
                break;
            case LEVEL_6:
                finalTarget = potLevel6Target;
                break;
            case LEVEL_7:
                finalTarget = potLevel7Target;
                break;
            case LEVEL_8:
                finalTarget = potLevel8Target;
                break;
            case CARGO_GRAB:
                finalTarget = potCargoGrabTarget;
                break;
        }
        
        boolean stop = false;

        if(finalTarget - pot.getPercentOutput() < -.01) {
            this.move(false);
        }
        else if(finalTarget - pot.getPercentOutput() > .01) {
            this.move(true);
        } else {
            stop();
            /*
            if (level != HappyPosition.BOTTOM) {
                this.keepInPlaceForTime();
            }
            */
            stop = true;
        }
        return stop;
    }

    private void initTalonCommon() {
        leftTalon.configFactoryDefault();
        rightTalon.configFactoryDefault();

        boolean currentLimitEnabled = true;

        rightTalon.enableCurrentLimit(currentLimitEnabled);
        rightTalon.configPeakCurrentLimit(50);
        rightTalon.configContinuousCurrentLimit(40);
        rightTalon.configPeakCurrentDuration(400);
        rightTalon.configAllowableClosedloopError(0, 100);
        rightTalon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
        rightTalon.setSelectedSensorPosition(0, 0, 0);

        leftTalon.enableCurrentLimit(currentLimitEnabled);
        leftTalon.configPeakCurrentLimit(50);
        leftTalon.configContinuousCurrentLimit(40);
        leftTalon.configPeakCurrentDuration(400);
        leftTalon.configAllowableClosedloopError(0, 100);
        leftTalon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
        leftTalon.setSelectedSensorPosition(0, 0 ,0);

        rightTalon.follow(leftTalon);

        leftTalon.setInverted(true);
        rightTalon.setInverted(false);
    }

    private double  pidPUp = 0.6;
    private int     cruiseSpeedUp = 2000;
    private int     accelerationUp = 3000;

    private double  pidPDown = 0.6;
    private int     cruiseSpeedDown = 2000;
    private int     accelerationDown = 2500;

    private void configTalonsForMotionMagic(boolean up) {

        double p;
        int  c, a;

        if (up) {
            p = pidPUp;
            c = cruiseSpeedUp;
            a = accelerationUp;
        } else {
            p = pidPDown;
            c = cruiseSpeedDown;
            a = accelerationDown;
        }
        logger.info("MM Config p[" + p + "] a[" + a + "] c[" + c + "]");

        leftTalon.config_kP(0, p, 0);
        leftTalon.config_kI(0, 0, 0);
        leftTalon.config_kD(0, 0, 0);
        leftTalon.config_kF(0, 0, 0);
        leftTalon.configAllowableClosedloopError(0, 100);

        leftTalon.configMotionCruiseVelocity(c);
        leftTalon.configMotionAcceleration(a);

        rightTalon.config_kP(0, p, 0);
        rightTalon.config_kI(0, 0, 0);
        rightTalon.config_kD(0, 0, 0);
        rightTalon.config_kF(0, 0, 0);
        rightTalon.configAllowableClosedloopError(0, 100);

        rightTalon.configMotionCruiseVelocity(c);
        rightTalon.configMotionAcceleration(a);
    }

    private double stallPercent = 0.08;

    public void keepInPlace() {
        if (!mmMode) {
            leftTalon.set(ControlMode.PercentOutput, stallPercent);
        }
    }

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new ElevatorDefault());
    }

    private boolean resetMMValues   = false;
    private NetworkTableEntry isLoggingEntry;
    private NetworkTableEntry resetMMValuesEntry;

    private NetworkTableEntry MMLevel1Entry;
    private NetworkTableEntry MMLevel2Entry;
    private NetworkTableEntry MMLevel3Entry;
    private NetworkTableEntry MMLevel4Entry;
    private NetworkTableEntry MMLevel5Entry;
    private NetworkTableEntry MMLevel6Entry;
    private NetworkTableEntry MMLevel7Entry;
    private NetworkTableEntry MMCargoGrabEntry;

    private NetworkTableEntry PotLevel1Entry;
    private NetworkTableEntry PotLevel2Entry;
    private NetworkTableEntry PotLevel3Entry;
    private NetworkTableEntry PotLevel4Entry;
    private NetworkTableEntry PotLevel5Entry;
    private NetworkTableEntry PotLevel6Entry;
    private NetworkTableEntry PotLevel7Entry;
    private NetworkTableEntry PotCargoGrabEntry;

    private NetworkTableEntry potPositionEntry;
    private NetworkTableEntry potMinEntry;
    private NetworkTableEntry potMaxEntry;
    private NetworkTableEntry leftEncoderPositionEntry;
    private NetworkTableEntry rightEncoderPositionEntry;
    private NetworkTableEntry percOutputUpEntry;
    private NetworkTableEntry percOutputDownEntry;
    private NetworkTableEntry cruiseUpEntry;
    private NetworkTableEntry cruiseDownEntry;
    private NetworkTableEntry accelUpEntry;
    private NetworkTableEntry accelDownEntry;
    private NetworkTableEntry pidPUpEntry;
    private NetworkTableEntry pidPDownEntry;
    private NetworkTableEntry mmModeEntry;
    private NetworkTableEntry leftCurrentEntry;
    private NetworkTableEntry rightCurrentEntry;
    private NetworkTableEntry leftVelocityEntry;

    public void initSB () {
        isLoggingEntry = tab.add("Elevator Logging", logging).withSize(1,1).withPosition(0,0).getEntry();
        mmModeEntry = tab.add("MM Mode", mmMode).withSize(1, 1).withPosition(0, 1).getEntry();
        
        potPositionEntry = tab.add("Pot Pos", 0).withSize(1,1).withPosition(3, 0).getEntry();
        potMinEntry = tab.add("Pot Min", 0.0).withSize(1,1).withPosition(4, 0).getEntry();
        potMaxEntry = tab.add("Pot Max", 0.0).withSize(1,1).withPosition(5, 0).getEntry();
        leftEncoderPositionEntry = tab.add("L Enc", 0).withSize(1, 1).withPosition(1, 0).getEntry();
        rightEncoderPositionEntry = tab.add("R Enc", 0).withSize(1, 1).withPosition(2, 0).getEntry();
        percOutputUpEntry = tab.add("% Up", percOutputUp).withSize(1, 1).withPosition(4, 1).getEntry();
        percOutputDownEntry = tab.add("% Dn", percOutputDown).withSize(1, 1).withPosition(4, 2).getEntry();
        resetMMValuesEntry = tab.add("Cfg MM", resetMMValues).withSize(1, 1).withPosition(0, 2).getEntry();
        pidPUpEntry = tab.add("pid P Up", pidPUp).withSize(1, 1).withPosition(1, 1).getEntry();
        pidPDownEntry = tab.add("pid P Dn", pidPDown).withSize(1, 1).withPosition(1, 2).getEntry();
        accelUpEntry = tab.add("Accel Up", accelerationUp).withSize(1, 1).withPosition(2, 1).getEntry();
        accelDownEntry = tab.add("Accel Dn", accelerationDown).withSize(1, 1).withPosition(2, 2).getEntry();
        cruiseUpEntry = tab.add("Vel Up", cruiseSpeedUp).withSize(1, 1).withPosition(3, 1).getEntry();
        cruiseDownEntry = tab.add("Vel Dn", cruiseSpeedDown).withSize(1, 1).withPosition(3, 2).getEntry();

        MMLevel1Entry = tab.add("MM 1", mmLevel1Target).withSize(1, 1).withPosition(0, 3).getEntry();
        MMLevel2Entry = tab.add("MM 2", mmLevel2Target).withSize(1, 1).withPosition(1, 3).getEntry();
        MMLevel3Entry = tab.add("MM 3", mmLevel3Target).withSize(1, 1).withPosition(2, 3).getEntry();
        MMLevel4Entry = tab.add("MM 4", mmLevel4Target).withSize(1, 1).withPosition(3, 3).getEntry();
        MMLevel5Entry = tab.add("MM 5", mmLevel5Target).withSize(1, 1).withPosition(4, 3).getEntry();
        MMLevel6Entry = tab.add("MM 6", mmLevel6Target).withSize(1, 1).withPosition(5, 3).getEntry();
        MMLevel7Entry = tab.add("MM 7", mmLevel7Target).withSize(1, 1).withPosition(6, 3).getEntry();
        MMCargoGrabEntry = tab.add("MMCargoGrab", mmLevel7Target).withSize(1, 1).withPosition(7, 3).getEntry();

        PotLevel1Entry = tab.add("Pot 1", potLevel1Target).withSize(1, 1).withPosition(0, 4).getEntry();
        PotLevel2Entry = tab.add("Pot 2", potLevel2Target).withSize(1, 1).withPosition(1, 4).getEntry();
        PotLevel3Entry = tab.add("Pot 3", potLevel3Target).withSize(1, 1).withPosition(2, 4).getEntry();
        PotLevel4Entry = tab.add("Pot 4", potLevel4Target).withSize(1, 1).withPosition(3, 4).getEntry();
        PotLevel5Entry = tab.add("Pot 5", potLevel5Target).withSize(1, 1).withPosition(4, 4).getEntry();
        PotLevel6Entry = tab.add("Pot 6", potLevel6Target).withSize(1, 1).withPosition(5, 4).getEntry();
        PotLevel7Entry = tab.add("Pot 7", potLevel7Target).withSize(1, 1).withPosition(6, 4).getEntry();
        PotCargoGrabEntry = tab.add("PotCargoGrab", potLevel7Target).withSize(1, 1).withPosition(7, 4).getEntry();

        leftCurrentEntry = tab.add("Left Current", 0.0).withWidget("Graph").withSize(3, 3).withPosition(6, 0).getEntry();
        rightCurrentEntry = tab.add("Right Current", 0.0).withWidget("Graph").withSize(3, 3).withPosition(9, 0).getEntry();
        leftVelocityEntry = tab.add("Left Velocity", 0.0).withWidget("Graph").withSize(3, 3).withPosition(9, 3).getEntry();
    }

    public void updateValues() {
        if (!inited) return;

        // read new values
        potLevel1Target = PotLevel1Entry.getDouble(potLevel1Target);
        potLevel2Target = PotLevel2Entry.getDouble(potLevel2Target);
        potLevel3Target = PotLevel3Entry.getDouble(potLevel3Target);
        potLevel4Target = PotLevel4Entry.getDouble(potLevel4Target);
        potLevel5Target = PotLevel5Entry.getDouble(potLevel5Target);
        potLevel6Target = PotLevel6Entry.getDouble(potLevel6Target);
        potLevel7Target = PotLevel7Entry.getDouble(potLevel7Target);
        potCargoGrabTarget = PotCargoGrabEntry.getDouble(potCargoGrabTarget);

        mmLevel1Target = MMLevel1Entry.getDouble(mmLevel1Target);
        mmLevel2Target = MMLevel2Entry.getDouble(mmLevel2Target);
        mmLevel3Target = MMLevel3Entry.getDouble(mmLevel3Target);
        mmLevel4Target = MMLevel4Entry.getDouble(mmLevel4Target);
        mmLevel5Target = MMLevel5Entry.getDouble(mmLevel5Target);
        mmLevel6Target = MMLevel6Entry.getDouble(mmLevel6Target);
        mmLevel7Target = MMLevel7Entry.getDouble(mmLevel7Target);
        mmCargoGrabTarget = MMCargoGrabEntry.getDouble(mmCargoGrabTarget);

        percOutputUp  = percOutputUpEntry.getDouble(percOutputUp);
        percOutputDown  = percOutputDownEntry.getDouble(percOutputDown);
       
        mmMode = mmModeEntry.getBoolean(mmMode);
        resetMMValues = resetMMValuesEntry.getBoolean(resetMMValues);
        logging = isLoggingEntry.getBoolean(false);

        // set current values
        leftEncoderPositionEntry.setDouble(leftTalon.getSelectedSensorPosition());
        rightEncoderPositionEntry.setDouble(rightTalon.getSelectedSensorPosition());

        potPositionEntry.setDouble(pot.getPercentOutput());
        pot.setMin(potMinEntry.getDouble(pot.getMin()));
        pot.setMax(potMaxEntry.getDouble(pot.getMax()));

        leftCurrentEntry.setDouble(leftTalon.getOutputCurrent());
        rightCurrentEntry.setDouble(rightTalon.getOutputCurrent());
        leftVelocityEntry.setDouble(leftTalon.getSelectedSensorVelocity());

        if (resetMMValues) {
            accelerationUp    = (int) accelUpEntry.getDouble(accelerationUp);
            accelerationDown  = (int) accelDownEntry.getDouble(accelerationDown);
            cruiseSpeedUp     = (int) cruiseUpEntry.getDouble(cruiseSpeedUp);
            cruiseSpeedDown   = (int) cruiseDownEntry.getDouble(cruiseSpeedDown);
            pidPUp            =  pidPUpEntry.getDouble(pidPUp);
            pidPDown          =  pidPDownEntry.getDouble(pidPDown);
            resetMMValues     = false;
            resetMMValuesEntry.setBoolean(resetMMValues);
        }
    }

    public boolean isLogging(){
        return logging;
    }
}