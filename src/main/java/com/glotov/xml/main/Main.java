package com.glotov.xml.main;

import com.glotov.xml.parsers.XmlParser;
import com.glotov.xml.parsers.XmlParserFactory;
import com.glotov.xml.util.PropertiesStreamReader;

public class Main {
    public static void main(String[] args) {
        try {
            // Создание экземпляра парсера с использованием фабрики
            XmlParser parserDom = XmlParserFactory.createDomXmlParser();
            XmlParser parserSax = XmlParserFactory.createSaxXmlParser();
            XmlParser parserStax = XmlParserFactory.createStaxXmlParser();
            XmlParser parserJaxb = XmlParserFactory.createJaxbXmlParser();

            // Создание объекта PropertiesStreamReader
            PropertiesStreamReader ob = new PropertiesStreamReader();
            // Получение пути к файлу "Devices.xml" из ресурсов
            String filePath = String.valueOf(ob.getFileFromResource("Devices.xml"));

            // Выполнение парсинга с помощью каждого парсера
            System.out.println("////DOM////");
            System.out.println();
            parserDom.parseXml(filePath);

            System.out.println("////SAX////");
            System.out.println();
            parserSax.parseXml(filePath);

            System.out.println("////STAX////");
            System.out.println();
            parserStax.parseXml(filePath);

            System.out.println("////JAXB////");
            System.out.println();
            parserJaxb.parseXml(filePath);

            System.out.println("////JAXB////");
            System.out.println();
            parserJaxb.parseXml("src/main/resources/Devices.xml");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


