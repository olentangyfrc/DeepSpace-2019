package org.usfirst.frc.team4611.robot.subsystems.WheelIntake.commands;

import org.usfirst.frc.team4611.robot.networktables.NetTableManager;
import org.usfirst.frc.team4611.robot.subsystems.SubsystemFactory;
import org.usfirst.frc.team4611.robot.subsystems.WheelIntake.WheelIntake;

import edu.wpi.first.wpilibj.command.Command;

public class IntakeStage extends Command {

    private double speed;
    private String returnMethod;
    private WheelIntake wheelIntake;

    public IntakeStage(double spd, String rm) {
        speed = spd;
        returnMethod = rm;
        wheelIntake = SubsystemFactory.getInstance().getWheelIntake();
    }

    @Override
    protected void execute() {
        System.out.println(speed);
        wheelIntake.moveIntake(speed);
    }

    @Override
    protected boolean isFinished() {
        if (returnMethod.equals("Switch1")){
            return !wheelIntake.isSwitch1Set();
        } else if (returnMethod.equals("Switch2")){
            return !wheelIntake.isSwitch2Set();
        } else {
            return true;
        }
    }
    
}