package com.glotov.xml.parsers.impl;

import com.glotov.xml.entity.Device;
import com.glotov.xml.exception.CustomException;
import com.glotov.xml.parsers.XmlParser;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;

public class StaxXmlParser implements XmlParser {
    private static final Logger logger = LogManager.getLogger(StaxXmlParser.class);

    @Override
    public void parseXml(String filePath) throws CustomException {
        try {
            XMLInputFactory inputFactory = XMLInputFactory.newInstance();
            XMLStreamReader reader = inputFactory.createXMLStreamReader(new FileInputStream(filePath));
            Device.Builder deviceBuilder = null;
            String currentElement = "";

            while (reader.hasNext()) {
                int eventType = reader.next();

                if (eventType == XMLStreamConstants.START_ELEMENT) {
                    currentElement = reader.getLocalName();
                    if (currentElement.equals("Device")) {
                        deviceBuilder = new Device.Builder();
                    }
                } else if (eventType == XMLStreamConstants.CHARACTERS) {
                    String text = reader.getText().trim();
                    if (!text.isEmpty()) {
                        switch (currentElement) {
                            case "id":
                                deviceBuilder.setId(text);
                                break;
                            case "Name":
                                deviceBuilder.setName(text);
                                break;
                            case "Origin":
                                deviceBuilder.setOrigin(text);
                                break;
                            case "Price":
                                double price = Double.parseDouble(text);
                                deviceBuilder.setPrice(price);
                                break;
                            case "Critical":
                                boolean isCritical = Boolean.parseBoolean(text);
                                deviceBuilder.setCritical(isCritical);
                                break;
                        }
                    }
                } else if (eventType == XMLStreamConstants.END_ELEMENT) {
                    currentElement = reader.getLocalName();
                    if (currentElement.equals("Device")) {
                        Device device = deviceBuilder.build();
                        System.out.println("Device ID: " + device.getId());
                        System.out.println("Name: " + device.getName());
                        System.out.println("Origin: " + device.getOrigin());
                        System.out.println("Price: " + device.getPrice());
                        System.out.println("Critical: " + device.isCritical());
                        System.out.println();
                    }
                }
            }

            reader.close();
        } catch (XMLStreamException e) {
            logger.error("Failed to parse XML: " + e.getMessage(), e);
            throw new CustomException("Failed to parse XML: " + e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Failed to parse XML: " + e.getMessage(), e);
            throw new CustomException("Failed to parse XML: " + e.getMessage(), e);
        }
    }
}

