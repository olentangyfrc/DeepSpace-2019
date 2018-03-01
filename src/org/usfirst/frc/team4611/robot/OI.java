package org.usfirst.frc.team4611.robot;

import org.usfirst.frc.team4611.robot.commands.auton.AimForBox;
import org.usfirst.frc.team4611.robot.commands.auton.AutoGrab;
import org.usfirst.frc.team4611.robot.commands.climber.ClimberToPos;
import org.usfirst.frc.team4611.robot.commands.climber.MoveClimber;
import org.usfirst.frc.team4611.robot.commands.climber.WindUpClimber;
import org.usfirst.frc.team4611.robot.commands.elevator.MoveElevatorDown;
import org.usfirst.frc.team4611.robot.commands.elevator.MoveElevatorUp;
import org.usfirst.frc.team4611.robot.commands.elevator.ResetElevator;
import org.usfirst.frc.team4611.robot.commands.happyshapes.AttackPos;
import org.usfirst.frc.team4611.robot.commands.happyshapes.ScalePos;
import org.usfirst.frc.team4611.robot.commands.happyshapes.StartingPos;
import org.usfirst.frc.team4611.robot.commands.happyshapes.SwitchPos;
import org.usfirst.frc.team4611.robot.commands.solenoid.ReleaseBox;
import org.usfirst.frc.team4611.robot.commands.solenoid.PushBox;
import org.usfirst.frc.team4611.robot.commands.solenoid.GrabBox;
import org.usfirst.frc.team4611.robot.commands.solenoid.ToggleSolenoid;
import org.usfirst.frc.team4611.robot.potentiometer.MovePotDown;
import org.usfirst.frc.team4611.robot.potentiometer.MovePotUp;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;


/**
 * This is where we create all of out buttons and joysticks and 
 * set up the "UI" of the bot for the drivers. You're gonna end up 
 * here a lot when people complain about buttons needing to be changed
 */

public class OI {
	
	//Joysticks
	public static Joystick leftJoy;
	public static Joystick rightJoy;
	public static Joystick auxJoy;
	public static XboxController con;
	
	//Movement Buttons
	public Button autoGrabBox;
	public Button aimBox;
	public Button aimBox2;
	
	//Solenoid Buttons
	public Button grabberToggle;
	public Button grabberExtend;
	public Button grabberRetract;
	public Button grabberExtend2;
	public Button pushBox;
	
	//LA Buttons
	public Button linearActuatorUp;
	public Button linearActuatorDown;
	public Button linearActuatorUp2;
	public Button linearActuatorDown2;
	public Button linearActuatorUp3;
	public Button linearActuatorDown3;
	public Button linearActuatorSwitch;
	
	//Elevator Buttons
	public Button moveElUp;
	public Button moveElDown;
	
	//Happy Place Buttons
	public Button happySwitch;
	public Button happyScale;
	public Button happyAttack;
	public Button happyStart;
    public Button happyReset;
	
	//Climber Buttons
	public Button moveClimber;
	public Button windClimber;
	public Button moveClimbertoPos;
	
	//Lidar Buttons
	public Button getDistance;
	public Button getDistanceController;
	

	public OI (){
		
		//Joystick
		leftJoy = new Joystick(RobotMap.LEFT_JOY_PORT); //The left joystick exists on this port in robot map
		rightJoy = new Joystick(RobotMap.RIGHT_JOY_PORT); //The right joystick exists on this port in robot map
		auxJoy = new Joystick(RobotMap.THIRD_JOY_PORT);
		con = new XboxController(RobotMap.LEFT_JOY_PORT);
		
		//Movement Buttons
		autoGrabBox = new JoystickButton(auxJoy, 11);
		aimBox = new JoystickButton(auxJoy, 10);
		aimBox2 = new JoystickButton(leftJoy, 3);
		
		//LA Buttons
		linearActuatorUp = new JoystickButton(rightJoy, 3);
		linearActuatorDown = new JoystickButton(rightJoy, 2);
		linearActuatorUp2 = new JoystickButton(auxJoy, 11);
		linearActuatorDown2 = new JoystickButton(auxJoy, 10);
		
		//Solenoid Buttons
		grabberToggle = new JoystickButton(leftJoy, 1);
		grabberExtend = new JoystickButton(leftJoy, 6);//open claw
		grabberExtend2 = new JoystickButton(auxJoy, 1);
		grabberRetract = new JoystickButton(leftJoy, 7);//close claw
		pushBox = new JoystickButton(rightJoy, 1);
		
		//Elevator Buttons
		moveElUp = new JoystickButton (auxJoy, 6);
		moveElDown = new JoystickButton (auxJoy, 7);
		
		//Climber Buttons
		moveClimber = new JoystickButton(rightJoy, 10);
		windClimber = new JoystickButton(rightJoy, 11);
		//moveClimbertoPos = new JoystickButton(rightJoy, 7);
		
		
		//Happy Shaping Buttons
		happyScale = new JoystickButton(auxJoy,5);                   
		happySwitch = new JoystickButton(auxJoy,3);
		happyAttack = new JoystickButton(auxJoy, 4);
		happyStart = new JoystickButton(auxJoy, 9);
		happyReset = new JoystickButton(auxJoy, 8);
		
		//Lidar Buttons
		getDistanceController = new JoystickButton(con, 2);
			
		//Sends the starting values of the joysticks to the Shuffleboard
		RobotMap.updateValue(RobotMap.joyStickSubTable, RobotMap.leftJoyXID, leftJoy.getX());
		RobotMap.updateValue(RobotMap.joyStickSubTable, RobotMap.leftJoyYID, leftJoy.getY());
		RobotMap.updateValue(RobotMap.joyStickSubTable, RobotMap.leftJoyZID, leftJoy.getZ());
		RobotMap.updateValue(RobotMap.joyStickSubTable, RobotMap.rightJoyXID, rightJoy.getX());
		RobotMap.updateValue(RobotMap.joyStickSubTable, RobotMap.rightJoyYID, rightJoy.getY());
		RobotMap.updateValue(RobotMap.joyStickSubTable, RobotMap.rightJoyZID, rightJoy.getZ());

		//Movement Commands
//		aimBox.whenPressed(new AimForBox());
//		aimBox2.whenPressed(new AimForBox());
//		autoGrabBox.whenPressed(new AutoGrab());
		
		//LA commands
		linearActuatorUp.whileHeld(new MovePotUp());
		linearActuatorUp2.whileHeld(new MovePotUp());			
		linearActuatorDown.whileHeld(new MovePotDown());
		linearActuatorDown2.whileHeld(new MovePotDown());

		//Elevator Commands
		moveElUp.whileHeld(new MoveElevatorUp());	
		moveElDown.whileHeld(new MoveElevatorDown());
		
		//Magic Shaping Commands	
		happyScale.whenPressed(new ScalePos());
		happySwitch.whenPressed(new SwitchPos());
		happyAttack.whenPressed(new AttackPos());
		happyStart.whenPressed(new StartingPos());
		happyReset.whenPressed(new ResetElevator());
		
		//Grabber Commands
		grabberToggle.whenPressed(new ToggleSolenoid());
		grabberExtend.whileHeld(new ReleaseBox());
		grabberExtend2.whileHeld(new ReleaseBox());
		grabberRetract.whileHeld(new GrabBox());
		pushBox.whenPressed(new PushBox());
		
		//Climber Commands
		moveClimber.whileHeld(new MoveClimber());
		windClimber.whileHeld(new WindUpClimber());
		//moveClimbertoPos.whenPressed(new ClimberToPos(26050));
		
		//Lidar Commands
		//getDistance.whenPressed(new laserDistance());
		//getDistanceController.whenPressed(new laserDistance());
	}
	
	public double filter(double raw) //We pass joystick values through the filter here and edit the raw value
    {
        if (Math.abs(raw) < (double)RobotMap.getValue(RobotMap.mecanumSubTable, RobotMap.deadZoneID)) {
            return 0; //If the value passed is less than 15% ignore it. This is reffered to as a deadzone
        } else {
            return  raw * (double)RobotMap.getValue(RobotMap.mecanumSubTable, RobotMap.motorPowerID); //Set the output to a ceratin percent of of the input
        }
    }
	
	public double strafeFilter(double raw) {
		if (Math.abs(raw) < (double)RobotMap.getValue(RobotMap.mecanumSubTable, RobotMap.deadZoneID)) {
            return 0; //If the value passed is less than 15% ignore it. This is reffered to as a deadzone
        } else {
            return  raw * Math.min((double)RobotMap.getValue(RobotMap.mecanumSubTable, RobotMap.strafePowerID) * 2, 1); //Set the output to a ceratin percent of of the input
        }
	}	
	
	public double rotateFilter(double raw) {
		if (Math.abs(raw) < (double)RobotMap.getValue(RobotMap.mecanumSubTable, RobotMap.deadZoneID)) {
            return 0; //If the value passed is less than 15% ignore it. This is reffered to as a deadzone
        } else {
            return  raw * Math.min((double)RobotMap.getValue(RobotMap.mecanumSubTable, RobotMap.rotateFilterID), 1); //Set the output to a ceratin percent of of the input
        }
	}
	public double LAFilter(double raw) {
		if (Math.abs(raw) < (double)RobotMap.getValue(RobotMap.mecanumSubTable, RobotMap.deadZoneID)) {
            return 0; //If the value passed is less than 15% ignore it. This is reffered to as a deadzone
        } else {
            return  raw * Math.min((double)RobotMap.getValue(RobotMap.linearActuatorSubTable, RobotMap.LAFilterID), 1); //Set the output to a ceratin percent of of the input
        }
	}
	
}