package org.usfirst.frc.team4611.robot.subsystems.singleStick;

import org.usfirst.frc.team4611.robot.networktables.NetTableManager;
import org.usfirst.frc.team4611.robot.subsystems.PortMan;
import org.usfirst.frc.team4611.robot.subsystems.stick.commands.Retract;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;

public class SingleStick extends Subsystem {

    private Solenoid pusher;

    private ShuffleboardTab tab;
	private NetworkTableEntry stickStatus;

    public SingleStick() {
    }

    public void init(PortMan pm) {
        try{
           pusher = new Solenoid(pm.acquirePort(PortMan.pcm0_label, "Stick.inDoubleSolenoid"));
        } catch(Exception e) {
            e.printStackTrace();
        }
        tab = Shuffleboard.getTab("Health Map");
		NetTableManager.updateValue("Health Map", "SingleStickInitialize", true);

		stickStatus = tab.add("SingleStick Engaged", false).getEntry();
    } 

    public void pushHatch() {
        pusher.set(true);

        stickStatus.setBoolean(true);
    }

    public void retractPistons() {
        pusher.set(false);

        stickStatus.setBoolean(false);
    }

    public boolean isRetracted() {
        return false;
    }

    @Override
    protected void initDefaultCommand() {
    }   
}