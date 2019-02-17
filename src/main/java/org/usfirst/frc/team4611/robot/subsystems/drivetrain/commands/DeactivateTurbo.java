package org.usfirst.frc.team4611.robot.subsystems.drivetrain.commands;

import java.util.logging.Logger;

import org.usfirst.frc.team4611.robot.subsystems.SubsystemFactory;
import org.usfirst.frc.team4611.robot.subsystems.drivetrain.interfaces.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

public class DeactivateTurbo extends Command {

    private final Logger logger = Logger.getLogger(DeactivateTurbo.class.getName());

    private DriveTrain driveTrain;
    
    public DeactivateTurbo(){
        driveTrain = SubsystemFactory.getInstance().getDriveTrain();
    }
    protected void initialize() {

    }

    protected void execute() {
        driveTrain.deactivateTurbo();
    }  

    protected boolean isFinished() {
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