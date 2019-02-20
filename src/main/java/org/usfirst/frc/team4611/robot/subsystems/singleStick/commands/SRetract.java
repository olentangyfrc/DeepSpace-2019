package org.usfirst.frc.team4611.robot.subsystems.singleStick.commands;

import org.usfirst.frc.team4611.robot.subsystems.SubsystemFactory;
import org.usfirst.frc.team4611.robot.subsystems.singleStick.SingleStick;
import org.usfirst.frc.team4611.robot.subsystems.stick.Stick;

import edu.wpi.first.wpilibj.command.Command;

public class SRetract extends Command {

    private SingleStick stick;

    public SRetract() {
        stick = SubsystemFactory.getInstance().getSingleStick();
        this.requires(stick);
    }

    @Override
    protected void execute() {
        System.out.println("Retracting Hatch");
        stick.retractPistons();
    }

    @Override
    protected boolean isFinished() {
        return true;
    }




}