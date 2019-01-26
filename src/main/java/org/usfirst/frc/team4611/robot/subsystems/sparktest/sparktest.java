package org.usfirst.frc.team4611.robot.subsystems.sparktest;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.command.Subsystem;

public class sparktest extends Subsystem {

    private CANSparkMax spark;


    public sparktest() {

    }

    public void init() {
        spark = new CANSparkMax(0, MotorType.kBrushless);
    }

    public void move() {
        spark.set(1);
    }

    @Override
    protected void initDefaultCommand() {

    }

}