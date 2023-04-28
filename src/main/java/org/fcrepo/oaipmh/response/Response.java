package org.fcrepo.oaipmh.response;

import org.fcrepo.oaipmh.xml.OaiRoot;
import org.fcrepo.oaipmh.xml.IdentifyElement;
import org.fcrepo.oaipmh.OaipmhException;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.annotation.PostConstruct;
import org.springframework.util.MultiValueMap;
import java.io.StringWriter;
import java.io.FileOutputStream;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.xml.bind.PropertyException;

public class Response {

    public static final String XSI_NS = "http://www.w3.org/2001/XMLSchema-instance";

    public static final String XSI_SCHEMA = "http://www.openarchives.org/OAI/2.0/ http://www.openarchives.org/OAI/2.0/OAI-PMH.xsd";

    protected OaiRoot oaiRoot;

    private JAXBContext jc;

    private Marshaller marshaller;

    protected String uri;

    private MultiValueMap paramMap;

    private List<String> errors;

    private Logger logger = LoggerFactory.getLogger(Response.class);

    /*
    public Response() {
        this.oaiRoot = new OaiRoot();
        try {
            jc = JAXBContext.newInstance(OaiRoot.class);
            marshaller = jc.createMarshaller();
            oaiRoot.setup();
        } catch (JAXBException e) {
            logger.error("Unhandled JAXBException");
        }

        try {
            oaiRoot.getRequest().setUri(uri);
            oaiRoot.getRequest().setVerb(paramMap.getFirst("verb").toString());
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.setProperty(Marshaller.JAXB_SCHEMA_LOCATION, XSI_SCHEMA);
        } catch (PropertyException e) {
            logger.error("unhandled PropertyException");
        }
    }
    */

    public Response(MultiValueMap paramMap, String uri) throws JAXBException  {
        this.uri = uri;
        this.paramMap = paramMap;
        this.oaiRoot = new OaiRoot();
        try {
            jc = JAXBContext.newInstance(OaiRoot.class);
            marshaller = jc.createMarshaller();
            oaiRoot.setup();
        } catch (JAXBException e) {
            logger.error("Unhandled JAXBException");
        }

        try {
            oaiRoot.getRequest().setUri(uri);
            oaiRoot.getRequest().setVerb(paramMap.getFirst("verb").toString());
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.setProperty(Marshaller.JAXB_SCHEMA_LOCATION, XSI_SCHEMA);
        } catch (PropertyException e) {
            logger.error("unhandled PropertyException");
        }

    }

    public void addError(String error) {
       errors.add(error);
    }

    public String getXmlString() throws Exception {
        StringWriter xml = new StringWriter();
        marshaller.marshal(oaiRoot, xml);
        var xmlString = xml.toString();
        return xmlString;
    }

    public OaiRoot getOaiRoot() {
        return this.oaiRoot;
    }
}
