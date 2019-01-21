
package org.usfirst.frc.team4611.robot.subsystems.spatula;

import java.util.logging.Logger;

import org.usfirst.frc.team4611.robot.subsystems.PortMan;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Spatula extends Subsystem {
    private static Logger logger = Logger.getLogger(Spatula.class.getName());
    private DoubleSolenoid grabber;

    public Spatula() {  
    }

    public void init(PortMan pm) throws Exception {
        logger.info("initializing");
        grabber = new DoubleSolenoid(pm.acquirePort(PortMan.pcm0_label, "Spatula.inDoubleSolenoid"), pm.acquirePort(PortMan.pcm1_label, "TriangleHatch.outDoubleSolenoid"));
    } 

    public void openSpatula() {
        grabber.set(DoubleSolenoid.Value.kForward);
    }

    public void closeSpatula() {
        grabber.set(DoubleSolenoid.Value.kReverse);
    }

	public void setOff(){
		grabber.set(DoubleSolenoid.Value.kOff);
	}

    @Override
    protected void initDefaultCommand() {

    }
}