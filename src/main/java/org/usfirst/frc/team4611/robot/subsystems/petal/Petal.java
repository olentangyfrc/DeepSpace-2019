package org.usfirst.frc.team4611.robot.subsystems.petal;

import org.usfirst.frc.team4611.robot.subsystems.PortMan;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Petal extends Subsystem{
	DoubleSolenoid petalSole;

	public Petal() {	
	}

	public void init(PortMan pm) {
        try{
            petalSole = new DoubleSolenoid(pm.acquirePort(PortMan.pcm0_label, "Petal.inDoubleSolenoid"), pm.acquirePort(PortMan.pcm1_label, "TriangleHatch.outDoubleSolenoid"));
        } catch(Exception e) {
            e.printStackTrace();
        }
        
    } 

	public void openPetal(){
		petalSole.set(DoubleSolenoid.Value.kForward);
	}

	public void closePetal(){
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