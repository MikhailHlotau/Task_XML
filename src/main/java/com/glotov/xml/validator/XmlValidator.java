package com.glotov.xml.validator;

import com.glotov.xml.exception.CustomException;
import com.glotov.xml.util.PropertiesStreamReader;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class XmlValidator {
    private static final Logger logger = LogManager.getLogger(XmlValidator.class);

    public static void main(String[] args) throws URISyntaxException, CustomException {
        // Получение путей к файлам XML и XSD с помощью класса PropertiesStreamReader
        PropertiesStreamReader ob = new PropertiesStreamReader();
        String xmlFilePath = String.valueOf(ob.getFileFromResource("Devices.xml"));
        String xsdFilePath = String.valueOf(ob.getFileFromResource("Devices.xsd"));

        boolean isValid = validateXMLSchema(xmlFilePath, xsdFilePath);
        if (isValid) {
            logger.info("XML is valid against the XSD.");
        } else {
            logger.info("XML is not valid against the XSD.");
        }
    }

    public static boolean validateXMLSchema(String xmlFilePath, String xsdFilePath) {
        try {
            // Создание фабрики схемы
            SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            // Загрузка схемы из XSD-файла
            Schema schema = schemaFactory.newSchema(new File(xsdFilePath));
            // Создание валидатора для схемы
            Validator validator = schema.newValidator();
            // Установка источника данных для валидации (XML-файл)
            Source source = new StreamSource(new File(xmlFilePath));
            // Валидация XML по схеме
            validator.validate(source);
            return true; // XML соответствует схеме
        } catch (IOException e) {
            logger.error("IO error while validating XML: " + e.getMessage(), e);
            return false; // XML не соответствует схеме или возникла ошибка при валидации
        } catch (Exception e) {
            logger.error("Failed to validate XML: " + e.getMessage(), e);
            return false; // XML не соответствует схеме или возникла ошибка при валидации
        }
    }
}

