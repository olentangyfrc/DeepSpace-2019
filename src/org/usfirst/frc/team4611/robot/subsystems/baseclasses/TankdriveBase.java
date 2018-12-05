package org.usfirst.frc.team4611.robot.subsystems.baseclasses;

import edu.wpi.first.wpilibj.command.Subsystem;

public abstract class TankdriveBase extends Drivetrain {
	
	public abstract void move();
	public abstract void turn();
	
	public abstract void moveForward(int d);
	public abstract void moveBackward(int d);
	
	public abstract int getAverageSensorPos();
	
	public abstract void resetEncoders();
	
	
	
}
