package org.usfirst.frc.team4611.robot.subsystems.doublewheel.commands;

import org.usfirst.frc.team4611.robot.subsystems.SubsystemFactory;
import org.usfirst.frc.team4611.robot.subsystems.doublewheel.DoubleWheel;

import edu.wpi.first.wpilibj.command.Command;

public class IntakeBall extends Command {

    private DoubleWheel doubleWheel;
    private int intakeVelocity = 480;

    public IntakeBall() {
        doubleWheel = SubsystemFactory.getInstance().getDoubleWheel();
        this.requires(doubleWheel);
    }

    @Override
    protected void execute() {
        doubleWheel.spinMotorsIntake(intakeVelocity);
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

}