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

    private ShuffleboardTab tab;
    private NetworkTableEntry doubleWheelLeftVelocity;
    private NetworkTableEntry doubleWheelRightVelocity;
    private NetworkTableEntry doubleWheelIntakeVelocity;


    private int wheelVelocity = 1600;
    private int indiWheelDefaultVelocity = 1600;
    private int adjusterVelocity = 1600;

    public void init(PortMan pm) throws Exception {

        logger.entering(DoubleWheel.class.getName(), "init()");

        wheelIntakeLeft = new WPI_TalonSRX(pm.acquirePort(PortMan.can_18_label, "DoubleWheel.leftWheelIntake"));
        wheelIntakeRight = new WPI_TalonSRX(pm.acquirePort(PortMan.can_19_label, "DoubleWheel.rightWheelIntake"));

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
        doubleWheelIntakeVelocity = tab.add("Shooter Velocity Percent", 1).getEntry();

        logger.exiting(DoubleWheel.class.getName(), "init()");

    }

    public void spinMotorsIntake(int speed){

        logger.entering(DoubleWheel.class.getName(), "spinMotorsIntake()");

        wheelIntakeLeft.set(ControlMode.Velocity, speed*doubleWheelIntakeVelocity.getDouble(0)); 
        
        doubleWheelLeftVelocity.setDouble(speed);
        doubleWheelRightVelocity.setDouble(-speed);

        logger.exiting(DoubleWheel.class.getName(), "spinMotorsIntake()");


    }

    public void spinMotorOutTake() {

        logger.info("entering spinMotorOutTake()");

        wheelIntakeLeft.set(ControlMode.Velocity, -wheelVelocity*doubleWheelIntakeVelocity.getDouble(wheelVelocity));

        logger.exiting(DoubleWheel.class.getName(), "spinMotorOutTake()");
    }

    public void stopMotors() {
        wheelIntakeLeft.set(ControlMode.Velocity, 0);
    }

   

    @Override
    protected void initDefaultCommand() {

    }
}
