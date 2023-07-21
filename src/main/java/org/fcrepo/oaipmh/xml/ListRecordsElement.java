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
@XmlRootElement(name = "ListRecords")
public class ListRecordsElement {

    // private List<SetsElement> sets;

    public ListRecordsElement() {}
    /* TODO 
    @XmlElement(name = "sets")
    public List<setsElement> getSets() {
        return this.sets;
    }
    public void setSetsElement(List<setElement> metadataFormats) {
        this.metadataFormats = metadataFormats;
    }

    public void addMetadataFormat(MetadataFormatElement metadataFormat) {
        this.metadataFormats.add(metadataFormat);
    }
    */

}
