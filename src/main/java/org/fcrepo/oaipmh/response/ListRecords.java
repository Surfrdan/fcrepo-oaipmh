package org.fcrepo.oaipmh.response;

import org.fcrepo.oaipmh.xml.OaiRoot;
import org.fcrepo.oaipmh.response.Response;
import org.fcrepo.oaipmh.xml.ListRecordsElement;
import org.fcrepo.oaipmh.Config;
import org.fcrepo.oaipmh.OaipmhException;
import org.springframework.util.MultiValueMap;
import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.JAXBException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ListRecords extends Response {

    static final String OAI_DC_SCHEMA = "http://www.openarchives.org/OAI/2.0/oai_dc.xsd";

    static final String OAI_DC_NAMESPACE = "http://www.openarchives.org/OAI/2.0/oai_dc/";

    static final String OAI_RDF_SCHEMA = "http://www.openarchives.org/OAI/2.0/rdf.xsd";

    static final String OAI_RDF_NAMESPACE = "http://www.w3.org/1999/02/22-rdf-syntax-ns#";

    private Logger logger = LoggerFactory.getLogger(Identify.class);

    private Config config;

    private ListRecordsElement listRecordsElement;

    private static final List<String> ALLOWED_ARGS = List.of(
            "from",
            "until",
            "metadataPrefix",
            "set",
            "resumptionToken"
    );

    private static final List<String> REQUIRED_ARGS = List.of("metadataPrefix");

    public ListRecords(MultiValueMap paramMap, String uri) throws JAXBException, OaipmhException {
        super(paramMap, uri);
        checkArgs(paramMap, ALLOWED_ARGS, REQUIRED_ARGS);
        config = new Config();
        // TODO: Complete the class
    }

}
