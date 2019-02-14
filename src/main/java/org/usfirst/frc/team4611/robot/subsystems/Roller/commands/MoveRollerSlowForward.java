package org.usfirst.frc.team4611.robot.subsystems.Roller.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team4611.robot.subsystems.SubsystemFactory;
import org.usfirst.frc.team4611.robot.subsystems.Roller.Roller;
public class MoveRollerSlowForward extends Command {

    private Roller roller; 
    private boolean stop = false;

    public MoveRollerSlowForward() {
        roller = SubsystemFactory.getInstance().getRoller();
        this.requires(roller);
    }

    @Override
    protected void initialize() {
        stop = false;
    }

    @Override
    protected void execute() {
        if(stop) 
            return;
        roller.spinRollerSlowForward();
    }

    @Override
    public synchronized void cancel() {
        roller.stopRoller();
        stop = true;
    }

    @Override
    protected boolean isFinished() {
        return stop;
    }

}