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

    public void Vision() {
        
    }

    public void init() {

        logger.info("initializing");
        tab = Shuffleboard.getTab("Vision");

        angleEntry = tab.add("angle", -1.0).getEntry();
      }

    public void setAngle(double a) {
        angle = a;
        angleEntry.setDouble(a);
    }

    public double getAngle(){
        return angle;
    }
    
    public boolean isCentered() {
        return angle == 0.0;
    }

    protected void initDefaultCommand() {
        setDefaultCommand(new PollNetworkTable());
    } 
}