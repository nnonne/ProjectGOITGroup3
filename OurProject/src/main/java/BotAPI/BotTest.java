package BotAPI;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class BotTest {

    public static void main(String[] args) {
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            TelegramBot telegramBot = new TelegramBot();
            botsApi.registerBot(telegramBot);
            telegramBot.sendMessageUsers();

        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
