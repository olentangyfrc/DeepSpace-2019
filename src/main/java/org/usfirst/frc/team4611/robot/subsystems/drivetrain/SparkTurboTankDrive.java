package org.usfirst.frc.team4611.robot.subsystems.drivetrain;

import org.usfirst.frc.team4611.robot.OI;
import org.usfirst.frc.team4611.robot.subsystems.drivetrain.commands.Move;
import org.usfirst.frc.team4611.robot.subsystems.drivetrain.interfaces.SparkDriveTrain;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

public class SparkTurboTankDrive extends SparkDriveTrain {

    private final int turboSolOpenPort = 0;
    private final int turboSolClosePort = 1;

    private DoubleSolenoid turboSol = new DoubleSolenoid(turboSolOpenPort, turboSolClosePort);

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

    @Override
    public void move() {

        double LYVal = -OI.getInstance().getLeftJoystickYValue();
        double RYVal = OI.getInstance().getRightJoystickYValue();


        velocity1 = 4 * (LYVal * YValScaler1);
        velocity2 = 4 * (RYVal * YValScaler2);

        frontLeft.set(velocity1);
        frontRight.set(velocity2);

        leftSideEntry.setDouble(velocity1);
        rightSideEntry.setDouble(velocity2);
    }

    @Override
    public void setupTalons() {

        frontLeft.getPIDController().setP(0.65);
        frontRight.getPIDController().setP(0.65);
        backLeft.getPIDController().setP(0.65);
        backRight.getPIDController().setP(0.65);

        leftSideEntry = tab.add("Leftside Velocity", -1.0).getEntry();
        rightSideEntry = tab.add("Rightside Velocity", -1.0).getEntry();

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
    public void setTrajectorySpeeds() {

    }

    @Override
    public int getAverageSensorPos() {
        return (int)((Math.abs(frontLeftEncoder.getPosition()) + Math.abs(frontRightEncoder.getPosition())
        + Math.abs (backLeftEncoder.getPosition()) + Math.abs(backRightEncoder.getPosition()))
        / 4);
    }

    @Override
    public void moveVelocityAuton(double YVal) {

    }

    @Override
    public void rotate(double velocity) {

    }

    @Override
    public double getVelocity() {
        return 0;
    }

    @Override
    public void activateTurbo() {
        turboSol.set(Value.kForward);
    }

    @Override
    public void deactivateTurbo() {
        turboSol.set(Value.kReverse);

    }

    @Override
    protected void initDefaultCommand() {
        this.setDefaultCommand(new Move());
    }

    
}