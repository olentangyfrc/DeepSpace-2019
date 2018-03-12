package org.usfirst.frc.team4611.robot.commands.drive;

import org.usfirst.frc.team4611.robot.RobotMap;
import org.usfirst.frc.team4611.robot.commands.auton.StopAndRepositionTalons;
import org.usfirst.frc.team4611.robot.commands.auton.Wait;
import org.usfirst.frc.team4611.robot.commands.elevator.MoveElevatorToPos;
import org.usfirst.frc.team4611.robot.commands.pigeon.PigeonAdjust2;
import org.usfirst.frc.team4611.robot.commands.pigeon.PigeonAdjustVision;
import org.usfirst.frc.team4611.robot.commands.pigeon.PigeonAdjustVision3;
import org.usfirst.frc.team4611.robot.logging.Logger;
import org.usfirst.frc.team4611.robot.potentiometer.MovePotPos;
import org.usfirst.frc.team4611.robot.subsystems.Elevator;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class FindBox extends CommandGroup {

	public FindBox() {
		//addSequential(new PigeonAdjustVision3());
		//addSequential(new StopAndRepositionTalons());
		
		//addSequential(new StopAndRepositionTalons());
		//addSequential(new VisionVerticalDrive2());
		
		//addSequential(new VisionHorizontalDrive3());
		//addSequential(new StopAndRepositionTalons());
		
		
		Logger.log("Wait", "FindBox");
		addSequential(new StopAndRepositionTalons());
		Logger.log("StopAndRepositionTalons", "FindBox");
		addSequential(new VisionHorizontalDrive3());
		Logger.log("Horizontal", "FindBox");
		addSequential(new Wait(1));
		Logger.log("Wait", "FindBox");
		addSequential(new PigeonAdjustVision());
		Logger.log("Turning", "FindBox");
		addSequential(new StopAndRepositionTalons());
		Logger.log("StopAndRepositionTalons", "FindBox");
		addSequential(new Wait(1));
		Logger.log("Wait", "FindBox");
		addSequential(new VisionVerticalDrive2());
		Logger.log("Vertical", "FindBox");
		addSequential(new StopAndRepositionTalons());
		Logger.log("StopAndRepositionTalons", "FindBox");
		addSequential(new MovePotPos(RobotMap.POTMIN));
		addSequential(new MoveElevatorToPos(Elevator.ELEVATOR_BOTTOM));
	}
}
