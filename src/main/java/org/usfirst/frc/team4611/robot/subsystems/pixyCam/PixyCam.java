package org.usfirst.frc.team4611.robot.subsystems.pixyCam;

import java.util.logging.Logger;

import org.usfirst.frc.team4611.robot.subsystems.vision.commands.PollNetworkTable;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;

public class PixyCam extends Subsystem{

    private I2C i2c;
	private static byte[] position;
	private static byte[] readyCheck;
	private java.util.Timer updater;
	private LIDARUpdater task;

	private final int LIDAR_ADDR = 0x62;
	private final int LIDAR_CONFIG_REGISTER = 0x00;
	private final int ready = 0x01;
	private final int LIDAR_DISTANCE_REGISTER_BYTE = 0x8f;
	//private final int LIDAR_DISTANCE_REGISTER_HIGH_BYTE = 0x0f;


    Logger logger = Logger.getLogger(PixyCam.class.getName());

    double targetCount = 0.0;
    private ShuffleboardTab tab;
    private NetworkTableEntry loggerEntry;

    public void PixyCam() {
        
    }

    public void init() {

        logger.info("initializing");
        tab = Shuffleboard.getTab("PixyCam");

      }

    protected void initDefaultCommand() {
        setDefaultCommand(new PollNetworkTable());
    } 
}