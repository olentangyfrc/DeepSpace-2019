package org.usfirst.frc.team4611.robot.subsystems.drivetrain.commands;

import org.usfirst.frc.team4611.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class DeactivateTurbo extends Command {

    protected void initialize() {

    }

    protected void execute() {
        Robot.driveTrain.deactivateTurbo();
    }

    protected boolean isFinished() {
        return true;
    }

}