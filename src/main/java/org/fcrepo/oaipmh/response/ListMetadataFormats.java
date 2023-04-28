package org.fcrepo.oaipmh.response;

import org.fcrepo.oaipmh.xml.OaiRoot;
import org.fcrepo.oaipmh.response.Response;
import org.fcrepo.oaipmh.xml.ListMetadataFormatsElement;
import org.fcrepo.oaipmh.xml.MetadataFormatElement;
import org.fcrepo.oaipmh.Config;
import org.springframework.util.MultiValueMap;
import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.JAXBException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ListMetadataFormats {

    static final String OAI_DC_SCHEMA = "http://www.openarchives.org/OAI/2.0/oai_dc.xsd";

    static final String OAI_DC_NAMESPACE = "http://www.openarchives.org/OAI/2.0/oai_dc/";

    static final String OAI_RDF_SCHEMA = "http://www.openarchives.org/OAI/2.0/rdf.xsd";

    static final String OAI_RDF_NAMESPACE = "http://www.w3.org/1999/02/22-rdf-syntax-ns#";

    private Logger logger = LoggerFactory.getLogger(Identify.class);

    private Response response;

    private Config config;

    private ListMetadataFormatsElement listMetadataFormatsElement;

    public ListMetadataFormats() {}

    public ListMetadataFormats(MultiValueMap paramMap, String uri) {
        if (paramMap.containsKey("identifier")) {
            // TODO: check for existance of PID and throw exception to be turned into an errorDoc
            logger.error("TODO: identifier arg not supported yet");
        }
        try {
            response = new Response(paramMap, uri);
        } catch (JAXBException e) {
            logger.error("Unhandles JAXBException");
        }
        config = new Config();
        listMetadataFormatsElement = new ListMetadataFormatsElement();
        var metadataFormats = new ArrayList<MetadataFormatElement>();

        var dcFormat = new MetadataFormatElement();
        dcFormat.setMetadataPrefix("oai_dc");
        dcFormat.setSchema(OAI_DC_SCHEMA);
        dcFormat.setMetadataNamespace(OAI_DC_NAMESPACE);
        metadataFormats.add(dcFormat);

        var rdfFormat = new MetadataFormatElement();
        rdfFormat.setMetadataPrefix("oai_rdf");
        rdfFormat.setSchema(OAI_RDF_SCHEMA);
        rdfFormat.setMetadataNamespace(OAI_RDF_NAMESPACE);
        metadataFormats.add(rdfFormat);

        listMetadataFormatsElement.setMetadataFormatElement(metadataFormats);
        response.getOaiRoot().setObject(listMetadataFormatsElement);
    }

    public Response getResponse() {
        return this.response;
    }
}
