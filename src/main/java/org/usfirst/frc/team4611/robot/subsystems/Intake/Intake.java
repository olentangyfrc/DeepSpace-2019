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

    private ShuffleboardTab tab;
	private NetworkTableEntry motorSpeed;

    public void init(PortMan pm) throws OzoneException {
        intake = new WPI_TalonSRX(pm.acquirePort(PortMan.can_22_label, "Intake.intakeTalon"));

        tab = Shuffleboard.getTab("Health Map");
		NetTableManager.updateValue("Health Map", "Intake Initialized", true);
        
        motorSpeed = tab.add("Intake Motor Speed", 1.0).getEntry();
    }

    public void spinIndiWheelFrontForward() {

        logger.entering(Intake.class.getName(), "spinIndiWheelFrontForward()");

        intake.set(ControlMode.Velocity, intakeSpeed*motorSpeed.getDouble(0));
    
        logger.exiting(Intake.class.getName(), "spinIndiWheelFrontForward()");


    
    }

    public void spinIndiWheelFrontBackward() {

        logger.entering(Intake.class.getName(), "spinIndiWheelFrontBackward()");

        intake.set(ControlMode.Velocity, -intakeSpeed*motorSpeed.getDouble(0));
    
        logger.exiting(Intake.class.getName(), "spinIndiWheelFrontBackward()");
        

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