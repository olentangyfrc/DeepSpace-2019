package org.usfirst.frc.team4611.robot.subsystems.arm.commands;

import org.usfirst.frc.team4611.robot.subsystems.arm.Arm;
import org.usfirst.frc.team4611.robot.subsystems.SubsystemFactory;

import edu.wpi.first.wpilibj.command.Command;

public class MovePotUp extends Command{
	private boolean stop = false;
	private Arm arm;
	
	public MovePotUp(){
		//this.requires(Robot.elevator); //This command uses this subsystem
		arm = SubsystemFactory.getInstance().getArm();
		this.requires(arm);
	} 

	@Override
	public void initialize(){
		stop = false;
	}

	protected void execute() {
		if (stop)
			return;
		arm.moveArmUp(0.7, 0.7);
	}

	@Override
	public synchronized void cancel() {
		arm.stopPot();
		stop = true;
	}

	@Override
	protected boolean isFinished() {
    	return stop;
	}

}