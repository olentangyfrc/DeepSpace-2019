package org.usfirst.frc.team4611.robot.subsystems.elevator;

import java.util.Timer;
import java.util.TimerTask;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import org.usfirst.frc.team4611.robot.subsystems.PortMan;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Elevator extends Subsystem {

    private WPI_TalonSRX elevatorLeftTalon;
    private WPI_TalonSRX elevatorRightTalon;
    private DigitalInput hardLimitTop;
    private DigitalInput softLimitTop;
    private DigitalInput softLimitBottom;
    private DigitalInput hardLimitBottom;

    private ElevatorUpdater speedUpdater;
    private Timer speedTimer;

    public Elevator(){

    }

    public void init(PortMan pm) throws Exception {
    
        elevatorLeftTalon = new WPI_TalonSRX(pm.acquirePort(PortMan.can_22_label, "Elevator.elevatorLeftTalon"));
        elevatorRightTalon = new WPI_TalonSRX(pm.acquirePort(PortMan.can_23_label, "Elevator.elevatorRightTalon"));
        hardLimitTop = new DigitalInput(pm.acquirePort(PortMan.digital0_label, "Elevator.hardStopTopA"));
        softLimitTop = new DigitalInput(pm.acquirePort(PortMan.digital1_label, "Elevator.hardStopTopB"));
        softLimitBottom = new DigitalInput(pm.acquirePort(PortMan.digital2_label, "Elevator.softStopTopA"));
        hardLimitBottom = new DigitalInput(pm.acquirePort(PortMan.digital3_label, "Elevator.softStopTopB"));

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
    
        elevatorLeftTalon.configForwardSoftLimitEnable(true , 0);
    }

    public void move(double speed) {

        if(hardLimitTop.get()) {
            if(elevatorLeftTalon.get() >= 0) {
                elevatorLeftTalon.set(ControlMode.PercentOutput, 0);
            } else {
                elevatorLeftTalon.set(ControlMode.PercentOutput, speed / 2);
            }
        } else if(softLimitTop.get()) {
            if(elevatorLeftTalon.get() >= 0) {
                elevatorLeftTalon.set(ControlMode.PercentOutput, speed / 2);
            } else {
                elevatorLeftTalon.set(ControlMode.PercentOutput, speed);
            }
        } else if(softLimitBottom.get()) {
            if(elevatorLeftTalon.get() <= 0) {
                elevatorLeftTalon.set(ControlMode.PercentOutput, speed / 2);
            } else {
                elevatorLeftTalon.set(ControlMode.PercentOutput, speed);
            }
        } else if(hardLimitBottom.get()) {
            if(elevatorLeftTalon.get() <= 0) {
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

