package org.usfirst.frc.team4611.robot.commands.drive;

import org.usfirst.frc.team4611.robot.Robot;
import org.usfirst.frc.team4611.robot.RobotMap;
import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.command.Command;

public class PositionDrive extends Command{
	
	private double position;
	
	private int currentBL;
	private int currentBR;
	private int currentFL;
	private int currentFR;
	private int cnt;
	
	private int factorBL;
	private int factorBR;
	
	public PositionDrive(double pos, String dir){
		this.requires(Robot.mecanum); //This command uses this subsystem
		this.position = (int) (pos / 1.5 * 1440); //Conversion factor from feet to position units
		this.cnt = 0;
		this.currentBL = RobotMap.driveTrainBL_Talon.getSelectedSensorPosition(0);
		this.currentBR = RobotMap.driveTrainBR_Talon.getSelectedSensorPosition(0);
		this.currentFL = RobotMap.driveTrainFL_Talon.getSelectedSensorPosition(0);
		this.currentFR = RobotMap.driveTrainFR_Talon.getSelectedSensorPosition(0);
		

		if (dir.toLowerCase().equals("forward")) {
			this.factorBL = -1;
			this.factorBR = 1;
		} else if (dir.toLowerCase().equals("backward")) {
			this.factorBL = 1;
			this.factorBR = -1;
		} else if (dir.toLowerCase().equals("left")) {
			this.factorBL = -1;
			this.factorBR = -1;
		} else if (dir.toLowerCase().equals("right")){
			this.factorBL = 1;
			this.factorBR = 1;
		} else //did not send a normal direction
			System.out.println("ERROR! BAD DIRECTION VALUE! Dir: " + dir);
	}
	
	protected void initialize() {
		RobotMap.driveTrainBL_Talon.setSelectedSensorPosition(0, 0, 0);
		RobotMap.driveTrainBR_Talon.setSelectedSensorPosition(0, 0, 0);
		RobotMap.driveTrainFL_Talon.setSelectedSensorPosition(0, 0, 0);
		RobotMap.driveTrainFR_Talon.setSelectedSensorPosition(0, 0, 0);	
	}
	
	protected void execute() {
		System.out.println("EXECUTE EXECUTE EXECUTE EXECUTE EXECUTE EXECUTE EXECUTE ");
		//this.position = (int)((double)RobotMap.networkManager.getValue(RobotMap.mecanumSubTable, RobotMap.positionDistanceID))/1.5*1440;
		cnt++;
		
		RobotMap.driveTrainBL_Talon.set(ControlMode.MotionMagic, (factorBL * position + currentBL));
		RobotMap.driveTrainBR_Talon.set(ControlMode.MotionMagic, (factorBR * position + currentBR));
		RobotMap.driveTrainFL_Talon.set(ControlMode.MotionMagic, (-factorBR * position + currentFL));
		RobotMap.driveTrainFR_Talon.set(ControlMode.MotionMagic, (-factorBL * position + currentFR));
	}
	
	
	protected void end() {
		
	}
	
	protected boolean isFinished() {
		if (RobotMap.driveTrainBL_Talon.getSelectedSensorVelocity(0) == 0 && cnt > 100) {
			cnt = 0;
			return true;
		} else {
			return false;
		}
	}

	

}
