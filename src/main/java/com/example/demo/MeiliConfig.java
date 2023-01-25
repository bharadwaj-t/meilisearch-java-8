package com.example.demo;

import com.meilisearch.sdk.Client;
import com.meilisearch.sdk.Config;
import com.meilisearch.sdk.Index;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class MeiliConfig {
    @Value("${meili.endpoint}")
    private String meiliEndpoint;

    @Value("${meili.masterkey}")
    private String meiliMasterKey;

    @Value("${meili.profile.index}")
    private String meiliProfileIndex;

    @Bean
    public Index createMeiliIndex() throws Exception {

        String[] versionElements = System.getProperty("java.version").split("\\.");
        int discard = Integer.parseInt(versionElements[0]);
        int version;
        if (discard == 1) {
            version = Integer.parseInt(versionElements[1]);
        } else {
            version = discard;
        }

        log.info("the current java version is: {}", version);

        try {
            Client meiliClient = new Client(new Config(meiliEndpoint,
                    meiliMasterKey));
            //Index meiliIndex = meiliClient.createIndex(meiliProfileIndex);
            Index meiliIndex = meiliClient.getIndex(meiliProfileIndex);
            //log.info("[Utilities] Meili {} Index initialized!", meiliIndex);
            return meiliIndex;
        } catch (Exception e) {
            throw new Exception("Failed to get Meili index...", e);
        }

    }
}
