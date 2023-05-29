package com.glotov.xml.parsers.impl;

import com.glotov.xml.entity.Device;
import com.glotov.xml.entity.Devices;
import com.glotov.xml.entity.Type;
import com.glotov.xml.exception.CustomException;
import com.glotov.xml.parsers.XmlParser;

import java.io.File;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class JaxbXmlParser implements XmlParser {
    private static final Logger logger = LogManager.getLogger(JaxbXmlParser.class);

    @Override
    public void parseXml(String filePath) throws CustomException {
        try {
            File xmlFile = new File(filePath);

            // Создание контекста JAXB и анмаршаллинг XML
            JAXBContext jaxbContext = JAXBContext.newInstance(Devices.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            Devices devices = (Devices) unmarshaller.unmarshal(xmlFile);

            // Вывод информации о каждом устройстве
            for (Device device : devices.getDeviceList()) {
                System.out.println("Device ID: " + device.getId());
                System.out.println("Name: " + device.getName());
                System.out.println("Origin: " + device.getOrigin());
                System.out.println("Price: " + device.getPrice());
                System.out.println("Critical: " + device.isCritical());

                // Вывод атрибутов элемента "Type"
                Type type = device.getType();
                System.out.println("Peripheral: " + type.isPeripheral());
                System.out.println("PowerConsumption: " + type.getPowerConsumption());
                System.out.println("Cooler: " + type.getHasCooler());
                System.out.println("ComponentGroup: " + type.getComponentGroup());

                // Вывод информации об атрибутах элемента "Port"
                List<Type.Port> ports = type.getPorts();
                for (Type.Port port : ports) {
                    System.out.println("Port: " + port.getName());
                }

                System.out.println();
            }
        } catch (JAXBException e) {
            logger.error("Failed to parse XML: " + e.getMessage(), e);
            throw new CustomException("Failed to parse XML: " + e.getMessage(), e);
        }
    }
}