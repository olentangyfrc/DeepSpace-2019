package org.usfirst.frc.team4611.robot.subsystems.navigation.sensors;

import java.util.logging.Logger;

import org.usfirst.frc.team4611.robot.networktables.NetTableManager;
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

    private AnalogInput lineTrackerLeft;
    private AnalogInput lineTrackerMid;
    private AnalogInput lineTrackerRight;

    private boolean onLeft;
    private boolean onRight;
    private boolean onMid;

    private ShuffleboardTab tab;
    private NetworkTableEntry colorIsWhite;
    private NetworkTableEntry onLeftEntry;
    private NetworkTableEntry onRightEntry;
    private NetworkTableEntry onMidEntry;

    public int getLineTrackerInputLeft()
    {
        logger.entering(LineTracker.class.getName(),"getLineTrackerInputLeft()");
        logger.exiting(LineTracker.class.getName(),"getLineTrackerInputLeft()");
        return lineTrackerLeft.getValue();
    }
    public int getLineTrackerInputMid()
    {
        logger.entering(LineTracker.class.getName(),"getLineTrackerInputMid()");
        logger.exiting(LineTracker.class.getName(),"getLineTrackerInputMid()");
        return lineTrackerMid.getValue();
    }

    public int getLineTrackerInputRight()
    {
        logger.entering(LineTracker.class.getName(),"getLineTrackerInputRight()");
        logger.exiting(LineTracker.class.getName(),"getLineTrackerInputRight()");
        return lineTrackerRight.getValue();
    }
    
    public NetworkTableEntry getColorIsWhite()
    {
        return colorIsWhite;
    }

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new LinetrackerDefault());
    }


    public void init(PortMan pm) throws Exception {
        tab = Shuffleboard.getTab("Health Map");
        
        lineTrackerLeft = new AnalogInput(pm.acquirePort(PortMan.analog1_label, "LineTracker.lineTrackerLeft"));
        lineTrackerMid = new AnalogInput(pm.acquirePort(PortMan.analog2_label, "LineTracker.lineTrackerMid"));
        lineTrackerRight = new AnalogInput(pm.acquirePort(PortMan.analog3_label, "LineTracker.lineTrackerRight"));
        colorIsWhite = tab.add("Line Color", false).getEntry();

        colorIsWhite = tab.add("Linetracker Line Color", false).getEntry();
        onLeftEntry = tab.add("Linetracker Left", onLeft).getEntry();
        onRightEntry = tab.add("Linetracker Right", onRight).getEntry();
        onMidEntry = tab.add("Linetracker Mid", onMid).getEntry();
    }

    public void checkLines() {

         logger.info("Left: "+lineTrackerLeft.getValue());
        // logger.info("Right: "+lineTrackerRight.getValue());
        // logger.info("Mid: "+lineTrackerMid.getValue());
        onLeft = (lineTrackerLeft.getValue() < 2600) ;
        onRight = (lineTrackerRight.getValue() < 2600) ;
        onMid = (lineTrackerMid.getValue() < 2600) ;
        onLeftEntry.setValue(onLeft);
        onRightEntry.setValue(onRight);
        onMidEntry.setValue(onMid);
    }
} 
