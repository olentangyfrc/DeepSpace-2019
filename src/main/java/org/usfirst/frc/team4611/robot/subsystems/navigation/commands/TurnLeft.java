package org.usfirst.frc.team4611.robot.subsystems.navigation.commands;

import org.usfirst.frc.team4611.robot.subsystems.SubsystemFactory;
import org.usfirst.frc.team4611.robot.subsystems.drivetrain.interfaces.DriveTrain;
import org.usfirst.frc.team4611.robot.subsystems.navigation.Navigation;

import edu.wpi.first.wpilibj.command.Command;

public class TurnLeft extends Command {

	private double desiredAngle;
	private double startingPigeonAngle;
	private double maxRPM = 1000;
	private double angle;

	private final double rotationDifference = 0;
	private final double ANGLE_TOLERANCE = 1;

	private DriveTrain driveTrain;
	private Navigation navigation;

	public TurnLeft(double angle) {
		this.angle = angle;
		driveTrain = SubsystemFactory.getInstance().getDriveTrain();
		navigation = SubsystemFactory.getInstance().getNavigation();
		this.requires(driveTrain);

	}

	/**
	 * Called when the command is first instantiated or resumed from termination.
	 * Only called once
	 */
	protected void initialize() {
		// Gets the current angle once the command begins
		startingPigeonAngle = navigation.getCurentHeading();

		// Desired angle is the difference between where we start and the angle to the
		// box
		desiredAngle = startingPigeonAngle + angle;

		// Account for drifting
		angle += rotationDifference;

	}

	/**
	 * Called about every 20 milliseconds
	 */
	protected void execute() {
		System.out.println(desiredAngle);
		System.out.println(navigation.getCurentHeading());
		// How far do we have to go b4 we get to the target?
		double errorAngle = navigation.getCurrentAbsoluteHeadingError(desiredAngle);

		// How do we respond to that error?
		double pVal = errorAngle * .15;

		// Set our speed to that adjusted speed
		double speed = Math.min(maxRPM, maxRPM * pVal);

		// Check to see if we are where we need to be before we even move. we might be
		// there.
		if (!isFinished()) {
			driveTrain.rotate(-speed);
		}
	}

	/**
	 * Checks to see if the command is done based on the specified criteria
	 */
	protected boolean isFinished() {

		// Checks to see if the bot has turned the desired amount within a certain
		// tolerance
		if (navigation.getCurrentAbsoluteHeadingError(desiredAngle) <= ANGLE_TOLERANCE) {
			return true;
		}

		// Checks to see if the bot has turned farther than before
		if (navigation.getCurentHeading() > desiredAngle) {
			return true;
		}
		return false;
	}

}