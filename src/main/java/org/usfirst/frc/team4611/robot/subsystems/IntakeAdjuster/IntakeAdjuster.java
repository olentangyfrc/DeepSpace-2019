package org.usfirst.frc.team4611.robot.subsystems.IntakeAdjuster;

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

public class IntakeAdjuster extends Subsystem {

    private WPI_TalonSRX intakeAdjuster;

    private ShuffleboardTab tab;
	private NetworkTableEntry adjusterVelocity;

    private int intakeSpeed = 1600;

    public void init(PortMan pm) throws OzoneException {
        intakeAdjuster = new WPI_TalonSRX(pm.acquirePort(PortMan.can_23_label, "Intake.intakeAdjuster"));

        tab = Shuffleboard.getTab("Health Map");
        NetTableManager.updateValue("Health Map", "IntakeAdjusterInitialize", true);

        adjusterVelocity = tab.add("IntakeAdjuster Velocity", intakeSpeed).getEntry();
    }

    
    private static Logger logger = Logger.getLogger(IntakeAdjuster.class.getName());

    public void spinIntakeAdjusterForward() {

        logger.entering(IntakeAdjuster.class.getName(), "spinIndiWheelBackForward()");

        intakeAdjuster.set(ControlMode.Velocity, (int)(adjusterVelocity.getDouble(intakeSpeed)));        
    
        logger.exiting(IntakeAdjuster.class.getName(), "spinIndiWheelBackForward()");
    
        }
    
    public void spinIntakeAdjusterBackward() {

        logger.entering(IntakeAdjuster.class.getName(), "spinIndiWheelBackBackward()");

        intakeAdjuster.set(ControlMode.Velocity, -(int)(adjusterVelocity.getDouble(intakeSpeed))); 
    
        logger.exiting(IntakeAdjuster.class.getName(), "spinIndiWheelBackBackward()");

    
    }
    public void stopIntakeAdjuster(){

        logger.entering(IntakeAdjuster.class.getName(), "stopIndiWheelBack()");

        intakeAdjuster.set(ControlMode.Velocity, 0); 

        logger.exiting(IntakeAdjuster.class.getName(), "stopIndiWheelBack()");

    }

    @Override
    protected void initDefaultCommand() {

    }



}