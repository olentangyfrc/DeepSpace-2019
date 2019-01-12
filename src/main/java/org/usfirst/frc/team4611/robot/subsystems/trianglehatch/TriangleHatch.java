package org.usfirst.frc.team4611.robot.subsystems.trianglehatch;

import org.usfirst.frc.team4611.robot.subsystems.PortMan;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class TriangleHatch extends Subsystem {

    private DoubleSolenoid pusher;

    public TriangleHatch() {  
    }

    public void init(PortMan pm) {
        try{
           pusher = new DoubleSolenoid(pm.acquirePort(PortMan.pcm0_label, "TriangleHatch.inDoubleSolenoid"), pm.acquirePort(PortMan.pcm1_label, "TriangleHatch.outDoubleSolenoid"));
        } catch(Exception e) {
            e.printStackTrace();
        }
    } 

    public void pushHatch() {
        pusher.set(DoubleSolenoid.Value.kForward);
    }

    public void retractPistons() {
        pusher.set(DoubleSolenoid.Value.kReverse);
    }

    public boolean isRetracted() {
        return pusher.get().equals(DoubleSolenoid.Value.kReverse);
    }

    @Override
    protected void initDefaultCommand() {

    }   
}