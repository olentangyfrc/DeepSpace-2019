package org.usfirst.frc.team4611.robot.subsystems.elevator.commands;

import org.usfirst.frc.team4611.robot.Robot;
import org.usfirst.frc.team4611.robot.subsystems.elevator.Elevator;
import org.usfirst.frc.team4611.robot.subsystems.SubsystemFactory;

import edu.wpi.first.wpilibj.command.Command;

public class MoveElevatorDown extends Command{
	
	private Elevator elevator;
	private double speed = 0.5;
	
	public MoveElevatorDown(){
		//this.requires(Robot.elevator); //This command uses this subsystem
		elevator = SubsystemFactory.getInstance().getElevator();
		this.requires(elevator);
	} 

	protected void execute() {
		elevator.move(speed);
	}

	@Override
	protected boolean isFinished() {
		return !elevator.isSwitchSet();
		// TODO Auto-generated method stub
	}
	
	protected void end() {
		elevator.move(0);
		elevator.resetEncoders();
	}

}