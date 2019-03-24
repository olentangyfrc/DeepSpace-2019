package org.usfirst.frc.team4611.robot.subsystems.elevator.commands;

import java.util.logging.Logger;

import org.usfirst.frc.team4611.robot.subsystems.SubsystemFactory;
import org.usfirst.frc.team4611.robot.subsystems.elevator.Elevator;

import edu.wpi.first.wpilibj.command.Command;

public class MoveHatch extends Command {

    private final Logger logger = Logger.getLogger(MoveElevator.class.getName());
    
    private Elevator elevator;
    private boolean stop = false;
    private double pos;

    public MoveHatch(double p) {
        elevator = SubsystemFactory.getInstance().getElevator();
        this.requires(elevator);
        pos = p;
    }

    protected void initialize() {
        stop = false;
    }

    protected void execute() {
        if (!stop) {
            stop = elevator.move(pos);
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