package org.usfirst.frc.team4611.robot.subsystems.baseclasses;

import edu.wpi.first.wpilibj.command.Subsystem;

public abstract class MecanumBase extends Subsystem {

	public abstract void move();
	public abstract void moveForward(double speed);
	public abstract void moveBackward(double speed);
	public abstract void resetEncoders();
	
	public abstract void rotate(double velocity);

}
