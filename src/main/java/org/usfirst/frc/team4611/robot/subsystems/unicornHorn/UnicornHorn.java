package org.usfirst.frc.team4611.robot.subsystems.unicornHorn;

import java.util.logging.Logger;

import org.usfirst.frc.team4611.robot.subsystems.PortMan;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;

public class UnicornHorn extends Subsystem {
    private static Logger logger = Logger.getLogger(UnicornHorn.class.getName());
    static private ShuffleboardTab tab = Shuffleboard.getTab("UnicornHorn");

    private DoubleSolenoid pusher;

    public UnicornHorn() {  
    }

    public void init(PortMan pm) throws Exception {
           pusher = new DoubleSolenoid(pm.acquirePort(PortMan.pcm0_label, "UnicornHorn.inDoubleSolenoid"),
                pm.acquirePort(PortMan.pcm1_label, "UnicornHorn.outDoubleSolenoid"));
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