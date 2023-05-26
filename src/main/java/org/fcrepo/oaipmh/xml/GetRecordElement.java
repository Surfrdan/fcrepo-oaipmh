package org.fcrepo.oaipmh.xml;

import org.fcrepo.oaipmh.xml.Record;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlAnyElement;
import jakarta.xml.bind.annotation.XmlValue;
import jakarta.xml.bind.annotation.XmlInlineBinaryData;
import org.springframework.context.annotation.Configuration;

/*
<GetRecord>
   <record> 
    <header>
      <identifier>oai:arXiv.org:cs/0112017</identifier> 
      <datestamp>2001-12-14</datestamp>
      <setSpec>cs</setSpec> 
      <setSpec>math</setSpec>
    </header>
    <metadata>
      <oai_dc:dc 
*/

@Configuration
@XmlRootElement(name = "GetRecord")
public class GetRecordElement {

    private String record;

    public GetRecordElement() {}

    @XmlAnyElement(value=RecordHandler.class)
    public String getRecord() {
        return this.record;
    }
    public void setRecord(String record) {
        this.record = record;
    }
}
