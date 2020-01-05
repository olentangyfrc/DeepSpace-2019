package org.usfirst.frc.team4611.robot.subsystems.drivetrain;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;

import org.usfirst.frc.team4611.robot.OI;
import org.usfirst.frc.team4611.robot.subsystems.PortMan;
import org.usfirst.frc.team4611.robot.subsystems.drivetrain.commands.Move;
import org.usfirst.frc.team4611.robot.subsystems.drivetrain.interfaces.DriveTrain;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

public class TurboTankDrive extends DriveTrain {

    DoubleSolenoid turboSol;
    private int maxRPM = 1200; // Reduced from 1200

    public double pVal = .65;
    public int interval = 10;

    private double velocity1;
    private double velocity2;

    private double YValScaler1 = 1;
    private double YValScaler2 = 1;

    public TurboTankDrive() {
    }

    public void init(PortMan pm) throws Exception {
        super.init(pm);
        turboSol = new DoubleSolenoid(pm.acquirePort(PortMan.pcm0_label, "Turbo.Open"),
                pm.acquirePort(PortMan.pcm1_label, "Turbo.Close"));
        
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
    }

    @Override
    public void move() {
        double LYVal = -OI.getInstance().getLeftJoystickYValue();
        double RYVal = OI.getInstance().getRightJoystickYValue();

        velocity1 = 4 * (maxRPM * (LYVal * YValScaler1));
        velocity2 = 4 * (maxRPM * (RYVal * YValScaler2));

        frontLeft.set(ControlMode.Velocity, velocity1);
        frontRight.set(ControlMode.Velocity, velocity2);
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

    public void setTrajectorySpeeds() {

    }

    public double getVelocity() {
        return 0;
    }

    public void activateTurbo() {
        turboSol.set(Value.kForward);
    }

    public void deactivateTurbo() {
        turboSol.set(Value.kReverse);
    }

    @Override
    public void moveSideways(double speed) {

    }
}