package org.usfirst.frc.team4611.robot.subsystems.elevator.commands;

import java.util.logging.Logger;

import org.usfirst.frc.team4611.robot.subsystems.SubsystemFactory;
import org.usfirst.frc.team4611.robot.subsystems.elevator.Elevator;

import edu.wpi.first.wpilibj.command.Command;

public class MoveElevator extends Command {

    private final Logger logger = Logger.getLogger(MoveElevator.class.getName());
    
    private Elevator elevator;
    private boolean stop = false;

    public MoveElevator() {
        elevator = SubsystemFactory.getInstance().getElevator();

        this.requires(elevator);
    }

    protected void initialize() {
        //logger.info("Init");
        stop = false;
    }

    protected void execute() {
        //logger.info("Exe");
        if (stop) {
            return;
        }
        elevator.move();
    }

    protected boolean isFinished() {
        //logger.info("is finsished");
        return stop;
    }

    @Override
    public void cancel() {
        logger.info("cancel");
        stop = true;
        elevator.stop();
    }

    @Override
    protected void interrupted() {
        logger.info("interrupted");
        //stop = elevator.move(0);
    }
}