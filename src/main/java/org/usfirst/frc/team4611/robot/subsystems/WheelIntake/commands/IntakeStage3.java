package org.usfirst.frc.team4611.robot.subsystems.WheelIntake.commands;

import org.usfirst.frc.team4611.robot.networktables.NetTableManager;
import org.usfirst.frc.team4611.robot.subsystems.SubsystemFactory;
import org.usfirst.frc.team4611.robot.subsystems.WheelIntake.WheelIntake;

import edu.wpi.first.wpilibj.command.Command;

public class IntakeStage3 extends Command {

    private WheelIntake wheelIntake;

    public IntakeStage3() {
        wheelIntake = SubsystemFactory.getInstance().getWheelIntake();
    }

    @Override
    protected void execute() {
        System.out.println("stage 3");
        wheelIntake.moveIntake(-.05);
    }

    @Override
    protected boolean isFinished() {
        return !wheelIntake.isSwitch1Set();
    }
    
}