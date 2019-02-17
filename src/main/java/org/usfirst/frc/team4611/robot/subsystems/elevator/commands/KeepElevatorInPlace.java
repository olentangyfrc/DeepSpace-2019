package org.usfirst.frc.team4611.robot.subsystems.elevator.commands;

import java.util.logging.Logger;

import org.usfirst.frc.team4611.robot.subsystems.SubsystemFactory;
import org.usfirst.frc.team4611.robot.subsystems.elevator.Elevator;

import edu.wpi.first.wpilibj.command.Command;

public class KeepElevatorInPlace extends Command {

    private Elevator elevator;
    private boolean stop = false;

    private final Logger logger = Logger.getLogger(Elevator.class.getName());

    //MUST SEND A NUMBER FROM 0 TO 1
    public KeepElevatorInPlace(){
        elevator = SubsystemFactory.getInstance().getElevator();
        
        this.requires(elevator);
    }

    protected void execute() {
        //logger.info("Execute");
        if(stop) {
            return;
        }
        elevator.keepInPlace();
    }

    @Override
    protected boolean isFinished() {
        return stop;
    }

    protected void initialize() {
        //logger.info("init");
        stop = false;
        elevator.resetEncoders();
    }
    public void cancel() {
        logger.info("cancel");
        stop = true;
        elevator.stopElevator();
    }

    @Override
    protected void interrupted() {
        logger.info("interrupted");
    }

   
}