package org.usfirst.frc.team4611.robot.subsystems.WheelIntake.Commands;

import org.usfirst.frc.team4611.robot.subsystems.SubsystemFactory;
import org.usfirst.frc.team4611.robot.subsystems.WheelIntake.WheelIntake;

import edu.wpi.first.wpilibj.command.Command;

public class SpinIntakeWheel extends Command {

    private WheelIntake wheelIntake;

    public SpinIntakeWheel() {
        SubsystemFactory.getInstance().getWheelIntake();
    }

    @Override
    protected void execute() {
        wheelIntake.moveIntake();
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
    
}