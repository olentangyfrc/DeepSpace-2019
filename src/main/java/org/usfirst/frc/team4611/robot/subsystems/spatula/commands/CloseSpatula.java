package org.usfirst.frc.team4611.robot.subsystems.spatula.commands;

import org.usfirst.frc.team4611.robot.subsystems.SubsystemFactory;
import org.usfirst.frc.team4611.robot.subsystems.spatula.Spatula;

import edu.wpi.first.wpilibj.command.Command;

public class CloseSpatula extends Command {

    private Spatula spatula;

    public CloseSpatula(){
        spatula = SubsystemFactory.getInstance().getSpatula();
        this.requires(spatula);
    }
    protected void initialize() {
        
    }

    protected void execute() {
        spatula.closeSpatula();
    }

    protected boolean isFinished() {
        return true;
    }

}