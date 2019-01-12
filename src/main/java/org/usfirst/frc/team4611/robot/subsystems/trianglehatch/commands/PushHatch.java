package org.usfirst.frc.team4611.robot.subsystems.trianglehatch.commands;

import org.usfirst.frc.team4611.robot.subsystems.SubsystemFactory;
import org.usfirst.frc.team4611.robot.subsystems.trianglehatch.TriangleHatch;

import edu.wpi.first.wpilibj.command.Command;

public class PushHatch extends Command {
    
    private TriangleHatch triangleHatch;

    public PushHatch() {
        triangleHatch = SubsystemFactory.getInstance().getTriangleHatch();
        this.requires(triangleHatch);
    }

    @Override
    protected void execute() {
        System.out.println("Trying to push hatch");
        triangleHatch.pushHatch();
    }

    @Override
    protected void end() {
        System.out.println("Ending push hatch");
        triangleHatch.retractPistons();
    }

    @Override
    protected boolean isFinished() {
        return !triangleHatch.isRetracted();
    }

}