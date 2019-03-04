package org.usfirst.frc.team4611.robot.subsystems.IntakeAdjuster;

import java.util.logging.Logger;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import org.usfirst.frc.team4611.robot.OzoneException;
import org.usfirst.frc.team4611.robot.subsystems.PortMan;
import org.usfirst.frc.team4611.robot.subsystems.IntakeAdjuster.commands.IntakeAdjusterDefault;
import org.usfirst.frc.team4611.robot.subsystems.navigation.sensors.Potentiometer;

import edu.wpi.first.networktables.NetworkTableEntry;
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
    
    private double minPos = 0.530;
    private double maxPos = 0.589;

    private double pos1 = 0, 
    pos2 = 0, 
    pos3 = 0, 
    pos4 = 0;

    private Potentiometer pot;

    public void init(PortMan pm) throws OzoneException {
        intakeAdjuster = new WPI_TalonSRX(pm.acquirePort(PortMan.can_23_label, "Intake.intakeAdjuster"));

        pot = new Potentiometer(pm.acquirePort(PortMan.analog1_label, "IntakeAdjuster Pot"), minPos, maxPos);

        initSB();
    }

    public boolean isLogging(){
        logging = isLogging.getBoolean(false);
        return logging;
    }

    public void spinIntakeAdjusterForward() {

        if(pot.getRawValue() < maxPos) {
            intakeAdjuster.set(ControlMode.PercentOutput, (adjusterVelocity.getDouble(power)));
        } else {
            intakeAdjuster.set(ControlMode.PercentOutput, 0);
        }
    }
    
    public void spinIntakeAdjusterBackward() {

        if(pot.getRawValue() > minPos) {
            intakeAdjuster.set(ControlMode.PercentOutput, -(adjusterVelocity.getDouble(power)));
        } else {
            intakeAdjuster.set(ControlMode.PercentOutput, 0);
        }
    }
    
    public void stopIntakeAdjuster(){
        intakeAdjuster.set(ControlMode.PercentOutput, 0); 
        currentAdjusterPosition.setDouble(pot.getPercentOutput()); 
    }

    public static enum HappyPositions {LEVEL1, LEVEL2, LEVEL3, LEVEL4};

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

        if(finalTarget - pot.getPercentOutput() < -.01) {
            spinIntakeAdjusterBackward();
        }
        else if(finalTarget - pot.getPercentOutput() > .01) {
            spinIntakeAdjusterForward();
        }
        else{
            this.stopIntakeAdjuster();
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

    public void initSB() {

        isLogging = tab.add("Intake Adjuster Logging", false).withPosition(5, 0).withSize(1, 1).getEntry();

        adjusterVelocity = tab.add("IntakeAdjuster PercentOutput", power).getEntry();
        currentAdjusterPosition = tab.add("Intake Adjuster Posittion", 1).getEntry();
        adjusterPosition1Entry = tab.add("Adjuster Position1", .5).withPosition(0, 1).withSize(1, 1).getEntry();
        adjusterPosition2Entry = tab.add("Adjuster Position2", .5).withPosition(0, 2).withSize(1, 1).getEntry();
        adjusterPosition3Entry = tab.add("Adjuster Position3", .5).withPosition(0, 3).withSize(1, 1).getEntry();
        adjusterPosition4Entry = tab.add("Adjuster Position4", .5).withPosition(0, 4).withSize(1, 1).getEntry();

        potRawEntry = tab.add("Raw Pot", 0).withPosition(0, 0).withSize(1, 1).getEntry();
        potPercentEntry = tab.add("Percent Pot", 0).withPosition(1, 0).withSize(1, 1).getEntry();

        bottomHardStopEntry = tab.add("Bottom\nHard Stop", minPos).withPosition(3, 1).withSize(1, 1).getEntry();
        topHardStopEntry = tab.add("Top\nHard Stop", maxPos).withPosition(3, 2).withSize(1, 1).getEntry();
    }

	public void updateValues() {
        pos1 = adjusterPosition1Entry.getDouble(pos1);
        pos2 = adjusterPosition2Entry.getDouble(pos2);
        pos3 = adjusterPosition3Entry.getDouble(pos3);
        pos4 = adjusterPosition4Entry.getDouble(pos4);

        minPos = bottomHardStopEntry.getDouble(minPos);
        maxPos = topHardStopEntry.getDouble(maxPos);

        potRawEntry.setDouble(pot.getRawValue());
        potPercentEntry.setDouble(pot.getPercentOutput());
	}
}