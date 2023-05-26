package org.fcrepo.oaipmh;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpClient.Version;
import java.net.Authenticator;
import java.net.http.HttpRequest;
import java.net.PasswordAuthentication;
import java.net.URISyntaxException;
import java.time.Duration;
import java.io.IOException;
import java.lang.InterruptedException;
import org.fcrepo.oaipmh.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class FedoraClient {

    private static FedoraClient INSTANCE;

    private URI root;

    private Config config;

    private HttpClient client;

    private Logger logger = LoggerFactory.getLogger(FedoraClient.class);

    private FedoraClient() {
        this.config = new Config();
        try {
            this.root = new URI(config.getProperty("fcrepo.root"));
        } catch (URISyntaxException e){
            logger.error("Invalid URL supplied to Fedora client");
        }
        this.client = getClient();
    }

    private URI getRoot() {
        return this.root;
    }

    public static FedoraClient getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new FedoraClient();
        }
        return INSTANCE;
    }

    private HttpClient getClient() {
        HttpClient client = HttpClient.newBuilder()
            .version(Version.HTTP_2)
            .authenticator(new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(
                        config.getProperty("fcrepo.username"),
                        config.getProperty("fcrepo.password").toCharArray());
                }
            })
            .build();
        return client;
    }

    public String getRecord(String pid) {
        var uri = URI.create(config.getProperty("fcrepo.root") + pid);
        logger.info("URL " + uri.toString());
        HttpRequest request = HttpRequest.newBuilder()
           .uri(uri)
           .timeout(Duration.ofMinutes(1))
           .header("Accept", "application/rdf+xml")
           .GET()
           .build();
        String record = "";
        try {
            HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
            record = response.body();
        } catch (IOException e) {
            logger.error("IOException in getRecord");
            record =  "ERROR";
        } catch (InterruptedException e) {
            logger.error("InteruptedException in getRecord");
            record = "ERROR";
        }
        return record;
    }

    /*
    public getObject(String pid) {
        return "object";
    }
    */
}
