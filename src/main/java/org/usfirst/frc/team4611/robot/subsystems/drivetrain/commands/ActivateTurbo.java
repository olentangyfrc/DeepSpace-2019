package org.usfirst.frc.team4611.robot.subsystems.drivetrain.commands;

import java.util.logging.Logger;

import org.usfirst.frc.team4611.robot.subsystems.SubsystemFactory;
import org.usfirst.frc.team4611.robot.subsystems.drivetrain.interfaces.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

public class ActivateTurbo extends Command {

    private final Logger logger = Logger.getLogger(ActivateTurbo.class.getName());

    private DriveTrain driveTrain;

    public ActivateTurbo(){
    }

    protected void initialize() {
        driveTrain = SubsystemFactory.getInstance().getDriveTrain();
    }

    protected void execute() {
        driveTrain.activateTurbo();
    }

    protected boolean isFinished() {
        driveTrain.deactivateTurbo();
        return true;
    }

    @Override
    public synchronized void cancel() {
        logger.info("cancel");
    }

    @Override
    protected void interrupted() {
        logger.info("interrupted");
    }

}