package org.usfirst.frc.team4611.robot.subsystems.doublewheel;

import java.util.logging.Logger;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import org.usfirst.frc.team4611.robot.networktables.NetTableManager;
import org.usfirst.frc.team4611.robot.subsystems.PortMan;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;

public class DoubleWheel extends Subsystem {

    private static Logger logger = Logger.getLogger(DoubleWheel.class.getName());

    private WPI_TalonSRX wheelIntakeLeft;
    private WPI_TalonSRX wheelIntakeRight;
    private WPI_TalonSRX intake;
    private WPI_TalonSRX roller;
    private WPI_TalonSRX intakeAdjuster;

    private ShuffleboardTab tab;
    private NetworkTableEntry doubleWheelLeftVelocity;
    private NetworkTableEntry doubleWheelRightVelocity;
    private NetworkTableEntry doubleWheelIntakeVelocity;
    private NetworkTableEntry indiWheelFrontVelocity;
    private NetworkTableEntry indiWheelBackVelocity;
    private NetworkTableEntry intakeAdjusterVelocity;


    private int wheelVelocity = 480;
    private int indiWheelDefaultVelocity = 400;
    private int adjusterVelocity = 400;

    public void init(PortMan pm) throws Exception {

        logger.entering(DoubleWheel.class.getName(), "init()");

        wheelIntakeLeft = new WPI_TalonSRX(pm.acquirePort(PortMan.can_18_label, "DoubleWheel.leftWheelIntake"));
        wheelIntakeRight = new WPI_TalonSRX(pm.acquirePort(PortMan.can_19_label, "DoubleWheel.rightWheelIntake"));
        intake = new WPI_TalonSRX(pm.acquirePort(PortMan.can_20_label, "DoubleWheel.intake"));
        roller = new WPI_TalonSRX(pm.acquirePort(PortMan.can_22_label, "DoubleWheel.roller"));
        intakeAdjuster = new WPI_TalonSRX(pm.acquirePort(PortMan.can_23_label, "DoubleWheel.intakeAdjuster"));

        wheelIntakeLeft.config_kP(0, .5, 0);
        wheelIntakeLeft.config_kI(0, 0, 0);
        wheelIntakeLeft.config_kD(0, 0, 0);
        wheelIntakeLeft.config_kF(0, 0, 0);
        wheelIntakeLeft.configMotionCruiseVelocity(4096, 0);
        wheelIntakeLeft.configMotionAcceleration(4096,0);
    
        wheelIntakeRight.follow(wheelIntakeLeft);
        wheelIntakeRight.setInverted(true);

        tab = Shuffleboard.getTab("Health Map");
		NetTableManager.updateValue("Health Map", "Double Wheel Initialize", true);

        doubleWheelLeftVelocity = tab.add("Double Wheel Left Engaged", -1).getEntry();
        doubleWheelRightVelocity = tab.add("Double Wheel Right Engaged", -1).getEntry();
        indiWheelFrontVelocity = tab.add("Indi Wheel Front Velocity", indiWheelDefaultVelocity).getEntry();
        indiWheelBackVelocity = tab.add("Indi Wheel Back Velocity", indiWheelDefaultVelocity).getEntry();
        doubleWheelIntakeVelocity = tab.add("Wheel Intake Velocity", (double)wheelVelocity).getEntry();
        intakeAdjusterVelocity = tab.add("Intake Adjuster Velocity", adjusterVelocity).getEntry();

        roller.config_kP(0, .5, 0);
        roller.config_kI(0, 0, 0);
        roller.config_kD(0, 0, 0);
        roller.config_kF(0, 0, 0);
        roller.configMotionCruiseVelocity(4096, 0);
        roller.configMotionAcceleration(4096,0);

        intake.config_kP(0, .5, 0);
        intake.config_kI(0, 0, 0);
        intake.config_kD(0, 0, 0);
        intake.config_kF(0, 0, 0);
        intake.configMotionCruiseVelocity(4096, 0);
        intake.configMotionAcceleration(4096,0);

        intakeAdjuster.config_kP(0, .5, 0);
        intakeAdjuster.config_kI(0, 0, 0);
        intakeAdjuster.config_kD(0, 0, 0);
        intakeAdjuster.config_kF(0, 0, 0);
        intakeAdjuster.configMotionCruiseVelocity(4096, 0);
        intakeAdjuster.configMotionAcceleration(4096,0);

        logger.exiting(DoubleWheel.class.getName(), "init()");

    }

    public void spinMotorsIntake(int speed){

        logger.entering(DoubleWheel.class.getName(), "spinMotorsIntake()");


        wheelIntakeLeft.set(ControlMode.Velocity, speed); 
        
        doubleWheelLeftVelocity.setDouble(speed);
        doubleWheelRightVelocity.setDouble(-speed);

        logger.exiting(DoubleWheel.class.getName(), "spinMotorsIntake()");


    }

    public void spinMotorOutTake() {

        logger.entering(DoubleWheel.class.getName(), "spinMotorOutTake()");

        wheelIntakeLeft.set(ControlMode.Velocity, -doubleWheelIntakeVelocity.getDouble(wheelVelocity));

        logger.exiting(DoubleWheel.class.getName(), "spinMotorOutTake()");
    }

    public void spinIndiWheelFrontForward() {

        logger.entering(DoubleWheel.class.getName(), "spinIndiWheelFrontForward()");

        intake.set(ControlMode.Velocity, indiWheelFrontVelocity.getDouble(indiWheelDefaultVelocity));
    
        logger.exiting(DoubleWheel.class.getName(), "spinIndiWheelFrontForward()");
    
    }

    public void spinIndiWheelFrontBackward() {

        logger.entering(DoubleWheel.class.getName(), "spinIndiWheelFrontBackward()");

        intake.set(ControlMode.Velocity, -indiWheelFrontVelocity.getDouble(indiWheelDefaultVelocity));
    
        logger.exiting(DoubleWheel.class.getName(), "spinIndiWheelFrontBackward()");
        

    }

    public void stopIndiWheelFront(){

        logger.entering(DoubleWheel.class.getName(), "stopIndiWheelFront()");

        intake.set(ControlMode.Velocity, 0); 

        logger.exiting(DoubleWheel.class.getName(), "stopIndiWheelFront()");

    }

    public void spinIndiWheelBackForward() {

        logger.entering(DoubleWheel.class.getName(), "spinIndiWheelBackForward()");

        roller.set(ControlMode.Velocity, indiWheelBackVelocity.getDouble(indiWheelDefaultVelocity));        
    
        logger.exiting(DoubleWheel.class.getName(), "spinIndiWheelBackForward()");
    
        }
    
    public void spinIndiWheelBackBackward() {

        logger.entering(DoubleWheel.class.getName(), "spinIndiWheelBackBackward()");

        roller.set(ControlMode.Velocity, -indiWheelBackVelocity.getDouble(indiWheelDefaultVelocity));
    
        logger.exiting(DoubleWheel.class.getName(), "spinIndiWheelBackBackward()");

    
    }
    
    public void spinIntakeAdjusterForward() {

        logger.entering(DoubleWheel.class.getName(), "spinIndiWheelBackForward()");

        intakeAdjuster.set(ControlMode.Velocity, intakeAdjusterVelocity.getDouble(adjusterVelocity));        
    
        logger.exiting(DoubleWheel.class.getName(), "spinIndiWheelBackForward()");
    
        }
    
    public void spinIntakeAdjusterBackward() {

        logger.entering(DoubleWheel.class.getName(), "spinIndiWheelBackBackward()");

        intakeAdjuster.set(ControlMode.Velocity, -intakeAdjusterVelocity.getDouble(adjusterVelocity));
    
        logger.exiting(DoubleWheel.class.getName(), "spinIndiWheelBackBackward()");

    
    }
    public void stopIntakeAdjuster(){

        logger.entering(DoubleWheel.class.getName(), "stopIndiWheelBack()");

        intakeAdjuster.set(ControlMode.Velocity, 0); 

        logger.exiting(DoubleWheel.class.getName(), "stopIndiWheelBack()");

    }

    public void stopIndiWheelBack(){

        logger.entering(DoubleWheel.class.getName(), "stopIndiWheelBack()");

        roller.set(ControlMode.Velocity, 0); 

        logger.exiting(DoubleWheel.class.getName(), "stopIndiWheelBack()");

    }
    @Override
    protected void initDefaultCommand() {

    }
}
