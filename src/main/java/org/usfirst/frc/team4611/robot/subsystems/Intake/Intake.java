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
    static private ShuffleboardTab tab = Shuffleboard.getTab("Intake");

    private double defaultPercent = .75;
    private double slowPercent = .5;

    private NetworkTableEntry isLogging;
    private NetworkTableEntry motorSpeed;
    private NetworkTableEntry slowMotorSpeed;
    
    private boolean logging = false;

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

        isLogging = tab.add("Intake Logging", false).getEntry();
        
        motorSpeed = tab.add("Intake Motor Speed", defaultPercent).getEntry();
        slowMotorSpeed = tab.add("Intake\nSlow Motor Speed", slowPercent).getEntry();

        logger.exiting(Intake.class.getName(), "init()");
    }

    public boolean isLogging(){
        logging = isLogging.getBoolean(false);
        return logging;
    }

    public void spinIndiWheelFrontForward() {
        intake.set(ControlMode.PercentOutput, motorSpeed.getDouble(defaultPercent));
    } 
    
    public void spinIndiWheelFrontBackwardSlow() {
        intake.set(ControlMode.PercentOutput, -slowMotorSpeed.getDouble(slowPercent));
    }

    public void spinIndiWheelFrontBackward() {

        if(logging)
            logger.info("entering spinIndiWheelFrontBackward()");

        intake.set(ControlMode.PercentOutput, -motorSpeed.getDouble(defaultPercent));
    
        if(logging)
            logger.info("Intake Velocity: " + -motorSpeed.getDouble(defaultPercent));
        if(logging)
            logger.info("exiting spinIndiWheelFrontBackward()");
        

    }

    public void stopIndiWheelFront(){
        intake.set(ControlMode.Velocity, 0); 
    }

    
   

    @Override
    protected void initDefaultCommand() {

    }
}