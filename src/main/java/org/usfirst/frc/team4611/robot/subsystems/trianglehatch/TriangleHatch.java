package org.usfirst.frc.team4611.robot.subsystems.trianglehatch;

import java.util.logging.Logger;

import org.usfirst.frc.team4611.robot.subsystems.PortMan;
import org.usfirst.frc.team4611.robot.subsystems.trianglehatch.commands.RetractHatch;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class TriangleHatch extends Subsystem {
    private static Logger logger = Logger.getLogger(TriangleHatch.class.getName());

    private DoubleSolenoid pusher;

    public TriangleHatch() {  
    }

    public void init(PortMan pm) throws Exception {
           logger.info("initializing");
           pusher = new DoubleSolenoid(pm.acquirePort(PortMan.pcm0_label, "TriangleHatch.inDoubleSolenoid"), pm.acquirePort(PortMan.pcm1_label, "TriangleHatch.outDoubleSolenoid"));
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
        this.setDefaultCommand(new RetractHatch());
    }   
}