package org.usfirst.frc.team4611.robot.subsystems.climber.commands;

import org.usfirst.frc.team4611.robot.subsystems.SubsystemFactory;
import org.usfirst.frc.team4611.robot.subsystems.climber.Climber;

import edu.wpi.first.wpilibj.command.Command;

public class SRetract extends Command {

    private Climber stick;

    public SRetract() {
        stick = SubsystemFactory.getInstance().getClimber();
        this.requires(stick);
    }

    @Override
    protected void execute() {
        stick.retractPistons();
    }

    @Override
    protected boolean isFinished() {
        return true;
    }




}