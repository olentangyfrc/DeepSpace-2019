package org.usfirst.frc.team4611.robot.subsystems.vision;

import org.usfirst.frc.team4611.robot.networktables.NetTableManager;

import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team4611.robot.subsystems.vision.commands.PollNetworkTable;


public class Vision extends Subsystem{

    boolean tapeFound = false;
    boolean ballFound = false;

    public void Vision() {
        
    }

    public void init() {

    }

    public boolean isTapeFound() {
        return tapeFound;
    }

    public boolean isBallFound() {
        return ballFound;
    }

    public void setTapeFound(Boolean f) {
        tapeFound   = f;
    }

    public void setBallFound(Boolean f) {
        ballFound   = f;
    }

    protected void initDefaultCommand() {
        setDefaultCommand(new PollNetworkTable());
    } 
}