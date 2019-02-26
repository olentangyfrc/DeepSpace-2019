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
    private boolean logging     = false;
    static private ShuffleboardTab tab = Shuffleboard.getTab("Elevator");

    private WPI_TalonSRX    leftTalon;
    private WPI_TalonSRX    rightTalon;

    private DigitalInput    hardLimitTop;
    private DigitalInput    softLimitTop;
    private DigitalInput    softLimitBottom;
    private DigitalInput    hardLimitBottom;

    private Potentiometer   pot;

    private double  percOutputUp    = 0.75;
    private double  percOutputDown  = 0.1;
    private double  potTop      = .85;
    private double  potBot      = .11;
    private int     maxEncoder  = 21470;

    private boolean upperSoftLimitToggle = false;
    private boolean lowerSoftLimitToggle = false;
    private boolean lowerHardLimit = false;

    private boolean mmMode  = false; // Motion Magic mode by default
    public Elevator(){
    }

    public void setMMMode(boolean on){
        mmMode = on;
    }

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
        initTalonsForMotionMagic();
    }

    public void stop() {
        if (!mmMode) {
            currentOutput = 0;
            leftTalon.set(ControlMode.PercentOutput, currentOutput);
        }
    }

    private int     stepUp        = 1000;
    private int     stepDown      = 500;

    public void move(boolean moveUp) {
        if (mmMode) {
            moveMM(moveUp);
        } else {
            movePercOutput(moveUp);
        }
    }

    private void moveMM(boolean moveUp) {
        int step;

        lowerHardLimit = false;

        if(moveUp) {
            step = stepUp;
        }
        else {
            step = -1 * stepDown;
        } 

        if(!softLimitBottom.get()) {
            lowerSoftLimitToggle = step < 0;
            if(logging) logger.info("Soft Limit Bottom");
        }

        if(!softLimitTop.get()) {
            upperSoftLimitToggle = step > 0;
            if(logging) logger.info("Soft Limit Top");
        }
        
        if(!hardLimitTop.get()) {
            if(step >= 0) {
                step = 0;
                if(logging) logger.info("Hard Limit Top");
            }
        }
        if(upperSoftLimitToggle) {
            if(step >= 0) {
                step = step/2;//for non changed soft upward movement
                if(logging) logger.info("Soft Limit Top");
            }
        }
        if(lowerSoftLimitToggle) {
            if(step <= 0) {
                step = (int) (step/2);
                if(logging) logger.info("Soft Limit Bottom");
            }
        }
        if(!hardLimitBottom.get()) {
            lowerHardLimit = true;
            if(step <= 0) {
                step = 0;
                if(logging) logger.info("Hard Limit Bottom");
            }
        }

        logger.info("MMMove step [" + step + "]");
        if (step != 0) {
            leftTalon.set(ControlMode.MotionMagic, step + leftTalon.getSelectedSensorPosition());
        } else if (lowerHardLimit) {
            leftTalon.set(ControlMode.Velocity, 0);
        }
    }

    private double mmLevel1Target  =  10000;
    private double mmLevel2Target  =  10000;
    private double mmLevel3Target  =  10000;
    private double mmLevel4Target  =  10000;
    private double mmLevel5Target  =  10000;
    private double mmLevel6Target  =  10000;
    private double mmLevel7Target  =  10000;
    private double positionTolerance    = 100;

    public boolean moveToLevel (HappyPosition level) {
        if (mmMode) {
            return moveToMMPos(level);
        } else {
            return moveToPotPos(level);
        }
    }

    public boolean moveToMMPos(HappyPosition level) {
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
            default:
                return false;
        }
        
        boolean stop = false;

        if(finalTarget - leftTalon.getSelectedSensorPosition() < -positionTolerance) {
            moveMM(false);
        } else if(finalTarget - leftTalon.getSelectedSensorPosition() > positionTolerance) {
            moveMM(true);
        } else {
            if (level == HappyPosition.BOTTOM) {
                leftTalon.set(ControlMode.Velocity, 0.0);
            }
            stop = true;
        }
        return stop;
    }

    private double currentOutput;
    public void movePercOutput(boolean moveUp) {
        if(moveUp) {
            currentOutput = percOutputUp;
        } else {
            currentOutput = -1.0 * percOutputDown;
        } 

        if(!softLimitBottom.get()) {
            lowerSoftLimitToggle = currentOutput < 0;
            if(logging) logger.info("Soft Limit Bottom");
        }

        if(!softLimitTop.get()) {
            upperSoftLimitToggle = currentOutput > 0;
            if(logging) logger.info("Soft Limit Top");
        }
        
        if(!hardLimitTop.get()) {
            if(currentOutput >= 0) {
                currentOutput = 0;
                if(logging) logger.info("Hard Limit Top");
            }
        }
        if(upperSoftLimitToggle) {
            if(currentOutput >= 0) {
                currentOutput = currentOutput/2;//for non changed soft upward movement
                if(logging) logger.info("Soft Limit Top");
            }
        }
        if(lowerSoftLimitToggle) {
            if(currentOutput <= 0) {
                currentOutput = (currentOutput/2)*.7;
                if(logging)
                    logger.info("Soft Limit Bottom");
            }
        }
        if(!hardLimitBottom.get()) {
            if(currentOutput <= 0) {
                currentOutput = 0;
                if(logging) logger.info("Hard Limit Bottom");
            }
        }

        leftTalon.set(ControlMode.PercentOutput, currentOutput);
    }

    public boolean moveToPosition(HappyPosition level) {
        if (mmMode) {
            return moveToMMPos(level);
        } else {
            return moveToPosition(level);
        }
    }
    public static enum HappyPosition {BOTTOM, LEVEL_1, LEVEL_2, LEVEL_3, LEVEL_4, LEVEL_5, LEVEL_6, LEVEL_7};

    private double potLevel1Target  =  0.0539;
    private double potLevel2Target  =  0.0400;
    private double potLevel3Target  =  0.5335;
    private double potLevel4Target  =  0.8742;
    private double potLevel5Target  =  0.9842;
    private double potLevel6Target  =  0.9689;
    private double potLevel7Target  =  0.7230;

    public boolean moveToPotPos(HappyPosition level) {
        double finalTarget  = 0;
        
        switch (level) {
            case BOTTOM:
                finalTarget = potBot;
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
            stop();
            if (level != HappyPosition.BOTTOM) {
                this.keepInPlaceForTime();
            }
            stop = true;
        }
        return stop;
    }

    public boolean isAtPosition(HappyPosition level) {
        double finalTarget;
        
        switch (level) {
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
            default:
                return false;
        }

        return (Math.abs(finalTarget - pot.getValue()) < .05);
    }

    private void initTalonCommon() {
        leftTalon.configFactoryDefault();
        rightTalon.configFactoryDefault();

        rightTalon.configPeakCurrentLimit(30);
        leftTalon.configPeakCurrentLimit(30);

        leftTalon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
        rightTalon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);

        rightTalon.follow(leftTalon);

        leftTalon.setInverted(true);
        rightTalon.setInverted(false);
    }

    private double  pidP = 1.0;
    private int     cruiseSpeed = 4000;
    private int     acceleration = 10000;

    private void initTalonsForMotionMagic() {

        leftTalon.config_kP(0, pidP, 0);
        leftTalon.config_kI(0, 0, 0);
        leftTalon.config_kD(0, 0, 0);
        leftTalon.config_kF(0, 0, 0);
        leftTalon.configAllowableClosedloopError(0, 100);

        leftTalon.setSelectedSensorPosition(0, 0 ,0);
        leftTalon.configMotionCruiseVelocity(cruiseSpeed);
        leftTalon.configMotionAcceleration(acceleration);

        rightTalon.config_kP(0, pidP, 0);
        rightTalon.config_kI(0, 0, 0);
        rightTalon.config_kD(0, 0, 0);
        rightTalon.config_kF(0, 0, 0);
        rightTalon.configAllowableClosedloopError(0, 100);

        rightTalon.setSelectedSensorPosition(0, 0, 0);
        rightTalon.configMotionCruiseVelocity(cruiseSpeed);
        rightTalon.configMotionAcceleration(acceleration);
    }
    
    private long stallDuration = 100;
    private double stallPercent = 0.08;

    public void keepInPlaceForTime() {
        
        long endTime = System.currentTimeMillis() + stallDuration;

        while(System.currentTimeMillis() <= endTime) {
            if(logging) logger.info("stalling");
            leftTalon.set(ControlMode.PercentOutput, stallPercent);
        }
    }

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

    private NetworkTableEntry PotLevel1Entry;
    private NetworkTableEntry PotLevel2Entry;
    private NetworkTableEntry PotLevel3Entry;
    private NetworkTableEntry PotLevel4Entry;
    private NetworkTableEntry PotLevel5Entry;
    private NetworkTableEntry PotLevel6Entry;
    private NetworkTableEntry PotLevel7Entry;

    private NetworkTableEntry potPositionEntry;
    private NetworkTableEntry leftEncoderPositionEntry;
    private NetworkTableEntry rightEncoderPositionEntry;
    private NetworkTableEntry percOutputUpEntry;
    private NetworkTableEntry percOutputDownEntry;
    private NetworkTableEntry stepUpEntry;
    private NetworkTableEntry stepDownEntry;
    private NetworkTableEntry cruiseEntry;
    private NetworkTableEntry accelEntry;
    private NetworkTableEntry pidPEntry;
    private NetworkTableEntry mmModeEntry;

    public void initSB () {
        isLoggingEntry = tab.add("Elevator Logging", logging).withSize(1,1).withPosition(0,0).getEntry();
        mmModeEntry = tab.add("MM Mode", mmMode).withSize(1, 1).withPosition(0, 1).getEntry();
        resetMMValuesEntry = tab.add("Set MM Values", resetMMValues).withSize(1, 1).withPosition(0, 2).getEntry();
        
        potPositionEntry = tab.add("Pot Position", 0).withSize(1,1).withPosition(3, 0).getEntry();
        leftEncoderPositionEntry = tab.add("Left Encoder", 0).withSize(1, 1).withPosition(1, 0).getEntry();
        rightEncoderPositionEntry = tab.add("Right Encoder", 0).withSize(1, 1).withPosition(2, 0).getEntry();
        percOutputUpEntry = tab.add("% Output\nUp Entry", percOutputUp).withSize(1, 1).withPosition(3, 1).getEntry();
        percOutputDownEntry = tab.add("% Output\nDown Entry", percOutputDown).withSize(1, 1).withPosition(4, 1).getEntry();

        stepUpEntry = tab.add("MM Step\nUp Entry", stepUp).withSize(1, 1).withPosition(1, 1).getEntry();
        stepDownEntry = tab.add("MM Step\nDown Entry", stepDown).withSize(1, 1).withPosition(2, 1).getEntry();
        pidPEntry = tab.add("pid P", pidP).withSize(1, 1).withPosition(1, 2).getEntry();
        accelEntry = tab.add("Acceleration", acceleration).withSize(1, 1).withPosition(2, 2).getEntry();
        cruiseEntry = tab.add("Cruise", cruiseSpeed).withSize(1, 1).withPosition(3, 2).getEntry();
    }

    public void updateValues() {
        // read new values
        /*
        potLevel1Target = PotLevel1Entry.getDouble(potLevel1Target);
        potLevel2Target = PotLevel2Entry.getDouble(potLevel2Target);
        potLevel3Target = PotLevel3Entry.getDouble(potLevel3Target);
        potLevel4Target = PotLevel4Entry.getDouble(potLevel4Target);
        potLevel5Target = PotLevel5Entry.getDouble(potLevel5Target);
        potLevel6Target = PotLevel6Entry.getDouble(potLevel6Target);
        potLevel7Target = PotLevel7Entry.getDouble(potLevel7Target);

        mmLevel1Target = MMLevel1Entry.getDouble(mmLevel1Target);
        mmLevel2Target = MMLevel2Entry.getDouble(mmLevel2Target);
        mmLevel3Target = MMLevel3Entry.getDouble(mmLevel3Target);
        mmLevel4Target = MMLevel4Entry.getDouble(mmLevel4Target);
        mmLevel5Target = MMLevel5Entry.getDouble(mmLevel5Target);
        mmLevel6Target = MMLevel6Entry.getDouble(mmLevel6Target);
        mmLevel7Target = MMLevel7Entry.getDouble(mmLevel7Target);
        */

        stepUp = (int)stepUpEntry.getDouble(stepUp);
        stepDown = (int)stepDownEntry.getDouble(stepDown);
        percOutputUp  = percOutputUpEntry.getDouble(percOutputUp);
        percOutputDown  = percOutputDownEntry.getDouble(percOutputDown);
        mmMode = mmModeEntry.getBoolean(mmMode);
        resetMMValues = resetMMValuesEntry.getBoolean(resetMMValues);
        logging = isLoggingEntry.getBoolean(false);

        // set current values
        leftEncoderPositionEntry.setDouble(leftTalon.getSelectedSensorPosition());
        rightEncoderPositionEntry.setDouble(rightTalon.getSelectedSensorPosition());

        potPositionEntry.setDouble(pot.getValue());
        stepUpEntry.setDouble(stepUp);
        stepDownEntry.setDouble(stepDown);
        percOutputUpEntry.setDouble(percOutputUp);
        percOutputDownEntry.setDouble(percOutputDown);

        if (resetMMValues) {
            acceleration    = (int) accelEntry.getDouble(acceleration);
            cruiseSpeed             = (int) cruiseEntry.getDouble(cruiseSpeed);
            pidP                    = (int) pidPEntry.getDouble(pidP);
            resetMMValues           = false;
            resetMMValuesEntry.setBoolean(resetMMValues);
            logger.info("Resetting Motion Magic Values");
        }
    }

    public boolean isLogging(){
        return logging;
    }
}
