package org.fcrepo.oaipmh.xml;

import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlValue;
import org.springframework.context.annotation.Configuration;
import org.fcrepo.oaipmh.xml.HeaderElement;
import java.util.List;
/*

  <ListIdentifiers>
   <header>
    <identifier>oai:arXiv.org:hep-th/9801001</identifier>
    <datestamp>1999-02-23</datestamp>
    <setSpec>physic:hep</setSpec>
   </header>
   <header>
    <identifier>oai:arXiv.org:hep-th/9801002</identifier>
    <datestamp>1999-03-20</datestamp>
    <setSpec>physic:hep</setSpec>
    <setSpec>physic:exp</setSpec>
   </header>
 */

@Configuration
@XmlRootElement(name = "ListIdentifiers")
public class ListIdentifiersElement {

    private List<HeaderElement> headers;

    public ListIdentifiersElement() {}

    @XmlElement(name = "header")
    public List<HeaderElement> getHeaders() {
        return this.headers;
    }

    public void setHeaderElement(List<HeaderElement> headers) {
        this.headers = headers;
    }

    public void addHeader(HeaderElement header) {
        this.headers.add(header);
    }

}
