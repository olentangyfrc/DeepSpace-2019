package org.usfirst.frc.team4611.robot.subsystems.elevator.commands;

import java.util.logging.Logger;

import org.usfirst.frc.team4611.robot.subsystems.SubsystemFactory;
import org.usfirst.frc.team4611.robot.subsystems.elevator.Elevator;

import edu.wpi.first.wpilibj.command.Command;

public class MoveElevator extends Command {

    private final Logger logger = Logger.getLogger(MoveElevator.class.getName());
    
    private Elevator elevator;
    private boolean stop = false;
    private boolean moveUp;

    public MoveElevator(boolean up) {
        elevator = SubsystemFactory.getInstance().getElevator();
        this.requires(elevator);
        moveUp = up;
    }

    protected void initialize() {
        stop = false;
    }

    protected void execute() {
        if (!stop) {
            elevator.move(moveUp);
        }
    }

    protected boolean isFinished() {
        return stop;
    }

    @Override
    public void cancel() {
        elevator.stop();
        stop = true;
    }

    @Override
    protected void interrupted() {
    }
}