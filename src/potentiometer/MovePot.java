package potentiometer;

import org.usfirst.frc.team4611.robot.OI;
import org.usfirst.frc.team4611.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class MovePot extends Command{
	
	private double speed;
	
	public MovePot(double speed) {
		// TODO Auto-generated constructor stub
		this.speed = speed;
	}

	protected void execute() {
		//System.out.println("LA speed: " + -speed);
		if(speed > 0.0)
			System.out.println("FORWARD");
		else
			System.out.println("BACKWARD");
		
		RobotMap.linearActuator.set(speed);
		
	}
	
	
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}
