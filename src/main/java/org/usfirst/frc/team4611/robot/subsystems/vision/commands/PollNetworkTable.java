package org.usfirst.frc.team4611.robot.subsystems.vision.commands;

import org.usfirst.frc.team4611.robot.subsystems.SubsystemFactory;
import org.usfirst.frc.team4611.robot.subsystems.vision.Vision;

import javax.sound.sampled.BooleanControl;

import org.usfirst.frc.team4611.robot.networktables.NetTableManager;


import edu.wpi.first.wpilibj.command.Command;

public class PollNetworkTable extends Command{

    private Vision  vision;
    public PollNetworkTable() {

    }

    public void init() {
        vision = SubsystemFactory.getInstance().getVision();
    }

    public void execute() {
        System.out.println("Polling Vision network table");
        vision.setTapeFound((Boolean)NetTableManager
                .getValue("Vision", "tapeFound", new Boolean(false)))
                ;
        vision.setBallFound((Boolean)NetTableManager
                .getValue("Vision", "tapeFound", new Boolean(false)));
    }

    public void end() {

    }

    public void cancel() {

    }

    public boolean isFinished() {
        return true;
    }
}