package org.usfirst.frc.team4611.robot.subsystems.WheelIntake.commands;

import org.usfirst.frc.team4611.robot.networktables.NetTableManager;
import org.usfirst.frc.team4611.robot.subsystems.SubsystemFactory;
import org.usfirst.frc.team4611.robot.subsystems.WheelIntake.WheelIntake;

import edu.wpi.first.wpilibj.command.Command;

public class IntakeStage2 extends Command {

    private WheelIntake wheelIntake;

    public IntakeStage2() {
        wheelIntake = SubsystemFactory.getInstance().getWheelIntake();
    }

    @Override
    protected void execute() {
        System.out.println("stage 2");
        wheelIntake.moveIntake(0.1);
    }

    @Override
    protected boolean isFinished() {
        return !wheelIntake.isSwitch2Set();
    }
    
}