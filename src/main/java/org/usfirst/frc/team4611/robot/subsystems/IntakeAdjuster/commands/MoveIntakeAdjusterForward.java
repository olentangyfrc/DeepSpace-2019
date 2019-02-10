package org.usfirst.frc.team4611.robot.subsystems.IntakeAdjuster.commands;

import edu.wpi.first.wpilibj.command.Command;

import java.util.logging.Logger;

import org.usfirst.frc.team4611.robot.subsystems.SubsystemFactory;
import org.usfirst.frc.team4611.robot.subsystems.IntakeAdjuster.IntakeAdjuster;

public class MoveIntakeAdjusterForward extends Command {


    private static Logger logger = Logger.getLogger(MoveIntakeAdjusterForward.class.getName());
    private IntakeAdjuster intakeAdjuster; 
    private boolean stop = false;

    public MoveIntakeAdjusterForward() {
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
        intakeAdjuster.spinIntakeAdjusterForward();
    }

    @Override
    public synchronized void cancel() {
        intakeAdjuster.stopIntakeAdjuster();
        stop = true;
    }

    @Override
    protected boolean isFinished() {
        return stop;
    }

}