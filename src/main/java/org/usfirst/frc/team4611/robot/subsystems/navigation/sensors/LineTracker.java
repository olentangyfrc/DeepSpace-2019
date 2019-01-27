package org.usfirst.frc.team4611.robot.subsystems.navigation.sensors;

// import java.util.TimerTask;

import edu.wpi.first.wpilibj.AnalogInput;
// import edu.wpi.first.wpilibj.I2C;
// import edu.wpi.first.wpilibj.I2C.Port;
// import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;

public class LineTracker extends Subsystem
{
    private AnalogInput lineTracker = new AnalogInput(0);

    public int lineTrackerInput()
    {
        return lineTracker.getValue();
    }

    @Override
    protected void initDefaultCommand() {

    }

    public void init() {

    }
}