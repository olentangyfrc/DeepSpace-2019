package org.usfirst.frc.team4611.robot.subsystems.elevator.commands;

import org.usfirst.frc.team4611.robot.subsystems.SubsystemFactory;
import org.usfirst.frc.team4611.robot.subsystems.elevator.Elevator;

import edu.wpi.first.wpilibj.command.Command;

class ElevatorLog extends Command {

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



}