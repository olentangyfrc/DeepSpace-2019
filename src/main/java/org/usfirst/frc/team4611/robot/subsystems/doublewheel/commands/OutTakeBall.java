package org.usfirst.frc.team4611.robot.subsystems.doublewheel.commands;

import org.usfirst.frc.team4611.robot.subsystems.SubsystemFactory;
import org.usfirst.frc.team4611.robot.subsystems.doublewheel.DoubleWheel;

import edu.wpi.first.wpilibj.command.Command;

public class  OutTakeBall extends Command {

    private DoubleWheel doubleWheel;

    public  OutTakeBall() {
        doubleWheel = SubsystemFactory.getInstance().getDoubleWheel();
    }

    @Override
    protected void execute() {
        doubleWheel.spinMotors(-.5);
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

}