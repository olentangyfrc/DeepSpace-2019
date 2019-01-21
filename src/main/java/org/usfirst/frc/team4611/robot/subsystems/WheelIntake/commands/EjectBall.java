package org.usfirst.frc.team4611.robot.subsystems.wheelintake.commands;

import org.usfirst.frc.team4611.robot.subsystems.SubsystemFactory;
import org.usfirst.frc.team4611.robot.subsystems.wheelintake.WheelIntake;

import edu.wpi.first.wpilibj.command.Command;

public class EjectBall extends Command {

    private WheelIntake wheelIntake;

    public EjectBall() {
        SubsystemFactory.getInstance().getWheelIntake();
    }

    @Override
    protected void execute() {
        wheelIntake.moveIntake(-690);
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
    
}