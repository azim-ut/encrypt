package com.example.enigma;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "enigma")
public class EnigmaConfig {
    private String key;
}
