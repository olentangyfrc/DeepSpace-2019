package org.usfirst.frc.team4611.robot.subsystems.WheelIntake.commands;

import org.usfirst.frc.team4611.robot.networktables.NetTableManager;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class IntakeGroup extends CommandGroup {
    //private double stage1Speed;
    //private double stage2Speed;
    //private double stage3Speed;
    //private double stage4Speed;

    public IntakeGroup() {
        System.out.println("group");
        //stage1Speed = (double)(NetTableManager.getValue("Health Map", "Stage 1", 0.1));
        //stage2Speed = (double)(NetTableManager.getValue("Health Map", "Stage 2", 0.05));
        //stage3Speed = (double)(NetTableManager.getValue("Health Map", "Stage 3", -0.05));
        //stage4Speed = (double)(NetTableManager.getValue("Health Map", "Stage 4", 0));


        addSequential(new IntakeStage1()); //take in until switch 1 triggered 
        addSequential(new IntakeStage2()); //take in at walkspeed until switch 2 triggered
        addSequential(new IntakeStage3()); //reverse at walkspeed until swtich 1 triggered
        addSequential(new IntakeStage4()); //stop motor
    }    
}