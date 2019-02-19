package org.usfirst.frc.team4611.robot.subsystems.elevator;

import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Logger;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import org.usfirst.frc.team4611.robot.networktables.NetTableManager;
import org.usfirst.frc.team4611.robot.subsystems.PortMan;
import org.usfirst.frc.team4611.robot.subsystems.elevator.commands.KeepElevatorInPlace;
import org.usfirst.frc.team4611.robot.subsystems.elevator.commands.StopElevator;
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

    private double potTop = .88;
    private double potBot = .14;

    private ElevatorUpdater speedUpdater;
    private Timer speedTimer;

    private Potentiometer pot;

    private boolean upperSoftLimitToggle = false;
    private boolean lowerSoftLimitToggle = false;

    public Elevator(){

    }

    public void init(PortMan pm) throws Exception {
    
        if(logging) logger.info("initializing");

        tab = Shuffleboard.getTab("Health Map");
        NetTableManager.updateValue("Health Map", "ElevatorInitialize", true);

        isLogging = tab.add("Elevator Logging", false).getEntry();
        elevatorPercentUp = tab.add("Elevator Percent Up", power).getEntry();
        elevatorPercentDown = tab.add("Elevator Percent Down", power/8).getEntry();
        elevatorPosition1 = tab.add("Elevator Position1", .15).getEntry();
        elevatorPosition2 = tab.add("Elevator Position2", .45).getEntry();
        elevatorPosition3 = tab.add("Elevator Position3", .62).getEntry();
        elevatorPosition4 = tab.add("Elevator Position4", .92).getEntry();
        elevatorPosition5 = tab.add("Elevator Position5", 1).getEntry();
        elevatorPosition6 = tab.add("Elevator Position6", 1).getEntry();
        elevatorPosition7 = tab.add("Elevator Position7", .76).getEntry();
        potPosition = tab.add("Current Pot Position", 0).getEntry();

        elevatorLeftTalon = new WPI_TalonSRX(pm.acquirePort(PortMan.can_15_label, "Elevator.elevatorLeftTalon"));
        elevatorRightTalon = new WPI_TalonSRX(pm.acquirePort(PortMan.can_16_label, "Elevator.elevatorRightTalon"));
        hardLimitTop = new DigitalInput(pm.acquirePort(PortMan.digital0_label, "Elevator.hardLimitTop"));
        softLimitTop = new DigitalInput(pm.acquirePort(PortMan.digital1_label, "Elevator.softLimitTop"));
        softLimitBottom = new DigitalInput(pm.acquirePort(PortMan.digital2_label, "Elevator.softLimitBottom"));
        hardLimitBottom = new DigitalInput(pm.acquirePort(PortMan.digital3_label, "Elevator.hardLimitBottom"));

        elevatorLeftTalon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
        elevatorRightTalon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);

        speedUpdater = new ElevatorUpdater(this);
        speedTimer = new Timer();
        speedTimer.scheduleAtFixedRate(speedUpdater, 0, 20);

        elevatorLeftTalon.config_kP(0, .5, 0);
        elevatorLeftTalon.config_kI(0, 0, 0);
        elevatorLeftTalon.config_kD(0, 0, 0);
        elevatorLeftTalon.config_kF(0, 0, 0);

        elevatorRightTalon.config_kP(0, .5, 0);
        elevatorRightTalon.config_kI(0, 0, 0);
        elevatorRightTalon.config_kD(0, 0, 0);
        elevatorRightTalon.config_kF(0, 0, 0);

        //elevatorLeftTalon.configMotionCruiseVelocity(4096, 0);
        //elevatorLeftTalon.configMotionAcceleration(4096,0);
        //elevatorRightTalon.configMotionCruiseVelocity(4096, 0);
        //elevatorRightTalon.configMotionAcceleration(4096,0);

        this.resetEncoders();

        elevatorRightTalon.follow(elevatorLeftTalon);
        elevatorRightTalon.setInverted(false);

        logger.exiting("Elevator", "out of init");

        pot = new Potentiometer(pm.acquirePort(PortMan.analog0_label, "Elevator Pot"),potBot,potTop);
    }

    public boolean isLogging(){
        logging = isLogging.getBoolean(false);
        return logging;
    }

    public void stop() {
        elevatorLeftTalon.set(ControlMode.Velocity, 0);
    }

    public void move(boolean direction) {
        double speed;
        if(direction) {
            speed = (int)(maxRPM*elevatorPercentUp.getDouble(power));
        }
        else {
            speed = (int)(maxRPM*-elevatorPercentDown.getDouble(power));
        } 
        //if(logging) logger.info(""+pot.getValue());

        if(!softLimitBottom.get()) {
            lowerSoftLimitToggle = speed < 0;
        }

        if(!softLimitTop.get()) {
            upperSoftLimitToggle = speed > 0;
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
       //if(logging) logger.info("Speed: " + speed);
        elevatorLeftTalon.set(ControlMode.Velocity, speed);
    }

    public void stopElevator() {
        elevatorLeftTalon.stopMotor();
    }

    public boolean moveToPos1() {
        double finalTarget = elevatorPosition1.getDouble(.5);
        
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
    public boolean moveToPos2() {
        if(logging)
            logger.info("Moving to position 2");
        double finalTarget = elevatorPosition2.getDouble(.5);
        
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
    public boolean moveToPos3() {
        double finalTarget = elevatorPosition3.getDouble(.5);
        
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
    public boolean moveToPos4() {
        double finalTarget = elevatorPosition4.getDouble(.5);
        
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
    public boolean moveToPos5() {
        double finalTarget = elevatorPosition5.getDouble(.5);
        
        boolean stop = false;

        if(finalTarget - pot.getValue() < -.05) {
            this.move(false);
        }
        else if(finalTarget - pot.getValue() > .05) {
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
    public boolean moveToPos6() {
        double finalTarget = elevatorPosition6.getDouble(.5);
        
        boolean stop = false;

        if(finalTarget - pot.getValue() < -.05) {
            this.move(false);
        }
        else if(finalTarget - pot.getValue() > .05) {
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
    public boolean moveToPos7() {
        
        double finalTarget = elevatorPosition7.getDouble(.5);
        
        boolean stop = false;

        if(finalTarget - pot.getValue() < -.05) {
            this.move(false);
        }
        else if(finalTarget - pot.getValue() > .05) {
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

    /**
     * This can replace the position specific methods ... and probably should. please simplify.
     * @param p
     * @return
     */
    public boolean moveToPos(int p) {
        double finalTarget;
        
        switch (p) {
            case 1:
                finalTarget = elevatorPosition1.getDouble(.5);
                break;
            case 2:
                finalTarget = elevatorPosition2.getDouble(.5);
                break;
            case 3:
                finalTarget = elevatorPosition3.getDouble(.5);
                break;
            case 4:
                finalTarget = elevatorPosition4.getDouble(.5);
                break;
            case 5:
                finalTarget = elevatorPosition5.getDouble(.5);
                break;
            case 6:
                finalTarget = elevatorPosition6.getDouble(.5);
                break;
            case 7:
                finalTarget = elevatorPosition7.getDouble(.5);
                break;
            default:
                return false;
        }
        
        boolean stop = false;

        if(finalTarget - pot.getValue() < -.05) {
            this.move(false);
        }
        else if(finalTarget - pot.getValue() > .05) {
            this.move(true);
        }
        else{
            this.stopElevator();
            stop = true;
        }
        return stop;

    }

    public boolean isAtPosition(int p) {
        double finalTarget;
        
        switch (p) {
            case 1:
                finalTarget = elevatorPosition1.getDouble(.5);
                break;
            case 2:
                finalTarget = elevatorPosition2.getDouble(.5);
                break;
            case 3:
                finalTarget = elevatorPosition3.getDouble(.5);
                break;
            case 4:
                finalTarget = elevatorPosition4.getDouble(.5);
                break;
            case 5:
                finalTarget = elevatorPosition5.getDouble(.5);
                break;
            case 6:
                finalTarget = elevatorPosition6.getDouble(.5);
                break;
            case 7:
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

    public void updateElevator(){
        
        if((hardLimitTop.get() && elevatorLeftTalon.get() >= 0) || (hardLimitBottom.get() && elevatorLeftTalon.get() <= 0)) {
            
            elevatorLeftTalon.configMotionCruiseVelocity(0, 0); 
        
        } else if((hardLimitTop.get() && elevatorLeftTalon.get() < 0) || 
        (softLimitTop.get() && elevatorLeftTalon.get() >= 0) || 
        (softLimitBottom.get() && elevatorLeftTalon.get() <= 0) || 
        (hardLimitBottom.get() && elevatorLeftTalon.get() > 0)) {
        
            elevatorLeftTalon.configMotionCruiseVelocity(4096 / 2, 0);
        
        } else {
        
            elevatorLeftTalon.configMotionCruiseVelocity(4096, 0);
        
        }
    }

    public void keepInPlace() {
        //if(logging) logger.info("keeping in place");
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

    class ElevatorUpdater extends TimerTask {

        private Elevator ele;

        public ElevatorUpdater(Elevator e) {
            ele = e;
        }

        @Override
        public void run() {
            ele.updateElevator();
        }

    }
}

