package org.usfirst.frc.team4611.robot.subsystems.elevator;

import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Logger;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import org.usfirst.frc.team4611.robot.networktables.NetTableManager;
import org.usfirst.frc.team4611.robot.subsystems.PortMan;
//import org.usfirst.frc.team4611.robot.subsystems.elevator.commands.StopElevator;
//import org.usfirst.frc.team4611.robot.subsystems.navigation.sensors.Potentiometer;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;

public class Elevator extends Subsystem {

    private final Logger logger = Logger.getLogger(Elevator.class.getName());

    private ShuffleboardTab tab;
    private NetworkTableEntry elevatorPercent;

    public static double maxRPM = 1700;
    public final static double ELEVATOR_TOP = -133000;
    public final static double ELEVATOR_BOTTOM = 0;

    private WPI_TalonSRX elevator_Talon;
    private DigitalInput hardLimitTop;
    private DigitalInput softLimitTop;
    private DigitalInput softLimitBottom;
    private DigitalInput hardLimitBottom;

    private DigitalInput limitSwitch;

    //private ElevatorUpdater speedUpdater;
    private Timer speedTimer;

    //private Potentiometer pot;

    private boolean upperSoftLimitToggle = false;
    private boolean lowerSoftLimitToggle = false;

    public Elevator(){

    }

    public void init(PortMan pm) throws Exception {
    
        logger.info("initializing");

        elevator_Talon = new WPI_TalonSRX(15);
        limitSwitch = new DigitalInput(0);
    }

    public void move(double speed) {
        elevator_Talon.set(ControlMode.PercentOutput, speed);
    }

    public void moveToPos(double position) {
        elevator_Talon.config_kP(0, 0.5, 0);
        elevator_Talon.config_kI(0, 0, 0);
        elevator_Talon.config_kD(0, 0, 0);
        elevator_Talon.config_kF(0, 0, 0);

        elevator_Talon.configMotionCruiseVelocity(4096, 0);
        elevator_Talon.configMotionAcceleration(4096, 0);

        elevator_Talon.set(ControlMode.MotionMagic, position);
    }

    public void resetEncoders() {
        elevator_Talon.setSelectedSensorPosition(0, 0 ,0);
    }

    public boolean isSwitchSet() { 
		return limitSwitch.get();
	}

    @Override
    protected void initDefaultCommand() {
        //this.setDefaultCommand(new StopElevator());
    }

    public void writeToShuffleboard() {

    }

    public void log() {

    }

}
