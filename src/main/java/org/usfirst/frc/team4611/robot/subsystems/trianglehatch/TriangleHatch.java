package org.usfirst.frc.team4611.robot.subsystems.trianglehatch;

import java.util.logging.Logger;

import org.usfirst.frc.team4611.robot.subsystems.PortMan;
import org.usfirst.frc.team4611.robot.subsystems.trianglehatch.commands.RetractHatch;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;

public class TriangleHatch extends Subsystem {
    private static Logger logger = Logger.getLogger(TriangleHatch.class.getName());

    private DoubleSolenoid pusher;

    private ShuffleboardTab tab;
    
    private NetworkTableEntry triangleStatus;

    public TriangleHatch() {  
    }

    public void init(PortMan pm) throws Exception {
           logger.info("initializing");
           pusher = new DoubleSolenoid(pm.acquirePort(PortMan.pcm0_label, "TriangleHatch.inDoubleSolenoid"), pm.acquirePort(PortMan.pcm1_label, "TriangleHatch.outDoubleSolenoid"));

        triangleStatus = tab.add("Triangle Enganged", false).getEntry();
    }

    public void pushHatch() {
        pusher.set(DoubleSolenoid.Value.kForward);
        triangleStatus.setBoolean(true);
    }

    public void retractPistons() {
        pusher.set(DoubleSolenoid.Value.kReverse);
        triangleStatus.setBoolean(true);
        }

    public boolean isRetracted() {
        triangleStatus.setBoolean(false);
        return pusher.get().equals(DoubleSolenoid.Value.kReverse);
    }

    @Override
    protected void initDefaultCommand() {
        this.setDefaultCommand(new RetractHatch());
    }   
}