package org.usfirst.frc.team4611.robot.commands.teleop.drive;

import org.usfirst.frc.team4611.robot.Robot;
import org.usfirst.frc.team4611.robot.subsystems.turbo.TurboTankDrive;

import edu.wpi.first.wpilibj.command.Command;

public class DeactivateTurbo extends Command {

    protected void initialize() {

    }

    protected void execute() {
        ((TurboTankDrive) Robot.driveTrain).deactivateTurbo();
    }

    protected boolean isFinished() {
        return true;
    }

}