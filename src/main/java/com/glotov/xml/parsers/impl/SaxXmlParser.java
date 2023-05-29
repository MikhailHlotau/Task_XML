package com.glotov.xml.parsers.impl;

import com.glotov.xml.entity.Device;
import com.glotov.xml.parsers.XmlParser;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class SaxXmlParser extends DefaultHandler implements XmlParser {
    private StringBuilder elementValue;
    private String currentElement;
    private Device.Builder deviceBuilder;

    @Override
    public void parseXml(String filePath) {
        try {
            // Создание объекта файла для XML-файла по заданному пути
            File xmlFile = new File(filePath);
            // Создание фабрики SAX-парсеров
            SAXParserFactory factory = SAXParserFactory.newInstance();
            // Создание SAX-парсера
            SAXParser parser = factory.newSAXParser();
            // Запуск парсинга XML-файла с использованием текущего объекта в качестве обработчика
            parser.parse(xmlFile, this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        // Инициализация переменных для нового элемента
        elementValue = new StringBuilder();
        currentElement = qName;

        if (qName.equals("Device")) {
            // Создание нового объекта Device.Builder при обнаружении элемента "Device"
            deviceBuilder = new Device.Builder();
            // Установка значения атрибута "id" для объекта Device.Builder
            deviceBuilder.setId(attributes.getValue("id"));
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        // Обработка текстового содержимого элемента
        elementValue.append(new String(ch, start, length));
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (currentElement.equals("Name")) {
            // Установка имени устройства из текстового содержимого элемента "Name"
            deviceBuilder.setName(elementValue.toString());
        } else if (currentElement.equals("Origin")) {
            // Установка происхождения устройства из текстового содержимого элемента "Origin"
            deviceBuilder.setOrigin(elementValue.toString());
        } else if (currentElement.equals("Price")) {
            // Преобразование текстового содержимого элемента "Price" в число и установка цены устройства
            double price = Double.parseDouble(elementValue.toString());
            deviceBuilder.setPrice(price);
        } else if (currentElement.equals("Critical")) {
            // Преобразование текстового содержимого элемента "Critical" в булево значение и установка флага критичности устройства
            boolean critical = Boolean.parseBoolean(elementValue.toString());
            deviceBuilder.setCritical(critical);
            // Создается объект Device посредством метода build() на основе установленных значений
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





