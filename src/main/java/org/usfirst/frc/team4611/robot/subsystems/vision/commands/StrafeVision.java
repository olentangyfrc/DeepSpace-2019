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

    private double maxRPM = 1500;

    public StrafeVision() {

        logger.entering(StrafeVision.class.getName(), "StrafeVision()");

        vision = SubsystemFactory.getInstance().getVision();
        driveTrain = SubsystemFactory.getInstance().getDriveTrain();

        driveTrain.configTalonsSideways();

        logger.exiting(StrafeVision.class.getName(), "StrafeVision()");
        
        this.requires(driveTrain);
    }

    @Override
    protected void execute() {
        
        logger.info("execute() ENTERING");

        logger.info("Is Tape Found " + vision.isTapeFound() + " Distance from Target: " + vision.getDistance() + " Current Angle:" + vision.getAngle());

		// how do we respond to that error?
		double pVal = Math.abs(vision.getAngle()) * .06;
		
		// set our speed to that adjusted speed
		double speed = Math.min(maxRPM, maxRPM * pVal);


        if(vision.getAngle() > 0) {
            logger.info("execute() : Moving right");
            driveTrain.moveSideways(-speed);
        } else if(vision.getAngle() < 0) {
            logger.info("execute() : Moving left");
            driveTrain.moveSideways(speed);
        }

        logger.info("execute() LEAVING");
    }

    @Override
    protected boolean isFinished() {
        
        logger.info("isFinished : " + (Math.abs(vision.getAngle()) <= 3) + " with angle: " + vision.getAngle());

        return Math.abs(vision.getAngle()) <= 3;
    }

}
