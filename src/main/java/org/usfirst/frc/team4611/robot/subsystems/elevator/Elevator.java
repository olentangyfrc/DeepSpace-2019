package org.usfirst.frc.team4611.robot.subsystems.elevator;

import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Logger;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import org.usfirst.frc.team4611.robot.subsystems.PortMan;
import org.usfirst.frc.team4611.robot.subsystems.navigation.sensors.Potentiometer;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Elevator extends Subsystem {

    public final Logger logger = Logger.getLogger(Elevator.class.getName());

    private WPI_TalonSRX elevatorLeftTalon;
    private WPI_TalonSRX elevatorRightTalon;
    private DigitalInput hardLimitTop;
    private DigitalInput softLimitTop;
    private DigitalInput softLimitBottom;
    private DigitalInput hardLimitBottom;

    private ElevatorUpdater speedUpdater;
    private Timer speedTimer;

    private Potentiometer pot;

    private boolean upperSoftLimitToggle = false;
    private boolean lowerSoftLimitToggle = false;

    public Elevator(){

    }

    public void init(PortMan pm) throws Exception {
    
        logger.info("initializing");

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

        elevatorLeftTalon.configMotionCruiseVelocity(4096, 0);
        elevatorLeftTalon.configMotionAcceleration(4096,0);
        elevatorRightTalon.configMotionCruiseVelocity(4096, 0);
        elevatorRightTalon.configMotionAcceleration(4096,0);

        this.resetEncoders();

        elevatorRightTalon.follow(elevatorLeftTalon);
        elevatorRightTalon.setInverted(true);

        pot = new Potentiometer(pm.acquirePort(pm.analog0_label, "Elevator Pot"));
    }

    public void move(double speed) {

        if(!softLimitBottom.get()) {
            lowerSoftLimitToggle = speed < 0;
        }

        if(!softLimitTop.get()) {
            upperSoftLimitToggle = speed > 0;
        }
        
        if(!hardLimitTop.get()) {
            if(speed >= 0) {
                speed = 0;
            }
        }
        if(upperSoftLimitToggle) {
            if(speed >= 0) {
                speed = speed/2;
            }
        }
        if(lowerSoftLimitToggle) {
            if(speed <= 0) {
                speed = speed/2;
            }
        }
        if(!hardLimitBottom.get()) {
            if(speed <= 0) {
                speed = 0;
            }
        }

        logger.info("Velocity: " +elevatorLeftTalon.getSelectedSensorVelocity() + " " + elevatorRightTalon.getSelectedSensorVelocity());
        logger.info("Position:  " + elevatorLeftTalon.getSelectedSensorPosition() + " " + elevatorRightTalon.getSelectedSensorPosition());
        elevatorLeftTalon.set(ControlMode.Velocity, speed * 1000);

    }

    public void moveToPos(double position) {
        if(position - pot.getValue() < -.05) {
            this.move(-800);
        }
        else if(position - pot.getValue() > .05) {
            this.move(800);
        }
        else{
            this.move(0);
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

    @Override
    protected void initDefaultCommand() {

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

