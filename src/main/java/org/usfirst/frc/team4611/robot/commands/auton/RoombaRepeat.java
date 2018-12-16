package org.usfirst.frc.team4611.robot.commands.auton;

import org.usfirst.frc.team4611.robot.commands.auton.blocks.Roomba;

import edu.wpi.first.wpilibj.command.Command;

public class RoombaRepeat extends Command{

	private Roomba prevCom;
	public RoombaRepeat(Roomba prev) {
		prevCom = prev;
	}
	
	protected void execute() {
		prevCom.cancel();
		(new Roomba()).start();
	}
	
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return true;
	}

}
