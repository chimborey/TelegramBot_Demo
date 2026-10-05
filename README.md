# 🚀 Enterprise Telegram Bot with Spring Boot & PostgreSQL

គម្រោងនេះគឺជាប្រព័ន្ធ **Telegram Bot** ស្តង់ដារឧស្សាហកម្ម (Production-Ready) ដែលបង្កើតឡើងដោយប្រើប្រាស់ **Spring Boot 3.x** និងរក្សាទុកទិន្នន័យអ្នកប្រើប្រាស់ទៅក្នុង **PostgreSQL Database**។ វាត្រូវបានរចនាឡើងតាមទម្រង់ Layered Architecture ដើម្បីធានាបាននូវភាពងាយស្រួលក្នុងការពង្រីក (Scalability) និងសុវត្ថិភាពខ្ពស់។ 🌟

---

## ✨ Features (លក្ខណៈពិសេសនៃប្រព័ន្ធ)
* 👤 **All-Type Message Tracking**: ឱ្យតែ User ផ្ញើសារមកកាន់ Bot (មិនថារូបភាព ឬ Sticker) ប្រព័ន្ធនឹងចាប់យក `fullName` ទៅកត់ត្រាក្នុង Database ភ្លាមៗ ២៤ម៉ោង។
* 🔐 **Data Security via DTO**: រាល់ទិន្នន័យដែលបង្ហាញទៅកាន់ Web API ត្រូវឆ្លងកាត់ `UserResponseDto` ដើម្បីលាក់ព័ត៌មានសម្ងាត់ (ដូចជា Chat ID) ការពារសុវត្ថិភាពជូន User។
* 🛠️ **DevOps & Containerization**: រៀបចំឡើងជាមួយ **Docker** និង **Docker Compose** ងាយស្រួលដំឡើង និងដំណើរការដោយប្រើបញ្ជាតែមួយជួរ។
* 📜 **API Documentation**: មានរៀបចំ **Swagger Open API** រួចជាស្រេចសម្រាប់ឱ្យ Admin ចូលពិនិត្យមើលលម្អិតពី API។

---

## 🛠️ 1. How to Create Telegram Bot (របៀបបង្កើត Bot ក្នុង Telegram)

ដើម្បីចាប់ផ្ដើមបង្កើត និងយកលេខ Token សម្រាប់ដំណើរការ Bot សូមអនុវត្តតាមជំហាននៅក្នុងកម្មវិធី Telegram ដូចខាងក្រោម៖

1. 🔍 បើកកម្មវិធី Telegram រួចស្វែងរកគណនីផ្លូវការឈ្មោះ **`@BotFather`** (សូមជ្រើសរើសយកមួយណាដែលមានសញ្ញាគ្រីសពណ៌ខៀវ ✅)។
2. ⚡ ចុចប៊ូតុង **START** រួចផ្ញើសារពាក្យថា `/newbot` ទៅកាន់វា។
3. ✍️ វាយបញ្ចូល **ឈ្មោះ (Name)** របស់ Bot របស់អ្នក (ឧទាហរណ៍៖ `My Smart Bot`) រួចចុចផ្ញើ។
4. 🏷️ វាយបញ្ចូល **ឈ្មោះគណនី (Username)** របស់ Bot ដោយតម្រូវឱ្យ៖
    * សរសេរជាអក្សរឡាតាំងជាប់ៗគ្នា គ្មានដកឃ្លា។
    * **ត្រូវតែបញ្ចប់ដោយពាក្យ `bot` ឬ `_bot` ជានិច្ច** (ឧទហរណ៍៖ `my_smart_java_2026_bot`)。
5. 🔑 បន្ទាប់ពីបង្កើតជោគជ័យ BotFather នឹងផ្ញើសារ **`HTTP API Token`** (អក្សរលាយលេខវែងៗ) មកឱ្យអ្នក។ សូមចម្លង (Copy) វាទុកដើម្បីយកទៅដាក់ក្នុងហ្វាយ `.env`។

---

## 📦 2. Required Dependencies (បណ្ណាល័យសំខាន់ៗក្នុង pom.xml)

ដើម្បីឱ្យគម្រោង Spring Boot គាំទ្រការងារ Telegram Bot, Database, Security និង Swagger ត្រូវបន្ថែម Dependencies ខាងក្រោមទៅក្នុង `pom.xml`៖

```xml
<!-- Telegram Bot Starter -->
<dependency>
    <groupId>org.telegram</groupId>
    <artifactId>telegrambots-spring-boot-starter</artifactId>
    <version>6.8.0</version>
</dependency>

<!-- Spring Data JPA & PostgreSQL Driver -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
<dependency>
    <groupId>org.postgresql</groupId>
    <artifactId>postgresql</artifactId>
    <scope>runtime</scope>
</dependency>

<!-- Spring Security -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>

<!-- Swagger Open API for Admin Dashboard Document -->
<dependency>
    <groupId>org.springdoc</groupId>
    <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
    <version>2.2.0</version>
</dependency>
```

---

## 📊 3. System Architecture & Flow (លំហូរការងារ និង Logic របស់ប្រព័ន្ធ)

គម្រោងនេះត្រូវបានបែងចែកតួនាទីដាច់ពីគ្នាទៅតាមស្រទាប់កូដនីមួយៗ (**Separation of Concerns**)៖

| Component Name | Standard Layer | Primary Responsibility (តួនាទីស្នូល) |
| :--- | :--- | :--- |
| **TelegramBotEngine** | 📥 Telegram Inbound Layer | ទទួលសញ្ញា (Update Object) ពី Telegram Server តាមរយៈ Token។ |
| **UpdateTelegramBot** | ⚙️ Message Handler Layer | ពិនិត្យ និងបែងចែកប្រភេទសារ (Text, Photo, Sticker) រួចរៀបចំសារតប។ |
| **BotService** | 🧠 Business Logic Layer | គ្រប់គ្រង Logic របស់កម្មវិធី និងបូកបញ្ចូលឈ្មោះបង្កើតជា `fullName`។ |
| **UserRepository** | 🗄️ Data Access Layer (JPA) | ទាក់ទងជាមួយ Database ផ្ទាល់ រួចទាញទិន្នន័យចេញមកជាទម្រង់ DTO។ |
| **UserController** | 🌐 REST API Layer (Admin) | បង្កើត Endpoint `/api/v1/users` សម្រាប់ទាញទិន្នន័យមើលលើ Web Browser។ |
| **TelegramUser** | 📐 Database Model (Entity) | កំណត់ទម្រង់រចនាសម្ព័ន្ធ Table `telegram_users` នៅក្នុង PostgreSQL។ |

### 🔄 លំហូរការងារលម្អិតនៅក្នុង `UpdateTelegramBot.onUpdateReceived()`
នៅពេលមានទិន្នន័យផ្ញើចូលមកពី Telegram មុខងារ `onUpdateReceived` នឹងចាត់ចែងការងារទៅតាមលំដាប់លំដោយដូចខាងក្រោម៖

1. 📥 **ពិនិត្យមើលសារចូល**: ប្រព័ន្ធនឹងឆែកមើលឱ្យតែមានសារ (Message) ផ្ញើចូលមកពី User (មិនថារូបភាព ឯកសារ ឬអក្សរឡើយ)។
2. 🆔 **ទាញយកតម្រុយ**: ចាប់យកព័ត៌មាន `chatId` ពីក្នុង Message ដើម្បីដឹងថាត្រូវឆ្លើយតបទៅនរណា។
3. 💾 **កត់ត្រាទិន្នន័យ**: ហៅប្រើប្រាស់ `BotService` ដើម្បីយកឈ្មោះ `firstName` និង `lastName` មកបូកបញ្ចូលគ្នាជាឈ្មោះពេញ (`fullName`) រួចរក្សាទុកទៅក្នុង PostgreSQL ភ្លាមៗ។
4. ✉️ **រៀបចំសារតប**: បង្កើត Object `SendMessage` រួចកំណត់បោះ `chatId` ត្រៀមផ្ញើត្រឡប់ទៅវិញ។
5. 🔀 **បែងចែកប្រភេទសារ (Message Types Logic)**:
    * 💬 **ករណីសារជាអក្សរ (Text Message)**: បើ User ផ្ញើពាក្យ `/start` វានឹងតបសារស្វាគមន៍ជោគជ័យ។ បើផ្ញើអក្សរផ្សេងៗ វានឹងតបសារប្រាប់វិញធម្មតា (Echo Message)។
    * 🖼️ **ករណីសារជារូបភាព (Photo)**: កំណត់អត្ថបទឆ្លើយតបប្រាប់ User ថាបច្ចុប្បន្ន Bot មិនទាន់អាចមើល ឬយល់អត្ថន័យរូបភាពដឹងឡើយ។
    * 🥰 **ករណីសារជា Sticker**: កំណត់អត្ថបទឆ្លើយតបបែបសប្បាយៗទៅកាន់ User (ឧទាហរណ៍៖ សរសើរ Sticker គាត់ថាស្អាត)។
    * 📁 **ករណីសារប្រភេទផ្សេងទៀត (File, Audio, Location)**: កំណត់អត្ថបទឆ្លើយតបប្រាប់ User ថាទទួលបានសញ្ញាហើយ ប៉ុន្តែមិនទាន់គាំទ្រប្រភេទសារទាំងនេះទេ។
6. 🚀 **បញ្ជាផ្ញើចេញ**: ប្រើប្រាស់បញ្ជា `execute(message)` តាមរយៈ Bot Engine ដើម្បីរុញសារដែលបានកំណត់ទាំងអស់ត្រឡប់ទៅកម្មវិធី Telegram វិញ។
7. 🛡️ ** can Prevent Crash**: គ្រប់គ្រងរាល់កំហុសបច្ចេកទេសទាំងអស់ដោយប្រើប្រាស់ block `try-catch` ដើម្បីចាប់យក Error (`TelegramApiException`) ការពារកុំឱ្យកម្មវិធីគាំងបិទដោយស្វ័យប្រវត្តិ។

---

## 🌐 4. Admin REST API Endpoints (ច្រកទាញទិន្នន័យសម្រាប់ Admin)

| Method | Endpoint | Description | Access |
| :--- | :--- | :--- | :--- |
| **GET** | `/api/v1/users` | ទាញយកទិន្នន័យ User ទាំងអស់ (ទម្រង់ DTO) | Public / Admin |
| **GET** | `/api/v1/users/{chatId}` | ទាញយកទិន្នន័យ User ម្នាក់តាម Chat ID | Public / Admin |
| **GET** | `/telegrambot` | បើកមើលឯកសារ API តាមរយៈ **Swagger UI** | Public |
