package org.usfirst.frc.team4611.robot.subsystems.elevator.commands;

import java.util.logging.Logger;

import org.usfirst.frc.team4611.robot.subsystems.SubsystemFactory;
import org.usfirst.frc.team4611.robot.subsystems.elevator.Elevator;

import edu.wpi.first.wpilibj.command.Command;

class ElevatorLog extends Command {

    private static Logger logger = Logger.getLogger(ElevatorLog.class.getName());

    private Elevator elevator;
    
    public ElevatorLog() {
        elevator = SubsystemFactory.getInstance().getElevator();
    }

    protected void execute() {
        elevator.writeToShuffleboard();
        elevator.log();
    }

    protected boolean isFinished() {
        return true;
    }

    @Override
    public synchronized void cancel() {
        if(elevator.isLogging())
            logger.info("cancel");
    }

    @Override
    protected void interrupted() {
        if(elevator.isLogging())
            logger.info("interrupted");
    }

}