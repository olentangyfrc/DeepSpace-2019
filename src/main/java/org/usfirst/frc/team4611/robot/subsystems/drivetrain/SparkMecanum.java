package org.usfirst.frc.team4611.robot.subsystems.drivetrain;

import edu.wpi.first.wpilibj.Timer;

import java.util.HashMap;
import java.util.logging.Logger;

import org.usfirst.frc.team4611.robot.OI;
import org.usfirst.frc.team4611.robot.networktables.NetTableManager;
import org.usfirst.frc.team4611.robot.subsystems.PortMan;
import org.usfirst.frc.team4611.robot.subsystems.drivetrain.commands.Move;
import org.usfirst.frc.team4611.robot.subsystems.drivetrain.interfaces.SparkDriveTrain;

public class SparkMecanum extends SparkDriveTrain {

    private static Logger logger = Logger.getLogger(SparkMecanum.class.getName());

    private double velocity1;
	private double velocity2;
	private double velocity3;
	private double velocity4;

	private int velocityInvert1 = 1;
	private int velocityInvert2 = -1;
	private int velocityInvert3 = -1;
	private int velocityInvert4 = 1;

    public double encoderPosFrontLeft = 0;
    public double encoderPosFrontRight = 0;
    public double encoderPosBackLeft = 0;
    public double encoderPosBackRight = 0;
    
    private double velocityScalarFront = 1.0;

    @Override
    public void init(PortMan pm) throws Exception {
        super.init(pm);
        NetTableManager.updateValue("Spark Mecanum", "Strafe adjustment", Double.valueOf(velocityScalarFront));
    }

    @Override
    public void move() {
        if (!inited) return;

        double strafingAdjustment = 1.0;
        velocityScalarFront = ((Double) NetTableManager.getValue("Spark Mecanum", "Strafe adjustment", Double.valueOf(velocityScalarFront)))
                                  .doubleValue();

        double YVal = -OI.getInstance().getLeftJoystickYValue();
		double XVal = OI.getInstance().getLeftJoystickXValue();
        double ZVal = OI.getInstance().getRightJoystickXValue();
        
        // make front motor adjustments if purely strafing laterally
        if (YVal == 0.0 && ZVal == 0.0 && XVal > 0) {
            strafingAdjustment = velocityScalarFront;
            logger.info("Strafing adjustment right [" + strafingAdjustment + "]");
            velocity1 = ((YVal + XVal + ZVal) * (velocityInvert1)) * strafingAdjustment;
            velocity2 = ((YVal - XVal - ZVal) * (velocityInvert2)) * strafingAdjustment;
            velocity3 = ((YVal + XVal - ZVal) * (velocityInvert3));
            velocity4 = ((YVal - XVal + ZVal) * (velocityInvert4));
        } else if(YVal == 0.0 && ZVal == 0.0 && XVal < 0) {
            strafingAdjustment = velocityScalarFront;
            logger.info("Strafing adjustment left [" + strafingAdjustment + "]");
            velocity1 = ((YVal + XVal + ZVal) * (velocityInvert1)) * strafingAdjustment;
            velocity2 = ((YVal - XVal - ZVal) * (velocityInvert2)) * strafingAdjustment;
            velocity3 = ((YVal + XVal - ZVal) * (velocityInvert3));
            velocity4 = ((YVal - XVal + ZVal) * (velocityInvert4));
        } else {
            velocity1 = ((YVal + XVal + ZVal) * (velocityInvert1));
            velocity2 = ((YVal - XVal - ZVal) * (velocityInvert2));
            velocity3 = ((YVal + XVal - ZVal) * (velocityInvert3));
            velocity4 = ((YVal - XVal + ZVal) * (velocityInvert4));
        }
        
        frontLeft.set(velocity1);
        frontRight.set(velocity2);
        backLeft.set(velocity4);
        backRight.set(velocity3);

        /*
        if (YVal == 0.0 && ZVal == 0.0) {
            logger.info("fl [" + velocity1 + "] fr [" + velocity2 + "] br [" + velocity3 + "] bl [" + velocity4 + "]");
        }
        */
    }

    @Override
    public void setupTalons() {
        frontLeft.getPIDController().setP(0.65);
        frontRight.getPIDController().setP(0.65);
        backLeft.getPIDController().setP(0.65);
        backRight.getPIDController().setP(0.65);

    }


    @Override
    public void moveLateral(double speed) {

    }

    @Override
    public void moveHorizontal(double speed) {

    }

    @Override
    public void moveLateralInches(double inches) {}

    @Override
    public void moveHorizontalInches(double inches) {}

    @Override
    public void setTrajectorySpeeds() {

        frontLeft.set(velocity1);
		frontRight.set(velocity2);
		backLeft.set(velocity4);
		backRight.set(velocity3);

    }

    @Override
    public int getAverageSensorPos() {
        return (int)(Math.abs(frontLeftEncoder.getPosition()) 
                                + Math.abs(frontRightEncoder.getPosition()) 
                                + Math.abs(backLeftEncoder.getPosition()) 
                                + Math.abs(backRightEncoder.getPosition()))/4;
    }

    @Override
    public void moveVelocityAuton(double speed) {
        double velocity1 = 4 * (speed * velocityInvert1);
		double velocity2 = 4 * (speed * velocityInvert2);
		double velocity3 = 4 * (speed * velocityInvert3);
		double velocity4 = 4 * (speed * velocityInvert4);

		frontLeft.set(velocity1);
		frontRight.set(velocity2);
		backLeft.set(velocity4);
		backRight.set( velocity3);
    }

    @Override
    public void rotate(double velocity) {

    }

    public double getMetersTraveled() {
		return getAverageSensorPos() / METER_PU_MULT;

	}

    public void moveAtSpeed(double speed1, double speed2, double speed3, double speed4) {

		frontLeft.set(speed1);
		frontRight.set(speed2);
		backLeft.set(speed4);
		backRight.set(speed3);

	}

    @Override
    public void activateTurbo() {

    }

    @Override
    public void deactivateTurbo() {

    }

    @Override
    protected void initDefaultCommand() {
        this.setDefaultCommand(new Move());
    }

    @Override
    public void moveSideways(double s) {

    }

}