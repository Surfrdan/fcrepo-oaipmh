package org.fcrepo.oaipmh.response;

import org.fcrepo.oaipmh.response.Response;
import org.fcrepo.oaipmh.xml.OaiRoot;
import org.fcrepo.oaipmh.xml.GetRecordElement;
import org.fcrepo.oaipmh.OaipmhException;
import org.fcrepo.oaipmh.Config;
import org.fcrepo.oaipmh.FedoraClient;
import org.springframework.util.MultiValueMap;
import jakarta.xml.bind.JAXBException;
import java.util.List;

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
        checkArgs(ALLOWED_ARGS);
        String identifier = super.paramMap.getFirst("identifier").toString();
        logger.info("GetRecord: " + identifier);
        getRecordElement = new GetRecordElement();
        config = new Config();

        FedoraClient client = FedoraClient.getInstance();
        var record = client.getRecord(identifier);
        getRecordElement.setRecord(record);
        oaiRoot.setObject(getRecordElement);
    }
}
