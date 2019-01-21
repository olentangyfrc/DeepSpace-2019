package org.usfirst.frc.team4611.robot.subsystems.doublewheel;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import org.usfirst.frc.team4611.robot.subsystems.PortMan;

import edu.wpi.first.wpilibj.command.Subsystem;

public class DoubleWheel extends Subsystem {

    private WPI_TalonSRX wheelIntakeLeft;
    private WPI_TalonSRX wheelIntakeRight;

    private int wheelVelocity = 480;

    public void init(PortMan pm) throws Exception {
        wheelIntakeLeft = new WPI_TalonSRX(pm.acquirePort(PortMan.can_18_label, "DoubleWheel.leftWheelIntake"));
        wheelIntakeRight = new WPI_TalonSRX(pm.acquirePort(PortMan.can_19_label, "DoubleWheel.rightWheelIntake"));

        wheelIntakeLeft.config_kP(0, .5, 0);
        wheelIntakeLeft.config_kI(0, 0, 0);
        wheelIntakeLeft.config_kD(0, 0, 0);
        wheelIntakeLeft.config_kF(0, 0, 0);
        wheelIntakeLeft.configMotionCruiseVelocity(4096, 0);
        wheelIntakeLeft.configMotionAcceleration(4096,0);
    
        wheelIntakeRight.follow(wheelIntakeLeft);
        wheelIntakeRight.setInverted(true);
    }

    public void spinMotorsIntake(){
        wheelIntakeLeft.set(ControlMode.Velocity, wheelVelocity);
    }

    public void spinMotorOutTake() {
        wheelIntakeLeft.set(ControlMode.Velocity, -wheelVelocity);
    }

    @Override
    protected void initDefaultCommand() {

    }
}
