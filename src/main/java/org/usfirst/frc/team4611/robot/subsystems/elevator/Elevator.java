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

    private boolean mmMode  = true; // Motion Magic mode by default

    public Elevator(){
    }

    public void setMMMode(boolean on){
        mmMode = on;
    }

    private double  potTop      = 0.98;
    private double  potBot      = .06;
    public void init(PortMan pm) throws Exception {
        initSB();

        leftTalon = new WPI_TalonSRX(pm.acquirePort(PortMan.can_15_label, "Elevator.elevatorLeftTalon"));
        rightTalon = new WPI_TalonSRX(pm.acquirePort(PortMan.can_16_label, "Elevator.elevatorRightTalon"));

        hardLimitTop = new DigitalInput(pm.acquirePort(PortMan.digital10_label, "Elevator.hardLimitTop"));
        softLimitTop = new DigitalInput(pm.acquirePort(PortMan.digital11_label, "Elevator.softLimitTop"));
        softLimitBottom = new DigitalInput(pm.acquirePort(PortMan.digital12_label, "Elevator.softLimitBottom"));
        hardLimitBottom = new DigitalInput(pm.acquirePort(PortMan.digital13_label, "Elevator.hardLimitBottom"));

        pot = new Potentiometer(pm.acquirePort(PortMan.analog4_label, "Elevator Pot"), potBot, potTop);

        initTalonCommon();
        initTalonsForMotionMagic();
    }

    public void stop() {
        if (!mmMode) {
            leftTalon.set(ControlMode.PercentOutput, 0.0);
        }
    }

    public void move(boolean moveUp) {
        if (mmMode) {
            controlMMDown = false;
            moveMM(moveUp);
        } else {
            movePercOutput(moveUp);
        }
        updateValues();
    }

    public boolean moveToLevel (HappyPosition level) {
        boolean done;
        if (mmMode) {
            controlMMDown = false;
            done =  moveToMMPos(level);
        } else {
            done =  moveToPotPos(level);
        }
        updateValues();
        return done;
    }

    private int     maxEncoder    = 22500;
    private int     stepUp        = 1500;
    private int     stepDown      = -500;

    private void moveMM(boolean moveUp) {
        int step;
        int target;
        boolean lowerSoftLimitToggle = false;
        boolean upperSoftLimitToggle = false;

        step = (moveUp ? stepUp : stepDown);

        if(!softLimitBottom.get()) {
            lowerSoftLimitToggle = step < 0;
        }

        if(!softLimitTop.get()) {
            upperSoftLimitToggle = step > 0;
        }
        
        if(lowerSoftLimitToggle && step <= 0) {
            step = (step/2);
        }

        if(upperSoftLimitToggle && step >=0) {
            step = (step/2);
        }

        if(!hardLimitTop.get() && step > 0) {
            return;
        }
        if(!hardLimitBottom.get() && step <= 0) {
            leftTalon.set(ControlMode.Velocity, 0);
            return;
        }

        target = leftTalon.getSelectedSensorPosition() + step;

        if (target > maxEncoder)
            target = maxEncoder - 10;

        if (target < 0)
            target = 0;

        leftTalon.set(ControlMode.MotionMagic, target);
    }

    private double mmLevel1Target  =  10000;
    private double mmLevel2Target  =  10000;
    private double mmLevel3Target  =  14200;
    private double mmLevel4Target  =  14623;
    private double mmLevel5Target  =  17400;
    private double mmLevel6Target  =  17600;
    private double mmLevel7Target  =  17500;
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
            case CARGO_GRAB:
                finalTarget = mmCargoGrabTarget;
            default:
                return false;
        }
        
        boolean stop = false;

        if (level == HappyPosition.BOTTOM) {
            leftTalon.set(ControlMode.Velocity, 0.0);
            stop = true;
        }else if(finalTarget - leftTalon.getSelectedSensorPosition() < -positionTolerance) {
            moveMM(false);
        } else if(finalTarget - leftTalon.getSelectedSensorPosition() > positionTolerance) {
            moveMM(true);
        } else {
            stop = true;
        }
        return stop;
    }

    private double  percOutputUp    = 0.75;
    private double  percOutputDown  = 0.1;

    public void movePercOutput(boolean moveUp) {
        boolean upperSoftLimitToggle = false;
        boolean lowerSoftLimitToggle = false;
        double output;

        output = (moveUp ? percOutputUp : (-1 * percOutputDown));
        logger.info("moveUp [" + moveUp + "] % [" + output + "]");

        if(!softLimitBottom.get()) {
            lowerSoftLimitToggle = output < 0;
        }

        if(!softLimitTop.get()) {
            upperSoftLimitToggle = output > 0;
        }
        
        if(!hardLimitTop.get() && output >=0 ) {
            output = 0;
        }
        if(upperSoftLimitToggle && output >= 0) {
            output = output/2;
        }
        if(lowerSoftLimitToggle && output <= 0) {
            output = (output/2);
        }
        if(!hardLimitBottom.get() && output <= 0) {
            output = 0;
        }

        leftTalon.set(ControlMode.PercentOutput, output);
    }

    public static enum HappyPosition {BOTTOM, LEVEL_1, LEVEL_2, LEVEL_3, LEVEL_4, LEVEL_5, LEVEL_6, LEVEL_7, CARGO_GRAB};

    private double potLevel1Target  =  0.0539;
    private double potLevel2Target  =  0.0400;
    private double potLevel3Target  =  0.5335;
    private double potLevel4Target  =  0.8742;
    private double potLevel5Target  =  0.9842;
    private double potLevel6Target  =  0.9689;
    private double potLevel7Target  =  0.7230;
    private double potCargoGrabTarget   = 0.5335;

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
            case CARGO_GRAB:
                finalTarget = potCargoGrabTarget;
                break;
        }
        
        boolean stop = false;

        if(finalTarget - pot.getPercentOutput() < -.03) {
            this.move(false);
        }
        else if(finalTarget - pot.getPercentOutput() > .03) {
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
            case CARGO_GRAB:
                finalTarget = potCargoGrabTarget;
                break;
            default:
                return false;
        }

        return (Math.abs(finalTarget - pot.getPercentOutput()) < .05);
    }

    private void initTalonCommon() {
        leftTalon.configFactoryDefault();
        rightTalon.configFactoryDefault();

        rightTalon.configPeakCurrentLimit(40);
        leftTalon.configPeakCurrentLimit(40);

        leftTalon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
        rightTalon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);

        rightTalon.follow(leftTalon);

        leftTalon.setInverted(true);
        rightTalon.setInverted(false);
    }

    private double  pidP = 1.0;
    private int     cruiseSpeed = 10000;
    private int     acceleration = 12000;

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

    private void keepInPlaceForTime() {
        
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
    }

    public void updateValues() {
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

        potPositionEntry.setDouble(pot.getRawValue());
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

        /** quick dirty hack so motion magic doesn't smoke motors */
        /** this needs to be smarter */
        if (mmMode) {
            if (!controlMMDown && encoderCheckCount == 0 && leftTalon.getSelectedSensorPosition() > 10) {
                encoderCheckCount += 1;
            } else if (encoderCheckCount > 1000) {
                if (Math.abs(lastEncoderPosition - leftTalon.getSelectedSensorPosition()) < 10) {
                    leftTalon.set(ControlMode.Velocity, 0.0);
                    encoderCheckCount = 0;
                    controlMMDown = true;
                }
            } else {
                encoderCheckCount += 1;
            }
            lastEncoderPosition = leftTalon.getSelectedSensorPosition();
            if (lastEncoderPosition < 5) {
                controlMMDown = false;
            }
        }
    }
    private double lastEncoderPosition = 0;
    private int encoderCheckCount = 0;
    private boolean controlMMDown = false;

    public boolean isLogging(){
        return logging;
    }
}