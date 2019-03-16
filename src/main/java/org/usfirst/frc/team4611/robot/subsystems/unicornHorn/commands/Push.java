package org.usfirst.frc.team4611.robot.subsystems.unicornHorn.commands;

import org.usfirst.frc.team4611.robot.subsystems.SubsystemFactory;
import org.usfirst.frc.team4611.robot.subsystems.unicornHorn.UnicornHorn;

import edu.wpi.first.wpilibj.command.Command;

public class Push extends Command {
    
    private UnicornHorn unicornHorn;

    public Push() {
        unicornHorn = SubsystemFactory.getInstance().getUnicornHorn();
        this.requires(unicornHorn);
    }

    @Override
    protected void execute() {
        unicornHorn.pushHatch();
    }

    @Override
    protected boolean isFinished() {
        return true;
    }

}