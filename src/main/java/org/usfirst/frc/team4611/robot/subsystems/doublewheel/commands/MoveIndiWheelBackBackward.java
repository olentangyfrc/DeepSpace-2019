package org.usfirst.frc.team4611.robot.subsystems.doublewheel.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team4611.robot.subsystems.SubsystemFactory;
import org.usfirst.frc.team4611.robot.subsystems.doublewheel.DoubleWheel;
public class MoveIndiWheelBackBackward extends Command {

    private DoubleWheel doubleWheel; 

    public MoveIndiWheelBackBackward(){
        SubsystemFactory.getInstance().getDoubleWheel();
        this.requires(doubleWheel);
    }

    @Override
    protected void execute() {
        doubleWheel.spinIndiWheelBackBackward();
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