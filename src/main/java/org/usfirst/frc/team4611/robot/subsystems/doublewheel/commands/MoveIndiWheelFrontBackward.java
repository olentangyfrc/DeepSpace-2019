package org.usfirst.frc.team4611.robot.subsystems.doublewheel.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team4611.robot.subsystems.SubsystemFactory;
import org.usfirst.frc.team4611.robot.subsystems.doublewheel.DoubleWheel;
public class MoveIndiWheelFrontBackward extends Command {

    private DoubleWheel doubleWheel; 

    public MoveIndiWheelFrontBackward(){
        SubsystemFactory.getInstance().getDoubleWheel();
        this.requires(doubleWheel);
    }

    @Override
    protected void execute() {
        doubleWheel.spinIndiWheelFrontBackward();
    }

    @Override
    public synchronized void cancel() {
        doubleWheel.stopIndiWheelFront();
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

}