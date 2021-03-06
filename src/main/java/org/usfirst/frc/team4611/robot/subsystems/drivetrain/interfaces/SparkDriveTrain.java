
package org.usfirst.frc.team4611.robot.subsystems.drivetrain.interfaces;

import org.usfirst.frc.team4611.robot.subsystems.PortMan;

import java.util.logging.Logger;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public abstract class SparkDriveTrain extends DriveTrain {

    public final Logger logger = Logger.getLogger(SparkDriveTrain.class.getName());
    protected boolean inited = false;

    public CANSparkMax frontLeft;
	public CANSparkMax frontRight;
	public CANSparkMax backLeft;
    public CANSparkMax backRight;
    
    public CANEncoder frontLeftEncoder;
    public CANEncoder frontRightEncoder;
    public CANEncoder backLeftEncoder;
    public CANEncoder backRightEncoder;

    public double frontLeftEncoderPos = 0;
    public double frontRightEncoderPos = 0;
    public double backLeftEncoderPos = 0;
    public double backRightEncoderPos = 0;
    
    public void init(PortMan pm) throws Exception {
        logger.info("initializing");

        frontLeft = new CANSparkMax(pm.acquirePort(PortMan.can_10_label, "DriveTrain.FrontLeft"), MotorType.kBrushless);
        frontRight = new CANSparkMax(pm.acquirePort(PortMan.can_11_label, "DriveTrain.FrontRight"), MotorType.kBrushless);
        backLeft = new CANSparkMax(pm.acquirePort(PortMan.can_13_label, "DriveTrain.BackLeft"), MotorType.kBrushless);
        backRight = new CANSparkMax(pm.acquirePort(PortMan.can_12_label, "DriveTrain.BackRight"), MotorType.kBrushless);

        frontLeftEncoder = new CANEncoder(frontLeft);
        frontRightEncoder = new CANEncoder(frontRight);
        backLeftEncoder = new CANEncoder(backLeft);
        backRightEncoder = new CANEncoder(backRight);

        setupTalons();
        inited = true;
    }

    @Override
    public void resetEncoders() {
        frontLeftEncoderPos = frontLeftEncoder.getPosition();
        frontRightEncoderPos = frontRightEncoder.getPosition();
        backLeftEncoderPos= backLeftEncoder.getPosition();
        backRightEncoderPos = backRightEncoder.getPosition();
    }
}