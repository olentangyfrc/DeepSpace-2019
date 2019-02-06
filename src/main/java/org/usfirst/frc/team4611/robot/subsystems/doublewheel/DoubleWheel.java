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
    private WPI_TalonSRX indiWheelFront;
    private WPI_TalonSRX indiWheelBack;

    private ShuffleboardTab tab;
    private NetworkTableEntry doubleWheelLeftVelocity;
    private NetworkTableEntry doubleWheelRightVelocity;
    private NetworkTableEntry doubleWheelIntakeVelocity;
    private NetworkTableEntry indiWheelFrontVelocity;
    private NetworkTableEntry indiWheelBackVelocity;


    private int wheelVelocity = 480;
    private int indiWheelDefaultVelocity = 400;

    public void init(PortMan pm) throws Exception {

        logger.entering(DoubleWheel.class.getName(), "init()");

        wheelIntakeLeft = new WPI_TalonSRX(pm.acquirePort(PortMan.can_18_label, "DoubleWheel.leftWheelIntake"));
        wheelIntakeRight = new WPI_TalonSRX(pm.acquirePort(PortMan.can_19_label, "DoubleWheel.rightWheelIntake"));
        indiWheelFront = new WPI_TalonSRX(pm.acquirePort(PortMan.can_20_label, "DoubleWheel.indiWheelFront"));
        indiWheelBack = new WPI_TalonSRX(pm.acquirePort(PortMan.can_22_label, "DoubleWheel.indiWheelBack"));

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

        indiWheelBack.config_kP(0, .5, 0);
        indiWheelBack.config_kI(0, 0, 0);
        indiWheelBack.config_kD(0, 0, 0);
        indiWheelBack.config_kF(0, 0, 0);
        indiWheelBack.configMotionCruiseVelocity(4096, 0);
        indiWheelBack.configMotionAcceleration(4096,0);

        indiWheelFront.config_kP(0, .5, 0);
        indiWheelFront.config_kI(0, 0, 0);
        indiWheelFront.config_kD(0, 0, 0);
        indiWheelFront.config_kF(0, 0, 0);
        indiWheelFront.configMotionCruiseVelocity(4096, 0);
        indiWheelFront.configMotionAcceleration(4096,0);

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

        indiWheelFront.set(ControlMode.Velocity, indiWheelFrontVelocity.getDouble(indiWheelDefaultVelocity));
    
        logger.exiting(DoubleWheel.class.getName(), "spinIndiWheelFrontForward()");
    
    }

    public void spinIndiWheelFrontBackward() {

        logger.entering(DoubleWheel.class.getName(), "spinIndiWheelFrontBackward()");

        indiWheelFront.set(ControlMode.Velocity, -indiWheelFrontVelocity.getDouble(indiWheelDefaultVelocity));
    
        logger.exiting(DoubleWheel.class.getName(), "spinIndiWheelFrontBackward()");
        

    }

    public void stopIndiWheelFront(){

        logger.entering(DoubleWheel.class.getName(), "stopIndiWheelFront()");

        indiWheelFront.set(ControlMode.Velocity, 0); 

        logger.exiting(DoubleWheel.class.getName(), "stopIndiWheelFront()");

    }

    public void spinIndiWheelBackForward() {

        logger.entering(DoubleWheel.class.getName(), "spinIndiWheelBackForward()");

        indiWheelBack.set(ControlMode.Velocity, indiWheelBackVelocity.getDouble(indiWheelDefaultVelocity));        
    
        logger.exiting(DoubleWheel.class.getName(), "spinIndiWheelBackForward()");
    
        }
    
    public void spinIndiWheelBackBackward() {

        logger.entering(DoubleWheel.class.getName(), "spinIndiWheelBackBackward()");

        indiWheelBack.set(ControlMode.Velocity, -indiWheelBackVelocity.getDouble(indiWheelDefaultVelocity));
    
        logger.exiting(DoubleWheel.class.getName(), "spinIndiWheelBackBackward()");

    
    }

    public void stopIndiWheelBack(){

        logger.entering(DoubleWheel.class.getName(), "stopIndiWheelBack()");

        indiWheelBack.set(ControlMode.Velocity, 0); 

        logger.exiting(DoubleWheel.class.getName(), "stopIndiWheelBack()");

    }
    @Override
    protected void initDefaultCommand() {

    }
}
