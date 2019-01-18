package org.usfirst.frc.team4611.robot.subsystems.kicker;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import org.usfirst.frc.team4611.robot.subsystems.PortMan;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Kicker extends Subsystem
{
    private WPI_TalonSRX kicker;
    private int kickDistance = 27648;


    public void init(PortMan pm) {
        try{
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
        } catch(Exception e) {
            e.printStackTrace();
        }
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