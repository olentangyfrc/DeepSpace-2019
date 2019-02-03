package org.usfirst.frc.team4611.robot.subsystems.doublewheel.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team4611.robot.subsystems.SubsystemFactory;
import org.usfirst.frc.team4611.robot.subsystems.doublewheel.DoubleWheel;
public class MoveIndiWheelBackForward extends Command {

    private DoubleWheel doubleWheel; 

    public MoveIndiWheelBackForward (){
        SubsystemFactory.getInstance().getDoubleWheel();
        this.requires(doubleWheel);
    }

    @Override
    protected void execute() {
        doubleWheel.spinIndiWheelBackForward();
    }

    @Override
    public synchronized void cancel() {
        doubleWheel.stopIndiWheelBack();
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

}