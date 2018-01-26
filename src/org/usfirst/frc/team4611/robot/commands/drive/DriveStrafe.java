package org.usfirst.frc.team4611.robot.commands.drive;

import org.usfirst.frc.team4611.robot.Robot;
import org.usfirst.frc.team4611.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class DriveStrafe extends Command{
	private int positionUnits;
	private int cpr;

	public DriveStrafe(double rotations) {
		this.requires(Robot.tankDrive);
		cpr = 1440;
		positionUnits = (int) Math.round(rotations * cpr);
	}
	
	protected void initialize() {
		RobotMap.driveTrainBL_Talon.setSelectedSensorPosition(0, 0, 0);
		RobotMap.driveTrainBR_Talon.setSelectedSensorPosition(0, 0, 0);
		RobotMap.driveTrainFR_Talon.setSelectedSensorPosition(0, 0, 0);
		RobotMap.driveTrainFL_Talon.setSelectedSensorPosition(0, 0, 0);
	}
	
	protected void execute () {
		System.out.println("Target Pos: " + positionUnits + " Current Position(BL): " + RobotMap.driveTrainBL_Talon.getSelectedSensorPosition(0));
		Robot.tankDrive.motionMagicStrafe(positionUnits);
	}
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}
