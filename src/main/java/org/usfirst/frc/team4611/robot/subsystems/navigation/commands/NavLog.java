package org.usfirst.frc.team4611.robot.subsystems.navigation.commands;

import org.usfirst.frc.team4611.robot.subsystems.SubsystemFactory;
import org.usfirst.frc.team4611.robot.subsystems.navigation.Navigation;

import edu.wpi.first.wpilibj.command.Command;

public class NavLog extends Command {


    private Navigation navigation;


    public NavLog(){
        navigation = SubsystemFactory.getInstance().getNavigation();
    }

    @Override
    protected void execute(){
        navigation.log();
        navigation.writeToShuffleBoard();
    }

    @Override
    protected boolean isFinished() {
        return false;
    }



}