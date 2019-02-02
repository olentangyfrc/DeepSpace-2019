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
    double distance = 0;
    double targetCount = 0.0;
    boolean isTapeFound = false;

    private ShuffleboardTab tab;
    private NetworkTableEntry angleEntry;
    private NetworkTableEntry distanceEntry;
    private NetworkTableEntry tapeFoundEntry;

    public void Vision() {
        
    }

    public void init() {

        logger.info("initializing");
        tab = Shuffleboard.getTab("Vision");

        angleEntry = tab.add("angle", -1.0).getEntry();
        distanceEntry = tab.add("distance", 0).getEntry();
        tapeFoundEntry = tab.add("found", false).getEntry();
      }

    public void setAngle(double a) {
        angle = a;
        angleEntry.setDouble(a);
    }

    public double getAngle(){
        return angle;
    }
    
    public void setDistance(double d){
        distance = d;
        distanceEntry.setDouble(d);
    }

    public void setTapeFound(boolean f) {
        isTapeFound = f;
        tapeFoundEntry.setBoolean(f);
    }

    public boolean isTapeFound() {
        return isTapeFound;
    }

    public boolean isCentered() {
        return angle == 0.0;
    }

    public double getDistance() {
        return distance;
    }

    protected void initDefaultCommand() {
        setDefaultCommand(new PollNetworkTable());
    } 
}