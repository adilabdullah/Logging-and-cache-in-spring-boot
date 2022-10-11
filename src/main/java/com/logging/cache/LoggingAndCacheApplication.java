package com.logging.cache;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LoggingAndCacheApplication {

	  private static final Logger LOGGER = LogManager.getLogger(LoggingAndCacheApplication.class);

	
	public static void main(String[] args) {
		  LOGGER.info("Info level log message");
	        LOGGER.debug("Debug level log message");
	        LOGGER.error("Error level log message");
		SpringApplication.run(LoggingAndCacheApplication.class, args);
		
	}

}
