package org.usfirst.frc.team4611.robot.subsystems.navigation.commands;

import java.util.logging.Logger;

import org.usfirst.frc.team4611.robot.subsystems.SubsystemFactory;
import org.usfirst.frc.team4611.robot.subsystems.navigation.Navigation;

import edu.wpi.first.wpilibj.command.Command;

public class NavigationDefault extends Command {

    private Logger logger = Logger.getLogger(NavigationDefault.class.getName());

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

    @Override
    public synchronized void cancel() {
        logger.info("canceled");
    }
    @Override
    protected void interrupted() {
        logger.info("interrupted");
    }   

}