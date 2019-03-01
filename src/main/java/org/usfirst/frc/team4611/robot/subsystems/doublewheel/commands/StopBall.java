package org.usfirst.frc.team4611.robot.subsystems.doublewheel.commands;

import java.util.logging.Logger;

import org.usfirst.frc.team4611.robot.subsystems.SubsystemFactory;
import org.usfirst.frc.team4611.robot.subsystems.doublewheel.DoubleWheel;

import edu.wpi.first.wpilibj.command.Command;

public class StopBall extends Command {

    private static Logger logger = Logger.getLogger(StopBall.class.getName());
    private DoubleWheel doubleWheel;

    public StopBall() {
        doubleWheel = SubsystemFactory.getInstance().getDoubleWheel();
        this.requires(doubleWheel);
    }

    @Override
    protected void execute() {
        doubleWheel.spinMotorsIntake(0);
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