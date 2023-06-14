package org.fcrepo.oaipmh;

import org.fcrepo.oaipmh.Config;
import org.fcrepo.oaipmh.OaipmhException;
import org.fcrepo.oaipmh.FedoraClient;
import java.nio.file.Path;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.nio.file.attribute.FileTime;
import java.nio.file.Files;
import java.io.File;
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
        Path filePath = Path.of(root + File.separator + identifier + "." + format);
        logger.info(filePath.toString());
        FileTime creationTime;
        try {
            creationTime = (FileTime) Files.getAttribute(filePath, "creationTime");
        } catch (IOException e) {
            logger.info("cache file doesnt exist yet: " + filePath.toString());
            FedoraClient fedora = FedoraClient.getInstance();
            record = fedora.getRecord(identifier, format);
            put(record, identifier, format);
            return record;
        }

        var lifetime = Long.parseLong(config.getProperty("cache.lifetime"));
        if(creationTime.toInstant().plus(lifetime, ChronoUnit.MINUTES).isBefore(Instant.now())) {
            logger.debug("cached file is more than " + lifetime + " minutes old. Regenerating");
            FedoraClient fedora = FedoraClient.getInstance();
            record = fedora.getRecord(identifier, format);
            put(record, identifier, format);
        } else {
            logger.info("Cache HIT");
            try {
                record = Files.readString(filePath);
            } catch (IOException e) {
                logger.error("error reading cached file: " + filePath);
            }
        }

        return record;
    }

    public static void put(String record, String identifier, String format) {
        Path filePath = Path.of(root + File.separator + identifier + "." + format);
        try {
            Files.deleteIfExists(filePath);
            Files.writeString(filePath, record);
        } catch (IOException e) {
            logger.error("Failed to write to cache for: " + filePath.toString());
        }
    }

}
