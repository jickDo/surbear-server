package com.surbear.member.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MemberConfig {
    @Bean
    public GroupedOpenApi getMemberApi() {
        return GroupedOpenApi.builder()
                .group("회원")
                .pathsToMatch("/member/**")
                .pathsToExclude("")
                .packagesToScan("com.surbear.member.controller")
                .build();
    }
}