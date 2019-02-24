package org.usfirst.frc.team4611.robot.subsystems.elevator.commands;

import java.util.logging.Logger;

import org.usfirst.frc.team4611.robot.Robot;
import org.usfirst.frc.team4611.robot.subsystems.elevator.Elevator;
import org.usfirst.frc.team4611.robot.subsystems.SubsystemFactory;

import edu.wpi.first.wpilibj.command.Command;

public class MoveElevatorDown extends Command{
	
	private Logger logger = Logger.getLogger(MoveElevatorDown.class.getName());

	private Elevator elevator;
	private double speed = 0.5;
	
	private boolean stop = false;

	public MoveElevatorDown(){
		//this.requires(Robot.elevator); //This command uses this subsystem
		elevator = SubsystemFactory.getInstance().getElevator();
		this.requires(elevator);
	} 
	
	@Override
	protected void initialize() {
		stop = false;
	}

	protected void execute() {
		if(stop) 
			return;
		logger.info("Moving");
		elevator.move(speed);
		
	}

	@Override
	protected boolean isFinished() {
		return stop;
	}
	
	@Override
	public synchronized void cancel() {
		elevator.move(0);
		logger.info("canceled");
		stop = true;
	}

}