package com.example.TelegramBot_Application_Demo.security;

import com.example.TelegramBot_Application_Demo.telegramBot.UpdateTelegramBot;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.tags.Tag;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.List;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http){
        return http
                .csrf(c-> c.disable())
                .authorizeHttpRequests(a -> a.anyRequest().permitAll())
                .build();
    }

    @Bean
    public OpenAPI openAPI(){

       Server server = new Server().url("http://localhost:8090").description("Local Development Server");

       Tag botManagement = new Tag().name("Bot Management").description("Endpoints for managing bot state and configurations");
       Tag adminManagement = new Tag().name("Admin Management").description("Endpoints for core user details and interactions");

       ApiResponse unauthorizedResponse = new ApiResponse().description("Unauthorized: Missing or invalid JWT Token");
       ApiResponse serverErrorResponse = new ApiResponse().description("Internal Server Error: Something went wrong on the server");

        final String securitySchemesName = "telegramBot";

        return new OpenAPI()
                .info(new Info()
                        .title("Your Telegram Bot Title.")
                        .description("API documentation with JWT security configuration")
                        .version("V3.1.0")
                        .termsOfService("Api terms of service example")
                        .contact(new Contact()
                                .name("api contact name")
                                .url("http://localhost:8090")
                                .email("telegramBot2026@gmail.com"))
                        .license(new License().name("Api license").url("http://localhost:8090")))
                .addSecurityItem(new SecurityRequirement().addList(securitySchemesName))
                .components(new Components()
                        .addSecuritySchemes(securitySchemesName, new SecurityScheme()
                                .name(securitySchemesName)
                                .scheme("bearer")
                                .type(SecurityScheme.Type.HTTP)
                                .bearerFormat("JWT"))
                        .addResponses("Unauthorized", unauthorizedResponse)
                        .addResponses("ServerError", serverErrorResponse))
                .tags(List.of(botManagement, adminManagement))
                .externalDocs(new ExternalDocumentation().url("http://localhost:8090").description("Api external doc Desc"))
                .servers(List.of(server));
    }

    @Bean
    public GroupedOpenApi privateApi(){
        return GroupedOpenApi.builder()
                .group("private")
                .pathsToMatch("/api/telegramBot/")
                .build();
    }

    @Bean
    public TelegramBotsApi telegramBotsApi(UpdateTelegramBot updateTelegramBot) throws TelegramApiException {
        TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
        botsApi.registerBot(updateTelegramBot);
        return botsApi;
    }
}
