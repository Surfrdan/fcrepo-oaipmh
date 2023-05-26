/*
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE and NOTICE files at the root of the source
 * tree.
 */
package org.fcrepo.oaipmh;

import java.util.Objects;
import org.fcrepo.oaipmh.Verb;
import org.fcrepo.oaipmh.response.Response;
import org.fcrepo.oaipmh.response.Identify;
import org.fcrepo.oaipmh.response.GetRecord;
import org.fcrepo.oaipmh.response.Error;
import org.fcrepo.oaipmh.response.ListMetadataFormats;
import org.fcrepo.oaipmh.OaipmhException;
import org.springframework.util.MultiValueMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Value;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Handles verbs specified by the OAI-PMH 2.0 Specification
 *
 * http://www.openarchives.org/OAI/openarchivesprotocol.html
 *
 * @author Dan Field <dan.field@lyrasis.org>
 */
@Configuration
public class VerbHandler {

    public VerbHandler() {}

    private Logger logger = LoggerFactory.getLogger(VerbHandler.class);

    public String handle(MultiValueMap paramMap, String uri) {
        String verb = String.valueOf(paramMap.getFirst("verb"));
        String response = null;

        switch(verb) {
            case Verb.GET_RECORD:
                try {
                    GetRecord doc = new GetRecord(paramMap, uri);
                    response = doc.getXmlString();
                } catch (Exception e) {
                    logger.error("getverb error");
                    e.printStackTrace(System.out);
                }
                break;
            case Verb.IDENTIFY:
                try {
                    Identify doc = new Identify(paramMap, uri);
                    response = doc.getXmlString();
                } catch (Exception e) {
                    try {
                        Error doc = new Error(paramMap, uri, e.getMessage());
                        response = doc.getXmlString();
                    } catch (Exception unhandled) {
                        logger.error(unhandled.getMessage());
                    }
                }
                break;
            case Verb.LIST_IDENTIFIERS:
                break;
            case Verb.LIST_METADATA_FORMATS:
                try {
                    ListMetadataFormats doc = new ListMetadataFormats(paramMap, uri);
                    //response = doc.getResponse().getXmlString();
                } catch (Exception e) {
                    logger.error("unhandled exception");
                    e.printStackTrace(System.out);
                    response = "TODO: ERROR Document";
                }

                break;
            case Verb.LIST_RECORDS:
                break;
            case Verb.LIST_SETS:
                break;
            default:
                logger.error("Unknown verb: " + verb);
                break;
        }
        if (!Objects.isNull(response)) {
            return response;
        } else {
            return "TODO: Verb Response Error";
        }
    }

}
