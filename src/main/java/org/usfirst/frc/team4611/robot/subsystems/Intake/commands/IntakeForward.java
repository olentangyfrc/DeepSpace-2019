package org.usfirst.frc.team4611.robot.subsystems.Intake.commands;

import java.util.logging.Logger;

import org.usfirst.frc.team4611.robot.subsystems.SubsystemFactory;
import org.usfirst.frc.team4611.robot.subsystems.Intake.Intake;
import org.usfirst.frc.team4611.robot.subsystems.Roller.Roller;

import edu.wpi.first.wpilibj.command.Command;

public class IntakeForward extends Command {

    private Logger logger = Logger.getLogger(IntakeForward.class.getName());

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
        if(intake.isLogging())
            logger.info("canceled");
        stop = true;
        intake.stopIndiWheelFront();
    }
    @Override
    protected void interrupted() {
        if(intake.isLogging())
            logger.info("interrupted");
    }
}