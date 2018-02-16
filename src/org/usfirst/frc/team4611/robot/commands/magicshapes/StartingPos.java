package org.usfirst.frc.team4611.robot.commands.magicshapes;

import org.usfirst.frc.team4611.robot.RobotMap;
import org.usfirst.frc.team4611.robot.commands.elevator.MoveElevatorToPos;
//import org.usfirst.frc.team4611.robot.potentiometer.MovePotPos;
import org.usfirst.frc.team4611.robot.potentiometer.MovePotPos;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class StartingPos extends CommandGroup {
	public StartingPos() {
		RobotMap.log(RobotMap.linearActuatorSubTable, "RUNNING COMMAND GROUP");
		addParallel(new MoveElevatorToPos(0));
		addParallel(new MovePotPos(.85));
	}
}
