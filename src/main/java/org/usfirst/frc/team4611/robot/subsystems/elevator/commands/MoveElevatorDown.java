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
        stop = false;
    }

    protected void execute() {
        if (!stop) {
            elevator.move(false);
        }
    }

    protected boolean isFinished() {
        return stop;
    }

    @Override
    public void cancel() {
        stop = true;
    }

    @Override
    protected void interrupted() {
    }
}