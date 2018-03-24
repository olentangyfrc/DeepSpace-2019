package org.usfirst.frc.team4611.robot.commands.auton;

import java.util.HashMap;

import org.usfirst.frc.team4611.robot.commands.auton.dualTargets.StartLeftLeftSwitchLeftScale;
import org.usfirst.frc.team4611.robot.commands.auton.dualTargets.StartLeftLeftSwitchRightScale;
import org.usfirst.frc.team4611.robot.commands.auton.dualTargets.StartLeftScaleLeftScaleLeft;
import org.usfirst.frc.team4611.robot.commands.auton.dualTargets.StartRightRightSwitchLeftScale;
import org.usfirst.frc.team4611.robot.commands.auton.dualTargets.StartRightRightSwitchRightScale;
import org.usfirst.frc.team4611.robot.commands.auton.dualTargets.StartRightScaleRightScaleRight;
import org.usfirst.frc.team4611.robot.commands.auton.singleTargets.StartCenterSwitchLeft;
import org.usfirst.frc.team4611.robot.commands.auton.singleTargets.StartCenterSwitchRight;
import org.usfirst.frc.team4611.robot.commands.auton.singleTargets.StartLeftScaleLeft;
import org.usfirst.frc.team4611.robot.commands.auton.singleTargets.StartLeftScaleRight;
import org.usfirst.frc.team4611.robot.commands.auton.singleTargets.StartLeftSwitchLeft;
import org.usfirst.frc.team4611.robot.commands.auton.singleTargets.StartLeftSwitchRight;
import org.usfirst.frc.team4611.robot.commands.auton.singleTargets.StartRightScaleLeft;
import org.usfirst.frc.team4611.robot.commands.auton.singleTargets.StartRightScaleRight;
import org.usfirst.frc.team4611.robot.commands.auton.singleTargets.StartRightSwitchLeft;
import org.usfirst.frc.team4611.robot.commands.auton.singleTargets.StartRightSwitchRight;

import edu.wpi.first.wpilibj.command.Command;

public class AutonCommandGroup <K, V> extends HashMap<String, Command> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AutonCommandGroup() {
		put("RRSWRSC", new StartRightRightSwitchRightScale());
		put("RRSWLSC", new StartRightRightSwitchLeftScale());
		put("RRSWRSW", new StartRightRightSwitchRightScale());
		put("RRSCRSC", new StartRightScaleRightScaleRight());
		put("LLSCLSC", new StartLeftScaleLeftScaleLeft());
		put("LLSWLSC", new StartLeftLeftSwitchLeftScale());
		put("LLSWRSC", new StartLeftLeftSwitchRightScale());
		
		
		
		put("TTRRR", new TestBlock());
		
		//Never go for scale in auton center
		put("DRIVEFORWARD", new JustDriveForward());
	}

}
