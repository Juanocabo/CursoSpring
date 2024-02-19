package com.bananaapps.bananamusic.config;

import com.bananaapps.bananamusic.persistence.music.SongJPARepository;
import com.bananaapps.bananamusic.persistence.music.SongRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RepoConfig {


    @Bean
    public SongRepository songRepository() {
        return new SongJPARepository();
    }


}
