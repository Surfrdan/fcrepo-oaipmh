package org.fcrepo.oaipmh;

import org.fcrepo.oaipmh.Config;
import org.fcrepo.oaipmh.OaipmhException;
import org.fcrepo.oaipmh.FedoraClient;
import java.nio.file.Path;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.nio.file.attribute.FileTime;
import java.nio.file.Files;
import java.io.IOException;
import java.time.temporal.ChronoUnit;
import java.time.Instant;

public final class CacheClient {

    private static CacheClient INSTANCE;

    private static String root;

    private static Config config;

    private static Logger logger = LoggerFactory.getLogger(CacheClient.class);

    private CacheClient() {
        this.config = new Config();
        this.root = config.getProperty("cache.root");
    }

    private String getRoot() {
        return this.root;
    }

    public static CacheClient getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new CacheClient();
        }
        return INSTANCE;
    }

    public String get(String identifier, String format) throws OaipmhException {
        String record = "";
        Path filePath = Path.of(root + identifier + "." + format);
        logger.info(filePath.toString());
        try {
            FileTime creationTime = (FileTime) Files.getAttribute(filePath, "creationTime");
            var lifetime = Long.parseLong(config.getProperty("cache.lifetime"));

            if(creationTime.toInstant().plus(lifetime, ChronoUnit.MINUTES).isBefore(Instant.now())) {
                logger.debug("cached file is more than " + lifetime + " minutes old. Regenerating");
                FedoraClient fedora = FedoraClient.getInstance();
                record = fedora.getRecord(identifier, "rdf");
                put(record, identifier, "rdf");
            } else {
                logger.info("Cache HIT");
                record = Files.readString(filePath);
            }
        } catch (IOException ex) {
            logger.error("Failed to retrieve cache file: " + filePath.toString());
        }
        return record;
    }

    public static void put(String record, String identifier, String format) {
        Path filePath = Path.of(root + identifier + "." + format);
        try {
            Files.deleteIfExists(filePath);
            Files.writeString(filePath, record);
        } catch (IOException e) {
            logger.error("Failed to write to cache for: " + filePath.toString());
        }
    }

}
