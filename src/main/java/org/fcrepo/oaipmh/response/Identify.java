package org.fcrepo.oaipmh.response;

import org.fcrepo.oaipmh.response.Response;
import org.fcrepo.oaipmh.xml.OaiRoot;
import org.fcrepo.oaipmh.xml.IdentifyElement;
import org.fcrepo.oaipmh.OaipmhException;
import org.springframework.util.MultiValueMap;
import jakarta.xml.bind.JAXBException;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Identify extends Response {

    static final String OAI_PROTOCOL_VERSION = "2.0";

    static final String OAI_GRANULARITY = "YYYY-MM-DDThh:mm:ssZ";

    static final String OAI_EARLIEST_DATESTAMP = "0000-01-01T00:00:00Z";

    static final String OAI_DELETED_RECORD = "no";

    protected IdentifyElement identifyElement;

    private Logger logger = LoggerFactory.getLogger(Identify.class);

    private static final List<String> ALLOWED_ARGS = List.of();

    public Identify(MultiValueMap paramMap, String uri) throws OaipmhException, JAXBException {
        super(paramMap, uri);
        checkArgs(ALLOWED_ARGS);

        identifyElement = new IdentifyElement();
        identifyElement.setProtocolVersion(OAI_PROTOCOL_VERSION);
        identifyElement.setGranularity(OAI_GRANULARITY);
        identifyElement.setEarliestDatestamp(OAI_EARLIEST_DATESTAMP);
        identifyElement.setBaseUrl(uri);
        identifyElement.setRepositoryName(config.getProperty("repository.name"));
        identifyElement.setDeletedRecord(OAI_DELETED_RECORD);
        identifyElement.setAdminEmail(config.getProperty("admin.email"));
        getOaiRoot().setObject(identifyElement);
    }

}
