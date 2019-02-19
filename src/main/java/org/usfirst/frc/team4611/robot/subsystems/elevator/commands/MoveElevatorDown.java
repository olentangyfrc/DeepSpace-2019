package org.usfirst.frc.team4611.robot.subsystems.elevator.commands;

import java.util.logging.Logger;

import org.usfirst.frc.team4611.robot.subsystems.SubsystemFactory;
import org.usfirst.frc.team4611.robot.subsystems.elevator.Elevator;

import edu.wpi.first.wpilibj.command.Command;

public class MoveElevatorDown extends Command {

    private final Logger logger = Logger.getLogger(MoveElevatorDown.class.getName());
    
    private Elevator elevator;
    private boolean stop = false;

    public MoveElevatorDown() {
        elevator = SubsystemFactory.getInstance().getElevator();

        this.requires(elevator);
    }

    protected void initialize() {
        //if(elevator.isLogging())logger.info("Init");
        stop = false;
    }

    protected void execute() {
        //if(elevator.isLogging())logger.info("Exe");
        if (stop) {
            return;
        }
        elevator.move(false);
    }

    protected boolean isFinished() {
        //if(elevator.isLogging())logger.info("is finsished");
        return stop;
    }

    @Override
    public void cancel() {
        if(elevator.isLogging())
            logger.info("cancel");
        stop = true;
        elevator.stopElevator();
    }

    @Override
    protected void interrupted() {
        if(elevator.isLogging())
            logger.info("interrupted");
        //stop = elevator.move(0);
    }
}