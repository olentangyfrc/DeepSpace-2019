package org.usfirst.frc.team4611.robot.subsystems.arm;

import org.usfirst.frc.team4611.robot.subsystems.PortMan;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Arm extends Subsystem{

	public Victor linearActuator;
	public Victor linearActuator2;
    private AnalogPotentiometer linearActuatorPot;
    private AnalogPotentiometer linearActuatorPot2;
    
    private double max1 = 0.8;
    private double min1 = 0.23;
    private double max2 = 0.8;
    private double min2 = 0.23;

    private double varianceLimit = 0.02;
    
    public Arm(){
        
    }
    
    public void init(PortMan pm) throws Exception {
		linearActuator = new Victor(4);
		linearActuator2 = new Victor(5);
		linearActuatorPot = new AnalogPotentiometer(0);
		linearActuatorPot2 = new AnalogPotentiometer(1);
    }

	@Override
	protected void initDefaultCommand() {

    }
    
	public void moveArmUp(double speed, double speed2) {		        
		double potValue1 = getPot();
        double potValue2 = getPot2();
        
		double pos1 = (potValue1 - min1) / (max1 - min1);
		double pos2 = (potValue2 - min2) / (max2 - min2);
        
        
        if(potValue1 < max1 && pos1 <= pos2 + varianceLimit) {
			linearActuator.set(speed);
		}
		else {
			linearActuator.set(0);
		}
        
        if(potValue2 < max2 && pos2 <= pos1 + varianceLimit) {
			linearActuator2.set(speed2);
		}
		else {
			linearActuator2.set(0);
        }
        
    }    
    
    public void moveArmDown(double speed, double speed2) {
		double potValue1 = getPot();
        double potValue2 = getPot2();
        
		double pos1 = (potValue1 - min1) / (max1 - min1);
        double pos2 = (potValue2 - min2) / (max2 - min2);
        
        //System.out.println(potValue1);
        //System.out.println(potValue2);
        
        
		if((potValue1 > min1 && pos1 >= pos2 - varianceLimit)) {
            linearActuator.set(-speed);
            //System.out.println("pot1 movign down speed: " + (-speed));
		}
		else {
            linearActuator.set(0);
            //System.out.println("pot 1 no move");
        }
        
		if(potValue2 > min2 && pos2 >= pos1 - varianceLimit) {
            linearActuator2.set(-speed2);
            //System.out.println("pot2 movign down speed: " + (-speed2));
		}
		else {
            linearActuator2.set(0);
            //System.out.println("pot 2 no move");
        }
        
    }
 
    /*
	public void movePotPos(double pos) {
		double potValue1 = getPot();
		double potValue2 = getPot2();
		double pos1 = (potValue1 - min1) / (max1 - min1);
		double pos2 = (potValue2 - min2) / (max2 - min2);
		
		if(potValue1 > pos) {
			if((potValue1 > min1 && pos1 >= pos2 - varianceLimit)) {
				linearActuator.set(-.7);
			}
			else {
				linearActuator.set(0);
			}
			if(potValue2 > min2 && pos2 >= pos1 - varianceLimit) {
					linearActuator2.set(-.7);
			}
			else {
				linearActuator2.set(0);
			}
		} else if (potValue1 < pos) {
			if(potValue1 < max1 && pos1 <= pos2 + varianceLimit) {
				linearActuator.set(.7);
			}
			else {
				linearActuator.set(0);
			}
			if(potValue2 < max2 && pos2 <= pos1 + varianceLimit) {
				linearActuator2.set(.7);
			}
			else {
				linearActuator2.set(0);
			}
		} else {
			linearActuator.set(0);
			linearActuator2.set(0);
		}
		
    }    
    */
    
    public double getPot(){
        return linearActuatorPot.get();
    }

    public double getPot2(){
        return linearActuatorPot2.get();
    }
	
	public void stopPot(){
        linearActuator.set(0);
        linearActuator2.set(0);
    }
	
}