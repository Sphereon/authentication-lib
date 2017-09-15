package com.sphereon.libs.authentication.impl.config.backends;

import com.sphereon.libs.authentication.api.config.PersistenceMode;
import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.BasicConfigurationBuilder;

import java.util.HashMap;
import java.util.Map;

public class InMemoryConfig extends AbstractCommonsConfig {

    private static final Map<String, Configuration> inMemoryConfiguration = new HashMap<>();


    public InMemoryConfig(String application) {
        super(PersistenceMode.READ_WRITE);

        this.config = inMemoryConfiguration.get(application);
        if (config == null) {
            BasicConfigurationBuilder<PropertiesConfiguration> builder = new BasicConfigurationBuilder<>(PropertiesConfiguration.class);
            try {
                this.config = builder.getConfiguration();
                inMemoryConfiguration.put(application, config);
            } catch (Exception e) {
                throw new RuntimeException("Could not initialize PropertyFileBackend / InMemoryConfig", e);
            }
        }
    }
}