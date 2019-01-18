package org.usfirst.frc.team4611.robot.subsystems.petal.commands;

import org.usfirst.frc.team4611.robot.subsystems.SubsystemFactory;
import org.usfirst.frc.team4611.robot.subsystems.petal.Petal;

import edu.wpi.first.wpilibj.command.Command;

public class TogglePetal extends Command {

    private Petal petal;

    public TogglePetal(){
        petal = SubsystemFactory.getInstance().getPetal();
        this.requires(petal);
    }

    protected void initialize() {
        
    }

    protected void execute() {
        petal.togglePetal();
    }

    @Override
    protected boolean isFinished() {
        return true;
    }

}