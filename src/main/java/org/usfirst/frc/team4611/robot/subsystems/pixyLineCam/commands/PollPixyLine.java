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
       logger.info("Getting Pixy vector----");
       byte err = line.getFeatures(Pixy2Line.LINE_GET_ALL_FEATURES, Pixy2Line.LINE_VECTOR, true);
       logger.info("getVector err " + err);
       Vector[] vectors = line.getVectors();
       if (vectors == null) {
           logger.info("Got no vectors :( ");
           pixyLineCam.resetPixyLine();
           return;
       }

       ArrayList<Pixy2Line.Vector> verticals = new ArrayList<>();

       logger.info("Got " + vectors.length + " vectors");
       for (Pixy2Line.Vector b : vectors) {
           String vert = " ";
           if (b.getX0() == b.getX1()) {
                verticals.add(b);
                vert = " <-- vertical :)";
           } else {
                int slope = (b.getY0()-b.getY1())/(b.getX0()-b.getX1());
                if (slope > 2) {
                    verticals.add(b);
                    vert = " <-- vertical :)";
                }
           }
           //logger.info(b.toString() + vert());
       }
       
       if (verticals.size() > 0) {
          Pixy2Line.Vector found = null;
          int largeY = -1;

          for (Pixy2Line.Vector v : verticals) {
               if (v.getY0() > largeY || v.getY1() > largeY) {
                    largeY = Math.max(v.getY0(), v.getY1());
                    found = v;
                }

            }

            pixyLineCam.writeLine(found, verticals.size());
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
