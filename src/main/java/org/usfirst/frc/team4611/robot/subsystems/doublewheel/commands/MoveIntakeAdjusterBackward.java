package org.usfirst.frc.team4611.robot.subsystems.doublewheel.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team4611.robot.subsystems.SubsystemFactory;
import org.usfirst.frc.team4611.robot.subsystems.doublewheel.DoubleWheel;
public class MoveIntakeAdjusterBackward extends Command {

    private DoubleWheel doubleWheel; 

    public MoveIntakeAdjusterBackward (){
        doubleWheel = SubsystemFactory.getInstance().getDoubleWheel();
        this.requires(doubleWheel);
    }

    @Override
    protected void execute() {
        doubleWheel.spinIntakeAdjusterBackward();
    }

    @Override
    public synchronized void cancel() {
        doubleWheel.stopIntakeAdjuster();
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

}