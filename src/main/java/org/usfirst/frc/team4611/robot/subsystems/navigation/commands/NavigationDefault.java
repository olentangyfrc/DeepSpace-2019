package org.usfirst.frc.team4611.robot.subsystems.navigation.commands;

import org.usfirst.frc.team4611.robot.subsystems.SubsystemFactory;
import org.usfirst.frc.team4611.robot.subsystems.navigation.Navigation;

import edu.wpi.first.wpilibj.command.Command;

public class NavigationDefault extends Command {


    private Navigation navigation;


    public NavigationDefault(){
        navigation = SubsystemFactory.getInstance().getNavigation();
        requires(navigation);
    }

    @Override
    protected void execute(){
        navigation.checkValues();
    }

    @Override
    protected boolean isFinished() {
        return false;
    }



}