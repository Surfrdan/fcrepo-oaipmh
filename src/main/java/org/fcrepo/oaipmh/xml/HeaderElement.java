package org.fcrepo.oaipmh.xml;

import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlValue;
import org.springframework.context.annotation.Configuration;
import org.fcrepo.oaipmh.xml.MetadataFormatElement;
/*
   <header>  
    <identifier>oai:arXiv.org:hep-th/9801001</identifier>
    <datestamp>1999-02-23</datestamp>
    <setSpec>physic:hep</setSpec>
   </header>
 */

@Configuration
@XmlRootElement(name = "header")
public class HeaderElement {

    private String identifier;

    private String datestamp;

    private String setSpec;

    public HeaderElement() {}

    @XmlElement(name = "identifier")
    public String getIdentifier() {
        return this.identifier;
    }
    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    @XmlElement(name = "datestamp")
    public String getDatetamp() {
        return this.datestamp;
    }
    public void setiDatestamp(String datestamp) {
        this.datestamp = datestamp;
    }


    @XmlElement(name = "setSpec")
    public String getSetSpec() {
        return this.setSpec;
    }
    public void setSetSpec(String setSpec) {
        this.setSpec = setSpec;
    }

}
