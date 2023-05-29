package com.glotov.xml.parsers;

import com.glotov.xml.parsers.impl.DomXmlParser;
import com.glotov.xml.parsers.impl.JaxbXmlParser;
import com.glotov.xml.parsers.impl.SaxXmlParser;
import com.glotov.xml.parsers.impl.StaxXmlParser;

public class XmlParserFactory {
    public static XmlParser createDomXmlParser() {
        return new DomXmlParser();
    }

    public static XmlParser createSaxXmlParser() {
        return new SaxXmlParser();
    }

    public static XmlParser createStaxXmlParser() {
        return new StaxXmlParser();
    }

    public static XmlParser createJaxbXmlParser() {
        return new JaxbXmlParser();
    }
}
