package org.usfirst.frc.team4611.robot.subsystems;

import java.util.TimerTask;

import org.usfirst.frc.team4611.robot.Robot;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Optical extends Subsystem{

		// TODO Auto-generated constructor stub
			private I2C i2c;
			private static byte[] distance;
			private static byte[] readyCheck;
			private java.util.Timer updater;
			private LIDARUpdater task;
			
			private final int LIDAR_ADDR = 0x62;
			private final int LIDAR_CONFIG_REGISTER = 0x00;
			private final int ready = 0x01;
			private final int LIDAR_DISTANCE_REGISTER_BYTE = 0x8f;
			//private final int LIDAR_DISTANCE_REGISTER_HIGH_BYTE = 0x0f;
			
			public Optical(Port port) {
				i2c = new I2C(port, LIDAR_ADDR);
				
				distance = new byte[2];
				readyCheck = new byte[1];
				
				task = new LIDARUpdater();
				updater = new java.util.Timer();
			}
			
			// Distance in cm
			public static int getDistance() {
				return (int)Integer.toUnsignedLong(distance[0] << 8) + Byte.toUnsignedInt(distance[1]);
			}
		 
			public double pidGet() {
				return getDistance();
			}
			
			
			// Start 10Hz polling
			public void start() {
				updater.scheduleAtFixedRate(task, 0, 1);
			}
			
			// Start polling for period in milliseconds
			public void start(int period) {
				updater.scheduleAtFixedRate(task, 0, period);
			}
			
			public void stop() {
				updater.cancel();
			}
			
			// Update distance variable
			public void update() {
				i2c.write(LIDAR_CONFIG_REGISTER, 0x04); // Initiate measurement
				Timer.delay(0.001); // Delay for measurement to be taken
				byte[] register = new byte[] {(byte)LIDAR_DISTANCE_REGISTER_BYTE};
				i2c.writeBulk(register, 1);
				i2c.readOnly(distance ,2); // Read in measurement
				Timer.delay(0.001); // Delay to prevent over polling
				
			}

			@Override
			protected void initDefaultCommand() {
				// TODO Auto-generated method stub
				
			}
			// Timer task to keep distance updated
			private class LIDARUpdater extends TimerTask {
				public void run() {
					Robot.opt.update();
					System.out.println("Ready : " + readyCheck[0]);
					System.out.println("Laser : " + pidGet());
						/*try {
							Thread.sleep(10);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}*/
				}
			}
			/*public void startReading() {
				//i2c.write(0, 0x04);
				if(i2c.write(0, 0x10))
					System.out.println("Error starting reading");
			}
			public int getInt() {
				byte[] buffer = new byte[1];
				//Timer.delay(5);
				if(i2c.read(0x10, 1, buffer))
				{
					System.out.println("Error getting if value read");
					return -1;
				}
				//i2c.read(0x10, 1, buffer);
				bufferPrint(buffer);
				/*if((buffer[0] & 0x17) == 0) {
					if(i2c.readOnly(buffer, 0x17))
					{
						System.out.println("Error getting value");
						return -1;
					}
					i2c.readOnly(buffer, 1);
					System.out.println("Second print: " + buffer[0] + ", " + buffer[1]);
					
				return (buffer[0] >> 8);
				//return -1;
			}
			
			public void bufferPrint(byte[] buffer) {
				for(int c = 0; c < buffer.length; c++) {
					System.out.println(c + ": " + buffer[c]);
				}
			}*/
			
	}
