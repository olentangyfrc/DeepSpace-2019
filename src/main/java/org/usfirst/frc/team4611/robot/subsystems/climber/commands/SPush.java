package org.usfirst.frc.team4611.robot.subsystems.climber.commands;

import org.usfirst.frc.team4611.robot.subsystems.SubsystemFactory;
import org.usfirst.frc.team4611.robot.subsystems.climber.Climber;

import edu.wpi.first.wpilibj.command.Command;

public class SPush extends Command {
    
    private Climber climber;

    public SPush() {
        climber = SubsystemFactory.getInstance().getClimber();
        this.requires(climber);
    }

    @Override
    protected void execute() {
        climber.pushHatch();
    }

    @Override
    protected boolean isFinished() {
        return true;
    }

}