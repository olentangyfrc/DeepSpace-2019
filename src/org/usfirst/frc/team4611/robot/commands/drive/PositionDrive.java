package org.usfirst.frc.team4611.robot.commands.drive;

import org.usfirst.frc.team4611.robot.Robot;
import org.usfirst.frc.team4611.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class PositionDrive extends Command{
	private boolean startedMoving;
	
	private double position;
	
	private int currentBL;
	private int currentBR;
	private int currentFL;
	private int currentFR;
	private int cnt;
	
	private int factorBL;
	private int factorBR;
	private String dir;
	
	public PositionDrive(double pos, String dir){
		this.requires(Robot.mecanum); //This command uses this subsystem
		this.dir = dir;
		this.cnt = 0;
		this.currentBL = RobotMap.driveTrainBL_Talon.getSelectedSensorPosition(0);
		this.currentBR = RobotMap.driveTrainBR_Talon.getSelectedSensorPosition(0);
		this.currentFL = RobotMap.driveTrainFL_Talon.getSelectedSensorPosition(0);
		this.currentFR = RobotMap.driveTrainFR_Talon.getSelectedSensorPosition(0);
		

		if (dir.toLowerCase().equals("forward")) {
			this.position = (int) (pos / 1.5 * 1440);//Conversion factor from feet to position units
			this.factorBL = 1;
			this.factorBR = -1;
		} else if (dir.toLowerCase().equals("backward")) {
			this.position = (int) (pos / 1.5 * 1440);//Conversion factor from feet to position units
			this.factorBL = -1;
			this.factorBR = 1;
		} else if (dir.toLowerCase().equals("left")) {
			this.position = (int) (pos / 1.125 * 1440);//Conversion factor from feet to position units
			this.factorBL = 1;
			this.factorBR = 1;
		} else if (dir.toLowerCase().equals("right")){
			this.position = (int) (pos / 1.125 * 1440);//Conversion factor from feet to position units
			this.factorBL = -1;
			this.factorBR = -1;
		} else //did not send a normal direction
			System.out.println("ERROR! BAD DIRECTION VALUE! Dir: " + dir);
	}
	
	protected void initialize() {
		cnt = 0;
		startedMoving = false;
		RobotMap.driveTrainFL_Talon.config_kP(0, 3, 0);
		RobotMap.driveTrainFR_Talon.config_kP(0, 3, 0);
		RobotMap.driveTrainBL_Talon.config_kP(0, 3, 0);
		RobotMap.driveTrainBR_Talon.config_kP(0, 3, 0);
	}
	
	protected void execute() {
		cnt++;
		
		if (dir.toLowerCase().equals("forward") || (dir.toLowerCase().equals("backward"))) {
			Robot.mecanum.motionMagicStraight((factorBL * position + currentBL));
		}
		
		else {
			Robot.mecanum.motionMagicStrafe(factorBL * position + currentBL);
		}
	}
	
	
	protected void end() {
		RobotMap.driveTrainBL_Talon.setSelectedSensorPosition(0, 0, 0);
		RobotMap.driveTrainBR_Talon.setSelectedSensorPosition(0, 0, 0);
		RobotMap.driveTrainFL_Talon.setSelectedSensorPosition(0, 0, 0);
		RobotMap.driveTrainFR_Talon.setSelectedSensorPosition(0, 0, 0);
		
		RobotMap.driveTrainFL_Talon.config_kP(0, .65, 0);
		RobotMap.driveTrainFR_Talon.config_kP(0, .65, 0);
		RobotMap.driveTrainBL_Talon.config_kP(0, .65, 0);
		RobotMap.driveTrainBR_Talon.config_kP(0, .65, 0);
	}
	
	protected boolean isFinished() {
		int speedSum = Math.abs(RobotMap.driveTrainBL_Talon.getSelectedSensorVelocity(0)) + Math.abs(RobotMap.driveTrainBR_Talon.getSelectedSensorVelocity(0))
			+ Math.abs(RobotMap.driveTrainFL_Talon.getSelectedSensorVelocity(0)) + Math.abs(RobotMap.driveTrainFR_Talon.getSelectedSensorVelocity(0));
		if(speedSum > 0) {
			startedMoving = true;
		}
		if ( speedSum == 0 && startedMoving) {
			return true;
		} else {
			return false;
		}
	}
	
	protected void cancelled() {
		end();
	}
}
