package com.example.TelegramBot_Application_Demo.telegramBot;

import com.example.TelegramBot_Application_Demo.dto.request.BotRequest;
import com.example.TelegramBot_Application_Demo.service.BotService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.time.LocalDateTime;

@Component
public class UpdateTelegramBot extends TelegramLongPollingBot {

    private final BotService botService;
    private final String botUsername;

    // Inject Bot Token, Bot Username តាមរយៈ Constructor ពី application.properties
    public UpdateTelegramBot(BotService botService,
                             @Value("${bot.token}") String botToken,
                             @Value("${bot.name}") String botUsername)
    {
        super(botToken);
        this.botService = botService;
        this.botUsername = botUsername;
    }

    @Override
    public String getBotUsername() {
        return botUsername;
    }

    @Override
    public void onUpdateReceived(Update update) {

        if (update.hasMessage()) {

            Long chatId = update.getMessage().getChatId();

            // 1. រៀបចំទិន្នន័យ Full Name
            String firstName = update.getMessage().getFrom().getFirstName();
            String lastName = update.getMessage().getFrom().getLastName();
            String fullName = ((firstName != null ? firstName : "") + " " + (lastName != null ? lastName : "")).trim();

            // 2. រក្សាទុកទិន្នន័យចូល Database តាមរយៈ BotService
            BotRequest request = new BotRequest();
            request.setFullName(fullName);
            request.setDateTime(LocalDateTime.now());

            botService.create(request);

            // 3. បង្កើត Object SendMessage ដើម្បីឆ្លើយតបទៅកាន់ User វិញ
            SendMessage message = new SendMessage();
            message.setChatId(chatId.toString());

            // 4. Check ប្រភេទសារដែល User ផ្ញើមក
            if (update.getMessage().hasText()) {
                String text = update.getMessage().getText();

                if (text.equals("/start")) {
                    message.setText("សួស្តី " + fullName + "! ព័ត៌មានរបស់អ្នកត្រូវបានរក្សាទុកក្នុង Database រួចរាល់ហើយ។");
                } else {
                    message.setText("អ្នកបាននិយាយថា: " + text);
                }
            }
            else if (update.getMessage().hasPhoto()) {
                message.setText("អ្នកបានផ្ញើរូបភាពមក! ប៉ុន្តែបច្ចុប្បន្នខ្ញុំអត់ទាន់អាចមើលរូបភាពដឹងឡើយ។");
            }
            else if (update.getMessage().hasSticker()) {
                message.setText("Sticker របស់អ្នកស្អាត និងគួរឱ្យស្រឡាញ់ណាស់! 🥰");
            }
            else {
                message.setText("ខ្ញុំទទួលបានសញ្ញារបស់អ្នកហើយ ប៉ុន្តែខ្ញុំមិនទាន់គាំទ្រប្រភេទសារនេះទេបាទ។");
            }

            // 5. ផ្ញើសារត្រឡប់ទៅ Telegram វិញ
            try {
                execute(message);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }
}