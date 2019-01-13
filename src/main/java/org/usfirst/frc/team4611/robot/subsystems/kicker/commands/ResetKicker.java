package org.usfirst.frc.team4611.robot.subsystems.kicker.commands;

import org.usfirst.frc.team4611.robot.subsystems.SubsystemFactory;
import org.usfirst.frc.team4611.robot.subsystems.kicker.Kicker;

import edu.wpi.first.wpilibj.command.Command;

public class ResetKicker extends Command
{
    private Kicker kicker;

    public ResetKicker()
    {
        kicker = SubsystemFactory.getInstance().getKicker();
    }
    
    @Override
    protected void execute() {
        kicker.reset();
        System.out.println("Resetting");
    }


    @Override
    protected boolean isFinished() {
        return true;
    }

}