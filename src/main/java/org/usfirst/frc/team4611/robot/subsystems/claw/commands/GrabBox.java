package org.usfirst.frc.team4611.robot.subsystems.claw.commands;

import org.usfirst.frc.team4611.robot.subsystems.claw.Claw;
import org.usfirst.frc.team4611.robot.subsystems.SubsystemFactory;
import edu.wpi.first.wpilibj.DoubleSolenoid;

import edu.wpi.first.wpilibj.command.Command;

public class GrabBox extends Command{
	
	private Claw claw;
	
	public GrabBox(){
		//this.requires(Robot.elevator); //This command uses this subsystem
		claw = SubsystemFactory.getInstance().getClaw();
		this.requires(claw);
	} 

	protected void execute() {
		claw.move(DoubleSolenoid.Value.kForward);
	}

	@Override
	protected boolean isFinished() {
		if(claw.getClawValue() == DoubleSolenoid.Value.kForward){ //get through subsystem		
			return true;
		}
		return false;
	}

}