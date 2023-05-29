package com.glotov.xml.parsers.impl;

import com.glotov.xml.exception.CustomException;
import com.glotov.xml.parsers.XmlParser;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DomXmlParser implements XmlParser {
    private static final Logger logger = LogManager.getLogger(DomXmlParser.class);

    @Override
    public void parseXml(String filePath) throws CustomException {
        try {
            // Создание фабрики строителей документов
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            // Создание строителя документов
            DocumentBuilder builder = factory.newDocumentBuilder();
            // Чтение XML-документа и создание объекта Document
            Document document = builder.parse(new File(filePath));
            // Получаем корневой элемент
            Element root = document.getDocumentElement();
            // Получаем список узлов "Device"
            NodeList deviceList = root.getElementsByTagName("Device");
            // Обход списка узлов "Device"
            for (int i = 0; i < deviceList.getLength(); i++) {
                Element device = (Element) deviceList.item(i);
                String id = getTextValue(device, "id");
                String name = getTextValue(device, "Name");
                String origin = getTextValue(device, "Origin");
                double price = Double.parseDouble(getTextValue(device, "Price"));
                boolean isCritical = Boolean.parseBoolean(getTextValue(device, "Critical"));
                /// Получение элемента "Type"
                Element typeElement = (Element) device.getElementsByTagName("Type").item(0);
                // Получение значения атрибута "Peripheral" элемента "Type"
                boolean isPeripheral = Boolean.parseBoolean(typeElement.getAttribute("Peripheral"));
                // Получение значения атрибута "PowerConsumption" элемента "Type"
                int powerConsumption = Integer.parseInt(typeElement.getAttribute("PowerConsumption"));
                // Получение значения опционального атрибута "Cooler" элемента "Type"
                Boolean hasCooler = getOptionalBooleanAttribute(typeElement, "Cooler");
                // Получение значения опционального атрибута "ComponentGroup" элемента "Type"
                String componentGroup = getOptionalAttribute(typeElement, "ComponentGroup");

                List<String> ports = new ArrayList<>();
                NodeList portList = device.getElementsByTagName("Port");
                // Обход списка элементов "Port"
                for (int j = 0; j < portList.getLength(); j++) {
                    Element port = (Element) portList.item(j);
                    // Получение значения элемента "Port"
                    ports.add(port.getTextContent());
                }

                // Вывод информации о каждом устройстве, его атрибутах и значениях портов
                System.out.println("Device ID: " + id);
                System.out.println("Name: " + name);
                System.out.println("Origin: " + origin);
                System.out.println("Price: " + price);
                System.out.println("Critical: " + isCritical);
                System.out.println("Peripheral: " + isPeripheral);
                System.out.println("Power Consumption: " + powerConsumption);
                System.out.println("Cooler: " + hasCooler);
                System.out.println("Component Group: " + componentGroup);
                System.out.println("Ports: " + ports);
                System.out.println();
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            logger.error("Failed to parse XML: " + e.getMessage(), e);
            throw new CustomException("Failed to parse XML: " + e.getMessage(), e);
        }
    }

    // Получение текстового значения элемента по заданному тегу
    private String getTextValue(Element element, String tagName) {
        NodeList nodeList = element.getElementsByTagName(tagName);
        if (nodeList.getLength() > 0) {
            return nodeList.item(0).getTextContent();
        }
        return "";
    }

    // Получение опционального булевого значения атрибута элемента
    private Boolean getOptionalBooleanAttribute(Element element, String attributeName) {
        String attributeValue = element.getAttribute(attributeName);
        return attributeValue.isEmpty() ? null : Boolean.parseBoolean(attributeValue);
    }

    // Получение опционального значения атрибута элемента
    private String getOptionalAttribute(Element element, String attributeName) {
        String attributeValue = element.getAttribute(attributeName);
        return attributeValue.isEmpty() ? null : attributeValue;
    }
}





