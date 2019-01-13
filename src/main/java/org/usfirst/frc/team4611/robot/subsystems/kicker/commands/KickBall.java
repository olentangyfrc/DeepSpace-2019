package org.usfirst.frc.team4611.robot.subsystems.kicker.commands;

import org.usfirst.frc.team4611.robot.subsystems.SubsystemFactory;
import org.usfirst.frc.team4611.robot.subsystems.kicker.Kicker;

import edu.wpi.first.wpilibj.command.Command;

public class KickBall extends Command
{
    private Kicker kicker;

    public KickBall()
    {
        kicker = SubsystemFactory.getInstance().getKicker();

    }
    
    @Override
    protected void execute() {
        kicker.kickBall();
        System.out.println("Kicking Ball");
    }


    @Override
    protected boolean isFinished() {
        return true;
    }

}