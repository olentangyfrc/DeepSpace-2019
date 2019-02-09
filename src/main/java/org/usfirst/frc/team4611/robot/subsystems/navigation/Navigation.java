package org.usfirst.frc.team4611.robot.subsystems.navigation;


import java.util.logging.Logger;

import org.usfirst.frc.team4611.robot.OzoneJavaLogger.LogTest;
import org.usfirst.frc.team4611.robot.subsystems.PortMan;
import org.usfirst.frc.team4611.robot.subsystems.navigation.sensors.LineTracker;
import org.usfirst.frc.team4611.robot.subsystems.navigation.sensors.Pigeon;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Navigation extends Subsystem {

    public final Logger logger = Logger.getLogger(LogTest.class.getName());

    private Pigeon rotationPigeon;
    private final String SHUFFLE_ROTATE_PIGEON_HEADING = "Rotation Pigeon Heading";
    private final String PORTMAN_PIGEON_TAG = "Navigation.Pigeon";

    private LineTracker lineTracker;

    private ShuffleboardTab tab; 
    private NetworkTableEntry colorWhite; 
    private NetworkTableEntry pigeonAngle;

    public Navigation(){
    }

    public void init(PortMan pm) throws Exception {
        logger.info("initializing");
        rotationPigeon = new Pigeon(pm.acquirePort(PortMan.can_21_label, PORTMAN_PIGEON_TAG));
        
        lineTracker = new LineTracker(pm.acquirePort(PortMan.analog0_label, "Navigation.LineTracker"));

        tab = Shuffleboard.getTab("Health Map")
        colorwhite = tab.add("IsColorWhite",false).getEntry(); 
        PigeonAngle = tab.add("PigeonAngle",0).getEntry();
    }


    public double getCurentHeading() {

        pigeonAngle.setDouble(rotationPigeon.getCurrentAngle());

        return rotationPigeon.getCurrentAngle();
    }

    public double getCurrentAbsoluteHeadingError(double angle) {
        return rotationPigeon.getAbolsuteAngleError(angle);
    }

    public boolean isColorWhite(){

        colorWhite.setBoolean(lineTracker.isColorWhite());

        return lineTracker.isColorWhite(); 
    }

    protected void initDefaultCommand() {
    }
}