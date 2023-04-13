package org.fcrepo.oaipmh.xml;

import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlElement;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

import org.fcrepo.oaipmh.xml.Request;

@XmlRootElement(name="OAI-PMH")
public class OaiRoot {

    protected Request request;
    protected String responseDate;

    public OaiRoot() {
        request = new Request();
        Instant zuluTime = Instant.now().truncatedTo(ChronoUnit.SECONDS);
        setResponseDate(zuluTime.toString());
    }

    @XmlElement(name = "request")
    public Request getRequest() {
        return this.request;
    }
    public void setRequest(Request request) {
        this.request = request;
    }

    @XmlElement(name = "responseDate")
    public String getResponseDate() {
        return this.responseDate;
    }
    public void setResponseDate(String date) {
        this.responseDate = date;
    }

}
