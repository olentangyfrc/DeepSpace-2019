package org.usfirst.frc.team4611.robot.subsystems.spatula.commands;

import org.usfirst.frc.team4611.robot.subsystems.SubsystemFactory;
import org.usfirst.frc.team4611.robot.subsystems.spatula.Spatula;

import edu.wpi.first.wpilibj.command.Command;

public class OpenSpatula extends Command {

    private Spatula spatula;

    public OpenSpatula(){
        spatula = SubsystemFactory.getInstance().getSpatula();
        this.requires(spatula);
    }
    protected void initialize() {
        
    }

    protected void execute() {
        spatula.openSpatula();
    }

    protected boolean isFinished() {
        return true;
    }

}