package com.example.TelegramBot_Application_Demo.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BotRequest implements Serializable {

    @NotBlank(message = "Full Name is request.")
    private String fullName;

    @NotNull(message = "Date of Time is request.")
    private LocalDateTime dateTime;
}
