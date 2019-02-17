package org.usfirst.frc.team4611.robot.subsystems.navigation.commands;

import java.util.logging.Logger;

import org.usfirst.frc.team4611.robot.subsystems.SubsystemFactory;
import org.usfirst.frc.team4611.robot.subsystems.navigation.sensors.LineTracker;

import edu.wpi.first.wpilibj.command.Command;

public class PrintLineTracker extends Command {

    private Logger logger = Logger.getLogger(PrintLineTracker.class.getName());

    private LineTracker lineTracker;

    public PrintLineTracker() {
        lineTracker = SubsystemFactory.getInstance().getLineTracker();
    }

    protected void execute(){
        lineTracker.checkLines();
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    public synchronized void cancel() {
        logger.info("canceled");
    }

    @Override
    protected void interrupted() {
        logger.info("interrupted");
    }
}