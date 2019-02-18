package org.usfirst.frc.team4611.robot.subsystems.stick;

import org.usfirst.frc.team4611.robot.networktables.NetTableManager;
import org.usfirst.frc.team4611.robot.subsystems.PortMan;
import org.usfirst.frc.team4611.robot.subsystems.stick.commands.Retract;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;

public class Stick extends Subsystem {

    private DoubleSolenoid pusher;

    private ShuffleboardTab tab;
    private NetworkTableEntry stickStatus;
    
    private ShuffleboardTab portTab;
    private NetworkTableEntry port1;
    private NetworkTableEntry port2;

    public Stick() {  
    }

    public void init(PortMan pm) {
        try{
           pusher = new DoubleSolenoid(pm.acquirePort(PortMan.pcm0_label, "Stick.inDoubleSolenoid"), pm.acquirePort(PortMan.pcm1_label, "Stick.outDoubleSolenoid"));
        } catch(Exception e) {
            e.printStackTrace();
        }
        tab = Shuffleboard.getTab("Health Map");
        NetTableManager.updateValue("Health Map", "StickInitialize", true);
        
        portTab = Shuffleboard.getTab("Port Manager");
        NetTableManager.updateValue("Port Manager", "Stick Ports", true);

        port1 = portTab.add("pcm0_label", true).getEntry();
        port2 = portTab.add("pcm1_label", true).getEntry();

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