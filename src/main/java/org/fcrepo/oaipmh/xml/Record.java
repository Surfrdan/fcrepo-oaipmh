package org.fcrepo.oaipmh.xml;

import java.io.StringReader;
import java.io.StringWriter;

import jakarta.xml.bind.ValidationEventHandler;
import jakarta.xml.bind.annotation.DomHandler;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

public class Record implements DomHandler<String, StreamResult> {

    private static final String RECORD_START_TAG = "<record>";
    private static final String RECORD_END_TAG = "</record>";

    private StringWriter xmlWriter = new StringWriter();

    public StreamResult createUnmarshaller(ValidationEventHandler errorHandler) {
        return new StreamResult(xmlWriter);
    }

    public String getElement(StreamResult rt) {
        String xml = rt.getWriter().toString();
        int beginIndex = xml.indexOf(RECORD_START_TAG) + RECORD_START_TAG.length();
        int endIndex = xml.indexOf(RECORD_END_TAG);
        return xml.substring(beginIndex, endIndex);
    }

    public Source marshal(String n, ValidationEventHandler errorHandler) {
        try {
            String xml = RECORD_START_TAG + n.trim() + RECORD_END_TAG;
            StringReader xmlReader = new StringReader(xml);
            return new StreamSource(xmlReader);
        } catch(Exception e) {
            throw new RuntimeException(e);
        }
    }
}
