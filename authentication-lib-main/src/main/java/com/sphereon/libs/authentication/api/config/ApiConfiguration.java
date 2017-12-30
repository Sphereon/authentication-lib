/*
 * Copyright (c) 2017 Sphereon BV https://sphereon.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.sphereon.libs.authentication.api.config;

import com.sphereon.libs.authentication.api.granttypes.Grant;
import com.sphereon.libs.authentication.impl.config.ApiConfigurator;

import java.io.File;

public interface ApiConfiguration extends ApiConfigurator {

    String getApplication();

    void setApplication(String defaultApplication);

    String getGatewayBaseUrl();

    void setGatewayBaseUrl(String gatewayBaseUrl);

    PersistenceType getPersistenceType();

    void setPersistenceType(PersistenceType persistenceType);

    PersistenceMode getPersistenceMode();

    void setPersistenceMode(PersistenceMode persistenceMode);

    File getStandalonePropertyFile();

    String getDefaultScope();

    void setDefaultScope(String scope);

    Long getDefaultValidityPeriod();

    void setDefaultValidityPeriod(Long validityPeriod);

    void setStandalonePropertyFile(File standalonePropertyFilePath);

    String getConsumerKey();

    void setConsumerKey(String consumerKey);

    String getConsumerSecret();

    void setConsumerSecret(String consumerSecret);

    Grant getDefaultGrant();

    void setDefaultGrant(Grant grant);

    boolean isAutoEncryptSecrets();

    void setAutoEncryptSecrets(boolean autoEncryptSecrets);

    String getAutoEncryptionPassword();

    void setAutoEncryptionPassword(String autoEncryptionPassword);

    void persist();
}
