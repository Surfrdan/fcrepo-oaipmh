/*
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE and NOTICE files at the root of the source
 * tree.
 */
package org.fcrepo.oaipmh;

import org.fcrepo.oaipmh.Verb;
import org.springframework.util.MultiValueMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Handles verbs specified by the OAI-PMH 2.0 Specification
 *
 * http://www.openarchives.org/OAI/openarchivesprotocol.html
 *
 * @author Dan Field <dan.field@lyrasis.org>
 */
public class VerbHandler {

    public VerbHandler() {}

    private Logger logger = LoggerFactory.getLogger(VerbHandler.class);

    public void handle(MultiValueMap paramMap) {

        String verb = String.valueOf(paramMap.getFirst("verb"));

        switch(verb) {
            case Verb.GET_RECORD:
                break;
            case Verb.IDENTIFY:
                identify(paramMap);
                break;
            case Verb.LIST_IDENTIFIERS:
                break;
            case Verb.LIST_METADATA_FORMATS:
                break;
            case Verb.LIST_RECORDS:
                break;
            case Verb.LIST_SETS:
                break;
            default:
                logger.error("Unknown verb: " + verb);
                break;
        }
    }

    private String identify(MultiValueMap paramMap) {

        return "";
    }
}
