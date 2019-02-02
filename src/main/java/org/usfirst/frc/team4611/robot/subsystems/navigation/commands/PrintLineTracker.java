package org.usfirst.frc.team4611.robot.subsystems.navigation.commands;

import org.usfirst.frc.team4611.robot.subsystems.SubsystemFactory;
import org.usfirst.frc.team4611.robot.subsystems.navigation.sensors.LineTracker;

import edu.wpi.first.wpilibj.command.Command;

public class PrintLineTracker extends Command {

    private LineTracker lineTracker;

    public PrintLineTracker() {
        lineTracker = SubsystemFactory.getInstance().getLineTracker();
    }

    protected void execute(){
        System.out.println(lineTracker.lineTrackerInput());
        System.out.println(lineTracker.isWhite());
        System.out.println(lineTracker.getColorIsWhite());
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}