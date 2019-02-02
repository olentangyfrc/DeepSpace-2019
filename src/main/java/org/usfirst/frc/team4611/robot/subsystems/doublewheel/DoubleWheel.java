package org.usfirst.frc.team4611.robot.subsystems.doublewheel;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import org.usfirst.frc.team4611.robot.networktables.NetTableManager;
import org.usfirst.frc.team4611.robot.subsystems.PortMan;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;

public class DoubleWheel extends Subsystem {

    private WPI_TalonSRX wheelIntakeLeft;
    private WPI_TalonSRX wheelIntakeRight;

    private ShuffleboardTab tab;
    private NetworkTableEntry doubleWheelLeftVelocity;
    private NetworkTableEntry doubleWheelRightVelocity;
    private NetworkTableEntry doubleWheelIntakeVelocity;

    private int wheelVelocity = 480;

    public void init(PortMan pm) throws Exception {
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
        doubleWheelIntakeVelocity = tab.add("Wheel Intake Velocity", (double)wheelVelocity).getEntry();

    }

    public void spinMotorsIntake(int speed){
        wheelIntakeLeft.set(ControlMode.Velocity, speed); 
        
        doubleWheelLeftVelocity.setDouble(speed);
        doubleWheelRightVelocity.setDouble(-speed);

    }

    public void spinMotorOutTake() {
        wheelIntakeLeft.set(ControlMode.Velocity, -doubleWheelIntakeVelocity.getDouble(wheelVelocity));

        
    }

    @Override
    protected void initDefaultCommand() {

    }
}
