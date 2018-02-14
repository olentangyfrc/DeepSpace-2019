package org.usfirst.frc.team4611.robot.potentiometer;

import org.usfirst.frc.team4611.robot.Robot;
import org.usfirst.frc.team4611.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class MoveMaxDown extends Command{
	private double pos;
	public MoveMaxDown(double pos) {
		// TODO Auto-generated constructor stub
		this.pos = pos;
	}
	
	protected void execute() {
		Robot.arm.movePotPos(pos);
	}
	
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		double potValue1 = RobotMap.linearActuatorPot.get();
		double potValue2 = RobotMap.linearActuatorPot2.get();
    	if((pos + 0.05 > potValue1 && pos - 0.05 < potValue1) && (pos + 0.05 > potValue2 && pos - 0.05 < potValue2)) {
    		return true;
    	}
    	else {
    		return false;
    	}
	}

}
