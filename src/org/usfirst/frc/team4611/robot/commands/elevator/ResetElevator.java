package org.usfirst.frc.team4611.robot.commands.elevator;

import org.usfirst.frc.team4611.robot.Robot;
import org.usfirst.frc.team4611.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class ResetElevator extends Command{
	
	public ResetElevator(){
		this.requires(Robot.elevator); //This command uses this subsystem
	}

	protected void execute() {
		Robot.elevator.move((double)RobotMap.getValue(RobotMap.elevatorSubtable, RobotMap.elevatorDownSpeed) * .4);
	}	
	@Override
	protected boolean isFinished() {
		//System.out.println(Robot.elevator.isSwitchSet());
		return !Robot.elevator.isSwitchSet();
	}
	
	protected void end() {
		RobotMap.elevator_Talon.setSelectedSensorPosition(0, 0, 0);
		RobotMap.elevator_Talon.configReverseSoftLimitThreshold(-137328, 0); //upper limit
		RobotMap.elevator_Talon.configForwardSoftLimitThreshold(0, 0); //lower limit
		RobotMap.elevator_Talon.configForwardSoftLimitEnable(true, 0);
		RobotMap.elevator_Talon.configReverseSoftLimitEnable(true, 0);
	}
}
