package org.usfirst.frc.team4611.robot.subsystems.trianglehatch.commands;

import org.usfirst.frc.team4611.robot.subsystems.SubsystemFactory;
import org.usfirst.frc.team4611.robot.subsystems.trianglehatch.TriangleHatch;

import edu.wpi.first.wpilibj.command.Command;

public class RetractHatch extends Command {

    private TriangleHatch triangleHatch;

    public RetractHatch(){
        triangleHatch = SubsystemFactory.getInstance().getTriangleHatch();
        this.requires(triangleHatch);
    }

    @Override
    protected void execute() {
        System.out.println("Retracting Hatch");
        triangleHatch.retractPistons();
    }

    @Override
    protected boolean isFinished() {
        return true;
    }




}