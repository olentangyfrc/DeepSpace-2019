package potentiometer;

import org.usfirst.frc.team4611.robot.OI;
import org.usfirst.frc.team4611.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class MovePot extends Command{
	
	private static double speed;
	private static boolean isHeld;
	
	public MovePot() {
		// TODO Auto-generated constructor stub
	}

	protected void execute() {
		
		speed = OI.leftJoy.getZ();
		System.out.println("LA speed: " + -speed);
		RobotMap.linearActuator.set(-speed);
		
	}
	
	public void doneHolding() {
		isHeld = false;
	}
	
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return !isHeld;
	}

}
