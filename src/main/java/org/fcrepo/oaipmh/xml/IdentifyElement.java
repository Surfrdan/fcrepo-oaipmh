package org.fcrepo.oaipmh.xml;

import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
@XmlRootElement(name = "Identify")
public class IdentifyElement {

    protected String baseUrl;

    protected String protocolVersion;

    private String repositoryName;

    public IdentifyElement() {}

    @XmlElement(name = "repositoryName")
    public String getRepositoryName() {
        return this.repositoryName;
    }
    public void setRepositoryName(String repositoryName) {
        this.repositoryName = repositoryName;
    }

    @XmlElement(name = "baseURL")
    public String getBaseUrl() {
        return this.baseUrl;
    }
    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @XmlElement(name = "protocolVersion")
    public String getProtocolVersion() {
        return this.protocolVersion;
    }
    public void setProtocolVersion(String protocolVersion) {
        this.protocolVersion = protocolVersion;
    }


}
