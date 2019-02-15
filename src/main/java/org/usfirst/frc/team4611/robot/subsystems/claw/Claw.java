package org.usfirst.frc.team4611.robot.subsystems.claw;

import org.usfirst.frc.team4611.robot.subsystems.PortMan;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Claw extends Subsystem{
    public boolean isRetracted;
    private DoubleSolenoid sol;
    
    public Claw(){
        
    }
    
    public void init(PortMan pm) throws Exception {
        sol = new DoubleSolenoid(1, 0);
    }

	@Override
	protected void initDefaultCommand() {
		//this.setDefaultCommand(new RetractSolenoid());
		//isRetracted = true;
	}
	
	public void move(Value v) {
		sol.set(v);
    }
    
    public Value getClawValue() {
        return sol.get();
    }
	
}