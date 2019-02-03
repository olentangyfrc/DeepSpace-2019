package org.usfirst.frc.team4611.robot.subsystems.WheelIntake.commands;

import org.usfirst.frc.team4611.robot.subsystems.SubsystemFactory;
import org.usfirst.frc.team4611.robot.subsystems.WheelIntake.WheelIntake;

import edu.wpi.first.wpilibj.command.Command;

public class EjectBall extends Command {

    private WheelIntake wheelIntake;

    public EjectBall() {
        wheelIntake = SubsystemFactory.getInstance().getWheelIntake();
        requires(wheelIntake);
    }

    @Override
    protected void initialize(){
    }

    @Override
    protected void execute() {
        wheelIntake.ejectBall();
    }

    @Override
    protected void end(){
    }

    @Override
    protected boolean isFinished() {
        return true;
    }
}