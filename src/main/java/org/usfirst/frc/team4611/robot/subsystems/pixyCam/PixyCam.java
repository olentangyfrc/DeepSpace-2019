package org.usfirst.frc.team4611.robot.subsystems.pixyCam;

import java.util.logging.Logger;

import org.usfirst.frc.team4611.robot.subsystems.pixyCam.commands.PollPixy;
import org.usfirst.frc.team4611.robot.subsystems.vision.commands.PollNetworkTable;



import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import io.github.pseudoresonance.pixy2api.Pixy2;
import io.github.pseudoresonance.pixy2api.Pixy2CCC;

public class PixyCam extends Subsystem{

    private Pixy2 pixy;
    private Pixy2CCC ccc;


    Logger logger = Logger.getLogger(PixyCam.class.getName());

    private ShuffleboardTab tab;
    private NetworkTableEntry loggerEntry;

    public void PixyCam() {

        
    }

    public void init() {

        logger.info("initializing");
        tab = Shuffleboard.getTab("PixyCam");
        logger.info("Creating Pixy with link type of SPI");
        pixy = Pixy2.createInstance(Pixy2.LinkType.SPI);
        pixy.init(0);
        logger.info("Here comes the version! :) " + pixy.getVersionInfo().toString());
        ccc = pixy.getCCC();

      }

    public Pixy2CCC getCCC(){
        return ccc;
    }

    protected void initDefaultCommand() {
        setDefaultCommand(new PollPixy());
    } 

}