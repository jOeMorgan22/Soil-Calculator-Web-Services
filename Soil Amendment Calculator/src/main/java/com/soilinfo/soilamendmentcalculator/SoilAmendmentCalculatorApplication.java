package com.soilinfo.soilamendmentcalculator;

import com.soilinfo.soilamendmentcalculator.config.RSAKeyProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@EnableConfigurationProperties(RSAKeyProperties.class)
@EnableWebSecurity
@SpringBootApplication
public class SoilAmendmentCalculatorApplication {

    public static void main(String[] args) {
        SpringApplication.run(SoilAmendmentCalculatorApplication.class, args);
    }

}
