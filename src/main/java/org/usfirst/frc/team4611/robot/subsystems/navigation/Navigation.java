package org.usfirst.frc.team4611.robot.subsystems.navigation;


import java.util.logging.Logger;

import org.usfirst.frc.team4611.robot.OzoneJavaLogger.LogTest;
import org.usfirst.frc.team4611.robot.subsystems.PortMan;
import org.usfirst.frc.team4611.robot.subsystems.navigation.commands.NavLog;
import org.usfirst.frc.team4611.robot.subsystems.navigation.sensors.Pigeon;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Navigation extends Subsystem {

    public final Logger logger = Logger.getLogger(LogTest.class.getName());

    private Pigeon rotationPigeon;
    private final String SHUFFLE_ROTATE_PIGEON_HEADING = "Rotation Pigeon Heading";

    private final String PORTMAN_PIGEON_TAG = "Navigation.Pigeon";

    public Navigation(){
        
    }

    public void init(PortMan pm) {
        try {
            rotationPigeon = new Pigeon(pm.acquirePort(PortMan.can_21_label, PORTMAN_PIGEON_TAG));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public double getCurentHeading() {
        return rotationPigeon.getCurrentAngle();
    }

    public double getCurrentAbsoluteHeadingError(double angle) {
        return rotationPigeon.getAbolsuteAngleError(angle);
    }

    public void log() {
        logger.info("Current Pigeon Heading:" + rotationPigeon.getCurrentAngle());
    }

    public void writeToShuffleBoard() {
        SmartDashboard.putNumber(SHUFFLE_ROTATE_PIGEON_HEADING, rotationPigeon.getCurrentAngle());
    }

    protected void initDefaultCommand() {
        this.setDefaultCommand(new NavLog());
    }



}