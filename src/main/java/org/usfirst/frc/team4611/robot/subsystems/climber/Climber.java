package org.usfirst.frc.team4611.robot.subsystems.climber;

import java.util.logging.Logger;

import org.usfirst.frc.team4611.robot.subsystems.PortMan;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;

public class Climber extends Subsystem {
    private static Logger logger = Logger.getLogger(Climber.class.getName());
    static private ShuffleboardTab tab = Shuffleboard.getTab("Stick");

    private DoubleSolenoid pusher;

	private NetworkTableEntry stickStatus;

    public Climber() {
    }

    public void init(PortMan pm) throws Exception {
           pusher = new DoubleSolenoid(pm.acquirePort(PortMan.pcm0_label, "Stick.inDoubleSolenoidx"), pm.acquirePort(PortMan.pcm1_label, "Stick.outDoubleSolenoidx"));
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