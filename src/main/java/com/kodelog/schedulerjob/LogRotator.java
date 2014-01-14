package com.kodelog.schedulerjob;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class LogRotator implements Serializable {
	private static final long serialVersionUID = -1908730657317231953L;
	public static final Logger LOG = Logger.getLogger(LogRotator.class);
	// the name of the vm arguement
	private static final String PROP_LOG_FILE = "logFile";
	private static final String DATE_FORMAT = "MM-dd-yyyy-HH-mm";

	public void rotateLog() {
		InputStream is = null;
		Properties prop = new Properties();
		try {
			is = getClass().getResourceAsStream("/log4j.properties");
			prop.load(is);
		} catch (Exception e) {
			LOG.error("Failed to find logger properties.", e);
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				// ignore
			}
		}

		System.setProperty(PROP_LOG_FILE, PROP_LOG_FILE + "-" + getCurrentDate() + ".log");
		LogManager.resetConfiguration();
		PropertyConfigurator.configure(prop);
		LOG.info("The logger properties were successfully reloaded.");
	}

	private String getCurrentDate() {
		DateFormat df = new SimpleDateFormat(DATE_FORMAT);
		return df.format(new Date());
	}
}
