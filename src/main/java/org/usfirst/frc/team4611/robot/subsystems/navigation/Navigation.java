package org.usfirst.frc.team4611.robot.subsystems.navigation;

import java.util.logging.Logger;

import org.usfirst.frc.team4611.robot.networktables.NetTableManager;
import org.usfirst.frc.team4611.robot.subsystems.PortMan;
import org.usfirst.frc.team4611.robot.subsystems.navigation.commands.NavigationDefault;
import org.usfirst.frc.team4611.robot.subsystems.navigation.sensors.LidarPWM;
import org.usfirst.frc.team4611.robot.subsystems.navigation.sensors.Pigeon;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;

public class Navigation extends Subsystem {

    public final Logger logger = Logger.getLogger(Navigation.class.getName());

    private LidarPWM leftFrontLidar, leftRearLidar;

    private double leftFrontLidarDistance, leftRearLidarDistance;
    private double leftFrontLidarOffset = 0; private double leftRearLidarOffset = 0;

    private LidarPWM rightFrontLidar, rightRearLidar;

    private double rightFrontLidarDistance, rightRearLidarDistance;
    private double rightFrontLidarOffset = 0; private double rightRearLidarOffset = 0;

    private boolean leftSideSquare, rightSideSquare;

    private double lidarTolerance   = 5;
    private boolean inited = false;
    private Pigeon pigeon;

    public Navigation(){
        
    }

    public void init(PortMan pm) throws Exception {
        logger.info("initializing");

        initSB();

        leftFrontLidar = new LidarPWM(pm.acquirePort(PortMan.digital0_label, "Navigation.leftFrontLidar"));
        leftRearLidar = new LidarPWM(pm.acquirePort(PortMan.digital1_label, "Navigation.leftRearLidar"));
        pigeon = new Pigeon(pm.acquirePort(PortMan.can_21_label, "Navigation.pigeon"));
        inited = true;
    }

    public double getCurrentAbsoluteHeadingError(double angle) {
        return pigeon.getAbolsuteAngleError(angle);
    }

    public double getCurentHeading() {
        return pigeon.getCurrentAngle();
    }

    public boolean isLeftSideSquare() {
        return (Math.abs((leftFrontLidarDistance + leftFrontLidarOffset)
            - (leftRearLidarDistance + leftRearLidarOffset)) < lidarTolerance);
    }

    public boolean isRightSideSquare() {
        return (Math.abs((rightFrontLidarDistance + rightFrontLidarOffset)
            - (rightRearLidarDistance + rightRearLidarOffset)) < lidarTolerance);
    }

    public void checkValues() {

        if (!inited) return;

        leftFrontLidarDistance = leftFrontLidar.getDistance();
        leftRearLidarDistance = leftRearLidar.getDistance();

        leftFrontLidarDistanceEntry.setDouble(leftFrontLidarDistance);
        leftRearLidarDistanceEntry.setDouble(leftRearLidarDistance);

        leftFrontLidarOffset = leftFrontLidarOffsetEntry.getDouble(leftFrontLidarOffset);
        leftRearLidarOffset = leftRearLidarOffsetEntry.getDouble(leftRearLidarOffset);

        rightFrontLidarDistanceEntry.setDouble(rightFrontLidarDistance);
        rightRearLidarDistanceEntry.setDouble(rightRearLidarDistance);

        rightFrontLidarOffset = rightFrontLidarOffsetEntry.getDouble(rightFrontLidarOffset);
        rightRearLidarOffset = rightRearLidarOffsetEntry.getDouble(rightRearLidarOffset);

        lidarTolerance  = lidarToleranceEntry.getDouble(lidarTolerance);

        leftSideSquareEntry.setBoolean(isLeftSideSquare());
        rightSideSquareEntry.setBoolean(isRightSideSquare());
    }

    protected void initDefaultCommand() {
        this.setDefaultCommand(new NavigationDefault());
    }

    private static ShuffleboardTab tab = Shuffleboard.getTab("Navigation");
    private NetworkTableEntry   leftFrontLidarDistanceEntry, leftRearLidarDistanceEntry, leftSideSquareEntry;
    private NetworkTableEntry   leftFrontLidarOffsetEntry, leftRearLidarOffsetEntry;

    private NetworkTableEntry   rightFrontLidarDistanceEntry, rightRearLidarDistanceEntry, rightSideSquareEntry;
    private NetworkTableEntry   rightFrontLidarOffsetEntry, rightRearLidarOffsetEntry;

    private NetworkTableEntry   lidarToleranceEntry;

    public void initSB() {
        leftFrontLidarDistanceEntry = tab.add("Left Front\nLidar Distance", leftFrontLidarDistance)
                                        .withSize(1,1).withPosition(0, 0).getEntry();
        leftFrontLidarOffsetEntry = tab.add("Left Front\nLidar Offset", leftFrontLidarOffset)
                                        .withSize(1,1).withPosition(1, 0).getEntry();

        leftRearLidarDistanceEntry = tab.add("Left Rear\nLidar Distance", leftRearLidarDistance)
                                        .withSize(1,1).withPosition(0, 1).getEntry();
        leftRearLidarOffsetEntry = tab.add("Left Rear\nLidar Offset", leftRearLidarOffset)
                                        .withSize(1,1).withPosition(1, 1).getEntry();

        leftSideSquareEntry = tab.add("Left Side Square", leftSideSquare)
                                        .withSize(2,1).withPosition(0, 2).getEntry();


        rightFrontLidarDistanceEntry = tab.add("Right Front\nLidar Distance", rightFrontLidarDistance)
                                        .withSize(1,1).withPosition(3, 0).getEntry();
        rightFrontLidarOffsetEntry = tab.add("Right Front\nLidar Offset", rightFrontLidarOffset)
                                        .withSize(1,1).withPosition(4, 0).getEntry();

        rightRearLidarDistanceEntry = tab.add("Right Rear\nLidar Distance", rightRearLidarDistance)
                                        .withSize(1,1).withPosition(3, 1).getEntry();
        rightRearLidarOffsetEntry = tab.add("Right Rear\nLidar Offset", rightRearLidarOffset)
                                        .withSize(1,1).withPosition(4, 1).getEntry();

        rightSideSquareEntry = tab.add("Right Side Square", rightSideSquare)
                                        .withSize(2,1).withPosition(3, 2).getEntry();

        lidarToleranceEntry = tab.add("Lidar Tolerance", lidarTolerance)
                                        .withSize(1,1).withPosition(0, 3).getEntry();

    }
}