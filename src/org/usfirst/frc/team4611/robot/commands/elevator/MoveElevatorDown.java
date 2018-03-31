package org.usfirst.frc.team4611.robot.commands.elevator;

import org.usfirst.frc.team4611.robot.Robot;
import org.usfirst.frc.team4611.robot.RobotMap;
import org.usfirst.frc.team4611.robot.logging.Logger;
import org.usfirst.frc.team4611.robot.subsystems.Elevator;

import edu.wpi.first.wpilibj.command.Command;

public class MoveElevatorDown extends Command{
	
	public MoveElevatorDown(){
		this.requires(Robot.elevator); //This command uses this subsystem
	}

	protected void initialize () {
		//RobotMap.elevator_Talon.configReverseSoftLimitEnable(false, 0);
		RobotMap.elevator_Talon.configForwardSoftLimitEnable(false, 0);
	}
	
	protected void execute() {	
		
		if(!isFinished()) {
		double speed = (double)RobotMap.getValue(RobotMap.elevatorSubtable, RobotMap.elevatorDownSpeed);
		if(RobotMap.elevator_Talon.getSelectedSensorPosition(0) >= Elevator.ELEVATOR_TOP * .2) {
			Robot.elevator.move(speed * .4);
		}
		else {
			Robot.elevator.move(speed);
		}
		}
	}

	@Override
	protected boolean isFinished() {
		return !Robot.elevator.isSwitchSet();
		// TODO Auto-generated method stub
	}
	
	protected void end() {
		Robot.elevator.move(0);
		RobotMap.elevator_Talon.setSelectedSensorPosition(0, 0, 0);
		RobotMap.elevator_Talon.configReverseSoftLimitThreshold(-133000, 0); //upper limit
		RobotMap.elevator_Talon.configReverseSoftLimitEnable(true, 0);
	}
}