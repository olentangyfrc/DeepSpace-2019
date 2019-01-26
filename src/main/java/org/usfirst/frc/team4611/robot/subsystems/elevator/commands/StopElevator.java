package org.usfirst.frc.team4611.robot.subsystems.elevator.commands;

import org.usfirst.frc.team4611.robot.subsystems.SubsystemFactory;
import org.usfirst.frc.team4611.robot.subsystems.elevator.Elevator;

import edu.wpi.first.wpilibj.command.Command;

public class StopElevator extends Command {

    private Elevator elevator;

    public StopElevator() {
        elevator = SubsystemFactory.getInstance().getElevator();

        this.requires(elevator);
    }

    protected void execute() {
        elevator.move(0);
    }

    protected boolean isFinished() {
        return true;
    }
}