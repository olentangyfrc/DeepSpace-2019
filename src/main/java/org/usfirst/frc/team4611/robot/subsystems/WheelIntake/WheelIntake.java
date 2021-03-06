package org.usfirst.frc.team4611.robot.subsystems.WheelIntake;

import java.util.logging.Logger;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import org.usfirst.frc.team4611.robot.networktables.NetTableManager;
import org.usfirst.frc.team4611.robot.subsystems.PortMan;
import org.usfirst.frc.team4611.robot.subsystems.SubsystemFactory;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;

public class WheelIntake extends Subsystem {

    Logger logger = Logger.getLogger(SubsystemFactory.class.getName());
    static private ShuffleboardTab tab = Shuffleboard.getTab("WheelIntake");

    private WPI_TalonSRX wheelIntakeTalon;

    private DigitalInput switch1;
    private DigitalInput switch2;

    private double pVal=0.5;

    public double ejectBallDuration = 1500; // spin for 1.5 seconds

    private NetworkTableEntry velocity;
    private NetworkTableEntry wheelIntakeSlowVelocity;
    private NetworkTableEntry wheelIntakeMaxRPM;
    private NetworkTableEntry wheelEjectPercent; 
    private NetworkTableEntry wheelStage1Percent;
    private NetworkTableEntry wheelStage2Percent;
    private NetworkTableEntry wheelStage3Percent;

    public WheelIntake() {
    }
   
    public void init(PortMan pm) throws Exception {

        logger.info("init");
        switch1 = new DigitalInput(pm.acquirePort(PortMan.digital0_label, "WheelIntake.switch1"));
        switch2 = new DigitalInput(pm.acquirePort(PortMan.digital1_label, "WheelIntake.switch2"));

        wheelIntakeTalon = new WPI_TalonSRX(pm.acquirePort(PortMan.can_17_label, "wheelIntake.talon"));
        
        wheelIntakeTalon.config_kP(0,pVal, 0);
        wheelIntakeTalon.config_kI(0, 0.000, 0);
        wheelIntakeTalon.config_kD(0, 0, 0);

        velocity = tab.add("Velocity", -1.0).getEntry();
        wheelIntakeSlowVelocity = tab.add("IntakeSlowPercent", 0).getEntry();
        wheelIntakeMaxRPM = tab.add("wheelIntakeMaxRPM", 1000).getEntry();
        wheelEjectPercent = tab.add("wheelEjectPercentage", .95).getEntry();
        wheelStage1Percent = tab.add("Wheel Intake Stage 1 Percent", 0.15).getEntry();
        wheelStage2Percent = tab.add("Wheel Intake Stage 2 Percent", 0.1).getEntry();
        wheelStage3Percent = tab.add("Wheel Intake Stage 3 Percent", 0.1).getEntry();
        
        NetTableManager.updateValue("Wheel Intake", "Wheel Intake Initialization", true);
    }

    public void moveIntake(double speed) {
        
        wheelIntakeTalon.set(ControlMode.Velocity, speed*wheelIntakeMaxRPM.getDouble(1000));
 
    }

    public void moveWheelIntakeSlow() {
 
        wheelIntakeTalon.set(ControlMode.Velocity, wheelIntakeSlowVelocity.getDouble(0)*wheelIntakeMaxRPM.getDouble(1000));
 
    }

    public void stopIntakeWheel() {
 
        wheelIntakeTalon.set(ControlMode.Velocity, 0);
 
    }

    public boolean isSwitch1Set(){
 
        return switch1.get();
 
    }

    public boolean isSwitch2Set(){
 
        return switch2.get();
 
    }
  
    public void moveIntake(int speed) {
 
        wheelIntakeTalon.set(ControlMode.Velocity, speed);

        velocity.setDouble(speed);  
    }

    public void ejectBall() {
        logger.info("eject");
        double endTime = System.currentTimeMillis() + ejectBallDuration;

        while (System.currentTimeMillis() < (long)endTime) {
            logger.info("in the while");
            moveIntake(-wheelEjectPercent.getDouble(0.95));
        }
        moveIntake(0);
    }



    public void captureBall() {
        logger.info("capture");
        boolean stage1 = true;
        boolean stage2 = false;
        boolean stage3 = false;
        boolean finished = false;

        while (!finished) {
            if (stage1) {
                moveIntake(-wheelStage1Percent.getDouble(0.15));
                if (!switch1.get()) {
                    stage1 = false;
                    stage2 = true;
                    logger.info("Switch 1");
                }
            } else if (stage2) {
                moveIntake(-wheelStage2Percent.getDouble(0.1));
                if (!switch2.get()) {
                    stage2 = false;
                    stage3 = true;
                    logger.info("Switch 2");
                }
            } else if (stage3) {
                moveIntake(wheelStage3Percent.getDouble(0.1));
                if (!switch1.get()) {
                    stage3 = false;
                    finished = true;
                    logger.info("Switch 3");
                }
            }
        }
        moveIntake(0.0);
    }


    @Override
    protected void initDefaultCommand() {

    }

}