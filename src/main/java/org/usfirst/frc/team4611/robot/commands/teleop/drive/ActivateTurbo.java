package org.usfirst.frc.team4611.robot.commands.teleop.drive;

import org.usfirst.frc.team4611.robot.subsystems.turbo.TurboTankDrive;
import org.usfirst.frc.team4611.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class ActivateTurbo extends Command {

    protected void initialize() {

    }

    protected void execute() {
        ((TurboTankDrive) Robot.driveTrain).activateTurbo();
    }

    protected boolean isFinished() {
        return true;
    }

}