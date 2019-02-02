package org.usfirst.frc.team4611.robot.subsystems.WheelIntake.commands;

import org.usfirst.frc.team4611.robot.networktables.NetTableManager;

import org.usfirst.frc.team4611.robot.subsystems.SubsystemFactory;
import org.usfirst.frc.team4611.robot.subsystems.WheelIntake.WheelIntake;

import edu.wpi.first.wpilibj.command.Command;

public class StopWheelIntake extends Command {

    private WheelIntake wheelIntake;
    private String attackSpeed = "Wheel Intake Attack Initialize";


    public StopWheelIntake() {
        wheelIntake = SubsystemFactory.getInstance().getWheelIntake();
    }


    protected void initialize() {
    }

    @Override
    protected void execute() {
        wheelIntake.moveIntake(0);
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
    
}