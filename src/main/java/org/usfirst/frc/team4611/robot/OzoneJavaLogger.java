package org.usfirst.frc.team4611.robot;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;


public class OzoneJavaLogger {
	
	private static OzoneJavaLogger instance	= null;
	private long startupTime = System.currentTimeMillis();
	private Logger logger	= Logger.getLogger("org.usfirst.frc.team4611.robot");
	private boolean	initted	= false;

	private OzoneJavaLogger() {
		
	}
	
	public static OzoneJavaLogger getInstance() {
		if (instance == null) {
			instance	= new OzoneJavaLogger();
		}
		return instance;
	}

	public void init(Level logLevel) {
		if (initted)
				return;
		
		logger.setUseParentHandlers(false);
		
		try {
			logger.setLevel(logLevel);
			
			Handler	consoleHandler	= new ConsoleHandler();
			consoleHandler.setFormatter(new OzoneLogFormatter());
			consoleHandler.setLevel(Level.ALL);
			logger.addHandler(consoleHandler);
		} catch (SecurityException e) {
			logger.log(Level.SEVERE, "XX", e);
		} finally {
			initted	= true;
		}
	}
	
	
	public class OzoneLogFormatter extends Formatter {
		StringBuffer	b	= new StringBuffer();
		SimpleDateFormat dateFormat	= new SimpleDateFormat("HH.mm:ss");

		@Override
		public String format(LogRecord record)  {
			String []parts	= record.getSourceClassName().split("\\.");

			b.setLength(0);
		
			b.append("[" + record.getLevel()).append("]")
				.append('[').append(LocalDateTime.now().minus(startupTime, ChronoUnit.MILLIS).format(DateTimeFormatter.ofPattern("mm:ss.SSS"))).append(']')
				.append('[').append(parts[parts.length-1]).append(']')
				.append('[').append(record.getMessage()).append(']')
				.append('\n');
			 return b.toString();
		}		
	}
	
	public class LogTest {
		final Logger	logger	= Logger.getLogger(LogTest.class.getName());
		public LogTest() {
			
		}
		
		public void test() {
			logger.info("LogTest info");
			logger.fine("LogTest fine");
		}
	}

	public static void main(String[] args) {
		
		OzoneJavaLogger.getInstance().init(Level.FINE);
		
		Logger logger = Logger.getLogger("org.usfirst.frc.team4611.robot.main");
		logger.info("Information");
		logger.severe("Severe");
		LogTest logTest	=  OzoneJavaLogger.getInstance().new LogTest();
		logTest.test();
		
		logger.finest("main finest");
	}

}
