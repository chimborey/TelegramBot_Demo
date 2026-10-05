package com.example.TelegramBot_Application_Demo.repository;

import com.example.TelegramBot_Application_Demo.dto.response.BotResponse;
import com.example.TelegramBot_Application_Demo.model.BotEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface BotRepo extends JpaRepository<BotEntity, Long> {

    // ================= 1. Standard Derived Query Methods =================

    Optional<BotEntity> findByFullName(String fullName);

    boolean existsByBotId(UUID botId);

    boolean existsByFullName(String fullName);

    void deleteByBotId(UUID botId);

    void deleteByFullName(String fullName);


    // ================= 2. JPQL DTO Projections (សម្រាប់ GET Operations) =================

    // 2.1 ទាញយក Bot Response ទាំងអស់ជា List DTO ផ្ទាល់
    @Query("SELECT new com.example.TelegramBot_Application_Demo.dto.response.BotResponse(" +
            "b.id, b.botId, b.fullName, b.dateTime) " +
            "FROM BotEntity b")
    List<BotResponse> findAllBotResponses();

    // 2.2 ទាញយក Bot Response តាមរយៈ FullName មកជា DTO ផ្ទាល់
    @Query("SELECT new com.example.TelegramBot_Application_Demo.dto.response.BotResponse(" +
            "b.id, b.botId, b.fullName, b.dateTime) " +
            "FROM BotEntity b WHERE b.fullName = :fullName")
    Optional<BotResponse> findBotResponseByFullName(@Param("fullName") String fullName);

    // 2.3 ទាញយក Bot Response តាមរយៈ BotId (UUID)
    @Query("SELECT new com.example.TelegramBot_Application_Demo.dto.response.BotResponse(" +
            "b.id, b.botId, b.fullName, b.dateTime) " +
            "FROM BotEntity b WHERE b.botId = :botId")
    Optional<BotResponse> findBotResponseByBotId(@Param("botId") UUID botId);
}
