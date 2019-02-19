package org.usfirst.frc.team4611.robot.subsystems.IntakeAdjuster.commands;

import edu.wpi.first.wpilibj.command.Command;

import java.util.logging.Logger;

import org.usfirst.frc.team4611.robot.subsystems.SubsystemFactory;
import org.usfirst.frc.team4611.robot.subsystems.IntakeAdjuster.IntakeAdjuster;

public class MoveIntakeAdjusterBackward extends Command {

    private static Logger logger = Logger.getLogger(MoveIntakeAdjusterBackward.class.getName());
    private IntakeAdjuster intakeAdjuster; 
    private boolean stop = false;

    public MoveIntakeAdjusterBackward() {
        intakeAdjuster = SubsystemFactory.getInstance().getIntakeAdjuster();
        this.requires(intakeAdjuster);
    }

    @Override
    protected void initialize() {
        stop = false;
    }

    @Override
    protected void execute() {
        if(stop)
            return;
        intakeAdjuster.spinIntakeAdjusterBackward();
    }

    @Override
    public synchronized void cancel() {
        if(intakeAdjuster.isLogging())
            logger.info("canceled");
        stop = true;
        intakeAdjuster.stopIntakeAdjuster();

    }

    @Override
    protected boolean isFinished() {
        return stop;
    }
    @Override
    protected void interrupted() {
        if(intakeAdjuster.isLogging())
            logger.info("interrupted");
    }
}