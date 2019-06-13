package com.supercode.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class AuditConfig {

    private static final Logger logger = LoggerFactory.getLogger(AuditConfig.class);

    /**
     * Creates bean for audit aware
     * @return
     */
    @Bean
    public AuditorAware<String> auditorAware() {
        logger.debug("Returning bean of AuditorAware");
        return new AuditorAwareImpl();
    }

}

