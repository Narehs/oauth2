package com.oauth2.demo.configuration.sequrity;

import com.oauth2.demo.domain.User;
import com.oauth2.demo.repository.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.TokenRequest;
import org.springframework.security.oauth2.provider.request.DefaultOAuth2RequestFactory;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class CustomOAuth2RequestFactory extends DefaultOAuth2RequestFactory {

    private final UserRepository userRepository;

    public CustomOAuth2RequestFactory(ClientDetailsService clientDetailsService, UserRepository userRepository) {
        super(clientDetailsService);
        this.userRepository = userRepository;
    }

    @Override
    public TokenRequest createTokenRequest(Map<String, String> parameters, ClientDetails authenticatedClient) {
        final User user = userRepository.findByUsernameIgnoreCase(parameters.get("username"))
                .orElseThrow(CustomOAuth2RequestFactory::usernameNotFoundException);
        boolean isPasswordRequest = "password".equals(parameters.get("grant_type")) && parameters.get("password") != null;

        if (isPasswordRequest && user.getAuthorities() != null) {
            final String token = parameters.get("token");
            if (user.isAccountNonLocked()) {
                user.setLocked(true);
            } else if (token != null) {
                user.setLocked(false);
            }
            userRepository.save(user);
        }
        return super.createTokenRequest(parameters, authenticatedClient);
    }


    private static UsernameNotFoundException usernameNotFoundException() {
        return new UsernameNotFoundException("Not found");
    }
}
