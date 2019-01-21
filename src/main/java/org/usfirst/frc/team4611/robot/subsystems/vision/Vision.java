package org.usfirst.frc.team4611.robot.subsystems.vision;

import java.util.logging.Logger;

import org.usfirst.frc.team4611.robot.subsystems.vision.commands.PollNetworkTable;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;

public class Vision extends Subsystem{
    Logger logger = Logger.getLogger(Vision.class.getName());

    double angle  = 180;
    double targetCount = 0.0;
    private ShuffleboardTab tab;
    private NetworkTableEntry angleEntry;
    private NetworkTableEntry countEntry;

    public void Vision() {
        
    }

    public void init() {

        logger.info("initializing");
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
    }
    
    public boolean isCentered() {
        return angle == 0.0;
    }

    protected void initDefaultCommand() {
        setDefaultCommand(new PollNetworkTable());
    } 
}