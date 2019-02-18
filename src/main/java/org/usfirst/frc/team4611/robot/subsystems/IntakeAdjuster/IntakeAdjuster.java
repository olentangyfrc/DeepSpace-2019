package org.usfirst.frc.team4611.robot.subsystems.IntakeAdjuster;

import java.util.logging.Logger;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import org.usfirst.frc.team4611.robot.OzoneException;
import org.usfirst.frc.team4611.robot.networktables.NetTableManager;
import org.usfirst.frc.team4611.robot.subsystems.PortMan;
import org.usfirst.frc.team4611.robot.subsystems.navigation.sensors.Potentiometer;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import org.usfirst.frc.team4611.robot.subsystems.navigation.sensors.Potentiometer; 

public class IntakeAdjuster extends Subsystem {

    private WPI_TalonSRX intakeAdjuster;

    private ShuffleboardTab tab;
    private NetworkTableEntry adjusterVelocity;
    private NetworkTableEntry currentAdjusterPosition;
    private NetworkTableEntry adjusterPosition1;
    private NetworkTableEntry adjusterPosition2;

    private ShuffleboardTab portTab;
    private NetworkTableEntry port1;
    private NetworkTableEntry port2;
    
    private double power = 1;
    private double pos = 1;
    private int intakeSpeed = 1600;
    private double minPos = 0.0582;
    private double maxPos = 0.0594;

    private Potentiometer pot;

    public void init(PortMan pm) throws OzoneException {
        intakeAdjuster = new WPI_TalonSRX(pm.acquirePort(PortMan.can_23_label, "Intake.intakeAdjuster"));

        tab = Shuffleboard.getTab("Health Map");
        NetTableManager.updateValue("Health Map", "IntakeAdjusterInitialize", true);

        portTab = Shuffleboard.getTab("Port Manager");
        NetTableManager.updateValue("Port Manager", "IntakeAdjuster Ports", true);

        adjusterVelocity = tab.add("IntakeAdjuster Velocity", power).getEntry();
        currentAdjusterPosition = tab.add("Intake Adjuster Posittion", pos).getEntry();
        adjusterPosition1 = tab.add("Adjuster Position1", pos).getEntry();
        adjusterPosition2 = tab.add("Adjuster Position2", pos).getEntry();

        port1 = portTab.add("can_23_label", true).getEntry();
        port2 = portTab.add("analog2_label", true).getEntry();
        
        pot = new Potentiometer(pm.acquirePort(PortMan.analog5_label, "IntakeAdjuster Pot"));
    }

    
    private static Logger logger = Logger.getLogger(IntakeAdjuster.class.getName());

    public void spinIntakeAdjusterForward() {

        logger.entering(IntakeAdjuster.class.getName(), "spinIndiWheelBackForward()");

        intakeAdjuster.set(ControlMode.Velocity, (int)(intakeSpeed*(adjusterVelocity.getDouble(power))));
        currentAdjusterPosition.setDouble((pot.getValue()-minPos)/(maxPos-minPos));
        logger.info(""+pot.getValue());        
    
        logger.exiting(IntakeAdjuster.class.getName(), "spinIndiWheelBackForward()");
    
        }
    
    public void spinIntakeAdjusterBackward() {

        logger.entering(IntakeAdjuster.class.getName(), "spinIndiWheelBackBackward()");

        intakeAdjuster.set(ControlMode.Velocity, (int)(-intakeSpeed*(adjusterVelocity.getDouble(power))));    
        currentAdjusterPosition.setDouble((pot.getValue()-minPos)/(maxPos-minPos)); 
        logger.info(""+pot.getValue());     
    
        logger.exiting(IntakeAdjuster.class.getName(), "spinIndiWheelBackBackward()");

    
    }
    public void stopIntakeAdjuster(){

        logger.entering(IntakeAdjuster.class.getName(), "stopIndiWheelBack()");

        intakeAdjuster.set(ControlMode.Velocity, 0); 
        currentAdjusterPosition.setDouble((pot.getValue()-minPos)/(maxPos-minPos)); 

        logger.exiting(IntakeAdjuster.class.getName(), "stopIndiWheelBack()");

    }
    public boolean moveToPos1() {
        double finalTarget = adjusterPosition1.getDouble(.5)*(maxPos-minPos)+minPos;
        
        boolean stop = false;


        if(finalTarget - pot.getValue() < -.01) {
            this.spinIntakeAdjusterBackward();
        }
        else if(finalTarget - pot.getValue() > .01) {
            this.spinIntakeAdjusterForward();
        }
        else{
            this.stopIntakeAdjuster();
            stop = true;
        }
        return stop;
    }
    public boolean moveToPos2() {
        double finalTarget = adjusterPosition2.getDouble(.5)*(maxPos-minPos)+minPos;
        
        boolean stop = false;

        if(finalTarget - pot.getValue() < -.01) {
            this.spinIntakeAdjusterBackward();
        }
        else if(finalTarget - pot.getValue() > .01) {
            this.spinIntakeAdjusterForward();
        }
        else{
            this.stopIntakeAdjuster();
            stop = true;
        }
        return stop;
    }

    @Override
    protected void initDefaultCommand() {

    }



}