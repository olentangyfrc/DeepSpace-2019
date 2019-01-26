package org.usfirst.frc.team4611.robot.subsystems.sparktest.Commands;

import org.usfirst.frc.team4611.robot.subsystems.SubsystemFactory;
import org.usfirst.frc.team4611.robot.subsystems.sparktest.sparktest;

import edu.wpi.first.wpilibj.command.Command;

public class SparkMove extends Command {

    private sparktest st;

    protected void initialize() {
        st = SubsystemFactory.getInstance().getSparkTest();
    }
    
    protected void execute() {
        st.move();
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

}