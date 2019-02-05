package org.usfirst.frc.team4611.robot.subsystems.WheelIntake;

import java.util.logging.Logger;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import org.usfirst.frc.team4611.robot.networktables.NetTableManager;
import org.usfirst.frc.team4611.robot.subsystems.PortMan;
import org.usfirst.frc.team4611.robot.subsystems.SubsystemFactory;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;

public class WheelIntake extends Subsystem {

    private WPI_TalonSRX wheelIntakeTalon;

    Logger logger = Logger.getLogger(SubsystemFactory.class.getName());

    private DigitalInput switch1;
    private DigitalInput switch2;

    private double pVal=0.5;

    public double ejectBallDuration = 1500; // spin for 1.5 seconds
    private double spin;
    private double attack;
    private double softThrow;
    private double adjustSpeed;

    private ShuffleboardTab tab;
    private NetworkTableEntry wheelIntakeSpin;
    private NetworkTableEntry wheelIntakeAttack;
    private NetworkTableEntry wheelIntakeSoftThrow;
    private NetworkTableEntry wheelIntakeAdjustSpeed;
    private NetworkTableEntry velocity;

    private String spinSpeed = "Wheel Intake Spin Initialize";
    private String attackSpeed = "Wheel Intake Attack Initialize";
    private String softSpeed = "Wheel Intake SoftThrow Initialize";
    private String adjustmentSpeed = "Wheel Intake AdjustSpeed Initialize";
    

    public WheelIntake() {
    }
   

    public void init(PortMan pm) throws Exception {

        logger.info("init");
        switch1 = new DigitalInput(pm.acquirePort(PortMan.digital0_label, "WheelIntake.switch1"));
        switch2 = new DigitalInput(pm.acquirePort(PortMan.digital1_label, "WheelIntake.switch2"));

        wheelIntakeTalon = new WPI_TalonSRX(pm.acquirePort(PortMan.can_33_label, "wheelIntake.talon"));
        
        wheelIntakeTalon.config_kP(0,pVal, 0);
        wheelIntakeTalon.config_kI(0, 0.000, 0);
        wheelIntakeTalon.config_kD(0, 0, 0);

        tab = Shuffleboard.getTab("WheelIntake");
        NetTableManager.updateValue("Health Map", spinSpeed, 0.0);
        NetTableManager.updateValue("Health Map", softSpeed, 0.0);
        NetTableManager.updateValue("Health Map", adjustmentSpeed, 0.0);
        velocity = tab.add("Velocity", -1.0).getEntry();
        NetTableManager.updateValue("Wheel Intake", "Wheel Intake Initialization", true);
    }

    public void moveIntake(double speed) {
        
        wheelIntakeTalon.set(ControlMode.PercentOutput, speed);
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
            moveIntake(-.95);
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
                moveIntake(-0.15);
                if (!switch1.get()) {
                    stage1 = false;
                    stage2 = true;
                    logger.info("Switch 1");
                }
            } else if (stage2) {
                moveIntake(-0.1);
                if (!switch2.get()) {
                    stage2 = false;
                    stage3 = true;
                    logger.info("Switch 2");
                }
            } else if (stage3) {
                moveIntake(0.1);
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