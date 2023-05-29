package com.glotov.xml.parsers;

import com.glotov.xml.exception.CustomException;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

public interface XmlParser {
    void parseXml(String filePath) throws CustomException;

}
