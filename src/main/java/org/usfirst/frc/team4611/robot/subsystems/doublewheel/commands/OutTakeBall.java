package org.usfirst.frc.team4611.robot.subsystems.doublewheel.commands;

import org.usfirst.frc.team4611.robot.subsystems.SubsystemFactory;
import org.usfirst.frc.team4611.robot.subsystems.doublewheel.DoubleWheel;

import edu.wpi.first.wpilibj.command.Command;

public class  OuttakeBall extends Command {

    private DoubleWheel doubleWheel;

    public  OuttakeBall() {
        doubleWheel = SubsystemFactory.getInstance().getDoubleWheel();
        this.requires(doubleWheel);
    }

    @Override
    protected void execute() {
        doubleWheel.spinMotorOutTake();
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

}