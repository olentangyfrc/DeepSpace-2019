package org.usfirst.frc.team4611.robot.subsystems.pixyCam.commands;

import java.util.ArrayList;
import java.util.logging.Logger;

import org.usfirst.frc.team4611.robot.subsystems.SubsystemFactory;
import org.usfirst.frc.team4611.robot.subsystems.pixyCam.PixyCam;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import io.github.pseudoresonance.pixy2api.Pixy2CCC;

public class PollPixy extends Command{

    private double lastUpdateTime = 0;
    private int noChangeCount = 0;
    private PixyCam  pixyCam;
    private Pixy2Line line;
    private Logger logger = Logger.getLogger(PollPixy.class.getName());




    public PollPixy() {
        pixyCam = SubsystemFactory.getInstance().getPixyCam();
        requires(pixyCam);
        ccc = pixyCam.getCCC();

    }

    public void init() {
        
    }

    public void execute() {
       logger.info("Getting Pixy blocks");
       int err = ccc.getBlocks(false, 1, 8);
       logger.info("getBlocks err " + err);
       ArrayList <Pixy2CCC.Block> blocks = ccc.getBlocks();
       logger.info("Got " + blocks.size() + " blocks");
       for (Pixy2CCC.Block b : blocks) {
           logger.info(b.toString());
       }

       if (blocks.size() > 0) {
            pixyCam.writeBlock(blocks.get(0));
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