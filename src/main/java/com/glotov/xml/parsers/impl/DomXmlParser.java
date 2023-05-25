package com.glotov.xml.parsers.impl;

import com.glotov.xml.parsers.XmlParser;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class DomXmlParser implements XmlParser {
    @Override
    public void parseXml(String filePath) {
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
                String id = device.getAttribute("id");
                String name = getTextValue(device, "Name");
                String origin = getTextValue(device, "Origin");
                double price = Double.parseDouble(getTextValue(device, "Price"));
                boolean isCritical = Boolean.parseBoolean(getTextValue(device, "Critical"));
                // Выводим информацию о каждом устройстве
                System.out.println("Device ID: " + id);
                System.out.println("Name: " + name);
                System.out.println("Origin: " + origin);
                System.out.println("Price: " + price);
                System.out.println("Critical: " + isCritical);
                System.out.println();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getTextValue(Element element, String tagName) {
        NodeList nodeList = element.getElementsByTagName(tagName);
        Element tagElement = (Element) nodeList.item(0);
        return tagElement.getTextContent();
    }
}

