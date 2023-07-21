package org.fcrepo.oaipmh.response;

import org.fcrepo.oaipmh.xml.OaiRoot;
import org.fcrepo.oaipmh.response.Response;
import org.fcrepo.oaipmh.xml.ListIdentifiersElement;
import org.fcrepo.oaipmh.xml.HeaderElement;
import org.fcrepo.oaipmh.Config;
import org.fcrepo.oaipmh.OaipmhException;
import org.fcrepo.oaipmh.CacheClient;
import org.springframework.util.MultiValueMap;
import java.util.ArrayList;
import java.util.List;
import java.time.temporal.ChronoUnit;
import java.time.Instant;
import jakarta.xml.bind.JAXBException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ListIdentifiers extends Response {

    static final String OAI_DC_SCHEMA = "http://www.openarchives.org/OAI/2.0/oai_dc.xsd";

    static final String OAI_DC_NAMESPACE = "http://www.openarchives.org/OAI/2.0/oai_dc/";

    static final String OAI_RDF_SCHEMA = "http://www.openarchives.org/OAI/2.0/rdf.xsd";

    static final String OAI_RDF_NAMESPACE = "http://www.w3.org/1999/02/22-rdf-syntax-ns#";

    private Logger logger = LoggerFactory.getLogger(Identify.class);

    private Config config;

    private ListIdentifiersElement listIdentifiersElement;

    private static final List<String> ALLOWED_ARGS = List.of(
            "from",
            "until",
            "metadataPrefix",
            "set",
            "resumptionToken"
    );

    private static final List<String> REQUIRED_ARGS = List.of(
            "metadataPrefix"
    );

    public ListIdentifiers(MultiValueMap paramMap, String uri) throws JAXBException, OaipmhException {
        super(paramMap, uri);
        checkArgs(paramMap, ALLOWED_ARGS, REQUIRED_ARGS);
        listIdentifiersElement = new ListIdentifiersElement();

        config = new Config();

        String metadataPrefix = null;
        try {
            metadataPrefix = paramMap.getFirst("metadataPrefix").toString();
            logger.info("metadataPrefix: " + metadataPrefix);
        } catch (NullPointerException e) {
            logger.debug("metadataPrefix not set");
        }

        String set = null;
        try {
            set = paramMap.getFirst("set").toString();
            logger.info("set: " + set);
        } catch(NullPointerException e) {
            logger.debug("set not set");
        }

        Instant from = null;
        try {
            from = Instant.parse(paramMap.getFirst("from").toString());
            logger.info("From: " + from);
        } catch(NullPointerException e) {
            logger.debug("from not set");
        }

        Instant until = null;
        try {
            until = Instant.parse(paramMap.getFirst("until").toString());
            logger.debug("Until: " + until);
        } catch(NullPointerException e) {
            logger.debug("until not set");
        }

        String resumptionToken = null;
        try {
            resumptionToken = paramMap.getFirst("resumptionToken").toString();
            logger.info("resumptionToken: " + resumptionToken);
        } catch(NullPointerException e) {
            logger.debug("resumptionToken not set");
        }

        var listIdentifiers = new ArrayList<HeaderElement>();


        CacheClient cache = CacheClient.getInstance();
        String headers = null;
        switch(metadataPrefix) {
            case "oai_rdf":
                headers = cache.getList(from, until, "rdf");
                break;
            case "oai_dc":
                headers = cache.getList(from, until, "dc");
                break;
            default:
                throw new OaipmhException("Invalid metadataPrefix supplied to ListIdentifiers: " + metadataPrefix);
        }
        listIdentifiersElement.setHeaderElement(listIdentifiers);
        oaiRoot.setObject(listIdentifiersElement);

        // TODO: Complete the class
    }

}
