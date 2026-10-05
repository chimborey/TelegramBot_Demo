package com.example.TelegramBot_Application_Demo.model;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "bots")
public class BotEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "botId", unique = true, updatable = false, nullable = false)
    private UUID botId = java.util.UUID.randomUUID();

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "date_time")
    private LocalDateTime dateTime;
}
