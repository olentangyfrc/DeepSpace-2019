package org.usfirst.frc.team4611.robot.subsystems.elevator.commands;

import org.usfirst.frc.team4611.robot.subsystems.SubsystemFactory;
import org.usfirst.frc.team4611.robot.subsystems.elevator.Elevator;

import edu.wpi.first.wpilibj.command.Command;

public class MoveElevatorToPos extends Command {

    private Elevator elevator;
    private boolean stop = false;;

    //MUST SEND A NUMBER FROM 0 TO 1
    public MoveElevatorToPos(){
        elevator = SubsystemFactory.getInstance().getElevator();
        
        this.requires(elevator);
    }

    protected void execute() {
        stop = elevator.moveToPos1();
    }

    @Override
    protected boolean isFinished() {
        return stop;
    }

    protected void initialize() {
        
    }

   
}