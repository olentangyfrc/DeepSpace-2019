package org.usfirst.frc.team4611.robot.subsystems.elevator.commands;

import org.usfirst.frc.team4611.robot.subsystems.SubsystemFactory;
import org.usfirst.frc.team4611.robot.subsystems.elevator.Elevator;

import edu.wpi.first.wpilibj.command.Command;

public class MoveElevator1 extends Command {

    private Elevator elevator;

    public MoveElevator1(){
        elevator = SubsystemFactory.getInstance().getElevator();
        this.requires(elevator);
    }

    @Override
    protected boolean isFinished() {
        //NOT DONE
        return true;
    }

    protected void initialize() {
        
    }

    protected void execute() {
        elevator.moveToPos(10);
    }

}