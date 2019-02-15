package org.usfirst.frc.team4611.robot.subsystems.navigation.sensors;

import java.util.logging.Logger;

import org.usfirst.frc.team4611.robot.subsystems.PortMan;
import org.usfirst.frc.team4611.robot.subsystems.navigation.commands.LinetrackerDefault;

import edu.wpi.first.networktables.NetworkTableEntry;

import edu.wpi.first.wpilibj.AnalogInput;
import org.usfirst.frc.team4611.robot.subsystems.PortMan;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;

public class LineTracker extends Subsystem
{
    static Logger logger = Logger.getLogger(LineTracker.class.getName());
    private int threshhold  = 3660;

    private AnalogInput lineTrackerLeft;
    private AnalogInput lineTrackerMid;
    private AnalogInput lineTrackerRight;

    private boolean onLeft;
    private boolean onRight;
    private boolean onMid;

    private int leftValue;
    private int rightValue;
    private int midValue;

    private ShuffleboardTab tab;
    private NetworkTableEntry leftEntry;
    private NetworkTableEntry rightEntry;
    private NetworkTableEntry midEntry;

    public int getLineTrackerInputLeft()
    {
        return lineTrackerLeft.getValue();
    }
    public int getLineTrackerInputMid()
    {
        return lineTrackerMid.getValue();
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
        tab = Shuffleboard.getTab("Navigation");
        
        lineTrackerLeft = new AnalogInput(pm.acquirePort(PortMan.analog1_label, "LineTracker.lineTrackerLeft"));
        lineTrackerMid = new AnalogInput(pm.acquirePort(PortMan.analog2_label, "LineTracker.lineTrackerMid"));
        lineTrackerRight = new AnalogInput(pm.acquirePort(PortMan.analog3_label, "LineTracker.lineTrackerRight"));

        leftEntry = tab.add("Linetracker Left Value", leftValue).getEntry();
        rightEntry = tab.add("Linetracker Right Value", rightValue).getEntry();
        midEntry = tab.add("Linetracker Mid Value", midValue).getEntry();
    }

    public boolean isOnLeft() {
        return (lineTrackerLeft.getValue() < threshhold) ;
    }

    public boolean isOnMid() {
        return (lineTrackerMid.getValue() < threshhold) ;
    }

    public boolean isOnRight() {
        return (lineTrackerRight.getValue() < threshhold) ;
    }

    public void checkLines() {
       leftEntry.setValue(lineTrackerLeft.getValue());
       rightEntry.setValue(lineTrackerRight.getValue());
       midEntry.setValue(lineTrackerMid.getValue());
    }
} 
