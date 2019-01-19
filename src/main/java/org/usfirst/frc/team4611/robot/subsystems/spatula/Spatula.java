
package org.usfirst.frc.team4611.robot.subsystems.spatula;

import org.usfirst.frc.team4611.robot.subsystems.PortMan;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Spatula extends Subsystem {

    private DoubleSolenoid grabber;

    public Spatula() {  
    }

    public void init(PortMan pm) {
        try{
            grabber = new DoubleSolenoid(pm.acquirePort(PortMan.pcm0_label, "Spatula.inDoubleSolenoid"), pm.acquirePort(PortMan.pcm1_label, "TriangleHatch.outDoubleSolenoid"));
        } catch(Exception e) {
            e.printStackTrace();
        }
        
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
