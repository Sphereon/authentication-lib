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

import com.sphereon.commons.exceptions.EnumParseException;
import org.apache.commons.lang3.StringUtils;

public enum PersistenceMode {
    READ_ONLY, READ_WRITE;


    public static PersistenceMode fromString(String value) {
        if (StringUtils.isNotEmpty(value)) {
            for (PersistenceMode persistenceMode : PersistenceMode.values()) {
                if (reformat(persistenceMode.name()).equals(reformat(value))) {
                    return persistenceMode;
                }
            }
        }
        throw new EnumParseException("Could not parse " + value + " as PersistenceMode");
    }


    private static String reformat(String value) {
        return value.replace("_", "")
                .replace("-", "")
                .toLowerCase();
    }

}
