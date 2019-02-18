package org.usfirst.frc.team4611.robot.subsystems.stick.commands;

import org.usfirst.frc.team4611.robot.subsystems.SubsystemFactory;
import org.usfirst.frc.team4611.robot.subsystems.stick.Stick;

import edu.wpi.first.wpilibj.command.Command;

public class Retract extends Command {

    private Stick stick;

    public Retract() {
        stick = SubsystemFactory.getInstance().getStick();
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