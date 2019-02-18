package org.usfirst.frc.team4611.robot.subsystems.doublewheel.commands;

import java.util.logging.Logger;

import org.usfirst.frc.team4611.robot.subsystems.SubsystemFactory;
import org.usfirst.frc.team4611.robot.subsystems.doublewheel.DoubleWheel;

import edu.wpi.first.wpilibj.command.Command;

public class IntakeBall extends Command {

    private static Logger logger = Logger.getLogger(IntakeBall.class.getName());
    private DoubleWheel doubleWheel;
    private int intakeVelocity = 480;
    private boolean stop = false;

    public IntakeBall() {
        doubleWheel = SubsystemFactory.getInstance().getDoubleWheel();
        this.requires(doubleWheel);
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
        doubleWheel.spinMotorsIntake(intakeVelocity);
    }

    @Override
    protected boolean isFinished() {
        return stop;
    }

    @Override
    public synchronized void cancel() {
        stop = true;
        doubleWheel.stopMotors();
        if(doubleWheel.isLogging())
            logger.info("Canceled");
    }
    @Override
    protected void interrupted() {
        if(doubleWheel.isLogging())
            logger.info("Interrupted");
    }
}