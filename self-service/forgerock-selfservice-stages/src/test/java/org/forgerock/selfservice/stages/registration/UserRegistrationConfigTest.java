/*
 * The contents of this file are subject to the terms of the Common Development and
 * Distribution License (the License). You may not use this file except in compliance with the
 * License.
 *
 * You can obtain a copy of the License at legal/CDDLv1.0.txt. See the License for the
 * specific language governing permission and limitations under the License.
 *
 * When distributing Covered Software, include this CDDL Header Notice in each file and include
 * the License file at legal/CDDLv1.0.txt. If applicable, add the following below the CDDL
 * Header, with the fields enclosed by brackets [] replaced by your own identifying
 * information: "Portions copyright [year] [name of copyright owner]".
 *
 * Copyright 2015 ForgeRock AS.
 */
package org.forgerock.selfservice.stages.registration;

import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.NamedType;
import org.forgerock.selfservice.core.config.StageConfig;
import org.testng.annotations.Test;

/**
 * Unit test for {@link UserRegistrationConfig}.
 *
 * @since 0.2.0
 */
public class UserRegistrationConfigTest {

    @Test
    public void testConfigFromJson() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerSubtypes(
                new NamedType(UserRegistrationConfig.class, UserRegistrationConfig.NAME)
        );
        StageConfig<?> config = mapper.readValue(getClass().getResource("/userRegistration.json"), StageConfig.class);

        assertThat(config).isInstanceOf(UserRegistrationConfig.class);
        UserRegistrationConfig userRegistrationConfig = (UserRegistrationConfig) config;
        assertThat(userRegistrationConfig.getIdentityServiceUrl()).isEqualTo("/users");
    }
}
