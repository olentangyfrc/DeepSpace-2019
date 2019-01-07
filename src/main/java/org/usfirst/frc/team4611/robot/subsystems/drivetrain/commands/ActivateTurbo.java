package org.usfirst.frc.team4611.robot.subsystems.drivetrain.commands;

import org.usfirst.frc.team4611.robot.subsystems.SubsystemFactory;
import org.usfirst.frc.team4611.robot.subsystems.drivetrain.interfaces.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

public class ActivateTurbo extends Command {

    private DriveTrain driveTrain;

    public ActivateTurbo(){
        driveTrain = SubsystemFactory.getInstance().getDriveTrain();
    }
    protected void initialize() {

    }

    protected void execute() {
        driveTrain.activateTurbo();
    }

    protected boolean isFinished() {
        return true;
    }

}