package org.usfirst.frc.team4611.robot.subsystems.elevator;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import org.usfirst.frc.team4611.robot.subsystems.PortMan;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Elevator extends Subsystem {

    private WPI_TalonSRX elevatorTalon;
    private DigitalInput limitSwitch = new DigitalInput(0); 

    public Elevator(){

    }

    public void init(PortMan pm) {
        elevatorTalon = new WPI_TalonSRX(22);
    }

    public void move(double speed) {
        elevatorTalon.set(ControlMode.PercentOutput, speed);
    }

    public void moveToPos(double position) {
        elevatorTalon.config_kP(0, .5, 0);
        elevatorTalon.config_kI(0, 0, 0);
        elevatorTalon.config_kD(0, 0, 0);
        elevatorTalon.config_kF(0, 0, 0);

        elevatorTalon.configMotionCruiseVelocity(4096, 0);
        elevatorTalon.configMotionAcceleration(4096,0);
        elevatorTalon.set(ControlMode.MotionMagic, position);

    }

    @Override
    protected void initDefaultCommand() {

    }

    public boolean isSwitchSet() {
        return limitSwitch.get();
    }
}