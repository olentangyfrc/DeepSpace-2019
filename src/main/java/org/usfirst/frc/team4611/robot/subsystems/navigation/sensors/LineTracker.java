package org.usfirst.frc.team4611.robot.subsystems.navigation.sensors;

import org.usfirst.frc.team4611.robot.networktables.NetTableManager;

import edu.wpi.first.networktables.NetworkTableEntry;

// import java.util.TimerTask;

import edu.wpi.first.wpilibj.AnalogInput;
// import edu.wpi.first.wpilibj.I2C;
// import edu.wpi.first.wpilibj.I2C.Port;
// import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;

public class LineTracker extends Subsystem
{
    private AnalogInput lineTracker = new AnalogInput(0);

    private ShuffleboardTab tab;
    private NetworkTableEntry colorIsWhite;

    public int lineTrackerInput()
    {
        return lineTracker.getValue();
    }
    public NetworkTableEntry getCsolorIsWhite()
    {
        return colorIsWhite;
    }

    @Override
    protected void initDefaultCommand() {
    }

    public void init() {
        
        colorIsWhite = tab.add("Line Color", false).getEntry();
    }

    public boolean isWhite(){
     if(lineTrackerInput() < 2200)  
      {
          colorIsWhite.setBoolean(true);
            return true;
        }
        else
        {
            return false;
        }
    }
} 
