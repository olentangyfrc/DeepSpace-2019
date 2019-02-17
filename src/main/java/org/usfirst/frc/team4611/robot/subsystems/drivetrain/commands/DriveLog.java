package org.usfirst.frc.team4611.robot.subsystems.drivetrain.commands;

import java.util.logging.Logger;

import edu.wpi.first.wpilibj.command.Command;

class DriveLog extends Command {

    private final Logger logger = Logger.getLogger(DriveLog.class.getName());

    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    public synchronized void cancel() {
        logger.info("cancel");
    }

    @Override
    protected void interrupted() {
        logger.info("interrupted");
    }

}