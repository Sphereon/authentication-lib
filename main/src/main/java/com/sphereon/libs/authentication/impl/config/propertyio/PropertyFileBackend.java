package com.sphereon.libs.authentication.impl.config.propertyio;

import com.sphereon.libs.authentication.api.config.PersistenceMode;
import com.sphereon.libs.authentication.impl.config.PropertyKey;
import org.apache.commons.configuration2.FileBasedConfiguration;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.ReloadingFileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Parameters;
import org.apache.commons.configuration2.convert.DefaultListDelimiterHandler;
import org.apache.commons.lang3.StringUtils;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.properties.PropertyValueEncryptionUtils;
import org.jasypt.salt.StringFixedSaltGenerator;

import java.io.File;
import java.net.URI;

public class PropertyFileBackend extends AbstractCommonsConfig {

    private static final char[] libKey = "p9Ep%MSzac%*2txW".toCharArray();

    private final StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();

    private final String application;


    public PropertyFileBackend(PersistenceMode persistenceMode, URI fileUri, String application) {
        super(persistenceMode);
        this.application = application;
        initEncryptor();

        Parameters params = new Parameters();
        FileBasedConfigurationBuilder<FileBasedConfiguration> builder =
                new ReloadingFileBasedConfigurationBuilder<FileBasedConfiguration>(PropertiesConfiguration.class)
                        .configure(params.properties()
                                .setFile(new File(fileUri))
                                .setListDelimiterHandler(new DefaultListDelimiterHandler(',')));
        builder.setAutoSave(persistenceMode == PersistenceMode.READ_WRITE);
        try {
            this.config = builder.getConfiguration();
        } catch (Exception e) {
            throw new RuntimeException("Could not initialize PropertyFileBackend for " + fileUri, e);
        }
    }


    private void initEncryptor() {
        encryptor.setPassword(new String(libKey));
        encryptor.setSaltGenerator(new StringFixedSaltGenerator(application));
    }


    @Override
    public String readProperty(String propertyPrefix, PropertyKey key, String defaultValue) {
        String value = super.readProperty(propertyPrefix, key, defaultValue);
        if (StringUtils.isNotEmpty(value)) {
            if (PropertyValueEncryptionUtils.isEncryptedValue(value)) {
                value = PropertyValueEncryptionUtils.decrypt(value, encryptor);
            } else if (key.isEncrypt()) {
                String encryptedValue = PropertyValueEncryptionUtils.encrypt(value, encryptor);
                super.saveProperty(propertyPrefix, key, encryptedValue);
            }
        }
        return value;
    }


    @Override
    public void saveProperty(String propertyPrefix, PropertyKey key, String value) {
        if (key.isEncrypt() && !PropertyValueEncryptionUtils.isEncryptedValue(value)) {
            value = PropertyValueEncryptionUtils.encrypt(value, encryptor);
        }
        super.saveProperty(propertyPrefix, key, value);
    }
}
