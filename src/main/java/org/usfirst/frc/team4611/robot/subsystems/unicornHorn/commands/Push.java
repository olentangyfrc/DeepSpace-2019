package org.usfirst.frc.team4611.robot.subsystems.unicornHorn.commands;

import org.usfirst.frc.team4611.robot.subsystems.SubsystemFactory;
import org.usfirst.frc.team4611.robot.subsystems.unicornHorn.UnicornHorn;

import edu.wpi.first.wpilibj.command.Command;

public class Push extends Command {
    
    private UnicornHorn unicornHorn;
    private long startTime = 0;
    private boolean stop = false;;

    public Push() {
        unicornHorn = SubsystemFactory.getInstance().getUnicornHorn();
        this.requires(unicornHorn);
    }

    protected void initialize() {
        startTime = 0;
        stop = false;
    }

    @Override
    protected void execute() {
        if(startTime == 0) {
            startTime = System.currentTimeMillis();
        }

        if(System.currentTimeMillis() > (3000 + startTime)) {
            unicornHorn.retractPistons();
            stop = true;
        } else {
            unicornHorn.pushHatch();
        }
    }

    @Override
    protected boolean isFinished() {
        return stop;
    }

}