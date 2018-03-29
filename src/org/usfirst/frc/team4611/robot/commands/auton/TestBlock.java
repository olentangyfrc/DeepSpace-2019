package org.usfirst.frc.team4611.robot.commands.auton;

import org.usfirst.frc.team4611.robot.commands.arm.MovePotPos;
import org.usfirst.frc.team4611.robot.commands.drive.StopAndRepositionTalons;
import org.usfirst.frc.team4611.robot.commands.elevator.ResetElevator;
import org.usfirst.frc.team4611.robot.commands.solenoid.GrabBox;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class TestBlock extends CommandGroup {
	public TestBlock() {
		addSequential(new ResetElevator());
		addSequential(new GrabBox());
		addSequential(new StopAndRepositionTalons());
		addSequential(new MovePotPos(.8));
	}
}

