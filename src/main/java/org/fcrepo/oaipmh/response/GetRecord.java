package org.fcrepo.oaipmh.response;

import org.fcrepo.oaipmh.response.Response;
import org.fcrepo.oaipmh.xml.OaiRoot;
import org.fcrepo.oaipmh.xml.GetRecordElement;
import org.fcrepo.oaipmh.OaipmhException;
import org.fcrepo.oaipmh.Config;
import org.fcrepo.oaipmh.CacheClient;
import org.springframework.util.MultiValueMap;
import jakarta.xml.bind.JAXBException;
import java.util.List;
import java.util.HashMap;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GetRecord extends Response {

    protected GetRecordElement getRecordElement;

    private Logger logger = LoggerFactory.getLogger(Identify.class);

    protected Response response;

    protected Config config;

    private static final List<String> ALLOWED_ARGS = List.of("identifier", "metadataPrefix");

    public GetRecord(MultiValueMap paramMap, String uri) throws OaipmhException, JAXBException  {

        super(paramMap, uri);
        checkArgs(paramMap, ALLOWED_ARGS);

        String identifier = paramMap.getFirst("identifier").toString();
        String metadataPrefix = paramMap.getFirst("metadataPrefix").toString();

        getRecordElement = new GetRecordElement();
        config = new Config();

        CacheClient cache = CacheClient.getInstance();
        String record;
        switch(metadataPrefix) {
            case "oai_rdf":
                record = cache.get(identifier, "rdf");
                break;
            case "oai_dc":
                record = cache.get(identifier, "dc");
                break;
            default:
                throw new OaipmhException("Invalid metadataPrefix supplied to GetRecord: " + metadataPrefix);
        }
        getRecordElement.setRecord(record);
        oaiRoot.setObject(getRecordElement);
    }

}
