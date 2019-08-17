package com.supercode.config;

import com.supercode.model.CurrentUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {

    Logger logger = LoggerFactory.getLogger(AuditorAwareImpl.class);

    /**
     * Get current logged in user for audit
     * in case of no user like password reset with otp, return guest as user
     * @return user id
     */
    @Override
    public Optional<String> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !(authentication.getPrincipal() instanceof CurrentUser || authentication instanceof AnonymousAuthenticationToken)) {
            return Optional.of("guest");
        }
        return Optional.of(((CurrentUser) authentication.getPrincipal()).getId().toString());
    }
}

