package org.usfirst.frc.team4611.robot.subsystems.WheelIntake.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class IntakeGroup extends CommandGroup {
    public IntakeGroup() {
        addSequential(new IntakeStage(0.1, "Switch1")); //take in until switch 1 triggered 
        addSequential(new IntakeStage(0.1, "Switch2")); //take in at walkspeed until switch 2 triggered
        addSequential(new IntakeStage(-0.05, "Switch1")); //reverse at walkspeed until swtich 1 triggered
        addSequential(new IntakeStage(0, "True")); //stop motor
    }    
}