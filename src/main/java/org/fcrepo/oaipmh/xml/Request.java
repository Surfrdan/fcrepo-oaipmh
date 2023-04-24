package org.fcrepo.oaipmh.xml;

import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlValue;

@XmlRootElement(name="request")
public class Request {

    private String verb;
    private String uri;

    //public Request() {}

    @XmlAttribute(name = "verb")
    public String getVerb() {
        return this.verb;
    }
    public void setVerb(String verb) {
        this.verb = verb;
    }

    @XmlValue
    public String getUri() {
        return this.uri;
    }
    public void setUri(String uri) {
        this.uri = uri;
    }
}
