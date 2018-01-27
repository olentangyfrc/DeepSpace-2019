package org.usfirst.frc.team4611.robot.commands.drive;
import org.usfirst.frc.team4611.robot.Robot;
import org.usfirst.frc.team4611.robot.RobotMap;
import edu.wpi.first.wpilibj.command.Command;

public class UltraDrive extends Command{
	private int x;
	
	/**
	 * Drives forward until the ultrasonic sensor is a distance in inches from a surface
	 * @param distance the value in inches the bot drives to
	 */
	public UltraDrive(int distance){
		this.requires(Robot.tankDrive); //This command uses this subsystem
		this.x = distance;
	}
	public void execute(){
			RobotMap.driveTrain.driveCartesian(0, 0.6, 0);
	}

	
	protected boolean isFinished() {
		if( Robot.ultrasonic.getInches() < x){
			RobotMap.driveTrain.driveCartesian(0, 0, 0);
			return true;
		}
		else{
			return false;
		}
	}

}
