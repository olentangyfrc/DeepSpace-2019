package org.usfirst.frc.team4611.robot.subsystems.drivetrain;

import java.util.HashMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;

import org.usfirst.frc.team4611.robot.OI;
import org.usfirst.frc.team4611.robot.networktables.NetTableManager;
import org.usfirst.frc.team4611.robot.subsystems.PortMan;
import org.usfirst.frc.team4611.robot.subsystems.drivetrain.commands.Move;
import org.usfirst.frc.team4611.robot.subsystems.drivetrain.interfaces.DriveTrain;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;

public class TankDrive extends DriveTrain {

    private int maxRPM = 1200;

    public double pVal = .65;
    public int interval = 10;

    private double velocity1;
    private double velocity2;

    private double YValScaler1 = 1;
    private double YValScaler2 = 1;

    private int velocityInvert1 = 1;
    private int velocityInvert2 = -1;

    private String tankSubtable = "Tank";

    private String velocity1ID = "Velocity1";
    private String velocity2ID = "Velocity2";

    private NetworkTableEntry leftSideEntry;
    private NetworkTableEntry rightSideEntry;

    public TankDrive() {
    }

    public void init(PortMan pm) throws Exception {
        super.init(pm);
    }

    public void setupTalons() {
        frontLeft.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
        frontRight.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
        backLeft.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
        backRight.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);

        frontLeft.setSelectedSensorPosition(0, 0, 0);
        frontRight.setSelectedSensorPosition(0, 0, 0);
        backLeft.setSelectedSensorPosition(0, 0, 0);
        backRight.setSelectedSensorPosition(0, 0, 0);

        frontLeft.config_kP(0, pVal, interval);
        frontRight.config_kP(0, pVal, interval);
        backLeft.config_kP(0, pVal, interval);
        backRight.config_kP(0, pVal, interval);

        frontLeft.config_kI(0, 0.000, 0);
        frontRight.config_kI(0, 0.000, 0);
        backLeft.config_kI(0, 0.000, 0);
        backRight.config_kI(0, 0.000, 0);

        frontLeft.config_kD(0, 0, 0);
        frontRight.config_kD(0, 0, 0);
        backLeft.config_kD(0, 0, 0);
        backRight.config_kD(0, 0, 0);

        frontLeft.setSensorPhase(true);
        frontRight.setSensorPhase(true);
        backLeft.setSensorPhase(true);
        backRight.setSensorPhase(true);

        backLeft.follow(frontLeft);
        backRight.follow(frontRight);

        backLeft.setInverted(false);
        backRight.setInverted(false);

        leftSideEntry = tab.add("Leftside Velocity", -1.0).getEntry();
        rightSideEntry = tab.add("Rightside Velocity", -1.0).getEntry();
    }

    @Override
    public void move() {
        double LYVal = -OI.getInstance().getLeftXboxYValue();
        double RYVal = OI.getInstance().getRightXboxYValue();


        velocity1 = 4 * (maxRPM * (LYVal * YValScaler1));
        velocity2 = 4 * (maxRPM * (RYVal * YValScaler2));

        frontLeft.set(ControlMode.Velocity, velocity1);
        frontRight.set(ControlMode.Velocity, velocity2);

        leftSideEntry.setDouble(velocity1);
        rightSideEntry.setDouble(velocity2);
    }

    @Override
    public void moveLateral(double speed) {

    }

    @Override
    public void moveHorizontal(double speed) {

    }

    @Override
    public void moveLateralInches(double inches) {

    }

    @Override
    public void moveHorizontalInches(double inches) {

    }

    @Override
    public int getAverageSensorPos() {
        return (Math.abs(frontLeft.getSelectedSensorPosition(0)) + Math.abs(frontRight.getSelectedSensorPosition(0))
                + Math.abs(backLeft.getSelectedSensorPosition(0)) + Math.abs(backRight.getSelectedSensorPosition(0)))
                / 4;
    }

    @Override
    public void resetEncoders() {
        frontLeft.setSelectedSensorPosition(0, 0, 0);
        frontRight.setSelectedSensorPosition(0, 0, 0);
        backLeft.setSelectedSensorPosition(0, 0, 0);
        backRight.setSelectedSensorPosition(0, 0, 0);
    }

    @Override
    protected void initDefaultCommand() {
        this.setDefaultCommand(new Move());
    }

    public void moveVelocityAuton(double speed) {

    }

    public void rotate(double d) {

    }

    /*
     * public void followTrajectory(Trajectory t) {
     * 
     * }
     */

    public void setTrajectorySpeeds() {

    }

    public double getVelocity() {
        return 0;
    }

    @Override
    public void moveSideways(double speed) {

    }

    @Override
    public void activateTurbo() {

    }

    @Override
    public void deactivateTurbo() {

    }
}