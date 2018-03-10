package org.usfirst.frc.team4611.robot.commands.solenoid;

import org.usfirst.frc.team4611.robot.Robot;
import org.usfirst.frc.team4611.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class PushBox extends Command{
	
	 public double initialTime;
	 public Timer timer;
	 public double actualTime;
	 
	public PushBox() {
    	this.actualTime= 2;
		this.requires(Robot.boxPusher);
	}
	protected void initialize() {
		this.initialTime = Timer.getFPGATimestamp();
	}
	protected void execute() {
		Robot.boxPusher.move(DoubleSolenoid.Value.kForward);
	}
	
	@Override
	protected boolean isFinished() {
		double remainingTime = Timer.getFPGATimestamp() - initialTime;  
		return remainingTime > actualTime;
	}

	protected void end() {
	
			Robot.boxPusher.move(DoubleSolenoid.Value.kReverse);
		
	}
	
	protected void interrupted() {
		end();
	}
	
}
