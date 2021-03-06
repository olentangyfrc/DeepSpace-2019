package org.usfirst.frc.team4611.robot.subsystems.doublewheel.commands;

import java.util.logging.Logger;

import org.usfirst.frc.team4611.robot.subsystems.SubsystemFactory;
import org.usfirst.frc.team4611.robot.subsystems.doublewheel.DoubleWheel;

import edu.wpi.first.wpilibj.command.Command;

public class IntakeBallSlow extends Command {

    private static Logger logger = Logger.getLogger(IntakeBallSlow.class.getName());
    private DoubleWheel doubleWheel;
    private int intakeVelocity = 240;

    public IntakeBallSlow() {
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
    @Override
    public synchronized void cancel() {
        if(doubleWheel.isLogging())
            logger.info("Canceled");
    }
    @Override
    protected void interrupted() {
        if(doubleWheel.isLogging())
            logger.info("Interrupted");
    }
}