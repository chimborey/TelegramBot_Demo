package com.example.TelegramBot_Application_Demo.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BotResponse implements Serializable {

    private Long id;
    private UUID botId;
    private String fullName;
    private LocalDateTime dateTime;
}
