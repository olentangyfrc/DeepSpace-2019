package org.usfirst.frc.team4611.robot.subsystems.elevator.commands;

import java.util.logging.Logger;

import org.usfirst.frc.team4611.robot.subsystems.SubsystemFactory;
import org.usfirst.frc.team4611.robot.subsystems.elevator.Elevator;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;

public class MoveElevatorToLevel extends Command {

    private final Logger logger = Logger.getLogger(MoveElevatorToLevel.class.getName());

    private Elevator elevator;
    private boolean stop = false;
    private Elevator.HappyPosition lvl;

    public MoveElevatorToLevel(Elevator.HappyPosition level){
        elevator = SubsystemFactory.getInstance().getElevator();
        this.requires(elevator);
        lvl = level;
    }

    protected void execute() {

        if(lvl == Elevator.HappyPosition.LEVEL_1) {
            stop = elevator.moveToLevel(Elevator.HappyPosition.LEVEL_1);
        } else if(lvl == Elevator.HappyPosition.LEVEL_2) {
            stop = elevator.moveToLevel(Elevator.HappyPosition.LEVEL_2);
        } else if(lvl == Elevator.HappyPosition.LEVEL_3) {
            stop = elevator.moveToLevel(Elevator.HappyPosition.LEVEL_3);
        } else if(lvl == Elevator.HappyPosition.LEVEL_4) {
            stop = elevator.moveToLevel(Elevator.HappyPosition.LEVEL_4);
        } else if(lvl == Elevator.HappyPosition.LEVEL_5) {
            stop = elevator.moveToLevel(Elevator.HappyPosition.LEVEL_5);
        } else if(lvl == Elevator.HappyPosition.LEVEL_6) {
            stop = elevator.moveToLevel(Elevator.HappyPosition.LEVEL_6);
        } else if(lvl == Elevator.HappyPosition.LEVEL_7) {
            stop = elevator.moveToLevel(Elevator.HappyPosition.LEVEL_7);
        } else if(lvl == Elevator.HappyPosition.LEVEL_8) {
            stop = elevator.moveToLevel(Elevator.HappyPosition.LEVEL_8);
        } else if(lvl == Elevator.HappyPosition.BOTTOM) {
            stop = elevator.moveToLevel(Elevator.HappyPosition.BOTTOM);
        } else {
            stop = true;
        }
    }

    @Override
    protected boolean isFinished() {
        return stop;
    }

    protected void initialize() {
        
    }

    @Override
    public synchronized void cancel() {
        elevator.stop();
        stop = true;
    }

    @Override
    protected void interrupted() {
        elevator.stop();
        stop = true;
    }

    @Override
    protected void end() {
        elevator.stop();
    }
}