package org.usfirst.frc.team4611.robot.subsystems.Roller.commands;

import edu.wpi.first.wpilibj.command.Command;

import java.util.logging.Logger;

import org.usfirst.frc.team4611.robot.subsystems.SubsystemFactory;
import org.usfirst.frc.team4611.robot.subsystems.Roller.Roller;
public class MoveRollerSlowForward extends Command {

    private static Logger logger = Logger.getLogger(MoveRollerSlowForward.class.getName());
    private Roller roller; 
    private boolean stop = false;

    public MoveRollerSlowForward() {
        roller = SubsystemFactory.getInstance().getRoller();
        this.requires(roller);
    }

    @Override
    protected void initialize() {
        stop = false;
    }

    @Override
    protected void execute() {
        if(stop) 
            return;
        roller.spinRollerSlowForward();
    }

    @Override
    public synchronized void cancel() {
        roller.stopRoller();
        stop = true;
        if(roller.isLogging())
            logger.info("Cancelled");
    }

    @Override
    protected void interrupted() {
        if(roller.isLogging())
            logger.info("Interrupted");
    }

    @Override
    protected boolean isFinished() {
        return stop;
    }

}