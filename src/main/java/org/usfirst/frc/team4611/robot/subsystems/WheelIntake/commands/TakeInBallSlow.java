package org.usfirst.frc.team4611.robot.subsystems.WheelIntake.commands;

import org.usfirst.frc.team4611.robot.subsystems.SubsystemFactory;
import org.usfirst.frc.team4611.robot.subsystems.WheelIntake.WheelIntake;

import edu.wpi.first.wpilibj.command.Command;

public class TakeInBallSlow extends Command {

    private WheelIntake wheelIntake;

    public TakeInBallSlow() {
        SubsystemFactory.getInstance().getWheelIntake();
    }

    @Override
    protected void execute() {
        wheelIntake.moveIntake(240);
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
    
}