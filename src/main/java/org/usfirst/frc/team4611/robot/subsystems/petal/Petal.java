package org.usfirst.frc.team4611.robot.subsystems.petal;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

//import java.util.HashMap;

//import com.ctre.phoenix.motorcontrol.ControlMode;

//import org.usfirst.frc.team4611.robot.OI;

//import edu.wpi.first.wpilibj.Timer;

public class Petal extends Subsystem{
	DoubleSolenoid petalSole;

	public Petal() {
		//setupTalons();
		petalSole = new DoubleSolenoid(1, 2); //TO:DO set port numbers
		
	}

	public void setForward(){
		petalSole.set(DoubleSolenoid.Value.kForward);
	}

	public void setReverse(){
		petalSole.set(DoubleSolenoid.Value.kReverse);
	}	

	public void setOff(){
		petalSole.set(DoubleSolenoid.Value.kOff);
	}
	
	/**
	 * Defines the command to start when the bot is enabled. For TalonMecanum, this
	 * begins the command for normal driving procedure
	 */
	protected void initDefaultCommand() {
		//this.setDefaultCommand(new Move());
	}


}