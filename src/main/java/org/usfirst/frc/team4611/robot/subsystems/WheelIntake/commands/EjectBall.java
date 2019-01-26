package org.usfirst.frc.team4611.robot.subsystems.WheelIntake.commands;

import org.usfirst.frc.team4611.robot.networktables.NetTableManager;
import org.usfirst.frc.team4611.robot.subsystems.SubsystemFactory;
import org.usfirst.frc.team4611.robot.subsystems.WheelIntake.WheelIntake;

import edu.wpi.first.wpilibj.command.Command;

public class EjectBall extends Command {

    private WheelIntake wheelIntake;
    private String attackSpeed = "Wheel Intake Attack Initialize";

    public EjectBall() {
        wheelIntake = SubsystemFactory.getInstance().getWheelIntake();
    }

    @Override
    protected void execute() {
        wheelIntake.moveIntake((double)(NetTableManager.getValue("Health Map", attackSpeed, 1.0)));
    }

    @Override
    protected boolean isFinished() {
        return true;
    }
    
}