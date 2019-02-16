package org.usfirst.frc.team4611.robot.subsystems.pixyLineCam;

import java.util.logging.Logger;

import org.usfirst.frc.team4611.robot.networktables.NetTableManager;
import org.usfirst.frc.team4611.robot.subsystems.pixyLineCam.commands.PollPixyLine;
//import org.usfirst.frc.team4611.robot.subsystems.vision.commands.PollNetworkTable;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import io.github.pseudoresonance.pixy2api.Pixy2;
import io.github.pseudoresonance.pixy2api.Pixy2Line;

public class PixyLineCam extends Subsystem{

    private Pixy2Line pixy;
    private Pixy2Line line;


    Logger logger = Logger.getLogger(PixyLineCam.class.getName());


    private ShuffleboardTab tab;
    private NetworkTableEntry x0;
    private NetworkTableEntry y0;
    private NetworkTableEntry x1;
    private NetworkTableEntry y1;
    private NetworkTableEntry index;
    private NetworkTableEntry flags;


    public void PixyLineCam() {

        
    }

    public void init() {

        logger.info("initializing");
        tab = Shuffleboard.getTab("PixyLineCam");
        logger.info("Creating Pixy with link type of SPI");
        pixy = Pixy2Line.createInstance(Pixy2.LinkType.SPI);
        pixy.init(0);
        logger.info("Here comes the version! :) " + pixy.getVersionInfo().toString());
        line = pixy.getLine();

        tab = Shuffleboard.getTab("Health Map");
        NetTableManager.updateValue("Health Map", "PixyInitialize", true);

        
        x0 = tab.add("Pixy x0", 0).getEntry();
        y0 = tab.add("Pixy y0", 0).getEntry();
        x1 = tab.add("Pixy x1", 0).getEntry();
        y1 = tab.add("Pixy y1", 0).getEntry();
        flags = tab.add("Pixy Flags", 0).getEntry();
        index = tab.add("Pixy Index", 0).getEntry();
        
      }

    public Pixy2Line getLine() {
        return this.line;
    }

    public void writeLine(Pixy2Line.Vector line) {
        x0.setNumber(line.getX0());
        y0.setNumber(line.getY0());
        x1.setNumber(line.getX1());
        y1.setNumber(line.getY1());
        index.setNumber(line.getIndex());
        flags.setNumber(line.getFlags());

    }

    protected void initDefaultCommand() {
        setDefaultCommand(new PollPixyLine());
    } 


}