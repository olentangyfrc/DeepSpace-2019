package org.usfirst.frc.team4611.robot.subsystems.vision.commands;

import org.usfirst.frc.team4611.robot.subsystems.SubsystemFactory;
import org.usfirst.frc.team4611.robot.subsystems.vision.Vision;

import org.usfirst.frc.team4611.robot.networktables.NetTableManager;


import edu.wpi.first.wpilibj.command.Command;

public class PollNetworkTable extends Command{

    private double lastUpdateTime = 0;
    private int noChangeCount = 0;
    private Vision  vision;

    public PollNetworkTable() {
        vision = SubsystemFactory.getInstance().getVision();
        requires(vision);

    }

    public void init() {
    }

    public void execute() {
        double thisTime = (double)NetTableManager.getValue("Vision", "rPi last update", 0.0);

        if ((thisTime - lastUpdateTime) == 0.00) {
            noChangeCount += 1;
        } else {
            noChangeCount = 0;
        }
        NetTableManager.updateValue("Vision", "rPi connected", noChangeCount <= 10);

        vision.setAngle((double)NetTableManager.getValue("Vision", "angle", 180.0));
        vision.setDistance((double)NetTableManager.getValue("Vision", "distance", 0));
        vision.setTapeFound((boolean)NetTableManager.getValue("Vision", "found", false));

        lastUpdateTime = thisTime;
    }

    public void end() {

    }

    public void cancel() {

    }

    public boolean isFinished() {
        return true;
    }
}