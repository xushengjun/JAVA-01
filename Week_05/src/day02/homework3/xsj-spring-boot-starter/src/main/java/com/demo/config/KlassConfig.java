package com.demo.config;

import com.demo.properties.KlassProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(KlassProperties.class)
public class KlassConfig {
}

