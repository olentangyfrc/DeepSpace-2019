package org.usfirst.frc.team4611.robot.subsystems.pixyLineCam.commands;

import java.util.ArrayList;
import java.util.logging.Logger;

import org.usfirst.frc.team4611.robot.subsystems.SubsystemFactory;
import org.usfirst.frc.team4611.robot.subsystems.pixyLineCam.PixyLineCam;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import io.github.pseudoresonance.pixy2api.Pixy2Line;
import io.github.pseudoresonance.pixy2api.Pixy2Line.Vector;

public class PollPixyLine extends Command{

    private double lastUpdateTime = 0;
    private int noChangeCount = 0;
    private PixyLineCam  pixyLineCam;
    private Pixy2Line line;
    private Logger logger = Logger.getLogger(PollPixyLine.class.getName());




    public PollPixyLine() {
        pixyLineCam = SubsystemFactory.getInstance().getPixyLineCam();
        requires(pixyLineCam);
        line = pixyLineCam.getLine();

    }

    public void init() {
        
    }

    public void execute() {
        logger.info("Getting Pixy vector");
       byte err = line.getFeatures(Pixy2Line.LINE_GET_MAIN_FEATURES, Pixy2Line.LINE_VECTOR, false);
       logger.info("getVector err " + err);
       Vector[] vectors = line.getVectors();
       logger.info("Got " + vectors.length + " vectors");
       for (Pixy2Line.Vector b : vectors) {
           logger.info(b.toString());
       }

       if (vectors.length > 0) {
            pixyLineCam.writeLine(vectors[0]);
       }
       
    }

    public void end() {

    }

    public void cancel() {

    }

    public boolean isFinished() {
        return true;
    }
}