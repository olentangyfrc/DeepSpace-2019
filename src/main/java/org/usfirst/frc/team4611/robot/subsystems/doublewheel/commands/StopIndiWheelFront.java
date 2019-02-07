package org.usfirst.frc.team4611.robot.subsystems.doublewheel.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team4611.robot.subsystems.SubsystemFactory;
import org.usfirst.frc.team4611.robot.subsystems.doublewheel.DoubleWheel;
public class StopIndiWheelFront extends Command {

    private DoubleWheel doubleWheel; 

    public StopIndiWheelFront(){
        doubleWheel = SubsystemFactory.getInstance().getDoubleWheel();
        this.requires(doubleWheel);
    }

    @Override
    protected void execute() {
        doubleWheel.stopIndiWheelFront();
    }

    @Override
    public synchronized void cancel() {
        doubleWheel.stopIndiWheelBack();
    }

    @Override
    protected boolean isFinished() {
        return true;
    }

}