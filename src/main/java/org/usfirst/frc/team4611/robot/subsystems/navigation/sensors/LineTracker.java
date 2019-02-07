package org.usfirst.frc.team4611.robot.subsystems.navigation.sensors;

import java.util.logging.Logger;

import org.usfirst.frc.team4611.robot.networktables.NetTableManager;
import org.usfirst.frc.team4611.robot.subsystems.PortMan;

import edu.wpi.first.networktables.NetworkTableEntry;

// import java.util.TimerTask;

import edu.wpi.first.wpilibj.AnalogInput;
// import edu.wpi.first.wpilibj.I2C;
// import edu.wpi.first.wpilibj.I2C.Port;
// import edu.wpi.first.wpilibj.Timer;
import org.usfirst.frc.team4611.robot.subsystems.PortMan;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;

public class LineTracker extends Subsystem
{
    static Logger logger = Logger.getLogger(LineTracker.class.getName());

    private AnalogInput lineTrackerLeft;
    private AnalogInput lineTrackerMid;
    private AnalogInput lineTrackerRight;

    private boolean crossedLeft;
    private boolean crossedRight;

    private ShuffleboardTab tab;
    private NetworkTableEntry colorIsWhite;

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
    }

    public void init(PortMan pm) throws Exception{
        lineTrackerLeft = new AnalogInput(pm.acquirePort(PortMan.analog0_label, "LineTracker.lineTrackerLeft"));
        lineTrackerMid = new AnalogInput(pm.acquirePort(PortMan.analog1_label, "LineTracker.lineTrackerMid"));
        lineTrackerRight = new AnalogInput(pm.acquirePort(PortMan.analog2_label, "LineTracker.lineTrackerRight"));
        colorIsWhite = tab.add("Line Color", false).getEntry();
    }

    public boolean isWhite() {
     if(getLineTrackerInputMid() < 2200)  
      {
          colorIsWhite.setBoolean(true);
            return true;
        }
        else
        {
            return false;
        }
    }

    public void setCrossedLeft() {
        if(getLineTrackerInputLeft() < 2200) 
        {
            crossedLeft = true;
        }
    }

    public void setCrossedRight() {
        if(getLineTrackerInputRight() < 2200) 
        {
            crossedRight = true;
        }
    }
} 
