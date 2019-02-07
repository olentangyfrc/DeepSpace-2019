package org.usfirst.frc.team4611.robot.subsystems.doublewheel.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team4611.robot.subsystems.SubsystemFactory;
import org.usfirst.frc.team4611.robot.subsystems.doublewheel.DoubleWheel;
public class StopIntakeAdjuster extends Command {

    private DoubleWheel doubleWheel; 

    public StopIntakeAdjuster(){
        doubleWheel = SubsystemFactory.getInstance().getDoubleWheel();
        this.requires(doubleWheel);
    }

    @Override
    protected void execute() {
        doubleWheel.stopIntakeAdjuster();
    }

    @Override
    public synchronized void cancel() {
        doubleWheel.stopIntakeAdjuster();
    }

    @Override
    protected boolean isFinished() {
        return true;
    }

}