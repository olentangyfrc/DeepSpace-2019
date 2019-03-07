package org.usfirst.frc.team4611.robot.subsystems.IntakeAdjuster;

import java.awt.Robot;
import java.util.logging.Logger;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import org.usfirst.frc.team4611.robot.OzoneException;
import org.usfirst.frc.team4611.robot.subsystems.PortMan;
import org.usfirst.frc.team4611.robot.subsystems.IntakeAdjuster.commands.IntakeAdjusterDefault;
import org.usfirst.frc.team4611.robot.subsystems.navigation.sensors.Potentiometer;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.AnalogOutput;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;

public class IntakeAdjuster extends Subsystem {

    private static Logger logger = Logger.getLogger(IntakeAdjuster.class.getName());

    private WPI_TalonSRX intakeAdjuster;

    private NetworkTableEntry isLogging;
    private NetworkTableEntry adjusterVelocity;
    private NetworkTableEntry currentAdjusterPosition;
    

    private boolean logging = false;
    
    private double power = 1;
    private int intakeSpeed = 1600;
    
    private double minPos = 3.95;
    private double maxPos = 4.26;

    private AnalogPotentiometer pot;

    public void init(PortMan pm) throws OzoneException {
        intakeAdjuster = new WPI_TalonSRX(pm.acquirePort(PortMan.can_23_label, "Intake.intakeAdjuster"));

		pot = new AnalogPotentiometer(pm.acquirePort(PortMan.analog1_label, "IntakeAdjuster Pot"));

        initSB();
    }

    public boolean isLogging(){
        logging = isLogging.getBoolean(false);
        return logging;
    }

    public void raiseIntakeAdjuster() {

        if(getVoltage() < maxPos) {
            intakeAdjuster.set(ControlMode.PercentOutput, (adjusterVelocity.getDouble(power)));
        } else {
            intakeAdjuster.set(ControlMode.PercentOutput, 0);
        }
    }
    
    public void lowerIntakeAdjuster() {
       intakeAdjuster.set(ControlMode.PercentOutput, -(adjusterVelocity.getDouble(power)));
    }
    
    public void stopIntakeAdjuster(){
        intakeAdjuster.set(ControlMode.PercentOutput, 0); 
        currentAdjusterPosition.setDouble(getVoltage()); 
    }

    public static enum HappyPositions {LEVEL1, LEVEL2, LEVEL3, LEVEL4};

    private double pos1 = .2, pos2 = 0.5, pos3 = 0.7, pos4 = 0.9;

    private double getPercentOutput() {
         return ((getVoltage() - minPos) / (maxPos - minPos));
    }

    private double getVoltage() {
        return (pot.get() * RobotController.getVoltage5V());
    }

    public boolean moveToPos(HappyPositions p) {
        double finalTarget = 0;

        if(p == HappyPositions.LEVEL1) {
            finalTarget = pos1;
        } else if(p == HappyPositions.LEVEL2) {
            finalTarget = pos2;
        } else if(p == HappyPositions.LEVEL3) {
            finalTarget = pos3;
        } else if(p == HappyPositions.LEVEL4) {
            finalTarget = pos4;
        } 
        
        boolean stop = false;

        if (getVoltage() < minPos || getVoltage() >= maxPos) {
            logger.warning("Intake Adjuster Pot returning unwanted [" + getVoltage() + "] value");
            return true;
        }

        logger.info("target [" + finalTarget + "] getPercentOutput [" + getPercentOutput() + "] diff [" + (finalTarget - getPercentOutput()) + "]");
        if(finalTarget - getPercentOutput() < -.01) {
            lowerIntakeAdjuster();
        }
        else if(finalTarget - getPercentOutput() > .01) {
            raiseIntakeAdjuster();
        }
        else {
            stopIntakeAdjuster();
            stop = true;
        }
        return stop;
    }

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new IntakeAdjusterDefault());
    }
    
    private ShuffleboardTab tab = Shuffleboard.getTab("IntakeAdjuster");

    private NetworkTableEntry adjusterPosition1Entry;
    private NetworkTableEntry adjusterPosition2Entry;
    private NetworkTableEntry adjusterPosition3Entry;
    private NetworkTableEntry adjusterPosition4Entry;

    private NetworkTableEntry potRawEntry;
    private NetworkTableEntry potPercentEntry;

    private NetworkTableEntry bottomHardStopEntry;
    private NetworkTableEntry topHardStopEntry;
    private NetworkTableEntry adjusterCurrentEntry;
    private NetworkTableEntry potWorking;

    public void initSB() {

        isLogging = tab.add("Intake Adjuster Logging", false).withPosition(5, 0).withSize(1, 1).getEntry();

        adjusterVelocity = tab.add("IntakeAdjuster PercentOutput", power).getEntry();
        currentAdjusterPosition = tab.add("Intake Adjuster Posittion", 1).getEntry();
        adjusterPosition1Entry = tab.add("Adjuster Position1", pos1).withPosition(0, 1).withSize(1, 1).getEntry();
        adjusterPosition2Entry = tab.add("Adjuster Position2", pos2).withPosition(0, 2).withSize(1, 1).getEntry();
        adjusterPosition3Entry = tab.add("Adjuster Position3", pos3).withPosition(0, 3).withSize(1, 1).getEntry();
        adjusterPosition4Entry = tab.add("Adjuster Position4", pos4).withPosition(0, 4).withSize(1, 1).getEntry();

        potRawEntry = tab.add("Raw Pot", 0).withPosition(0, 0).withSize(1, 1).getEntry();
        potPercentEntry = tab.add("Percent Pot", 0).withPosition(1, 0).withSize(1, 1).getEntry();
        potWorking = tab.add("Pot Working", true).withPosition(9, 0).withSize(1, 1).getEntry();

        bottomHardStopEntry = tab.add("Bottom\nHard Stop", minPos).withPosition(3, 1).withSize(1, 1).getEntry();
        topHardStopEntry = tab.add("Top\nHard Stop", maxPos).withPosition(3, 2).withSize(1, 1).getEntry();

        adjusterCurrentEntry = tab.add("Adjuster Current", 0.0).withWidget("Graph").withSize(3, 3).withPosition(5, 0).getEntry();
    }

	public void updateValues() {
        pos1 = adjusterPosition1Entry.getDouble(pos1);
        pos2 = adjusterPosition2Entry.getDouble(pos2);
        pos3 = adjusterPosition3Entry.getDouble(pos3);
        pos4 = adjusterPosition4Entry.getDouble(pos4);

        minPos = bottomHardStopEntry.getDouble(minPos);
        maxPos = topHardStopEntry.getDouble(maxPos);

        potRawEntry.setDouble(getVoltage());
        potPercentEntry.setDouble(getPercentOutput());
        potWorking.setBoolean(getVoltage() > 0.1);

        adjusterCurrentEntry.setDouble(intakeAdjuster.getOutputCurrent());
	}
}