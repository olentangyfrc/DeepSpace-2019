package org.usfirst.frc.team4611.robot.subsystems.petal.commands;

import org.usfirst.frc.team4611.robot.subsystems.SubsystemFactory;
import org.usfirst.frc.team4611.robot.subsystems.petal.Petal;

import edu.wpi.first.wpilibj.command.Command;

public class OpenPetal extends Command {

    private Petal petal;

    public OpenPetal(){
        petal = SubsystemFactory.getInstance().getPetal();
        this.requires(petal);
    }
    protected void initialize() {
        
    }

    protected void execute() {
        petal.setForward();
    }

    protected boolean isFinished() {
        return true;
    }

}