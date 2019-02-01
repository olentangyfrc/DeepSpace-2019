package org.usfirst.frc.team4611.robot.subsystems.elevator.commands;

import org.usfirst.frc.team4611.robot.subsystems.SubsystemFactory;
import org.usfirst.frc.team4611.robot.subsystems.elevator.Elevator;

import edu.wpi.first.wpilibj.command.Command;

public class MoveElevatorToPos extends Command {

    private Elevator elevator;
    private int pos;

    public MoveElevatorToPos(int pos){
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