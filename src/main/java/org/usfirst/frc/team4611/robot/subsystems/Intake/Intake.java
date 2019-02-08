package org.usfirst.frc.team4611.robot.subsystems.Intake;

import java.util.logging.Logger;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import org.usfirst.frc.team4611.robot.OzoneException;
import org.usfirst.frc.team4611.robot.subsystems.PortMan;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Intake extends Subsystem {

    private WPI_TalonSRX intake;

    private static Logger logger = Logger.getLogger(Intake.class.getName());


    private int intakeSpeed = 1600;

    public void init(PortMan pm) throws OzoneException {
        intake = new WPI_TalonSRX(pm.acquirePort(PortMan.can_22_label, "Intake.intakeTalon"));
    }

    public void spinIndiWheelFrontForward() {

        logger.entering(Intake.class.getName(), "spinIndiWheelFrontForward()");

        intake.set(ControlMode.Velocity, intakeSpeed);
    
        logger.exiting(Intake.class.getName(), "spinIndiWheelFrontForward()");
    
    }

    public void spinIndiWheelFrontBackward() {

        logger.entering(Intake.class.getName(), "spinIndiWheelFrontBackward()");

        intake.set(ControlMode.Velocity, -intakeSpeed);
    
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