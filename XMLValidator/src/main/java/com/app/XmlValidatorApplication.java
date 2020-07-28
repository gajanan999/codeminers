package com.app;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.app.service.XmlValidatorService;

@SpringBootApplication
public class XmlValidatorApplication implements CommandLineRunner{
	 private final Logger logger = LoggerFactory.getLogger(XmlValidatorApplication.class);
	
	@Autowired
	XmlValidatorService xmlValidatorService;

	public static void main(String[] args) {
		SpringApplication.run(XmlValidatorApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		File input = new File(System.getProperty("user.dir")+File.separator+"data"+File.separator+"input.xml");
		File XSD = new File(System.getProperty("user.dir")+File.separator+"data"+File.separator+"shiporder.xsd");
		logger.info(String.valueOf(xmlValidatorService.validate(input, XSD)));
		
	}

}
