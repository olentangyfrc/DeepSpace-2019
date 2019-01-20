package org.usfirst.frc.team4611.robot.subsystems.vision;

import org.usfirst.frc.team4611.robot.subsystems.vision.commands.PollNetworkTable;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;

public class Vision extends Subsystem{

    boolean tapeFound   = false;
    boolean ballFound   = false;
    double angle  = 180;
    double targetCount = 0;
    private ShuffleboardTab tab;
    private NetworkTableEntry angleEntry;
    private NetworkTableEntry countEntry;

    public void Vision() {
        
    }

    public void init() {
        tab = Shuffleboard.getTab("Vision");

        angleEntry = tab.add("Angle to target", 180.0).getEntry();
        countEntry = tab.add("Target Count", 0.0).getEntry();
      }

    public void setAngle(double a) {
        angle = a;
        angleEntry.setDouble(a);
    }

    public void setTargetCount(double c) {
        targetCount = c;
        countEntry.setValue(c);
        System.out.println("targetCount [" + targetCount + "]");
    }
    
    public boolean isCentered() {
        return angle == 0.0;
    }

    protected void initDefaultCommand() {
        setDefaultCommand(new PollNetworkTable());
    } 
}