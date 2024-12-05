package com.example.application.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.example.application.profile.DevProfile;
import com.example.application.profile.ProductionProfile;
import com.example.application.profile.SystemProfile;

@Configuration
public class ProfileConfig {
    @Bean
    @ConditionalOnProperty(name = "profile.dev", havingValue = "true")
    public SystemProfile devProfile() {
        return new DevProfile();
    }

    @Bean
    @ConditionalOnProperty(name = "profile.dev", havingValue = "false")
    public SystemProfile prodProfile() {
        return new ProductionProfile();
    }
}
