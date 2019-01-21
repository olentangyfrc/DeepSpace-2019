package org.usfirst.frc.team4611.robot.subsystems.stick.commands;

import org.usfirst.frc.team4611.robot.subsystems.SubsystemFactory;
import org.usfirst.frc.team4611.robot.subsystems.stick.Stick;

import edu.wpi.first.wpilibj.command.Command;

public class PushHatch extends Command {
    
    private Stick stick;

    public PushHatch() {
        stick = SubsystemFactory.getInstance().getStick();
        this.requires(stick);
    }

    @Override
    protected void execute() {
        System.out.println("Trying to push hatch");
        stick.pushHatch();
    }

    @Override
    protected boolean isFinished() {
        return true;
    }

}