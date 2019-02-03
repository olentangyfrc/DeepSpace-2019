package org.usfirst.frc.team4611.robot.subsystems.WheelIntake.commands;

import org.usfirst.frc.team4611.robot.subsystems.SubsystemFactory;
import org.usfirst.frc.team4611.robot.subsystems.WheelIntake.WheelIntake;

import edu.wpi.first.wpilibj.command.Command;

public class TakeInBall extends Command {


    private WheelIntake wheelIntake;

    public TakeInBall() {
        wheelIntake = SubsystemFactory.getInstance().getWheelIntake();
        requires(wheelIntake);
    }

    @Override
    protected void execute() {
        wheelIntake.captureBall();
    }

    @Override
    protected boolean isFinished() {
        return true;
    }
    
}