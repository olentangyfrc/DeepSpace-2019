package org.usfirst.frc.team4611.robot.subsystems.elevator.commands;

import org.usfirst.frc.team4611.robot.Robot;
import org.usfirst.frc.team4611.robot.subsystems.elevator.Elevator;
import org.usfirst.frc.team4611.robot.subsystems.SubsystemFactory;

import edu.wpi.first.wpilibj.command.Command;

public class MoveElevatorUp extends Command{
	
	private Elevator elevator;
	private boolean stop = false;
	private double speed = 1;

	public MoveElevatorUp(){
		//this.requires(Robot.elevator); //This command uses this subsystem
		elevator = SubsystemFactory.getInstance().getElevator();
		this.requires(elevator);
	} 

	@Override
	public void initialize(){
		stop = false;
	}
	
	@Override
	protected void execute() {	
		if (stop)
			return;
		
		elevator.move(-speed);
	}

	@Override
	public synchronized void cancel() {
		elevator.move(0);
		stop = true;
	}

	@Override
	protected boolean isFinished() {
		return stop;
		// TODO Auto-generated method stub
	}
	
	//protected void end() {
	//	elevator.move(0);
	//}

}