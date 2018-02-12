package org.usfirst.frc.team4611.robot.commands.elevator;

import org.usfirst.frc.team4611.robot.Robot;
import org.usfirst.frc.team4611.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class ResetElevator extends Command{

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return Robot.el.isSwitchSet();
	}

	protected void execute() {
		if (!Robot.el.isSwitchSet()) {
		Robot.el.move((double)RobotMap.getValue(RobotMap.elevatorSubtable, RobotMap.elevatorDownSpeed));
	}
		else {
			Robot.el.move(0);
		}
	}
}
