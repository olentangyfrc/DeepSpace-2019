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
    private int threshhold  = 3860;

    private AnalogInput lineTrackerLeft;
     private AnalogInput lineTrackerRight;

    private boolean onLeft;
    private boolean onRight;

    private int leftValue;
    private int rightValue;

    private ShuffleboardTab tab;
    private NetworkTableEntry leftEntry;
    private NetworkTableEntry rightEntry;
    //private NetworkTableEntry midEntry;
    private NetworkTableEntry onLeftEntry;
    private NetworkTableEntry onRightEntry;
    //private NetworkTableEntry onMidEntry;

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
        tab = Shuffleboard.getTab("Navigation");
        
        lineTrackerLeft = new AnalogInput(pm.acquirePort(PortMan.analog2_label, "LineTracker.lineTrackerLeft"));
        lineTrackerRight = new AnalogInput(pm.acquirePort(PortMan.analog3_label, "LineTracker.lineTrackerRight"));

        leftEntry = tab.add("Linetracker Left Value", leftValue).getEntry();
        rightEntry = tab.add("Linetracker Right Value", rightValue).getEntry();
        onLeftEntry = tab.add("Linetracker On Left", onLeft).getEntry();
        onRightEntry = tab.add("Linetracker On Right", onRight).getEntry();
      
    }

    public boolean isOnLeft() {
        onLeftEntry.setBoolean(lineTrackerLeft.getValue() < threshhold);
        return (lineTrackerLeft.getValue() < threshhold) ;
    }

    /*public boolean isOnMid() {
        return (lineTrackerMid.getValue() < threshhold) ;
    }*/

    public boolean isOnRight() {
        onRightEntry.setBoolean(lineTrackerRight.getValue() < threshhold);
        return (lineTrackerRight.getValue() < threshhold) ;
    }

    public void checkLines() {
       leftEntry.setValue(lineTrackerLeft.getValue());
       rightEntry.setValue(lineTrackerRight.getValue());
      // midEntry.setValue(lineTrackerMid.getValue());

       onLeftEntry.setValue(this.isOnLeft());
       //onMidEntry.setValue(this.isOnMid());
       onRightEntry.setValue(this.isOnRight());

       logger.info(""+lineTrackerLeft.getValue());
       logger.info(""+lineTrackerRight.getValue());
       //logger.info(""+lineTrackerMid.getValue());
    }
} 
