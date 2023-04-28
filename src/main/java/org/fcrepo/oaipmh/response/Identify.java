package org.fcrepo.oaipmh.response;

import org.fcrepo.oaipmh.response.Response;
import org.fcrepo.oaipmh.xml.OaiRoot;
import org.fcrepo.oaipmh.xml.IdentifyElement;
import org.fcrepo.oaipmh.OaipmhException;
import org.fcrepo.oaipmh.Config;
import org.springframework.util.MultiValueMap;
import jakarta.xml.bind.JAXBException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Identify {

    static final String OAI_PROTOCOL_VERSION = "2.0";

    static final String OAI_GRANULARITY = "YYYY-MM-DDThh:mm:ssZ";

    static final String OAI_EARLIEST_DATESTAMP = "0000-01-01T00:00:00Z";

    static final String OAI_DELETED_RECORD = "no";

    protected IdentifyElement identifyElement;

    private Logger logger = LoggerFactory.getLogger(Identify.class);

    protected Response response;

    protected Config config;

    public Identify() {}

    public Identify(MultiValueMap paramMap, String uri) throws OaipmhException  {
        if(paramMap.size() > 1) {
            throw new OaipmhException("badArgument");
        }
        try {
            this.response = new Response(paramMap, uri);
        } catch (JAXBException e) {
            logger.error("TODO: Unhandled JAXBException 1");
        }
        identifyElement = new IdentifyElement();
        config = new Config();
        identifyElement.setProtocolVersion(OAI_PROTOCOL_VERSION);
        identifyElement.setGranularity(OAI_GRANULARITY);
        identifyElement.setEarliestDatestamp(OAI_EARLIEST_DATESTAMP);
        identifyElement.setBaseUrl(uri);
        identifyElement.setRepositoryName(config.getProperty("repository.name"));
        identifyElement.setDeletedRecord(OAI_DELETED_RECORD);
        identifyElement.setAdminEmail(config.getProperty("admin.email"));
        this.response.getOaiRoot().setObject(identifyElement);
    }

    public Response getResponse() {
        return this.response;
    }
}
