package org.usfirst.frc.team4611.robot.subsystems.sparktest.Commands;

import org.usfirst.frc.team4611.robot.subsystems.SubsystemFactory;
import org.usfirst.frc.team4611.robot.subsystems.drivetrain.interfaces.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

public class SparkMove extends Command {

    private DriveTrain st;

    protected void initialize() {
        st = SubsystemFactory.getInstance().getDriveTrain();
    }
    
    protected void execute() {
        st.move();
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

}