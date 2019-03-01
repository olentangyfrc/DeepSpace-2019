package org.usfirst.frc.team4611.robot.subsystems.petal;

import java.util.logging.Logger;

import org.usfirst.frc.team4611.robot.networktables.NetTableManager;
import org.usfirst.frc.team4611.robot.subsystems.PortMan;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;

public class Petal extends Subsystem {
	private static Logger logger = Logger.getLogger(Petal.class.getName());

	DoubleSolenoid petalSole;

	private ShuffleboardTab tab;
	private NetworkTableEntry petalStatus;


	public Petal() {	

	}

	public void init(PortMan pm) throws Exception {
		logger.info("initializing");
		petalSole = new DoubleSolenoid(pm.acquirePort(PortMan.pcm0_label, "Petal.inDoubleSolenoid"), pm.acquirePort(PortMan.pcm1_label, "Petal.outDoubleSolenoid"));

		petalStatus = tab.add("Petal Engaged", false).getEntry();
		

    } 

	public void togglePetal(){
		if(isOpen()){

			petalSole.set(DoubleSolenoid.Value.kReverse);

			petalStatus.setBoolean(false);

		} else {

			petalSole.set(DoubleSolenoid.Value.kForward);

			petalStatus.setBoolean(true);


		}
		
	}
	
	public boolean isOpen() {
		return petalSole.get().equals(DoubleSolenoid.Value.kForward);
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