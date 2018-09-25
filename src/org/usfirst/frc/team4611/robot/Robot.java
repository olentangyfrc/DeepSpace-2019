
package org.usfirst.frc.team4611.robot;

import org.usfirst.frc.team4611.robot.commands.Box;
import org.usfirst.frc.team4611.robot.networktables.NetTableManager;
import org.usfirst.frc.team4611.robot.subsystems.TalonMecanum;
import org.usfirst.frc.team4611.robot.subsystems.VictorMecanum;
import org.usfirst.frc.team4611.robot.subsystems.baseclasses.MecanumBase;
import org.usfirst.frc.team4611.robot.subsystems.sensors.pigeon.Pigeon;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;

public class Robot extends IterativeRobot {

	public String motorControllerType = "t";
	Command autonomousCommand;
	SendableChooser<Command> chooser = new SendableChooser<>();
	public static MecanumBase mecanum;
	public static OI oi;
	
	private Pigeon pigeon;
	
	@Override
	public void robotInit() {
		NetTableManager.startNetworkTables();
		
		//Initialize the sensor classes
		pigeon = new Pigeon(21);
		
		//Initialize the subsystems
		if(motorControllerType.toLowerCase().equals("t")) {
			mecanum = new TalonMecanum(pigeon);
		}
		else {
			mecanum = new VictorMecanum();
		}
		oi = new OI();
		
	}

	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void autonomousInit() {
		autonomousCommand = new Box();

		if (autonomousCommand != null)
			autonomousCommand.start();
	}

	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (autonomousCommand != null)
			autonomousCommand.cancel();
	}

	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}


	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}
}
