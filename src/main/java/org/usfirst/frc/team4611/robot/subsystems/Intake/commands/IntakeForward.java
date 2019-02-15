package org.usfirst.frc.team4611.robot.subsystems.Intake.commands;

import org.usfirst.frc.team4611.robot.subsystems.SubsystemFactory;
import org.usfirst.frc.team4611.robot.subsystems.Intake.Intake;
import org.usfirst.frc.team4611.robot.subsystems.Roller.Roller;

import edu.wpi.first.wpilibj.command.Command;

public class IntakeForward extends Command {

    private Intake intake;
    private int intakeVelocity = 480;
    private boolean stop = false;

    public IntakeForward() {
        intake = SubsystemFactory.getInstance().getShooterIntake();
        this.requires(intake);
    }

    @Override
    protected void initialize() {
        stop = false;
    }

    @Override
    protected void execute() {
        if(stop) {
            return;
        }
        intake.spinIndiWheelFrontForward();
    }

    @Override
    protected boolean isFinished() {
        return stop;
    }

    @Override
    public synchronized void cancel() {
        stop = true;
        intake.stopIndiWheelFront();
    }

}