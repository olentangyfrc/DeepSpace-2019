package org.usfirst.frc.team4611.robot.commands.elevator;

import org.usfirst.frc.team4611.robot.OI;
import org.usfirst.frc.team4611.robot.Robot;
import org.usfirst.frc.team4611.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class MoveElevator extends Command{
	
	public MoveElevator(){
		this.requires(Robot.el); //This command uses this subsystem
	}

	protected void execute() {
		double y = -OI.thirdJoy.getY();
		System.out.println("State of Upper Limit: "+ RobotMap.elevator_Talon.getSensorCollection().isFwdLimitSwitchClosed());
		System.out.println("State of lower limit: "+RobotMap.elevator_Talon.getSensorCollection().isRevLimitSwitchClosed());
		if (y < 0 && !(RobotMap.elevator_Talon.getSensorCollection().isFwdLimitSwitchClosed())) { //move up
				Robot.el.move(y * (double)RobotMap.getValue(RobotMap.elevatorSubtable, RobotMap.elevatorUpSpeed));
		}
		
		else if (y > 0 && !(RobotMap.elevator_Talon.getSensorCollection().isRevLimitSwitchClosed())){ //move down
			
				Robot.el.move(y * (double)RobotMap.getValue(RobotMap.elevatorSubtable, RobotMap.elevatorDownSpeed));
		}
		//Robot.el.moveToPos(47494);
	}

	@Override
	protected boolean isFinished() {
		return false;
		// TODO Auto-generated method stub
	}

}
