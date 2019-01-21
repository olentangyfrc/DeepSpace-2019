package org.usfirst.frc.team4611.robot.subsystems.vision.commands;

import org.usfirst.frc.team4611.robot.subsystems.SubsystemFactory;
import org.usfirst.frc.team4611.robot.subsystems.vision.Vision;

import org.usfirst.frc.team4611.robot.networktables.NetTableManager;


import edu.wpi.first.wpilibj.command.Command;

public class PollNetworkTable extends Command{

    private Vision  vision;

    public PollNetworkTable() {
        vision = SubsystemFactory.getInstance().getVision();
        requires(vision);

    }

    public void init() {
    }

    public void execute() {
        vision.setAngle((double)NetTableManager.getValue("Vision", "angle", 180.0));
        vision.setTargetCount((double)NetTableManager.getValue("Vision", "targetCount", 0.0));    
    }

    public void end() {

    }

    public void cancel() {

    }

    public boolean isFinished() {
        return true;
    }
}