package org.usfirst.frc.team4611.robot.subsystems.claw.commands;

import org.usfirst.frc.team4611.robot.subsystems.claw.Claw;
import org.usfirst.frc.team4611.robot.subsystems.SubsystemFactory;
import edu.wpi.first.wpilibj.DoubleSolenoid;

import edu.wpi.first.wpilibj.command.Command;

public class ReleaseBox extends Command{
	
	private Claw claw;
	
	public ReleaseBox(){
		//this.requires(Robot.elevator); //This command uses this subsystem
		claw = SubsystemFactory.getInstance().getClaw();
		this.requires(claw);
	} 

	protected void execute() {
		claw.move(DoubleSolenoid.Value.kReverse);
	}

	@Override
	protected boolean isFinished() {
		if(claw.getClawValue() == DoubleSolenoid.Value.kReverse){ //get through subsystem		
			return true;
		}
		return false;
	}

}