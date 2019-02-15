package org.usfirst.frc.team4611.robot.subsystems.pixyLineCam;

import java.util.logging.Logger;

import org.usfirst.frc.team4611.robot.networktables.NetTableManager;
import org.usfirst.frc.team4611.robot.subsystems.pixyLineCam.commands.PollPixy;
import org.usfirst.frc.team4611.robot.subsystems.vision.commands.PollNetworkTable;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import io.github.pseudoresonance.pixy2api.Pixy2;
import io.github.pseudoresonance.pixy2api.Pixy2CCC;
import io.github.pseudoresonance.pixy2api.Pixy2Line;

public class PixyLineCam extends Subsystem{

    private Pixy2 pixy;
    private Pixy2Line line;


    Logger logger = Logger.getLogger(PixyLineCam.class.getName());


    private ShuffleboardTab tab;
    private NetworkTableEntry signature;
    private NetworkTableEntry x;
    private NetworkTableEntry y;
    private NetworkTableEntry width;
    private NetworkTableEntry height;
    private NetworkTableEntry angle;
    private NetworkTableEntry index;
    private NetworkTableEntry age;


    public void PixyCam() {

        
    }

    public void init() {

        logger.info("initializing");
        tab = Shuffleboard.getTab("PixyLineCam");
        logger.info("Creating Pixy with link type of SPI");
        pixy = Pixy2.createInstance(Pixy2.LinkType.SPI);
        pixy.init(0);
        logger.info("Here comes the version! :) " + pixy.getVersionInfo().toString());
        line = pixy.getLine();

        tab = Shuffleboard.getTab("Health Map");
        NetTableManager.updateValue("Health Map", "PixyInitialize", true);

        
        signature = tab.add("Pixy Signature", 0).getEntry();
        x = tab.add("Pixy x Coordinate", 0).getEntry();
        y = tab.add("Pixy y Coordinate", 0).getEntry();
        width = tab.add("Pixy Width", 0).getEntry();
        height = tab.add("Pixy Height", 0).getEntry();
        angle = tab.add("Pixy Angle", 0).getEntry();
        index = tab.add("Pixy Index", 0).getEntry();
        age = tab.add("Pixy Age", 0).getEntry();
        
      }

    public Pixy2Line getLine() {
        return line;
    }

    public void writeBlock(Pixy2Line.Block block) {
        signature.setNumber(block.getSignature());
        x.setNumber(block.getX());
        y.setNumber(block.getY());
        width.setNumber(block.getWidth());
        height.setNumber(block.getHeight());
        angle.setNumber(block.getAngle());
        index.setNumber(block.getIndex());
        age.setNumber(block.getAge());

    }

    protected void initDefaultCommand() {
        setDefaultCommand(new PollPixy());
    } 

}