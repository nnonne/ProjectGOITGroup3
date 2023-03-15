package BotAPI;

import Dto.SettingsUserDto;
import Enums.BankName;
import Enums.Currency;
import Enums.DigitsAfterDecimalPoint;
import Enums.NotificationTime;
import Settings.UserSettings;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

import java.util.List;

import static BotAPI.Buttons.*;
import static BotAPI.Buttons.EUR_BUTTON;
import static BotAPI.Keyboards.*;

public class BotFunctions {

    public static void pressStart(SendMessage message, String userID) {
        if (!UserSettings.existUserById(userID)){
            UserSettings.saveUserSettings(new SettingsUserDto(userID, BankName.MONOBANK, List.of(Currency.EUR, Currency.USD), NotificationTime.ELEVEN, DigitsAfterDecimalPoint.TWO));
        }
        message.setText("Ласкаво просимо. Цей бот допоможе відслідковувати актуальні курси валют.");
        createDefaultKeyboard(message);

    }
    public static EditMessageReplyMarkup placeCheckMark(String callBackData, Update update, SettingsUserDto settingsUserDto) {
        EditMessageReplyMarkup newMessage = new EditMessageReplyMarkup();
        newMessage.setChatId(update.getCallbackQuery().getMessage().getChatId().toString());
        newMessage.setMessageId(update.getCallbackQuery().getMessage().getMessageId());
        newMessage.setInlineMessageId(update.getCallbackQuery().getInlineMessageId());
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        if (NBU_BUTTON.equals(callBackData) || PRIVATBANK_BUTTON.equals(callBackData) || MONOBANK_BUTTON.equals(callBackData)) {
            settingsUserDto.setBankName(BankName.getByValue(callBackData));
            UserSettings.saveUserSettings(settingsUserDto);
            inlineKeyboardMarkup = createBankKeyboard(settingsUserDto);
        }
        if (TWO_DIGITS_BUTTON.equals(callBackData) || THREE_DIGITS_BUTTON.equals(callBackData) || FOUR_DIGITS_BUTTON.equals(callBackData)) {
            settingsUserDto.setDecimalPoint(DigitsAfterDecimalPoint.getByValue(callBackData));
            UserSettings.saveUserSettings(settingsUserDto);
            inlineKeyboardMarkup = createDigitsKeyboard(settingsUserDto);
        }
        if (USD_BUTTON.equals(callBackData) || EUR_BUTTON.equals(callBackData) || ("✅ " + USD_BUTTON).equals(callBackData) || ("✅ " + EUR_BUTTON).equals(callBackData)) {
            inlineKeyboardMarkup = changeCurrencyKeyboard(callBackData, settingsUserDto);
        }
        newMessage.setReplyMarkup(inlineKeyboardMarkup);
        return newMessage;
    }

    public static void pressNotificationTime(SendMessage message, SettingsUserDto settingsUserDto) {
        String notificationTime = NotificationTime.valueOf(settingsUserDto.getNotificationTime().name()).toString();
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
