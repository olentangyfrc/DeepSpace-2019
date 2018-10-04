package org.usfirst.frc.team4611.robot.commands.auton;

import org.usfirst.frc.team4611.robot.commands.auton.blocks.Roomba;

import edu.wpi.first.wpilibj.command.Command;

public class RoombaRepeat extends Command{

	protected void execute() {
		(new Roomba()).start();
	}
	
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return true;
	}

}
