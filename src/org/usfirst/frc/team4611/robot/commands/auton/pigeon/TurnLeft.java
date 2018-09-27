package org.usfirst.frc.team4611.robot.commands.auton.pigeon;

import org.usfirst.frc.team4611.robot.Robot;
import org.usfirst.frc.team4611.robot.subsystems.baseclasses.MecanumBase;
import org.usfirst.frc.team4611.robot.subsystems.sensors.Pigeon;

import edu.wpi.first.wpilibj.command.Command;

public class TurnLeft extends Command {
	
	private Pigeon pigeon;
	private MecanumBase mecanum;
	
	private double desiredAngle;
	private double startingPigeonAngle;
	private double maxRPM = 500;
	private double angle;
	
	private final double rotationDifference = 0;
	private final double ANGLE_TOLERANCE = 1;
	
	public TurnLeft(MecanumBase mec, Pigeon pig, double angle) {
		this.mecanum = mec;
		this.angle = angle; 
		this.pigeon = pig;
		this.requires(mecanum);
	}
	
	protected void initialize() {
		//Gets the current angle once the command begins
		startingPigeonAngle = pigeon.getCurrentAngle();

		// Desired angle is the difference between where we start and the angle to the box
		desiredAngle = startingPigeonAngle + angle;
	
		// Account for drifting
		angle += rotationDifference;

	}
	protected void execute() {
		System.out.println(desiredAngle);
		System.out.println(pigeon.getCurrentAngle());
		// How far do we have to go b4 we get to the target?
		double errorAngle = pigeon.getAbolsuteAngleError(desiredAngle);
		
		// How do we respond to that error?
		double pVal = errorAngle * .15;
		
		// Set our speed to that adjusted speed
		double speed = Math.min(maxRPM, maxRPM * pVal);

		// Check to see if we are where we need to be before we even move. we might be there.
		if(!isFinished()) {
			mecanum.rotate(-speed);
		 }
	}
	
	protected boolean isFinished(){

		//Checks to see if the bot has turned the desired amount within a certain tolerance
		if(pigeon.getAbolsuteAngleError(desiredAngle) <= ANGLE_TOLERANCE) {
			return true;
		}
		
		//Checks to see if the bot has turned farther than before
		if(pigeon.getCurrentAngle() > desiredAngle) {
			return true;
		}
		return false;
	}
	
}