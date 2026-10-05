package com.example.TelegramBot_Application_Demo.service;

import com.example.TelegramBot_Application_Demo.dto.request.BotRequest;
import com.example.TelegramBot_Application_Demo.dto.response.BotResponse;
import com.example.TelegramBot_Application_Demo.exception.ResourceNotFound;
import com.example.TelegramBot_Application_Demo.model.BotEntity;
import com.example.TelegramBot_Application_Demo.repository.BotRepo;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class BotService {

    private final BotRepo botRepo;

//    ======================================== create (Post) ========================================
    public BotResponse create(BotRequest request){

//        check user in database
        Optional<BotEntity> existingBot = botRepo.findByFullName(request.getFullName());
        BotEntity botToSave;

        if (existingBot.isPresent()) {
            // បើមានក្នុង DB រួចហើយ យក Entity ចាស់មកប្រើ ឬ Update Date time ថ្មី
            botToSave = existingBot.get();
            botToSave.setDateTime(request.getDateTime());
        } else {
            // បើមិនទាន់មាន ៖ បង្កើត Entity ថ្មីដើម្បី Save
            botToSave = new BotEntity();
            botToSave.setFullName(request.getFullName());
            botToSave.setDateTime(request.getDateTime());
        }

        // 2. Save / Update ចូលក្នុង Database
        BotEntity savedBot = botRepo.save(botToSave);

        return new BotResponse(
                savedBot.getId(),
                savedBot.getBotId(),
                savedBot.getFullName(),
                savedBot.getDateTime()
        );

    }
    public List<BotResponse> getAllBots() {
        return botRepo.findAllBotResponses(); // មិនបាច់ប្រើ Stream/Map ឡើយ!
    }

    // ២. ទាញយក Bot តាម FullName
    public BotResponse getBotByFullName(String fullName) {
        return botRepo.findBotResponseByFullName(fullName)
                .orElseThrow(() -> new ResourceNotFound("User not found with name: " + fullName));
    }

    // ៣. ទាញយក Bot តាម BotId (UUID)
    public BotResponse getBotByBotId(UUID botId) {
        return botRepo.findBotResponseByBotId(botId)
                .orElseThrow(() -> new ResourceNotFound("User not found with botId: " + botId));
    }

}
