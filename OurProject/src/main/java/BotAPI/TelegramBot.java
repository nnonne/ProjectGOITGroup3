package BotAPI;

import Dto.SettingsUserDto;
import Enums.NotificationTime;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Properties;

import static BotAPI.Buttons.*;
import static BotAPI.Keyboards.*;

public class TelegramBot extends TelegramLongPollingBot {

    Properties property = new Properties();
    public static final String FILE_NAME = "./src/main/resources/botsettings.properties";
    SettingsUserDto settingsUserDto;

    @Override
    public String getBotUsername() {
        String botName;
        try (Reader reader = new FileReader(FILE_NAME)) {
            property.load(reader);
            botName = property.getProperty("bot.name");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return botName;
    }

    @Override
    public String getBotToken() {
        String token;
        try (Reader reader = new FileReader(FILE_NAME)) {
            property.load(reader);
            token = property.getProperty("bot.token");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return token;
    }

    @Override
    public void onUpdateReceived(Update update) {
        SendMessage message = new SendMessage();
        if (update.hasMessage() && update.getMessage().hasText()) {
            String userID = update.getMessage().getChatId().toString();
            String messageText = update.getMessage().getText();
            message.setChatId(userID);
            new GetUser().getUsetTGId(messageText, userID);
            //                settingsUserDto = findUser(userID);
            String notificationTime = NotificationTime.valueOf(settingsUserDto.getHourOfAwakening().name()).toString();
            if (messageText.equals("/start")) {
                message.setText("Ласкаво просимо. Цей бот допоможе відслідковувати актуальні курси валют.");
                createDefaultKeyboard(message);
            }
            if (SETTINGS_BUTTON.equals(messageText)) {
                message.setText(SETTINGS_BUTTON);
                createSettingsKeyboard(message);
            }
            if (GET_INFO_BUTTON.equals(messageText)) {
                // прописываем метод который вызываеться при нажатии кнопки "Отримати інфо"
                message.setText("Отримуємо інфо по курсу валют"); // здесь текст нужно изменить на информацию по курсу валют
                createStartKeyboard(message);
            }
            if (notificationTime.equals(messageText)){
//                updateUserSettings (userID, "notificationTime", notificationTime);
                message.setText("Обраний час сповіщень: " + notificationTime);
            }
        } else if (update.hasCallbackQuery()) {
            String callBackData = update.getCallbackQuery().getData();
            String userID = update.getCallbackQuery().getMessage().getChatId().toString();
            message.setChatId(userID);
//            settingsUserDto = findUser(userID);
            switch (callBackData) {
                case GET_INFO_BUTTON:
                    // прописываем метод который вызываеться при нажатии кнопки "Отримати інфо"
                    message.setText("Отримуємо інфо по курсу валют"); // здесь текст нужно изменить на информацию по курсу валют
                    createStartKeyboard(message);
                    break;
                case SETTINGS_BUTTON:
                    message.setText(SETTINGS_BUTTON);
                    createSettingsKeyboard(message);
                    break;
                case DIGITS_AFTER_DECIMAL_POINT_BUTTON:
                    // прописываем метод который вызываеться при нажатии кнопки "К-сть знаків після коми"
                    message.setText(DIGITS_AFTER_DECIMAL_POINT_BUTTON);
                    message.setReplyMarkup(createDigitsKeyboard(settingsUserDto));
                    break;
                case TWO_DIGITS_BUTTON:
                case THREE_DIGITS_BUTTON:
                case FOUR_DIGITS_BUTTON:
                case NBU_BUTTON:
                case PRIVATBANK_BUTTON:
                case MONOBANK_BUTTON:
                case USD_BUTTON:
                case EUR_BUTTON:
                case "✅ " + EUR_BUTTON:
                case "✅ " + USD_BUTTON:
                    executeChangedMessage(placeCheckMark(callBackData, update, settingsUserDto));
                    break;
                case BANK_BUTTON:
                    // прописываем метод который вызываеться при нажатии кнопки "Банк"
                    message.setText(BANK_BUTTON);
                    message.setReplyMarkup(createBankKeyboard(settingsUserDto));
                    break;
                case CURRENCY_RATE_BUTTON:
                    // прописываем метод который вызываеться при нажатии кнопки "Валюти"
                    message.setText(CURRENCY_RATE_BUTTON);
                    createCurrencyKeyboard(message, settingsUserDto);
                    break;
                case NOTIFICATION_TIME_BUTTON:
                    sendInfoAboutNotificationTime(message);
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

    public void executeChangedMessage(EditMessageReplyMarkup editMessageReplyMarkup) {
        try {
            execute(editMessageReplyMarkup);

        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void sendInfoAboutNotificationTime(SendMessage message) {
        String notificationTime = NotificationTime.valueOf(settingsUserDto.getHourOfAwakening().name()).toString();
        // прописываем метод который вызываеться при нажатии кнопки "Оберіть час сповіщення"
        if (notificationTime.equals("Вимкнути повідомлення")) {
            message.setText("Наразі опція отримання повідомлень вимкнена. " +
                    "Якщо Ви бажаєте отримувати повідомлення у визначений час, будь-ласка, оберіть час на клавіатурі.");
            createNotificationTimeKeyboard(message);
        } else {
            message.setText("Обранний час сповіщення: о " + notificationTime +
                    " кожного дня. Будь-ласка, оберіть інший час сповіщень за потреби.");
            createNotificationTimeKeyboard(message);
        }
    }
}
