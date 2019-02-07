package org.usfirst.frc.team4611.robot.subsystems.doublewheel.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team4611.robot.subsystems.SubsystemFactory;
import org.usfirst.frc.team4611.robot.subsystems.doublewheel.DoubleWheel;
public class StopIndiWheelBack extends Command {

    private DoubleWheel doubleWheel; 

    public StopIndiWheelBack(){
        doubleWheel = SubsystemFactory.getInstance().getDoubleWheel();
        this.requires(doubleWheel);
    }

    @Override
    protected void execute() {
        doubleWheel.stopIndiWheelBack();
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