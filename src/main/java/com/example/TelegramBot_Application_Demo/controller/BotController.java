package com.example.TelegramBot_Application_Demo.controller;


import com.example.TelegramBot_Application_Demo.dto.response.BotResponse;
import com.example.TelegramBot_Application_Demo.service.BotService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/telegramBot/")
@AllArgsConstructor

public class BotController {

    private final BotService botService;

//    ==================================== post (Post) ====================================
    @Tag(name = "Bot Management")
    @PostMapping
    public ResponseEntity<BotResponse>post(){
        return ResponseEntity.ok(null);
    }

//    ==================================== getAll (Get) ====================================
    @GetMapping
    @ApiResponses(value = {
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Bot status details"),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", ref = "#/components/responses/Unauthorized")
    })
    @Tag(name = "Admin Management")
    @Operation(summary = "Get All")
    public ResponseEntity<List<BotResponse>> getAllBotUsers() {
        List<BotResponse> users = botService.getAllBots();
        return ResponseEntity.ok(users);
    }

//    ==================================== getByBotId (Get) ====================================
    @GetMapping("/botId/{botId}")
    @ApiResponses(value = {
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Bot status details"),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", ref = "#/components/responses/Unauthorized")
    })
    @Tag(name = "Admin Management")
    @Operation(summary = "Get Bot ID")
    public ResponseEntity<BotResponse> getBotUserByBotId(@PathVariable UUID botId) {
        BotResponse response = botService.getBotByBotId(botId);
        return ResponseEntity.ok(response);
    }

//    ==================================== getByFullName (Get) ====================================
    @GetMapping("fullName/{search}")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Bot status details"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", ref = "#/components/responses/Unauthorized")
    })
    @Tag(name = "Admin Management")
    @Operation(summary = "Get full Name")
    public ResponseEntity<BotResponse> getBotUserByFullName(@RequestParam String fullName) {
        BotResponse response = botService.getBotByFullName(fullName);
        return ResponseEntity.ok(response);
    }
}
