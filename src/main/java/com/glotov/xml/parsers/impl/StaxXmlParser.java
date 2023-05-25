package com.glotov.xml.parsers.impl;

import com.glotov.xml.entity.Device;
import com.glotov.xml.parsers.XmlParser;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;

public class StaxXmlParser implements XmlParser {
    @Override
    public void parseXml(String filePath) {
        try {
            // Создание экземпляра XMLInputFactory
            XMLInputFactory inputFactory = XMLInputFactory.newInstance();
            // Создание XMLStreamReader для чтения XML из файла
            XMLStreamReader reader = inputFactory.createXMLStreamReader(new FileInputStream(filePath));
            // Инициализация объекта Device.Builder
            Device.Builder deviceBuilder = null;
            // Текущий элемент в XML
            String currentElement = "";
            // Чтение XML поэлементно
            while (reader.hasNext()) {
                int eventType = reader.next();

                switch (eventType) {
                    case XMLStreamConstants.START_ELEMENT:
                        // Обработка начального элемента
                        currentElement = reader.getLocalName();

                        if (currentElement.equals("Device")) {
                            // Создание нового объекта Device.Builder при обнаружении элемента "Device"
                            deviceBuilder = new Device.Builder();
                            deviceBuilder.setId(reader.getAttributeValue(null, "id"));
                        }
                        break;

                    case XMLStreamConstants.CHARACTERS:
                        // Обработка текста между элементами
                        String text = reader.getText().trim();

                        if (!text.isEmpty()) {
                            if (currentElement.equals("Name")) {
                                // Установка имени устройства
                                deviceBuilder.setName(text);
                            } else if (currentElement.equals("Origin")) {
                                // Установка происхождения устройства
                                deviceBuilder.setOrigin(text);
                            } else if (currentElement.equals("Price")) {
                                // Преобразование текста в число и установка цены устройства
                                double price = Double.parseDouble(text);
                                deviceBuilder.setPrice(price);
                            } else if (currentElement.equals("Critical")) {
                                // Преобразование текста в логическое значение и установка критичности устройства
                                boolean isCritical = Boolean.parseBoolean(text);
                                deviceBuilder.setCritical(isCritical);
                            }
                        }
                        break;

                    case XMLStreamConstants.END_ELEMENT:
                        // Обработка закрывающего элемента
                        currentElement = reader.getLocalName();
                        if (currentElement.equals("Device")) {
                            // Построение объекта Device и вывод информации о нем
                            Device device = deviceBuilder.build();
                            System.out.println("Device ID: " + device.getId());
                            System.out.println("Name: " + device.getName());
                            System.out.println("Origin: " + device.getOrigin());
                            System.out.println("Price: " + device.getPrice());
                            System.out.println("Critical: " + device.isCritical());
                            System.out.println();
                        }
                        break;
                }
            }
            reader.close();
        } catch (XMLStreamException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


