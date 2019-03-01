package org.usfirst.frc.team4611.robot.subsystems.IntakeAdjuster.commands;

import java.util.logging.Logger;

import org.usfirst.frc.team4611.robot.subsystems.SubsystemFactory;
import org.usfirst.frc.team4611.robot.subsystems.IntakeAdjuster.IntakeAdjuster;

import edu.wpi.first.wpilibj.command.Command;

public class MoveAdjusterToPos extends Command {

    private Logger  logger = Logger.getLogger(MoveAdjusterToPos.class.getName());

    private IntakeAdjuster intakeAdjuster;
    private boolean stop = false;
    private IntakeAdjuster.HappyPositions pos;


    //MUST SEND A NUMBER 1 OR 2
    //We use this to decide what level to move the intake adjuster to
    public MoveAdjusterToPos(IntakeAdjuster.HappyPositions p){
        intakeAdjuster = SubsystemFactory.getInstance().getIntakeAdjuster();
        pos = p;
        this.requires(intakeAdjuster);
    }

    protected void execute() {
        stop=intakeAdjuster.moveToPos(pos);
    }

    @Override
    protected boolean isFinished() {
        return stop;
    }

    @Override
    public synchronized void cancel() {
        if(intakeAdjuster.isLogging())
            logger.info("canceled");
    }

    @Override
    protected void interrupted() {
        if(intakeAdjuster.isLogging())
            logger.info("interrupted");
    }
    
    protected void initialize() {
        
    }

   
}