package org.usfirst.frc.team4611.robot.subsystems.WheelIntake.commands;

import org.usfirst.frc.team4611.robot.networktables.NetTableManager;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class TopLoader extends CommandGroup {
    public TopLoader() {
        addSequential(new IntakeStage(-0.1, "Switch2"));
        addSequential(new IntakeStage(-0.05, "Switch1"));
        addSequential(new IntakeStage(0, "True"));
    }    
}