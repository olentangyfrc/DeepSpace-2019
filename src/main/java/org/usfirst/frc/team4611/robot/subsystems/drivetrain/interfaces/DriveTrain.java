package org.usfirst.frc.team4611.robot.subsystems.drivetrain.interfaces;

import org.usfirst.frc.team4611.robot.OzoneJavaLogger.LogTest;
import org.usfirst.frc.team4611.robot.networktables.NetTableManager;
import org.usfirst.frc.team4611.robot.subsystems.PortMan;

import java.util.logging.Logger;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;

public abstract class DriveTrain extends Subsystem {

    public final double INCH_PU_MULT = 215.910640625;

    public final double METER_PU_MULT = 39.3701 * INCH_PU_MULT;

    public final Logger logger = Logger.getLogger(LogTest.class.getName());
    
    public WPI_TalonSRX frontLeft;
	public WPI_TalonSRX frontRight;
	public WPI_TalonSRX backLeft;
    public WPI_TalonSRX backRight;
    
    protected ShuffleboardTab tab; 
    private NetworkTableEntry isLogging;

    private boolean logging = false;

    public abstract void move();

    public void init(PortMan pm) throws Exception {

        frontLeft = new WPI_TalonSRX(pm.acquirePort(PortMan.can_10_label, "DriveTrain.FrontLeft"));
        frontRight = new WPI_TalonSRX(pm.acquirePort(PortMan.can_11_label, "DriveTrain.FrontRight"));
        backLeft = new WPI_TalonSRX(pm.acquirePort(PortMan.can_13_label, "DriveTrain.BackLeft"));
        backRight = new WPI_TalonSRX(pm.acquirePort(PortMan.can_12_label, "DriveTrain.BackRight"));

        tab = Shuffleboard.getTab("Health Map");
        NetTableManager.updateValue("Health Map", "Drive Train Initialize", true);
        isLogging = tab.add("Drive Train Logging", false).getEntry();
        
        if(isLogging.getBoolean(false))
            logger.info("initializing");

        setupTalons();
    }

    /*public boolean isLogging(){
        logging = isLogging.getBoolean(false);
        return logging;
    }*/

    /**
     * Used to setup DriveTrian talons as needed
     */
    public abstract void setupTalons();

    /**
     * Resets the drive talon encoders to zero position units
     */
    public abstract void resetEncoders();

    // public abstract void followTrajectory(Trajectory t);

    /**
     * Moves the bot laterally at a set speed
     * 
     * @param speed The speed of the bot
     */
    public abstract void moveLateral(double speed);

    /**
     * Moves the bot horizontally at a set speed
     * 
     * @param speed The speed of the bot
     */
    public abstract void moveHorizontal(double speed);

    /**
     * Moves the bot laterally a given amount of inches using motion magic.
     * 
     * @param inches The amount of inches to move laterally
     */
    public abstract void moveLateralInches(double inches);

    /**
     * Moves the bot horizontally a given amount of inches using motion magic.
     * 
     * @param inches The amount of inches to move horizontally
     */
    public abstract void moveHorizontalInches(double inches);

    public abstract void setTrajectorySpeeds();

    /**
     * Averages all of the drive talon encoder values to get the average distance in
     * position units
     * 
     * @return the average distance traveled in position units
     */
    public abstract int getAverageSensorPos();

    /**
     * Moves the bot at a specific speed after applying teleop calculations
     * 
     * @param YVal The velocity of the motors
     */
    public abstract void moveVelocityAuton(double YVal);

    /**
     * Rotates the bot
     * 
     * @param velocity - The velocity for the motors to turn at
     */
    public abstract void rotate(double velocity);

    /**
     * @return The approximate velocity of the wheels (in m/s)
     */
    public abstract double getVelocity();

    public abstract void activateTurbo();

    public abstract void deactivateTurbo();

    public abstract void moveSideways(double pu);
    
    public void configTalonsSideways() {
       
        backLeft.setSelectedSensorPosition(0, 0, 0);
		backRight.setSelectedSensorPosition(0, 0, 0);
		frontLeft.setSelectedSensorPosition(0, 0, 0);
        frontRight.setSelectedSensorPosition(0, 0, 0);
        
		backRight.config_kP(0, 1, 0);
    	backLeft.config_kP(0, 1, 0);
    	frontRight.config_kP(0, 1, 0);
        frontLeft.config_kP(0, 1, 0);
        
    	backRight.configOpenloopRamp(5, 0);
    	backLeft.configOpenloopRamp(5, 0);
    	frontRight.configOpenloopRamp(5, 0);
    	frontLeft.configOpenloopRamp(5, 0);
    }
}