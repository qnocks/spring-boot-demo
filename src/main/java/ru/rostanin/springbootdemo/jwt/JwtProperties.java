package ru.rostanin.springbootdemo.jwt;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "auth.jwt")
@Getter
@Setter
@Component
public class JwtProperties {
    public String secret;
    public int expirationTime;
    public String tokenPrefix;
    public String headerString;
}
