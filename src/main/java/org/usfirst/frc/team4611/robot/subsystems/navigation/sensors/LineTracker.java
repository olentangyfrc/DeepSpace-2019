package org.usfirst.frc.team4611.robot.subsystems.navigation.sensors;

import java.util.logging.Logger;

import org.usfirst.frc.team4611.robot.subsystems.PortMan;
import org.usfirst.frc.team4611.robot.subsystems.navigation.commands.LinetrackerDefault;

import edu.wpi.first.networktables.NetworkTableEntry;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;

public class LineTracker extends Subsystem
{
    static Logger logger = Logger.getLogger(LineTracker.class.getName());
    static private ShuffleboardTab tab = Shuffleboard.getTab("Navigation");

    private boolean inited = false;
    private int threshhold  = 3860;

    private AnalogInput lineTrackerLeft;
    private AnalogInput lineTrackerRight;

    public int getLineTrackerInputLeft()
    {
        return lineTrackerLeft.getValue();
    }

    public int getLineTrackerInputRight()
    {
        return lineTrackerRight.getValue();
    }
    
    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new LinetrackerDefault());
    }

    public void init(PortMan pm) throws Exception {
        
        lineTrackerLeft = new AnalogInput(pm.acquirePort(PortMan.analog2_label, "LineTracker.lineTrackerLeft"));
        lineTrackerRight = new AnalogInput(pm.acquirePort(PortMan.analog3_label, "LineTracker.lineTrackerRight"));

        initSB();
        inited = true;
    }

    public boolean isOnLeft() {
        return (lineTrackerLeft.getValue() < threshhold) ;
    }

    public boolean isOnRight() {
        return (lineTrackerRight.getValue() < threshhold) ;
    }

    public void checkLines() {
        if (!inited) return;

       leftEntry.setValue(lineTrackerLeft.getValue());
       rightEntry.setValue(lineTrackerRight.getValue());

       onLeftEntry.setValue(isOnLeft());
       onRightEntry.setValue(isOnRight());

       threshhold = (int) thresholdEntry.getDouble(threshhold);
    }

    private NetworkTableEntry leftEntry;
    private NetworkTableEntry rightEntry;
    private NetworkTableEntry onLeftEntry;
    private NetworkTableEntry onRightEntry;
    private NetworkTableEntry thresholdEntry;

    public void initSB() {
        leftEntry = tab.add("LT Left Value", 0)
                            .withSize(1,1).withPosition(1,0).getEntry();
        onLeftEntry = tab.add("LT Left On", false)
                            .withSize(1,1).withPosition(0,0).getEntry();

        rightEntry = tab.add("LT Right Value", 0)
                            .withSize(1,1).withPosition(3,0).getEntry();
        onRightEntry = tab.add("LT Right On", false)
                            .withSize(1,1).withPosition(4,0).getEntry();

        thresholdEntry = tab.add("LT Threshold", threshhold)
                            .withSize(1,1).withPosition(3,4).getEntry();
    }
} 
