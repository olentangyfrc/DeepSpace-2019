package org.usfirst.frc.team4611.robot.subsystems.Intake;

import java.util.logging.Logger;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import org.usfirst.frc.team4611.robot.OzoneException;
import org.usfirst.frc.team4611.robot.networktables.NetTableManager;
import org.usfirst.frc.team4611.robot.subsystems.PortMan;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;

public class Intake extends Subsystem {

    private WPI_TalonSRX intake;

    private static Logger logger = Logger.getLogger(Intake.class.getName());


    private int intakeSpeed = 1600;
    private double defaultPercent = .5;

    private ShuffleboardTab tab;
	private NetworkTableEntry motorSpeed;

    public Intake() {
        
    }

    public void init(PortMan pm) throws OzoneException {

        logger.entering(Intake.class.getName(), "init()");
        
        intake = new WPI_TalonSRX(pm.acquirePort(PortMan.can_22_label, "Intake.intakeTalon"));

        intake.config_kP(0, .5, 0);
        intake.config_kI(0, 0, 0);
        intake.config_kD(0, 0, 0);
        intake.config_kF(0, 0, 0);
        intake.configMotionCruiseVelocity(4096, 0);
        intake.configMotionAcceleration(4096,0);

        tab = Shuffleboard.getTab("Health Map");
		NetTableManager.updateValue("Health Map", "Intake Initialized", true);
        
        motorSpeed = tab.add("Intake Motor Speed", defaultPercent).getEntry();

        logger.exiting(Intake.class.getName(), "init()");
    }

    public void spinIndiWheelFrontForward() {

        logger.entering(Intake.class.getName(), "spinIndiWheelFrontForward()");

        intake.set(ControlMode.Velocity, (int)(intakeSpeed*motorSpeed.getDouble(defaultPercent)));
       
        logger.exiting(Intake.class.getName(), "spinIndiWheelFrontForward()");


    
    }

    public void spinIndiWheelFrontBackward() {

        logger.info("entering spinIndiWheelFrontBackward()");

        intake.set(ControlMode.Velocity, -(int)(intakeSpeed*motorSpeed.getDouble(defaultPercent)));
    
        logger.info("Intake Velocity: " + -(int)(intakeSpeed*motorSpeed.getDouble(defaultPercent)));
        logger.info("exiting spinIndiWheelFrontBackward()");
        

    }

    public void stopIndiWheelFront(){

        logger.entering(Intake.class.getName(), "stopIndiWheelFront()");

        intake.set(ControlMode.Velocity, 0); 

        logger.exiting(Intake.class.getName(), "stopIndiWheelFront()");

    }

    
   

    @Override
    protected void initDefaultCommand() {

    }

}