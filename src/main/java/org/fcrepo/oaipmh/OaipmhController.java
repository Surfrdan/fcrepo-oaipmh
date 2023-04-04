package org.fcrepo.oaipmh;

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

@RestController
//@RequestMapping(path = "/")
@RequestMapping
public class OaipmhController {

    private Logger logger = LoggerFactory.getLogger(OaipmhController.class);

    @PostMapping(value = "oai", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    @ResponseBody
    public ResponseEntity<String> post(@RequestParam MultiValueMap<String,String> paramMap) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        String result = null;
        return new ResponseEntity<>(result, headers, HttpStatus.ACCEPTED);
    }

    @GetMapping(value = "oai")
    @ResponseBody
    public ResponseEntity<String> get(@PathVariable("verb") String verb) {
        HttpHeaders headers = new HttpHeaders();
        String result = null;
        return new ResponseEntity<>(result, headers, HttpStatus.ACCEPTED);
    }

}
