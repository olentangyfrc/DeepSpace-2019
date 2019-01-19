package org.usfirst.frc.team4611.robot.subsystems.vision;

import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team4611.robot.subsystems.vision.commands.PollNetworkTable;


public class Vision extends Subsystem{

    boolean tapeFound   = false;
    boolean ballFound   = false;
    double angle  = 180;

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

    public void setAngle(double a) {
        angle = a;
    }
    
    public boolean isCentered() {
        System.out.println("angle [" + angle + "]");
        return angle == 0.0;
    }

    protected void initDefaultCommand() {
        setDefaultCommand(new PollNetworkTable());
    } 
}