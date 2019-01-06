package org.usfirst.frc.team4611.robot.commands.auton;

import org.usfirst.frc.team4611.robot.commands.auton.blocks.Roomba;

import edu.wpi.first.wpilibj.command.Command;

public class RoombaRepeat extends Command {

	private Roomba prevCom;

	public RoombaRepeat(Roomba prev) {
		prevCom = prev;
	}

	/**
	 * Called about every 20 milliseconds
	 */
	protected void execute() {
		// Stops the previous Roomba command so that it doesn't interfere with the new
		// one
		prevCom.cancel();

		// Starts a new Roomba command
		(new Roomba()).start();
	}

	/**
	 * Checks to see if the command is done based on the specified criteria
	 */
	@Override
	protected boolean isFinished() {
		return true;
	}

}
