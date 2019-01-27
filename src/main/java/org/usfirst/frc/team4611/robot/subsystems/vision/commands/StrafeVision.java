package org.usfirst.frc.team4611.robot.subsystems.vision.commands;

import java.util.logging.Logger;

import org.usfirst.frc.team4611.robot.subsystems.SubsystemFactory;
import org.usfirst.frc.team4611.robot.subsystems.drivetrain.interfaces.DriveTrain;
import org.usfirst.frc.team4611.robot.subsystems.vision.Vision;

import edu.wpi.first.wpilibj.command.Command;

public class StrafeVision extends Command {

    private Logger logger = Logger.getLogger(StrafeVision.class.getName());

    private Vision vision;  
    private DriveTrain driveTrain;

    public StrafeVision() {

        logger.entering(StrafeVision.class.getName(), "StrafeVision()");

        vision = SubsystemFactory.getInstance().getVision();
        driveTrain = SubsystemFactory.getInstance().getDriveTrain();

        driveTrain.configTalonsSideways();

        logger.exiting(StrafeVision.class.getName(), "StrafeVision()");
    }

    @Override
    protected void execute() {
        
        logger.entering(StrafeVision.class.getName(), "execute()");

        driveTrain.moveSideways(vision.getDistance());

        logger.exiting(StrafeVision.class.getName(), "execute()");
    }

    @Override
    protected boolean isFinished() {
        
        logger.info("isFinished :" + vision.getDistance());

        return vision.getDistance() <= 200;
    }

}
