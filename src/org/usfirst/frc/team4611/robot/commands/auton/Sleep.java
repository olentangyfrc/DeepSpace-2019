package org.usfirst.frc.team4611.robot.commands.auton;

import org.usfirst.frc.team4611.robot.logging.Logger;

import edu.wpi.first.wpilibj.command.Command;
/**
 * this class is DANGEROUS. Use it SPARINGLY. It will stop the Bot from responding for the sleep period.
 * @author worthp
 *
 */

public class Sleep extends Command {
	
	private long millisecondsToSleep	= 0;


	/**
	 * I will not let you sleep longer than 1 second
	 * @param ms
	 */
	public Sleep(long ms) {
		millisecondsToSleep	= Math.min(ms, 1000);
	}

	public void execute() {
		
		try {
			Thread.sleep(millisecondsToSleep);
		} catch (InterruptedException e) {
			Logger.log(e.getMessage(), "Sleep");
		}
	}

	@Override
	protected boolean isFinished() {
		return true;
	}

}
