package com.oauth2.demo.configuration.sequrity;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class ClientDetailsProvider {
    private final String client;
    private final String secret;
    private final String[] scopes;
    private final String[] grantTypes;
    private final String signingKey;
    private final int accessTokenValiditySeconds;
    private final int refreshTokenValiditySeconds;

    public ClientDetailsProvider(@Value("${security.oauth2.client.client-id:client}") String client,
                                 @Value("${security.oauth2.client.client-secret:secret}") String secret,
                                 @Value("${security.oauth2.client.scope:read,write}") String[] scopes,
                                 @Value("${security.oauth2.client.authorized-grant-types:password,refresh_token}") String[] grantTypes,
                                 @Value("${security.signing-key:123}") String signingKey,
                                 @Value("${security.oauth2.client.access-token-validity-seconds: 300}") int accessTokenValiditySeconds,
                                 @Value("${security.oauth2.client.refresh-token-validity-seconds: 1800}") int refreshTokenValiditySeconds) {
        this.client = client;
        this.secret = secret;
        this.scopes = scopes;
        this.grantTypes = grantTypes;
        this.signingKey = signingKey;
        this.accessTokenValiditySeconds = accessTokenValiditySeconds;
        this.refreshTokenValiditySeconds = refreshTokenValiditySeconds;
    }
}
