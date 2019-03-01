package org.usfirst.frc.team4611.robot.subsystems.stick;

import java.util.logging.Logger;

import org.usfirst.frc.team4611.robot.subsystems.PortMan;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;

public class Stick extends Subsystem {
    private static Logger logger = Logger.getLogger(Stick.class.getName());
    static private ShuffleboardTab tab = Shuffleboard.getTab("Stick");

    private DoubleSolenoid pusher;

	private NetworkTableEntry stickStatus;

    public Stick() {  
    }

    public void init(PortMan pm) {
        try{
           pusher = new DoubleSolenoid(pm.acquirePort(PortMan.pcm2_label, "Stick.inDoubleSolenoid"), pm.acquirePort(PortMan.pcm3_label, "Stick.outDoubleSolenoid"));
        } catch(Exception e) {
            e.printStackTrace();
        }

		stickStatus = tab.add("Stick Engaged", false).getEntry();
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