package com.glotov.xml.parsers.impl;

import com.glotov.xml.entity.Device;
import com.glotov.xml.entity.Devices;
import com.glotov.xml.parsers.XmlParser;

import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class JaxbXmlParser implements XmlParser {
    @Override
    public void parseXml(String filePath) {
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
                System.out.println();
            }
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}

