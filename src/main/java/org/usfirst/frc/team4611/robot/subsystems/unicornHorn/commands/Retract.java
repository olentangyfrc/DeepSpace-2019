package org.usfirst.frc.team4611.robot.subsystems.unicornHorn.commands;

import org.usfirst.frc.team4611.robot.subsystems.SubsystemFactory;
import org.usfirst.frc.team4611.robot.subsystems.unicornHorn.UnicornHorn;

import edu.wpi.first.wpilibj.command.Command;

public class Retract extends Command {

    private UnicornHorn unicornHorn;

    public Retract() {
        unicornHorn = SubsystemFactory.getInstance().getUnicornHorn();
        this.requires(unicornHorn);
    }

    @Override
    protected void execute() {
        unicornHorn.retractPistons();
    }

    @Override
    protected boolean isFinished() {
        return true;
    }




}