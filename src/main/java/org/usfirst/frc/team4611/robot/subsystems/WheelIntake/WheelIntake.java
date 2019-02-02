package org.usfirst.frc.team4611.robot.subsystems.WheelIntake;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import org.usfirst.frc.team4611.robot.networktables.NetTableManager;
import org.usfirst.frc.team4611.robot.subsystems.PortMan;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;

public class WheelIntake extends Subsystem {

    public double ejectBallDuration = 0.3;

    private WPI_TalonSRX wheelIntakeTalon;
    private double pVal=0.5;

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

    private DigitalInput switch1;
    private DigitalInput switch2;

    private String spinSpeed = "Wheel Intake Spin Initialize";
    private String attackSpeed = "Wheel Intake Attack Initialize";
    private String softSpeed = "Wheel Intake SoftThrow Initialize";
    private String adjustmentSpeed = "Wheel Intake AdjustSpeed Initialize";
    

    public WheelIntake() {
        switch1 = new DigitalInput(0);
        switch2 = new DigitalInput(1);
    }
   
    

    public void init(PortMan pm) throws Exception {
       tab = Shuffleboard.getTab("Health Map");
       NetTableManager.updateValue("Health Map", spinSpeed, 0.0);
       NetTableManager.updateValue("Health Map", softSpeed, 0.0);
       NetTableManager.updateValue("Health Map", adjustmentSpeed, 0.0);


        wheelIntakeTalon = new WPI_TalonSRX(pm.acquirePort(PortMan.can_33_label, "wheelIntake.talon"));
        
        wheelIntakeTalon.config_kP(0,pVal, 0);
        wheelIntakeTalon.config_kI(0, 0.000, 0);
        wheelIntakeTalon.config_kD(0, 0, 0);

        tab = Shuffleboard.getTab("WheelIntake");
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


    @Override
    protected void initDefaultCommand() {

    }

}