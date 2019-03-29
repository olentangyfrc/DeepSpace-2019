
package org.usfirst.frc.team4611.robot.subsystems.spatula;

import java.util.logging.Logger;

import org.usfirst.frc.team4611.robot.subsystems.PortMan;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;

public class Spatula extends Subsystem {
    private static Logger logger = Logger.getLogger(Spatula.class.getName());
    private DoubleSolenoid grabber;

    private ShuffleboardTab tab;
    private NetworkTableEntry spatulaStatus;


    public Spatula() {  
    }

    public void init(PortMan pm) throws Exception {
        logger.info("initializing");
        grabber = new DoubleSolenoid(pm.acquirePort(PortMan.pcm0_label, "Spatula.inDoubleSolenoid"), pm.acquirePort(PortMan.pcm1_label, "TriangleHatch.outDoubleSolenoid"));
        tab = Shuffleboard.getTab("Spatula");
        spatulaStatus = tab.add("Spatula Engaged", false).getEntry();
    } 

    public void openSpatula() {
        grabber.set(DoubleSolenoid.Value.kForward);
        spatulaStatus.setBoolean(true);
    }

    public void closeSpatula() {
        grabber.set(DoubleSolenoid.Value.kReverse);

        spatulaStatus.setBoolean(false);
    }

	public void setOff(){
		grabber.set(DoubleSolenoid.Value.kOff);
	}

    @Override
    protected void initDefaultCommand() {

    }
}