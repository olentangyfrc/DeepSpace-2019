package org.usfirst.frc.team4611.robot.commands.auton.blocks;

import org.usfirst.frc.team4611.robot.Robot;
import org.usfirst.frc.team4611.robot.commands.auton.RoombaRepeat;
import org.usfirst.frc.team4611.robot.commands.auton.StopAndRepositionTalons;
import org.usfirst.frc.team4611.robot.commands.auton.drive.DriveUntilDistance;
import org.usfirst.frc.team4611.robot.commands.auton.pigeon.TurnRight;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class Roomba extends CommandGroup{

	public Roomba() {
			this.addSequential(new DriveUntilDistance(Robot.opt, 40, 0.5));
			this.addSequential(new TurnRight(180));
			Robot.opt.update();
			this.addSequential(new RoombaRepeat(this));
	}
}
