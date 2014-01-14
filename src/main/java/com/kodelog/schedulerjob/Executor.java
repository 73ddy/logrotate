package com.kodelog.schedulerjob;

import org.apache.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Do not forget to set the vm arguement -> -DlogFile
 * 
 * @author kodelog
 */
public class Executor {
	public static final Logger LOG = Logger.getLogger(Executor.class);
	public static void main(String[] args) throws Exception {
		new ClassPathXmlApplicationContext("spring-quartz.xml");
		
		while(true) {
			LOG.info("Some logging!");
			Thread.sleep(2000);
		}
	}
}
