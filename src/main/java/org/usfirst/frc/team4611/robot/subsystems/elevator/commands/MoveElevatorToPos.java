package org.usfirst.frc.team4611.robot.subsystems.elevator.commands;

import java.util.logging.Logger;

import org.usfirst.frc.team4611.robot.subsystems.SubsystemFactory;
import org.usfirst.frc.team4611.robot.subsystems.elevator.Elevator;

import edu.wpi.first.wpilibj.command.Command;

public class MoveElevatorToPos extends Command {

    private final Logger logger = Logger.getLogger(MoveElevatorToPos.class.getName());

    private Elevator elevator;
    private boolean stop = false;
    private int lvl;


    //MUST SEND A NUMBER FROM 0 TO 7
    //We use this to decide what level to move the elvator to
    public MoveElevatorToPos(int level){
        elevator = SubsystemFactory.getInstance().getElevator();
        lvl = level;
        this.requires(elevator);
    }

    protected void execute() {
        if(lvl == 1) {
            stop = elevator.moveToPos1();
        } 
        else if(lvl == 2) {
            stop = elevator.moveToPos2();
        }
        else if(lvl == 3) {
            stop = elevator.moveToPos3();
        }
        else if(lvl == 4) {
            stop = elevator.moveToPos4();
        }
        else if(lvl == 5) {
            stop = elevator.moveToPos5();
        }
        else if(lvl == 6) {
            stop = elevator.moveToPos6();
        }
        else if(lvl == 7) {
            stop = elevator.moveToPos7();
        }
        else {
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
        if(elevator.isLogging())
            logger.info("cancel");
    }

    @Override
    protected void interrupted() {
        if(elevator.isLogging())
            logger.info("interrupted");
    }

   
}