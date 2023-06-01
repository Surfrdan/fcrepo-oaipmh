package org.fcrepo.oaipmh.response;

import org.fcrepo.oaipmh.xml.OaiRoot;
import org.fcrepo.oaipmh.xml.IdentifyElement;
import org.fcrepo.oaipmh.OaipmhException;
import org.fcrepo.oaipmh.Config;
import jakarta.xml.bind.JAXBContext;
import org.eclipse.persistence.jaxb.JAXBContextFactory;
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
import java.util.Iterator;
import java.util.Set;
import org.apache.commons.lang3.SerializationUtils;

public class Response {

    public static final String XSI_NS = "http://www.w3.org/2001/XMLSchema-instance";

    public static final String XSI_SCHEMA = "http://www.openarchives.org/OAI/2.0/ http://www.openarchives.org/OAI/2.0/OAI-PMH.xsd";

    protected OaiRoot oaiRoot;

    private JAXBContext jc;

    private Marshaller marshaller;

    protected String uri;

    protected String verb;

    protected MultiValueMap paramMap;

    private List<String> errors;

    protected Config config;

    private Logger logger = LoggerFactory.getLogger(Response.class);

    public Response(MultiValueMap paramMap, String uri) throws JAXBException  {
        this.config = new Config();
        this.uri = uri;

        if(paramMap.get("verb") != null) {
            this.verb = paramMap.get("verb").toString();
        }
        this.oaiRoot = new OaiRoot();
        try {
            jc = JAXBContextFactory.createContext(new Class[] {OaiRoot.class}, null);
            marshaller = jc.createMarshaller();
            oaiRoot.setup();
        } catch (JAXBException e) {
            logger.error("Unhandled JAXBException");
        }

        try {
            oaiRoot.getRequest().setUri(uri);
            oaiRoot.getRequest().setVerb(verb);
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.setProperty(Marshaller.JAXB_SCHEMA_LOCATION, XSI_SCHEMA);
        } catch (PropertyException e) {
            logger.error("unhandled PropertyException");
        }

    }

    public void checkArgs(MultiValueMap paramMap, List<String> allowedArgs) throws OaipmhException {
        paramMap.remove("verb");
        Iterator<String> mapIterator = paramMap.keySet().iterator();
        while(mapIterator.hasNext()) {
            String key = mapIterator.next();
            if(!allowedArgs.contains(key)) {
                logger.error("badArgument: " + key);
                throw new OaipmhException("badArgument");
            }
        }
    }

    public void addError(String error) {
       errors.add(error);
    }

    public String getXmlString() throws Exception {
        StringWriter xml = new StringWriter();
        marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
        marshaller.marshal(oaiRoot, xml);
        var xmlString = xml.toString();
        return xmlString;
    }

    public OaiRoot getOaiRoot() {
        return this.oaiRoot;
    }
}
