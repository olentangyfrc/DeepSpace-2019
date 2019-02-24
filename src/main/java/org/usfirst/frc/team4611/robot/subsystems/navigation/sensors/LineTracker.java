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
    static private ShuffleboardTab tab = Shuffleboard.getTab("Navigation");

    private boolean inited = false;
    private int threshhold  = 3860;

    private AnalogInput lineTrackerLeft;
    private AnalogInput lineTrackerRight;

    private boolean onLeft;
    private boolean onRight;

    private int leftValue;
    private int rightValue;

    private NetworkTableEntry leftEntry;
    private NetworkTableEntry rightEntry;
    private NetworkTableEntry onLeftEntry;
    private NetworkTableEntry onRightEntry;

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

        leftEntry = tab.add("Linetracker Left Value", leftValue).getEntry();
        rightEntry = tab.add("Linetracker Right Value", rightValue).getEntry();
        onLeftEntry = tab.add("Linetracker On Left", onLeft).getEntry();
        onRightEntry = tab.add("Linetracker On Right", onRight).getEntry();
        inited = true;
    }

    public boolean isOnLeft() {
        onLeftEntry.setBoolean(lineTrackerLeft.getValue() < threshhold);
        return (lineTrackerLeft.getValue() < threshhold) ;
    }

    public boolean isOnRight() {
        onRightEntry.setBoolean(lineTrackerRight.getValue() < threshhold);
        return (lineTrackerRight.getValue() < threshhold) ;
    }

    public void checkLines() {
        if (!inited) return;

       leftEntry.setValue(lineTrackerLeft.getValue());
       rightEntry.setValue(lineTrackerRight.getValue());

       onLeftEntry.setValue(this.isOnLeft());
       onRightEntry.setValue(this.isOnRight());
    }
} 
