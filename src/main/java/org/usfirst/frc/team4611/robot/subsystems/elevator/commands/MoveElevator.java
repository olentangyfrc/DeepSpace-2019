package org.usfirst.frc.team4611.robot.subsystems.elevator.commands;

import org.usfirst.frc.team4611.robot.subsystems.SubsystemFactory;
import org.usfirst.frc.team4611.robot.subsystems.elevator.Elevator;

import edu.wpi.first.wpilibj.command.Command;

public class MoveElevator extends Command {

    private Elevator elevator;
    private double speed;

    public MoveElevator(double s) {
        elevator = SubsystemFactory.getInstance().getElevator();
        speed = s;

        this.requires(elevator);
    }

    protected void execute() {
        elevator.move(speed);
    }

    protected boolean isFinished() {
        return true;
    }
}