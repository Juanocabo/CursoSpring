package com.bananaapps.bananamusic.config;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan({"com.bananaapps.bananamusic.persistence","com.bananaapps.bananamusic.service"})
@EnableJpaRepositories("com.bananaapps.bananamusic.persistence")
@EntityScan("com.bananaapps.bananamusic.domain")
@PropertySource("classpath:application.yml")
@EnableAutoConfiguration
public class SpringConfig {

}
