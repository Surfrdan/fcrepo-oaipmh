package org.fcrepo.oaipmh.xml;

import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlValue;
import org.springframework.context.annotation.Configuration;
import org.fcrepo.oaipmh.xml.MetadataFormatElement;
import java.util.List;

/*
<ListMetadataFormats>
   <metadataFormat>
     <metadataPrefix>oai_dc</metadataPrefix>
     <schema>http://www.openarchives.org/OAI/2.0/oai_dc.xsd
       </schema>
     <metadataNamespace>http://www.openarchives.org/OAI/2.0/oai_dc/
       </metadataNamespace>
   </metadataFormat>
*/

@Configuration
@XmlRootElement(name = "ListMetadataFormats")
public class ListMetadataFormatsElement {

    private List<MetadataFormatElement> metadataFormats;

    public ListMetadataFormatsElement() {}

    @XmlElement(name = "metadataFormat")
    public List<MetadataFormatElement> getMetadataFormats() {
        return this.metadataFormats;
    }
    public void setMetadataFormatElement(List<MetadataFormatElement> metadataFormats) {
        this.metadataFormats = metadataFormats;
    }

    public void addMetadataFormat(MetadataFormatElement metadataFormat) {
        this.metadataFormats.add(metadataFormat);
    }


}
