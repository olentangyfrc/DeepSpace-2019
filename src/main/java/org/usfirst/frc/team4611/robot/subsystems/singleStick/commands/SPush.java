package org.usfirst.frc.team4611.robot.subsystems.singleStick.commands;

import org.usfirst.frc.team4611.robot.subsystems.SubsystemFactory;
import org.usfirst.frc.team4611.robot.subsystems.singleStick.SingleStick;
import org.usfirst.frc.team4611.robot.subsystems.stick.Stick;

import edu.wpi.first.wpilibj.command.Command;

public class SPush extends Command {
    
    private SingleStick stick;

    public SPush() {
        stick = SubsystemFactory.getInstance().getSingleStick();
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