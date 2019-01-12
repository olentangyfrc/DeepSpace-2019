package org.usfirst.frc.team4611.robot.subsystems.trianglehatch.commands;

import org.usfirst.frc.team4611.robot.subsystems.SubsystemFactory;
import org.usfirst.frc.team4611.robot.subsystems.trianglehatch.TriangleHatch;

import edu.wpi.first.wpilibj.command.Command;

public class PushHatch extends Command {
    
    private TriangleHatch triangleHatch;

    public PushHatch() {
        triangleHatch = SubsystemFactory.getInstance().getTriangleHatch();
    }

    @Override
    protected void execute() {
        triangleHatch.pushHatch();
    }

    @Override
    protected void end() {
        triangleHatch.retractPistons();
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

}