/*
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE and NOTICE files at the root of the source
 * tree.
 */
package org.fcrepo.oaipmh;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.ResponseEntity;
import org.springframework.http.MediaType;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.util.MultiValueMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;

@RestController
@RequestMapping
public class OaipmhController {

    private @Autowired AutowireCapableBeanFactory beanFactory;

    private Logger logger = LoggerFactory.getLogger(OaipmhController.class);

    @PostMapping(value = "oai", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    @ResponseBody
    public ResponseEntity<String> post(@RequestParam MultiValueMap<String,String> paramMap, HttpServletRequest request) {
        // var newParamMap = paramMap.deepCopy();
        String result = null;
        VerbHandler handler = new VerbHandler();
        result = handler.handle(paramMap, request.getRequestURL().toString());
        return ResponseEntity.ok()
            .header("Content-type", MediaType.TEXT_XML_VALUE)
            .body(result);

    }

    @GetMapping(value = "oai")
    @ResponseBody
    public ResponseEntity<String> get(@RequestParam MultiValueMap<String,String> paramMap, HttpServletRequest request) {
        String result = null;
        VerbHandler handler = new VerbHandler();

        result = handler.handle(paramMap, request.getRequestURL().toString());
        return ResponseEntity.ok()
            .header("Content-type", MediaType.TEXT_XML_VALUE)
            .body(result);
    }

}
