/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4611.robot;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;

/**
 * Simple wrapped of Exception that will put an exception on Shuffleboard
 */
public class OzoneException extends Exception {
    static final long serialVersionUID = 1;

    private static NetworkTableEntry exceptionMessageEntry;
    private static NetworkTableEntry exceptionIndicatorEntry;

    public OzoneException (String message) {
        super (message);

        if (exceptionMessageEntry == null) {
            exceptionMessageEntry = Shuffleboard.getTab("Health Tab").add("ExceptionMessage", "No Exception").getEntry();
            exceptionIndicatorEntry = Shuffleboard.getTab("Health Tab").add("ExceptionIndicator", false).getEntry();
        }
        exceptionMessageEntry.setString(message);
        exceptionIndicatorEntry.setBoolean(true);
    }
}
