package org.fcrepo.oaipmh.response;

import org.fcrepo.oaipmh.xml.OaiRoot;
import org.fcrepo.oaipmh.response.Response;
import org.fcrepo.oaipmh.xml.IdentifyElement;
import org.fcrepo.oaipmh.Config;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.annotation.XmlElement;
import java.io.StringWriter;
import java.io.FileOutputStream;
import org.springframework.util.MultiValueMap;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Primary;
import org.springframework.beans.factory.annotation.Autowired;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Identify {

    static final String OAI_PROTOCOL_VERSION = "2.0";

    protected IdentifyElement identifyElement;

    private Logger logger = LoggerFactory.getLogger(Identify.class);

    protected Response response;

    protected Config config;

    public Identify() {}

    public Identify(MultiValueMap paramMap, String uri) throws Exception  {
        response = new Response(paramMap, uri);
        identifyElement = new IdentifyElement();
        config = new Config();
        identifyElement.setProtocolVersion(OAI_PROTOCOL_VERSION);
        identifyElement.setBaseUrl(uri);
        identifyElement.setRepositoryName(config.getProperty("repository.name"));
        response.getOaiRoot().setObject(identifyElement);
    }

    public Response getResponse() {
        return this.response;
    }
}
