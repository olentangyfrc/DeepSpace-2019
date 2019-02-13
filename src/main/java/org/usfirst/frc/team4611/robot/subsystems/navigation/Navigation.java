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

    private static ShuffleboardTab tab = Shuffleboard.getTab("Navigation");

    private Pigeon rotationPigeon;
    private double currentPigeonAngle ;

    private NetworkTableEntry   pigeonAngleEntry;

    private LidarPWM leftLidar  = null;
    private LidarPWM rightLidar = null;
    private double leftLidarDistance, rightLidarDistance;
    private NetworkTableEntry   leftLidarDistanceEntry, rightLidarDistanceEntry, lidarsAreSquareEntry;
    private boolean lidarsAreSquare;

    public Navigation(){
        
    }

    public void init(PortMan pm) throws Exception {
        logger.info("initializing");
        rotationPigeon = new Pigeon(pm.acquirePort(PortMan.can_21_label, "Navigation.Pigeon"));
        pigeonAngleEntry    = tab.add("Pigeon Angle", currentPigeonAngle).getEntry();

        leftLidar = new LidarPWM(pm.acquirePort(PortMan.digital0_label, "Navigation.leftLidar"));
        rightLidar = new LidarPWM(pm.acquirePort(PortMan.digital1_label, "Navigation.rightLidar"));

        leftLidarDistanceEntry    = tab.add("Left Lidar Distance", leftLidarDistance).getEntry();
        rightLidarDistanceEntry   = tab.add("Right Lidar Distance", rightLidarDistance).getEntry();
        lidarsAreSquareEntry   = tab.add("Lidars Square", lidarsAreSquare).getEntry();

        NetTableManager.updateValue("Health Map", "Navigation Initialized", true);
    }

    public double getCurrentAbsoluteHeadingError(double angle) {
        return rotationPigeon.getAbolsuteAngleError(angle);
    }

    public double getCurentHeading() {
        return rotationPigeon.getCurrentAngle();
    }

    public void checkValues() {
        currentPigeonAngle  = rotationPigeon.getCurrentAngle();
        pigeonAngleEntry.setDouble(currentPigeonAngle);

        leftLidarDistance = leftLidar.getDistance();
        rightLidarDistance = rightLidar.getDistance();

        leftLidarDistanceEntry.setDouble(leftLidarDistance);
        rightLidarDistanceEntry.setDouble(rightLidarDistance);

        lidarsAreSquareEntry.setBoolean(Math.abs(leftLidarDistance - rightLidarDistance) < 0.5);
    }
    protected void initDefaultCommand() {
        this.setDefaultCommand(new NavigationDefault());
    }
}