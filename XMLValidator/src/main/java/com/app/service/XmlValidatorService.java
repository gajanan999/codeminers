package com.app.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.xml.XMLConstants;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.stax.StAXSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import com.app.XmlValidatorApplication;

@Service
public class XmlValidatorService {
	
	 private final Logger logger = LoggerFactory.getLogger(XmlValidatorService.class);

	public boolean validate(File input , File xsd) {
		StAXSource source = null;
        FileInputStream inputStream = null;
        boolean flag = false;
        
        try {
        	 SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        	 Schema schema = factory.newSchema(xsd);
        	 Validator validator = schema.newValidator();
             XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
             inputStream = new FileInputStream(input);
             XMLStreamReader xmlStreamReader = xmlInputFactory.createXMLStreamReader(inputStream);
             source = new StAXSource(xmlStreamReader);
             validator.validate(source);
             flag =true;
        }catch(SAXException e) {
        	logger.error(e.getMessage(),e);
        } catch (FileNotFoundException e) {
        	logger.error(e.getMessage(),e);
		} catch (XMLStreamException e) {
			logger.error(e.getMessage(),e);
		} catch (IOException e) {
			logger.error(e.getMessage(),e);
		}
		
		return flag;
	}
}
