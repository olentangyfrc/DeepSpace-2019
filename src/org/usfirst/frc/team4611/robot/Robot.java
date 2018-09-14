
package org.usfirst.frc.team4611.robot;

import java.util.HashMap;

import org.usfirst.frc.team4611.robot.subsytems.iMecanum;
import org.usfirst.frc.team4611.robot.subsytems.tMecanum;
import org.usfirst.frc.team4611.robot.subsytems.vMecanum;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;

public class Robot extends IterativeRobot {

	public String motorControllerType;
	Command autonomousCommand;
	SendableChooser<Command> chooser = new SendableChooser<>();
	public static iMecanum mecanum;
	public static OI oi;
	
	@Override
	public void robotInit() {
		//Initialize the subsystems
		if(motorControllerType.toLowerCase().equals("t")) {
			mecanum = new tMecanum();
		}
		else {
			mecanum = new vMecanum();
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
		autonomousCommand = chooser.getSelected();

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
