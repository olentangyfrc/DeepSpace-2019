package org.usfirst.frc.team4611.robot.commands.auton;

import java.util.HashMap;

import org.usfirst.frc.team4611.robot.commands.auton.dualTargets.CenterLeftReset;
import org.usfirst.frc.team4611.robot.commands.auton.dualTargets.CenterRightReset;
import org.usfirst.frc.team4611.robot.commands.auton.dualTargets.StartLeftLeftSwitchLeftScale;
import org.usfirst.frc.team4611.robot.commands.auton.dualTargets.StartLeftLeftSwitchRightScale;
import org.usfirst.frc.team4611.robot.commands.auton.dualTargets.StartLeftRightSwitchRightScale;
import org.usfirst.frc.team4611.robot.commands.auton.dualTargets.StartLeftScaleLeftScaleLeft;
import org.usfirst.frc.team4611.robot.commands.auton.dualTargets.StartLeftScaleRightScaleRight;
import org.usfirst.frc.team4611.robot.commands.auton.dualTargets.StartRightLeftSwitchLeftScale;
import org.usfirst.frc.team4611.robot.commands.auton.dualTargets.StartRightRightSwitchLeftScale;
import org.usfirst.frc.team4611.robot.commands.auton.dualTargets.StartRightRightSwitchRightScale;
import org.usfirst.frc.team4611.robot.commands.auton.dualTargets.StartRightScaleLeftScaleLeft;
import org.usfirst.frc.team4611.robot.commands.auton.dualTargets.StartRightScaleRightScaleRight;

import edu.wpi.first.wpilibj.command.Command;

public class AutonCommandGroup <K, V> extends HashMap<String, Command> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AutonCommandGroup() {
		put("RRSWRSC", new StartRightRightSwitchRightScale());
		put("RRSWLSC", new StartRightRightSwitchLeftScale());
		put("RLSWLSC", new StartRightLeftSwitchLeftScale());
		put("RLSWRSC", new StartRightLeftSwitchLeftScale());
		put("RLSCLSC", new StartRightScaleLeftScaleLeft());
		put("RRSCRSC", new StartRightScaleRightScaleRight());
		put("LLSCLSC", new StartLeftScaleLeftScaleLeft());
		put("LLSWLSC", new StartLeftLeftSwitchLeftScale());
		put("LLSWRSC", new StartLeftLeftSwitchRightScale());
		put("LRSWRSC", new StartLeftRightSwitchRightScale());
		put("LRSWLSC", new StartLeftRightSwitchRightScale());
		put("LRSCRSC", new StartLeftScaleRightScaleRight());
		put("CRSW", new CenterRightReset());
		put("CLSW", new CenterLeftReset());
		put("CRSWRSC", new CenterRightReset());
		put("CLSWRSC", new CenterLeftReset());
		put("CRSWLSC", new CenterRightReset());
		put("CLSWLSC", new CenterLeftReset());
		//put("CRSWSW", new CenterRightReset());
		//put("CLSWSW", new CenterLeftReset());
		
		put("TTRRR", new TestBlock());
		
		//Never go for scale in auton center
		put("DRIVEFORWARD", new JustDriveForward());
	}

}
