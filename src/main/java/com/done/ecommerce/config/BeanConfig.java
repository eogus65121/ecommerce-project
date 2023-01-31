package com.done.ecommerce.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    // JPA return Entity > return DTO 로 변환하기 위한 modelMapper
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
