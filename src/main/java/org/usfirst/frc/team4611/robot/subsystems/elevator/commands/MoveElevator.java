package org.usfirst.frc.team4611.robot.subsystems.elevator.commands;

import java.util.logging.Logger;

import org.usfirst.frc.team4611.robot.subsystems.SubsystemFactory;
import org.usfirst.frc.team4611.robot.subsystems.elevator.Elevator;

import edu.wpi.first.wpilibj.command.Command;

public class MoveElevator extends Command {

    private final Logger logger = Logger.getLogger(MoveElevator.class.getName());
    
    private Elevator elevator;
    private double speed;
    private double pVal;
    private double defaultSpeed;
    private boolean stop = false;

    public MoveElevator(double s) {
        elevator = SubsystemFactory.getInstance().getElevator();
        speed = s;
        defaultSpeed = speed;

        this.requires(elevator);
    }

    protected void initialize() {
        //logger.info("Init");
    }

    protected void execute() {
        //logger.info("Exe");
        if (stop) {
            return;
        }
        stop = elevator.move(speed);
    }

    protected boolean isFinished() {
        //logger.info("is finsished");
        speed = defaultSpeed;
        stop = false;
        return !stop;
    }

    @Override
    public void cancel() {
        logger.info("cancel");
        speed = 0.0;
        stop = elevator.move(speed);
        
    }

    @Override
    protected void interrupted() {
        logger.info("interrupted");
        //stop = elevator.move(0);
    }
}