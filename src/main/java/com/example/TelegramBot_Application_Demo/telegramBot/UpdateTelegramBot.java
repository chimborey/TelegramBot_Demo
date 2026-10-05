package com.example.TelegramBot_Application_Demo.telegramBot;

import org.telegram.telegrambots.meta.api.objects.Update;

public class UpdateTelegramBot {

    public void onUpdateReceived(Update update){

        // 1. ពិនិត្យមើលឱ្យតែមានសារ (Message) ផ្ញើចូលមក (មិនថារូបភាព ឯកសារ ឬអក្សរ)

        // 2. ទាញយក Chat ID ពីក្នុង Message

        // 3. ហៅប្រើ BotService ដើម្បីរក្សាទុកឈ្មោះពេញ (FullName) និងព័ត៌មានរបស់ User ទៅក្នុង PostgreSQL ភ្លាមៗ

        // 4. បង្កើត Object សម្រាប់ផ្ញើសារឆ្លើយតប (SendMessage) រួច Set Chat ID ទៅកាន់អ្នកផ្ញើ

        // 5. ពិនិត្យមើលប្រភេទសារ (Message Types) ដើម្បីកំណត់អត្ថបទឆ្លើយតប៖

        // 5.1 ករណីសារជាអក្សរ (Text Message)
        // - ពិនិត្យមើលបើជាពាក្យ "/start" ឱ្យឆ្លើយតបសារស្វាគមន៍
        // - បើជាអក្សរផ្សេងៗ ឱ្យឆ្លើយតបសារប្រាប់វិញធម្មតា

        // 5.2 ករណីសារជារូបភាព (Photo)
        // - កំណត់អត្ថបទឆ្លើយតបប្រាប់ User ថាបច្ចុប្បន្ន Bot មិនទាន់អាចមើលរូបភាពដឹងឡើយ

        // 5.3 ករណីសារជា Sticker
        // - កំណត់អត្ថបទឆ្លើយតបបែបសប្បាយៗទៅកាន់ User (ឧទាហរណ៍៖ សរសើរ Sticker គាត់)

        // 5.4 ករណីសារប្រភេទផ្សេងទៀត (ដូចជា ឯកសារ, Audio, ទីតាំង Location)
        // - កំណត់អត្ថបទឆ្លើយតបប្រាប់ User ថាទទួលបានសញ្ញាហើយ ប៉ុន្តែមិនទាន់គាំទ្រប្រភេទសារនេះទេ

        // 6. ប្រើប្រាស់បញ្ជា execute(message) ដើម្បីផ្ញើសារដែលបានកំណត់ទាំងអស់ត្រឡប់ទៅ Telegram វិញ

        // 7. ប្រើប្រាស់ block try-catch ដើម្បីចាប់យក Error (TelegramApiException) ករណីផ្ញើសារទៅវិញមិនជោគជ័យ

    }
}
