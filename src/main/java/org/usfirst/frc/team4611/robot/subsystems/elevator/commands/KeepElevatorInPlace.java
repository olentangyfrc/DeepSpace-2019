package org.usfirst.frc.team4611.robot.subsystems.elevator.commands;

import java.util.logging.Logger;

import org.usfirst.frc.team4611.robot.subsystems.SubsystemFactory;
import org.usfirst.frc.team4611.robot.subsystems.elevator.Elevator;

import edu.wpi.first.wpilibj.command.Command;

public class KeepElevatorInPlace extends Command {

    private Elevator elevator;
    private boolean stop = false;

    private final Logger logger = Logger.getLogger(Elevator.class.getName());

    public KeepElevatorInPlace(){
        elevator = SubsystemFactory.getInstance().getElevator();
        
        this.requires(elevator);
    }

    protected void initialize() {
        stop = false;
    }

    protected void execute() {
        if(!stop) {
            elevator.keepInPlace();
        }
    }

    @Override
    protected boolean isFinished() {
        return stop;
    }

    public void cancel() {
        stop = true;
    }

    @Override
    protected void interrupted() {
    }

   
}