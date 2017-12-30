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

package com.sphereon.libs.authentication.impl.objects.granttypes;

import com.sphereon.libs.authentication.api.granttypes.ClientCredentialGrant;
import com.sphereon.libs.authentication.api.granttypes.GrantType;
import com.sphereon.libs.authentication.impl.RequestParameters;
import com.sphereon.libs.authentication.impl.config.ConfigManager;
import com.sphereon.libs.authentication.impl.config.ConfigPersistence;
import com.sphereon.libs.authentication.impl.objects.RequestParameterKey;

import java.util.Map;

class ClientCredentialGrantImpl implements ClientCredentialGrant, RequestParameters, ConfigPersistence {

    ClientCredentialGrantImpl() {
    }


    @Override
    public void headerParameters(Map<RequestParameterKey, String> parameterMap) {
    }


    @Override
    public void bodyParameters(Map<RequestParameterKey, String> parameterMap) {
        parameterMap.put(RequestParameterKey.GRANT_TYPE, GrantTypeKey.CLIENT_CREDENTIALS.getValue());
    }


    @Override
    public void loadConfig(ConfigManager configManager) {
    }


    @Override
    public void persistConfig(ConfigManager configManager) {
    }


    @Override
    public GrantType getGrantType() {
        return GrantType.CLIENT_CREDENTIAL;
    }

}
