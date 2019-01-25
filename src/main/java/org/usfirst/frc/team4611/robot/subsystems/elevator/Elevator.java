package org.usfirst.frc.team4611.robot.subsystems.elevator;

import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Logger;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import org.usfirst.frc.team4611.robot.subsystems.PortMan;
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

    private boolean upperSoftLimtToggle = false;
    private boolean lowerSoftLimitToggle = false;

    public Elevator(){

    }

    public void init(PortMan pm) throws Exception {
    
        logger.info("initializing");

        elevatorLeftTalon = new WPI_TalonSRX(pm.acquirePort(PortMan.can_22_label, "Elevator.elevatorLeftTalon"));
        elevatorRightTalon = new WPI_TalonSRX(pm.acquirePort(PortMan.can_23_label, "Elevator.elevatorRightTalon"));
        hardLimitTop = new DigitalInput(pm.acquirePort(PortMan.digital0_label, "Elevator.upperHardLimit"));
        softLimitTop = new DigitalInput(pm.acquirePort(PortMan.digital1_label, "Elevator.upperSoftLimit"));
        softLimitBottom = new DigitalInput(pm.acquirePort(PortMan.digital2_label, "Elevator.lowerSoftLimit"));
        hardLimitBottom = new DigitalInput(pm.acquirePort(PortMan.digital3_label, "Elevator.lowerHardLimit"));

        speedUpdater = new ElevatorUpdater(this);
        speedTimer = new Timer();
        speedTimer.scheduleAtFixedRate(speedUpdater, 0, 20);

        elevatorRightTalon.follow(elevatorLeftTalon);

        elevatorLeftTalon.config_kP(0, .5, 0);
        elevatorLeftTalon.config_kI(0, 0, 0);
        elevatorLeftTalon.config_kD(0, 0, 0);
        elevatorLeftTalon.config_kF(0, 0, 0);

        elevatorLeftTalon.configMotionCruiseVelocity(4096, 0);
        elevatorLeftTalon.configMotionAcceleration(4096,0);

    }

    public void move(double speed) {

        if(softLimitTop.get()) {
            upperSoftLimtToggle = !upperSoftLimtToggle;
        } else if(softLimitBottom.get()) {
            lowerSoftLimitToggle = !lowerSoftLimitToggle;
        }
 
        if(hardLimitTop.get()) {
            if(speed >= 0) {
                elevatorLeftTalon.set(ControlMode.PercentOutput, 0);
            } else {
                elevatorLeftTalon.set(ControlMode.PercentOutput, speed / 2);
            }
        } else if(upperSoftLimtToggle) {
            if(speed >= 0) {
                elevatorLeftTalon.set(ControlMode.PercentOutput, speed / 2);
            } else {
                elevatorLeftTalon.set(ControlMode.PercentOutput, speed);
            }
        } else if(lowerSoftLimitToggle) {
            if(speed <= 0) {
                elevatorLeftTalon.set(ControlMode.PercentOutput, speed / 2);
            } else {
                elevatorLeftTalon.set(ControlMode.PercentOutput, speed);
            }
        } else if(hardLimitBottom.get()) {
            if(speed <= 0) {
                elevatorLeftTalon.set(ControlMode.PercentOutput, 0);
            } else {
                elevatorLeftTalon.set(ControlMode.PercentOutput, speed / 2);
            }
        } else {
            elevatorLeftTalon.set(ControlMode.PercentOutput, speed);
        }
    }

    public void moveToPos(double position) {
        elevatorLeftTalon.set(ControlMode.MotionMagic, position);
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

