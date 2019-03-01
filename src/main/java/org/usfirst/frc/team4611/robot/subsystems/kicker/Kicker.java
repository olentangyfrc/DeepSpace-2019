package org.usfirst.frc.team4611.robot.subsystems.kicker;

import java.util.logging.Logger;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import org.usfirst.frc.team4611.robot.networktables.NetTableManager;
import org.usfirst.frc.team4611.robot.subsystems.PortMan;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;

public class Kicker extends Subsystem
{
    private static Logger logger = Logger.getLogger(Kicker.class.getName());
    private WPI_TalonSRX kicker;
    private int kickDistance = 27648;

    private ShuffleboardTab tab;
    private NetworkTableEntry networkKickDistance;


    public void init(PortMan pm) throws Exception {
        logger.info("initializing");

        kicker = new WPI_TalonSRX(pm.acquirePort(PortMan.can_17_label, "Kicker.talon"));
        kicker.setSensorPhase(true);  
        kicker.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
        kicker.setSelectedSensorPosition(0);

        kicker.config_kP(0, .5, 0);
        kicker.config_kI(0, 0, 0);
        kicker.config_kD(0, 0, 0);
        kicker.config_kF(0, 0, 0);

        kicker.configMotionAcceleration(2000);
        kicker.configMotionCruiseVelocity(2000);
        
        networkKickDistance = tab.add("Kicker Distance", kickDistance).getEntry();
    } 

    public void kickBall()
    {
        kicker.set(ControlMode.MotionMagic, kickDistance);
    }

    public void reset()
    {
        kicker.set(ControlMode.MotionMagic, -kickDistance);
        kicker.setSelectedSensorPosition(0);
    }

	@Override
	protected void initDefaultCommand() {
		
	}

}