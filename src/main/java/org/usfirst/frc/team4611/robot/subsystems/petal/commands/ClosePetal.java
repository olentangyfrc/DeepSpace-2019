package org.usfirst.frc.team4611.robot.subsystems.petal.commands;

import org.usfirst.frc.team4611.robot.subsystems.SubsystemFactory;
import org.usfirst.frc.team4611.robot.subsystems.petal.Petal;

import edu.wpi.first.wpilibj.command.Command;

public class ClosePetal extends Command {

    private Petal petal;

    public ClosePetal(){
        petal = SubsystemFactory.getInstance().getPetal();
        this.requires(petal);
    }
    protected void initialize() {
        
    }

    protected void execute() {
        petal.closePetal();
    }

    protected boolean isFinished() {
        return true;
    }

}