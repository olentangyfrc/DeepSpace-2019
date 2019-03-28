package org.usfirst.frc.team4611.robot.subsystems.hook;

import java.util.logging.Logger;

import org.usfirst.frc.team4611.robot.subsystems.PortMan;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;

public class Hook extends Subsystem {
    private static Logger logger = Logger.getLogger(Hook.class.getName());
    static private ShuffleboardTab tab = Shuffleboard.getTab("Hook");

    private DoubleSolenoid pusher;

    public Hook() {
    }

    public void init(PortMan pm) throws Exception {
           pusher = new DoubleSolenoid(pm.acquirePort(PortMan.pcm4_label, "Hook.inDoubleSolenoidx"),
                                                pm.acquirePort(PortMan.pcm5_label, "Hook.outDoubleSolenoidx"));
    } 

    public void move(boolean up) {
      if (up) {
        pusher.set(DoubleSolenoid.Value.kReverse);
      } else {
        pusher.set(DoubleSolenoid.Value.kForward);
      }
    }
    public boolean isRetracted() {
        return pusher.get().equals(DoubleSolenoid.Value.kReverse);
    }

    @Override
    protected void initDefaultCommand() {
    }   
}