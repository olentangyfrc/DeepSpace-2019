package org.usfirst.frc.team4611.robot.utilities;

import org.usfirst.frc.team4611.robot.Robot;
import org.usfirst.frc.team4611.robot.RobotMap;
import org.usfirst.frc.team4611.robot.commands.MakeLight;

public class PiLights {
	private static int cnt = 0;
	private static String lastTimeStamp = "";
	private static boolean isAlive = false;
	
	public static void reset(){
		cnt = 0;
		lastTimeStamp = "";
		isAlive = false;
	}
	
	public static void lightsFromPi(){
		if(isPiAlive()){
			if( Math.abs((double) RobotMap.networkManager.getVisionValue(RobotMap.horizontalDistanceID)) <= 3 
					&& (boolean) RobotMap.networkManager.getVisionValue(RobotMap.foundID)){
				((MakeLight)Robot.lightsCommand).setColor(7);
			}else if((boolean) RobotMap.networkManager.getVisionValue(RobotMap.foundID)){
				((MakeLight)Robot.lightsCommand).setColor(2);
			}else{
				((MakeLight)Robot.lightsCommand).setColor(5);
			}
		}
		else{
			((MakeLight)Robot.lightsCommand).setColor(1);
		}
			
	}
	
	public static boolean isPiAlive(){
		return isAlive;
	}
	
	/**
	 * If the pi tables are null or the pi time stamp isn't updating then the pi is "not alive"
	 * Depends on being called ~every 20 seconds in a robot periodic
	 */
	public static boolean checkPiAlive(){
		if(RobotMap.networkManager.getVisionValue(RobotMap.foundID) != null){
			if( RobotMap.networkManager.getVisionValue(RobotMap.piTimeStampID) != null){
				if(lastTimeStamp.equals((String)RobotMap.networkManager.getVisionValue(RobotMap.piTimeStampID))){
					cnt++;
					if(cnt>=50){
						RobotMap.log(RobotMap.generalSubTable, "The pi time stamp is not updating often enoughd (pi is dead, probably died after startup)");
						isAlive = false;
						return false;
					}else{
						isAlive = true;
						return true;
					}
				}else{
					cnt = 0;
					lastTimeStamp = (String) RobotMap.networkManager.getVisionValue(RobotMap.piTimeStampID);
					isAlive = true;
					return true;
				}
			} else{
				isAlive = true;
				return true;
			}
		}
		RobotMap.log(RobotMap.generalSubTable, "Found on vision table is null (pi is dead, probably wasn't there to begin with)");
		isAlive = false;
		return false;
	}
}
