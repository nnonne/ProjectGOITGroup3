package BotAPI;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static BotAPI.Buttons.*;
import static BotAPI.Keyboards.*;

public class TelegramBot extends TelegramLongPollingBot {

    public static final String BOT_USER_NAME = "Grmavi_bot";
    public static final String BOT_TOKEN = "6134878051:AAF7pkjwjk8AuOeAKCGF6PUyWbhGKHEG3lk";

    @Override
    public String getBotUsername() {
        return BOT_USER_NAME;  // для поиска в приложении используем @myowntelegrambot_bot, можно заменить название
    }

    @Override
    public String getBotToken() {
        return BOT_TOKEN; //нужно заменить токен, если будем менять имя бота
    }

    @Override
    public void onUpdateReceived(Update update) {
        SendMessage message = new SendMessage();
        if (update.hasMessage() && update.getMessage().hasText()) {
            message.setChatId(update.getMessage().getChatId().toString());
            if (update.getMessage().getText().equals("/start")) {
                message.setText("Ласкаво просимо. Цей бот допоможе відслідковувати актуальні курси валют.");
                String messageText = update.getMessage().getText();
                getUsetTGId(messageText, message.getChatId().toString());
//                createDefaultKeyboard(message);  - вернуть строку, когда будет описан функционал кнопок и удалить 49 строку
                createStartKeyboard(message);
            }
        } else if (update.hasCallbackQuery()) {
            String callBackData = update.getCallbackQuery().getData();
            message.setChatId(update.getCallbackQuery().getMessage().getChatId().toString());
            switch (callBackData) {
                case GET_INFO_BUTTON:
                    // прописываем метод который вызываеться при нажатии кнопки "Отримати інфо"
                    message.setText("Отримуэмо інфо по курсу валют");
                    createStartKeyboard(message);
                    break;
                case SETTINGS_BUTTON:
                    // прописываем метод который вызываеться при нажатии кнопки "Налаштування"
                    message.setText("Налаштування");
                    createSettingsKeyboard(message);
                    break;
                case DIGITS_AFTER_DECIMAL_POINT_BUTTON:
                    // прописываем метод который вызываеться при нажатии кнопки "К-сть знаків після коми"
                    message.setText("К-сть знаків після коми");
                    createDigitsKeyboard(message);
                    break;
                case BANK_BUTTON:
                    // прописываем метод который вызываеться при нажатии кнопки "Банк"
                    message.setText("Банк");
                    createBankKeyboard(message);
                    break;
                case CURRENCY_RATE_BUTTON:
                    // прописываем метод который вызываеться при нажатии кнопки "Валюти"
                    message.setText("Валюти");
                    createCurrencyKeyboard(message);
                    break;
                case NOTIFICATION_TIME_BUTTON:
                    // прописываем метод который вызываеться при нажатии кнопки "Оберіть час сповіщення"
                    message.setText("Оберіть час сповіщення");
                    createNotificationTimeKeyboard(message);
                    break;
            }
        }
        try {
            execute(message);
        } catch (
                TelegramApiException e) {
            e.printStackTrace();
        }
    }
    public void getUsetTGId(String messageText, String idUser ){
        Map<String,String> base=new HashMap<>();
        Gson gson=new GsonBuilder().setPrettyPrinting().create();
        System.out.println("messageText = " + messageText);

        if (messageText.equals("/start")){
            System.out.println("messageText = " + messageText);
            System.out.println("idUser = " + idUser);
            base.put("idUser",idUser);
            base.put("DecimalPoint","2");
            base.put("Bank","Privat");
            base.put("Currency-USD","Ok");
            base.put("Currency-EUR","Ok");
            base.put("HourOfAwakening","10");
            FileWriter fileWriter;
            try {
                fileWriter = new FileWriter("userID.json.txt",true);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            gson.toJson(base, fileWriter);
            try {
                fileWriter.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }

    }

}
