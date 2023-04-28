package org.fcrepo.oaipmh.xml;

import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlValue;
import org.springframework.context.annotation.Configuration;
import org.fcrepo.oaipmh.xml.MetadataFormatElement;

@Configuration
@XmlRootElement(name = "metadataFormat")
public class MetadataFormatElement {

    private String metadataPrefix;

    private String schema;

    private String metadataNamespace;

    public MetadataFormatElement() {}

    @XmlElement(name = "metadataPrefix")
    public String getMetadataPrefix() {
        return this.metadataPrefix;
    }
    public void setMetadataPrefix(String metadataPrefix) {
        this.metadataPrefix = metadataPrefix;
    }

    @XmlElement(name = "schema")
    public String getSchema() {
        return this.schema;
    }
    public void setSchema(String schema) {
        this.schema = schema;
    }


    @XmlElement(name = "metadataNamespace")
    public String getMetadataNamespace() {
        return this.metadataNamespace;
    }
    public void setMetadataNamespace(String metadataNamespace) {
        this.metadataNamespace = metadataNamespace;
    }

}
