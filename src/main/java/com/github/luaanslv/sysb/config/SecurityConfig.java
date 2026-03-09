package com.github.luaanslv.sysb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

//Indica para o spring que essa classe é uma fonte de configuraçõs
@Configuration
public class SecurityConfig {

    // Instancia bibliotecas que o spring não conhece
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}