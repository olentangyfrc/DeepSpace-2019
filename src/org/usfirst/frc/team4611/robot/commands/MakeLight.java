package org.usfirst.frc.team4611.robot.commands;

import org.usfirst.frc.team4611.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class MakeLight extends Command{
	public int color;
	
	/**
	 * 
	 * @param c is the color: 0=Off, 1=Red, 2=Yellow, 3=Green, 4=Cyan, 5=Blue, 6=Purple, 7=White
	 */
	public MakeLight(int c){
		color = c;
	}
	
	public void setColor(int c){
		color = c;
	}
	
	protected void execute(){
		switch (color){
			case 0:
				Robot.fancyLight.makeOff();
				//System.out.println("Lights off");
				break;
			case 1:
				//System.out.println("Lights red");
				Robot.fancyLight.makeRed();
				break;
			case 2:
				Robot.fancyLight.makeYellow();
				//System.out.println("Lights yellow");
				break;
			case 3:
				Robot.fancyLight.makeGreen();
				//System.out.println("Lights green");
				break;
			case 4:
				Robot.fancyLight.makeCyan();
				//System.out.println("Lights cyan");
				break;
			case 5:
				Robot.fancyLight.makeBlue();
				//System.out.println("Lights blue");
				break;
			case 6:
				Robot.fancyLight.makePurple();
				//System.out.println("Lights purple");
				break;
			case 7:
				Robot.fancyLight.makeWhite();
				//System.out.println("Lights ");
				break;
		}
	}
	
	protected void end(){
		Robot.fancyLight.makeOff();
	}

	protected boolean isFinished() {
		return false;
	}
	
	public void cancel(){
		this.end();
	}
	
	

}
