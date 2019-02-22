package org.usfirst.frc.team4611.robot.subsystems.Roller;

import java.util.logging.Logger;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import org.usfirst.frc.team4611.robot.networktables.NetTableManager;
import org.usfirst.frc.team4611.robot.subsystems.PortMan;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;

public class Roller extends Subsystem {

    private static Logger logger = Logger.getLogger(Roller.class.getName());
    static private ShuffleboardTab tab = Shuffleboard.getTab("Roller");

    private WPI_TalonSRX roller;

    private NetworkTableEntry isLogging;
    private NetworkTableEntry rollerVelocity;
    private NetworkTableEntry motorSpeed;
    private NetworkTableEntry slowVelocity;

    private boolean logging = false;
    private double rollerPercent = .75;
    private double slowPercent = .5;
    private int maxRPM = 2400;


    public void init(PortMan pm) throws Exception {

        logger.entering(Roller.class.getName(), "init()");
        
        roller = new WPI_TalonSRX(pm.acquirePort(PortMan.can_20_label, "Roller.talon"));

        NetTableManager.updateValue("Health Map", "Roller Initialize", true);
        isLogging = tab.add("Roller Logging", false).getEntry();
        rollerVelocity = tab.add("Roller Velocity", rollerPercent).getEntry();
        slowVelocity = tab.add("Slow Roller Percent", slowPercent).getEntry();

        roller.config_kP(0, .5, 0);
        roller.config_kI(0, 0, 0);
        roller.config_kD(0, 0, 0);
        roller.config_kF(0, 0, 0);
        roller.configMotionCruiseVelocity(4096, 0);
        roller.configMotionAcceleration(4096,0);

        logger.exiting(Roller.class.getName(), "init()");
    }

    public boolean isLogging(){
        logging = isLogging.getBoolean(false);
        return logging;
    }
   

    public void spinRollerForward() {

        logger.entering(Roller.class.getName(), "spinIndiWheelBackForward()");

        roller.set(ControlMode.Velocity, (int)(maxRPM * rollerVelocity.getDouble(rollerPercent)));        
    
        logger.exiting(Roller.class.getName(), "spinIndiWheelBackForward()");
    
    }
    

    public void spinRollerSlowForward() {

        logger.entering(Roller.class.getName(), "spinIndiWheelBackSlowForward()");
    
        roller.set(ControlMode.Velocity, ((int)(maxRPM * rollerVelocity.getDouble(rollerPercent)))*slowVelocity.getDouble(slowPercent));        
    
        logger.exiting(Roller.class.getName(), "spinIndiWheelBackSlowForward()");
        
    }

    public void spinRollerBackward() {

        logger.entering(Roller.class.getName(), "spinIndiWheelBackBackward()");

        roller.set(ControlMode.Velocity, (int)(maxRPM * -rollerVelocity.getDouble(rollerPercent)));
    
        logger.exiting(Roller.class.getName(), "spinIndiWheelBackBackward()");

    
    }
    

    public void stopRoller(){

        logger.entering(Roller.class.getName(), "stopIndiWheelBack()");

        roller.set(ControlMode.Velocity, 0); 

        logger.exiting(Roller.class.getName(), "stopIndiWheelBack()");

    }
    @Override
    protected void initDefaultCommand() {

    }
}
