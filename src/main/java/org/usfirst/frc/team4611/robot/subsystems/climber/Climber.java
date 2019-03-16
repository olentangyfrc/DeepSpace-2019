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
           pusher = new DoubleSolenoid(pm.acquirePort(PortMan.pcm4_label, "Stick.inDoubleSolenoidx"), pm.acquirePort(PortMan.pcm5_label, "Stick.outDoubleSolenoidx"));
    } 

    public void pushHatch() {
        pusher.set(DoubleSolenoid.Value.kForward);

        stickStatus.setBoolean(true);
    }

    public void retractPistons() {
        pusher.set(DoubleSolenoid.Value.kReverse);

        stickStatus.setBoolean(false);
    }

    public boolean isRetracted() {
        return pusher.get().equals(DoubleSolenoid.Value.kReverse);
    }

    @Override
    protected void initDefaultCommand() {
    }   
}