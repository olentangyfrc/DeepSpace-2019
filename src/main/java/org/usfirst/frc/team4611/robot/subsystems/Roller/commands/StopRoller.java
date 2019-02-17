package org.usfirst.frc.team4611.robot.subsystems.Roller.commands;

import edu.wpi.first.wpilibj.command.Command;

import java.util.logging.Logger;

import org.usfirst.frc.team4611.robot.subsystems.SubsystemFactory;
import org.usfirst.frc.team4611.robot.subsystems.Roller.Roller;

public class StopRoller extends Command {

    private static Logger logger = Logger.getLogger(StopRoller.class.getName());
    private Roller roller;

    public StopRoller() {
        roller = SubsystemFactory.getInstance().getRoller();
        this.requires(roller);
    }

    @Override
    protected void execute() {
        roller.stopRoller();
    }

    @Override
    public synchronized void cancel() {
        roller.stopRoller();
        logger.info("Cancelled");
    }

    @Override
    protected void interrupted() {
        logger.info("Interrupted");
    }

    @Override
    protected boolean isFinished() {
        return true;
    }

}