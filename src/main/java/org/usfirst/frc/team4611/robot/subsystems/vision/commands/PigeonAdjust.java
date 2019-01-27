package org.usfirst.frc.team4611.robot.subsystems.vision.commands;

import org.usfirst.frc.team4611.robot.subsystems.SubsystemFactory;
import org.usfirst.frc.team4611.robot.subsystems.navigation.Navigation;
import org.usfirst.frc.team4611.robot.subsystems.drivetrain.interfaces.DriveTrain;
import org.usfirst.frc.team4611.robot.subsystems.vision.Vision;



import edu.wpi.first.wpilibj.command.Command;

public class PigeonAdjust extends Command {

	private double desiredAngle;
	private double startingPigeonAngle;
	private double currentPigeonHeading;
	private double errorAngle;
	private double maxRPM = 1500;
	private double speedLimit = 100;
	private double angle;
	private double speed;
	private Navigation nav;
	private DriveTrain drive;
	private Vision vis;
	private Direction dir;
	 
	public PigeonAdjust() { 
		System.out.println("BigeonAdjust");
		nav = SubsystemFactory.getInstance().getNavigation();
		drive = SubsystemFactory.getInstance().getDriveTrain();
		vis = SubsystemFactory.getInstance().getVision();
		//this.requires(drive);
		//this.requires(nav);
		//this.requires(vis);
	}
	
	protected void initialize() {
		System.out.println("initialize");
		angle = vis.getAngle();
		startingPigeonAngle = nav.getCurentHeading();

		// desired angle is the difference between where we start and the angle to the box
		desiredAngle = startingPigeonAngle - angle;
	
		// which way do we need to go?
		if(desiredAngle > startingPigeonAngle) {
			dir = Direction.LEFT;
			angle += 3.5;
		}else{
			dir = Direction.RIGHT;
			angle -= 3.5;
		}

	}

	
	protected void execute() {
		System.out.println("execute");
		initialize();

		// where are we now?
		currentPigeonHeading = nav.getCurentHeading();
		
		// how far do we have to go b4 we get to the target?
		errorAngle = Math.abs(desiredAngle - currentPigeonHeading);
		
		// how do we respond to that error?
		double pVal = errorAngle * .06;
		
		// set our speed to that adjusted speed
		speed = Math.min(maxRPM, maxRPM * pVal);

		/**
		 * check to see if we are where we need to be before
		 * we even move. we might be there.
		 */
		
		
		if(!isFinished()) {

			if(dir == Direction.RIGHT) {
				drive.rotate(speed);
				
			}else if(dir == Direction.LEFT) {
				drive.rotate(-speed);
			}
		 }
		System.out.println("Speed [" + speed + "]");
	}
	
	protected boolean isFinished(){//
		System.out.println("desired angle [" + desiredAngle + 
				" ]current angle" + currentPigeonHeading);
		
		
		
		
		//if(!drive.isTargetSpeedWithinThreshold(speed)) {
		//	return true;
		//}
		if(Math.abs(this.desiredAngle-currentPigeonHeading) <= 1) {
			return true;
		}
		
		//stop if you go over
		if(dir == Direction.RIGHT && currentPigeonHeading < desiredAngle) {
			return true;
		}
		if(dir == Direction.LEFT && currentPigeonHeading > desiredAngle) {
			return true;
		}
		return false;
	}
	
	protected void end() {
		System.out.println("] current angle[" + currentPigeonHeading
				+ "] starting pigeon angle [" + startingPigeonAngle
				+ "] desired angle [" + desiredAngle + "]");
	}
	
	public enum Direction {
		LEFT, RIGHT, NONE
	}
}
