package org.fcrepo.oaipmh.response;

import org.fcrepo.oaipmh.response.Response;
import org.fcrepo.oaipmh.xml.OaiRoot;
import org.fcrepo.oaipmh.xml.ErrorElement;
import org.springframework.util.MultiValueMap;
import jakarta.xml.bind.JAXBException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Error extends Response {

    protected ErrorElement errorElement;

    private Logger logger = LoggerFactory.getLogger(Error.class);

    public Error(MultiValueMap paramMap, String uri, String type) throws JAXBException  {
        super(paramMap, uri);
        ErrorElement errorElement = new ErrorElement();
        errorElement.setType(type);
        getOaiRoot().setObject(errorElement);
    }

}
