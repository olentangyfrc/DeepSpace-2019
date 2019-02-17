package org.usfirst.frc.team4611.robot.subsystems.elevator.commands;

import java.util.logging.Logger;

import org.usfirst.frc.team4611.robot.subsystems.SubsystemFactory;
import org.usfirst.frc.team4611.robot.subsystems.elevator.Elevator;

import edu.wpi.first.wpilibj.command.Command;

public class StopElevator extends Command {

    private final Logger logger = Logger.getLogger(StopElevator.class.getName());

    private Elevator elevator;

    public StopElevator() {
        elevator = SubsystemFactory.getInstance().getElevator();

        this.requires(elevator);
    }

    protected void execute() {
        elevator.stop();
    }

    protected boolean isFinished() {
        return true;
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