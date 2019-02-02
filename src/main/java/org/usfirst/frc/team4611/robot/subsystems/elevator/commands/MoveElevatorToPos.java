package org.usfirst.frc.team4611.robot.subsystems.elevator.commands;

import org.usfirst.frc.team4611.robot.subsystems.SubsystemFactory;
import org.usfirst.frc.team4611.robot.subsystems.elevator.Elevator;

import edu.wpi.first.wpilibj.command.Command;

public class MoveElevatorToPos extends Command {

    private Elevator elevator;
    private double pos;

    //MUST SEND A NUMBER FROM 0 TO 1
    public MoveElevatorToPos(double pos){
        elevator = SubsystemFactory.getInstance().getElevator();
        this.pos = pos;
        
        this.requires(elevator);
    }

    protected void execute() {
        elevator.moveToPos(pos);
    }

    @Override
    protected boolean isFinished() {
        return true;
    }

    protected void initialize() {
        
    }

   
}