package org.usfirst.frc.team4611.robot.subsystems.drivetrain.commands;

import org.usfirst.frc.team4611.robot.subsystems.SubsystemFactory;
import org.usfirst.frc.team4611.robot.subsystems.drivetrain.interfaces.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

public class DeactivateTurbo extends Command {

    private DriveTrain driveTrain;
    
    public DeactivateTurbo(){
        driveTrain = SubsystemFactory.getInstance().getDriveTrain();
    }
    protected void initialize() {

    }

    protected void execute() {
        driveTrain.deactivateTurbo();
    }  

    protected boolean isFinished() {
        return true;
    }

}