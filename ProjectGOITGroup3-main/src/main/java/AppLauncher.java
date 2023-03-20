import botapi.TelegramBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class AppLauncher {

    public static void main(String[] args) {
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            TelegramBot telegramBot = new TelegramBot();
            botsApi.registerBot(telegramBot);

            telegramBot.sendDailyNotificationMessage();
            telegramBot.currencyUpdate();

        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
