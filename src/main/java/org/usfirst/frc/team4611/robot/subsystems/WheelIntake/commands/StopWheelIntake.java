package org.usfirst.frc.team4611.robot.subsystems.WheelIntake.commands;

import org.usfirst.frc.team4611.robot.subsystems.SubsystemFactory;
import org.usfirst.frc.team4611.robot.subsystems.WheelIntake.WheelIntake;

import edu.wpi.first.wpilibj.command.Command;

public class StopWheelIntake extends Command {

    private WheelIntake wheelIntake;

    public StopWheelIntake() {
        wheelIntake = SubsystemFactory.getInstance().getWheelIntake();
    }

    protected void initialize() {
    }

    @Override
    protected void execute() {
        wheelIntake.stopIntakeWheel();
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
    
}