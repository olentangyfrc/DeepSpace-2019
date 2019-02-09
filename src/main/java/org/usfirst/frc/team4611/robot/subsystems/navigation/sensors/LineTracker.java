package org.usfirst.frc.team4611.robot.subsystems.navigation.sensors;

import org.usfirst.frc.team4611.robot.networktables.NetTableManager;

import edu.wpi.first.networktables.NetworkTableEntry;

// import java.util.TimerTask;

import edu.wpi.first.wpilibj.AnalogInput;
// import edu.wpi.first.wpilibj.I2C;
// import edu.wpi.first.wpilibj.I2C.Port;
// import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;

public class LineTracker 
{
    private AnalogInput lineTracker; 

    public LineTracker (int port) {
       lineTracker = new AnalogInput(port);

    }

    public int lineTrackerInput()
    {
        return lineTracker.getValue();
    }
    public boolean isColorWhite()
    {
        return isWhite();
    }

    public void init() {
        
    }

    public boolean isWhite(){
     if(lineTrackerInput() < 2200)  
      {
            return true;
        }
        else
        {
            return false;
        }
    }
} 
