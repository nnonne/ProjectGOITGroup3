package BotAPI;

import Dto.SettingsUserDto;
import Enums.NotificationTime;
import Settings.UserSettings;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Arrays;
import java.util.Objects;
import java.util.Properties;

import static BotAPI.BotFunctions.*;
import static BotAPI.Buttons.*;
import static BotAPI.Keyboards.*;

public class TelegramBot extends TelegramLongPollingBot {

    Properties property = new Properties();
    public static final String FILE_NAME = "OurProject/src/main/resources/botsettings.properties";
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
            message.setChatId(update.getMessage().getChatId().toString());
            settingsUserDto = UserSettings.getUserById(userID);
            if (messageText.equals("/start")) {
                settingsUserDto = pressStart(message, userID);
            }
            else if (SETTINGS_BUTTON.equals(messageText)) {
                message.setText(SETTINGS_BUTTON);
                createSettingsKeyboard(message);
            }
            else if (GET_INFO_BUTTON.equals(messageText)) {
                message.setText(MessageUserInfo.showInfo(settingsUserDto));
                createStartKeyboard(message);
            }
            else if (Arrays.stream(NotificationTime.values()).anyMatch(element -> Objects.equals(element.getValue(),
                                                                                  messageText))){
                settingsUserDto.setNotificationTime(NotificationTime.getByValue(messageText));
                UserSettings.saveUserSettings(settingsUserDto);
                message.setText("Обраний час сповіщень: " + messageText);
                createDefaultKeyboard(message);
            }
        } else if (update.hasCallbackQuery()) {
            String callBackData = update.getCallbackQuery().getData();
            String userID = update.getCallbackQuery().getMessage().getChatId().toString();
            message.setChatId(update.getCallbackQuery().getMessage().getChatId().toString());
            settingsUserDto = UserSettings.getUserById(userID);
            switch (callBackData) {
                case GET_INFO_BUTTON:
                    message.setText(MessageUserInfo.showInfo(settingsUserDto));
                    createStartKeyboard(message);
                    break;
                case SETTINGS_BUTTON:
                    message.setText(SETTINGS_BUTTON);
                    createSettingsKeyboard(message);
                    break;
                case DIGITS_AFTER_DECIMAL_POINT_BUTTON:
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
                    message.setText(BANK_BUTTON);
                    message.setReplyMarkup(createBankKeyboard(settingsUserDto));
                    break;
                case CURRENCY_RATE_BUTTON:
                    message.setText(CURRENCY_RATE_BUTTON);
                    createCurrencyKeyboard(message, settingsUserDto);
                    break;
                case NOTIFICATION_TIME_BUTTON:
                    pressNotificationTime(message, settingsUserDto);
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
}
