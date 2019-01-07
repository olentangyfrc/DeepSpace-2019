package org.usfirst.frc.team4611.robot.subsystems.navigation;

import org.usfirst.frc.team4611.robot.subsystems.navigation.commands.NavLog;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Navigation extends Subsystem {

    
    public Navigation(){

    }

    protected void initDefaultCommand() {
        this.setDefaultCommand(new NavLog());
    }



}