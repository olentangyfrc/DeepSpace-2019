package org.usfirst.frc.team4611.robot.subsystems;

import org.usfirst.frc.team4611.robot.commands.Move;
import org.usfirst.frc.team4611.robot.subsystems.baseclasses.MecanumBase;

import edu.wpi.first.wpilibj.Victor;

public class VictorMecanum extends MecanumBase {
	
	private Victor frontLeft = new Victor(0);
	private Victor frontRight = new Victor(1);
	private Victor backLeft = new Victor(2);
	private Victor backRight = new Victor(3);
	
	public void moveForward(double speed) {
		frontLeft.set(speed);
		frontRight.set(speed);
		backRight.set(speed);
		backLeft.set(speed);
	}
	
	public void moveBackward(double speed) {
		frontLeft.set(speed);
		frontRight.set(speed);
		backRight.set(speed);
		backLeft.set(speed);
	}
	
	@Override
	protected void initDefaultCommand() {
		this.setDefaultCommand(new Move(this));
	}

	public void move() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resetEncoders() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void rotate(double velocity) {
		// TODO Auto-generated method stub
		
	}
}