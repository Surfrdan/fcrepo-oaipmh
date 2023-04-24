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

    private String baseUrl;

    private String protocolVersion;

    private String repositoryName;

    private String earliestDatestamp;

    private String deletedRecord;

    private String granularity;

    private String adminEmail;

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

    @XmlElement(name = "earliestDatestamp")
    public String getEarliestDatestamp() {
        return this.earliestDatestamp;
    }
    public void setEarliestDatestamp(String earliestDatestamp) {
        this.earliestDatestamp = earliestDatestamp;
    }

    @XmlElement(name = "deletedRecord")
    public String getDeletedRecord() {
        return this.deletedRecord;
    }
    public void setDeletedRecord(String deletedRecord) {
        this.deletedRecord = deletedRecord;
    }

    @XmlElement(name = "granularity")
    public String getGranularity() {
        return this.granularity;
    }
    public void setGranularity(String granularity) {
        this.granularity = granularity;
    }

    @XmlElement(name = "adminEmail")
    public String getAdminEmail() {
        return this.adminEmail;
    }
    public void setAdminEmail(String adminEmail) {
        this.adminEmail = adminEmail;
    }


}
