package org.usfirst.frc.team4611.robot.subsystems.Roller.commands;

import edu.wpi.first.wpilibj.command.Command;

import java.util.logging.Logger;

import org.usfirst.frc.team4611.robot.subsystems.SubsystemFactory;
import org.usfirst.frc.team4611.robot.subsystems.Roller.Roller;
public class MoveRollerBackward extends Command {


    private static Logger logger = Logger.getLogger(MoveRollerBackward.class.getName());
    private Roller roller; 
    private boolean stop = false;

    public MoveRollerBackward(){
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
        roller.spinRollerBackward();
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