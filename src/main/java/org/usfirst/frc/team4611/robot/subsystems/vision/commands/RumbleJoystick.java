package org.usfirst.frc.team4611.robot.subsystems.vision.commands;

import org.usfirst.frc.team4611.robot.subsystems.SubsystemFactory;
import org.usfirst.frc.team4611.robot.subsystems.vision.Vision;
import org.usfirst.frc.team4611.robot.OI;
import org.usfirst.frc.team4611.robot.networktables.NetTableManager;


import edu.wpi.first.wpilibj.command.Command;

public class RumbleJoystick extends Command{

    private Vision  vision;
    public RumbleJoystick() {
        vision = SubsystemFactory.getInstance().getVision();
        requires(vision);

    }

    public void init() {
    }

    public void execute() {
        System.out.println("Checking centered");
       if (vision.isCentered() || true) {
           // rumble the joystick
           OI.getInstance().rumbleJoystick(0);
       }
    }

    public void cancel() {

    }

    public boolean isFinished() {
        return true;
    }
}