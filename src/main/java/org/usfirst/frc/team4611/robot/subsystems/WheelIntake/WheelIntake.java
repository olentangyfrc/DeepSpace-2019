package org.usfirst.frc.team4611.robot.subsystems.WheelIntake;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import org.usfirst.frc.team4611.robot.subsystems.PortMan;

import edu.wpi.first.wpilibj.command.Subsystem;

public class WheelIntake extends Subsystem {

    private WPI_TalonSRX wheelIntakeTalon;
    private double pVal=0.5;


    public WheelIntake() {

    }

    public void init(PortMan pm) throws Exception {

        wheelIntakeTalon = new WPI_TalonSRX(pm.acquirePort(PortMan.can_14_label, "wheelIntake.talon"));
      
        wheelIntakeTalon.config_kP(0,pVal, 0);
        wheelIntakeTalon.config_kI(0, 0.000, 0);
        wheelIntakeTalon.config_kD(0, 0, 0);

    }

    public void moveIntake() {
        wheelIntakeTalon.set(ControlMode.Velocity, 480);
    }


    @Override
    protected void initDefaultCommand() {

    }

}