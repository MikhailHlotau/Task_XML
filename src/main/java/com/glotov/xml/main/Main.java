package com.glotov.xml.main;

import com.glotov.xml.parsers.XmlParser;
import com.glotov.xml.parsers.XmlParserFactory;

public class Main {
    public static void main(String[] args) {
        try {
            // Создание экземпляра парсера с использованием фабрики
            XmlParser parserDom = XmlParserFactory.createDomXmlParser();
            XmlParser parserSax = XmlParserFactory.createSaxXmlParser();
            XmlParser parserStax = XmlParserFactory.createStaxXmlParser();
            XmlParser parserJaxb = XmlParserFactory.createJaxbXmlParser();

            // Выполнение парсинга
            System.out.println("////DOM////");
            System.out.println();
            parserDom.parseXml("src/main/resources/Devices.xml");

            System.out.println("////SAX////");
            System.out.println();
            parserSax.parseXml("src/main/resources/Devices.xml");

            System.out.println("////STAX////");
            System.out.println();
            parserStax.parseXml("src/main/resources/Devices.xml");

            System.out.println("////JAXB////");
            System.out.println();
            parserJaxb.parseXml("src/main/resources/Devices.xml");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
