package org.usfirst.frc.team4611.robot.subsystems.drivetrain.interfaces;

import org.usfirst.frc.team4611.robot.OzoneJavaLogger.LogTest;
import org.usfirst.frc.team4611.robot.networktables.NetTableManager;
import org.usfirst.frc.team4611.robot.subsystems.PortMan;

import java.util.logging.Logger;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;

public abstract class TalonDriveTrain extends DriveTrain {

    public final Logger logger = Logger.getLogger(TalonDriveTrain.class.getName());
    
    public WPI_TalonSRX frontLeft;
	public WPI_TalonSRX frontRight;
	public WPI_TalonSRX backLeft;
    public WPI_TalonSRX backRight;
    
    protected ShuffleboardTab tab; 

    private ShuffleboardTab portTab;
    private NetworkTableEntry port1;
    private NetworkTableEntry port2;
    private NetworkTableEntry port3;
    private NetworkTableEntry port4;

    public void init(PortMan pm) throws Exception {
        logger.info("initializing");

        frontLeft = new WPI_TalonSRX(pm.acquirePort(PortMan.can_10_label, "DriveTrain.FrontLeft"));
        frontRight = new WPI_TalonSRX(pm.acquirePort(PortMan.can_11_label, "DriveTrain.FrontRight"));
        backLeft = new WPI_TalonSRX(pm.acquirePort(PortMan.can_13_label, "DriveTrain.BackLeft"));
        backRight = new WPI_TalonSRX(pm.acquirePort(PortMan.can_12_label, "DriveTrain.BackRight"));

        tab = Shuffleboard.getTab("Health Map");
        NetTableManager.updateValue("Health Map", "Drive Train Initialize", true);

        portTab = Shuffleboard.getTab("Port Manager");
        NetTableManager.updateValue("PortManager", "TalonDrive Train Ports", true);

        port1 = portTab.add("can_10_label", true).getEntry();
        port2 = portTab.add("can_11_label", true).getEntry();
        port3 = portTab.add("can_13_label", true).getEntry();
        port4 = portTab.add("can_12_label", true).getEntry();
        
        setupTalons();
    }

}