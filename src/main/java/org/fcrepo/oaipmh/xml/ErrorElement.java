package org.fcrepo.oaipmh.xml;

import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlValue;

@XmlRootElement(name="error")
public class ErrorElement {

    private String type;

    @XmlValue
    public String getType() {
        return this.type;
    }
    public void setType(String type) {
        this.type = type;
    }
}
