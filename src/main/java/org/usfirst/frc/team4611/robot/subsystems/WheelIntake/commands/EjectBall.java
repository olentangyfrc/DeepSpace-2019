package org.usfirst.frc.team4611.robot.subsystems.WheelIntake.commands;

import org.usfirst.frc.team4611.robot.networktables.NetTableManager;
import org.usfirst.frc.team4611.robot.subsystems.SubsystemFactory;
import org.usfirst.frc.team4611.robot.subsystems.WheelIntake.WheelIntake;

import edu.wpi.first.wpilibj.command.Command;

public class EjectBall extends Command {

    private final double duration = .3;
    private double currentDuration;
    private WheelIntake wheelIntake;
    private String attackSpeed = "Wheel Intake Attack Initialize";

    public EjectBall() {
        currentDuration = 0;
        wheelIntake = SubsystemFactory.getInstance().getWheelIntake();
    }

    @Override
    protected void initialize(){
        currentDuration = 0;
    }

    @Override
    protected void execute() {
        currentDuration += 0.020;
        wheelIntake.moveIntake((double)(NetTableManager.getValue("Health Map", attackSpeed, 1.0)));
    }

    @Override
    protected void end(){
        wheelIntake.moveIntake(0);
    }

    @Override
    protected boolean isFinished() {
        System.out.println(currentDuration);
        if (currentDuration >= duration){
            return true;
        } else {
            return false;
        }
    }
    
}