package org.fcrepo.oaipmh.xml;

import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.bind.annotation.XmlAnyElement;
import jakarta.xml.bind.annotation.XmlType;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.lang.Object;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;

import org.fcrepo.oaipmh.xml.Request;
import org.fcrepo.oaipmh.xml.IdentifyElement;

@XmlRootElement(name="OAI-PMH")
@XmlType(name="OAI-PMH",propOrder={"responseDate","request","object"})
@XmlSeeAlso({IdentifyElement.class})
public class OaiRoot {

    protected Request request;
    protected String responseDate;
    protected Object object;

    public OaiRoot() {
        Instant zuluTime = Instant.now().truncatedTo(ChronoUnit.SECONDS);
        setResponseDate(zuluTime.toString());
    }

    public void setup() {
        request = new Request();
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

    @XmlAnyElement
    public void setObject(Object object) {
        this.object = object;
    }

    public Object getObject() {
        return this.object;
    }
}
