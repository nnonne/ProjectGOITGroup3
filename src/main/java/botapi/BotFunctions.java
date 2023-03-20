package botapi;

import settings.*;
import enums.BankName;
import enums.Currency;
import enums.DigitsAfterDecimalPoint;
import enums.NotificationTime;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

import java.util.List;

import static botapi.Buttons.*;
import static botapi.Buttons.EUR_BUTTON;
import static botapi.Keyboards.*;

public class BotFunctions {

    public static SettingsUserDto pressStart(SendMessage message, String userID) {
        if (!UserSettings.existUserById(userID)){
            UserSettings.saveUserSettings(new SettingsUserDto(userID, BankName.MONOBANK,
                    List.of(Currency.EUR, Currency.USD), NotificationTime.ELEVEN, DigitsAfterDecimalPoint.TWO));
        }
        SettingsUserDto settingsUserDto = UserSettings.getUserById(userID);
        message.setText("Ласкаво просимо. Цей бот допоможе Вам відслідковувати актуальні курси валют.");
        createDefaultKeyboard(message);
        return settingsUserDto;
    }
    public static EditMessageReplyMarkup placeCheckMark(String callBackData, Update update,
                                                        SettingsUserDto settingsUserDto) {
        EditMessageReplyMarkup newMessage = new EditMessageReplyMarkup();
        newMessage.setChatId(update.getCallbackQuery().getMessage().getChatId().toString());
        newMessage.setMessageId(update.getCallbackQuery().getMessage().getMessageId());
        newMessage.setInlineMessageId(update.getCallbackQuery().getInlineMessageId());
        InlineKeyboardMarkup inlineKeyboardMarkup = getChangedInlineKeyboardMarkup(callBackData, settingsUserDto);
        newMessage.setReplyMarkup(inlineKeyboardMarkup);
        return newMessage;
    }

    private static InlineKeyboardMarkup getChangedInlineKeyboardMarkup(String callBackData, SettingsUserDto settingsUserDto) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        if (NBU_BUTTON.equals(callBackData)
                || PRIVATBANK_BUTTON.equals(callBackData)
                || MONOBANK_BUTTON.equals(callBackData)) {
            settingsUserDto.setBankName(BankName.getByValue(callBackData));
            UserSettings.saveUserSettings(settingsUserDto);
            inlineKeyboardMarkup = createBankKeyboard(settingsUserDto);
        }
        if (TWO_DIGITS_BUTTON.equals(callBackData)
                || THREE_DIGITS_BUTTON.equals(callBackData)
                || FOUR_DIGITS_BUTTON.equals(callBackData)) {
            settingsUserDto.setDecimalPoint(DigitsAfterDecimalPoint.getByValue(callBackData));
            UserSettings.saveUserSettings(settingsUserDto);
            inlineKeyboardMarkup = createDigitsKeyboard(settingsUserDto);
        }
        if (USD_BUTTON.equals(callBackData)
                || EUR_BUTTON.equals(callBackData)
                || ("✅ " + USD_BUTTON).equals(callBackData)
                || ("✅ " + EUR_BUTTON).equals(callBackData)) {
            inlineKeyboardMarkup = changeCurrencyKeyboard(callBackData, settingsUserDto);
        }
        return inlineKeyboardMarkup;
    }

    public static void pressNotificationTime(SendMessage message, SettingsUserDto settingsUserDto) {
        String notificationTime = settingsUserDto.getNotificationTime().getValue();
        if (notificationTime.equals("Вимкнути повідомлення")) {
            message.setText("Наразі опція отримання повідомлень вимкнена. " +
                    "Якщо Ви бажаєте отримувати повідомлення у визначений час, " +
                    "будь ласка, оберіть його на клавіатурі.");
            createNotificationTimeKeyboard(message);
        } else {
            message.setText("Обраний час сповіщення: о " + notificationTime +
                    " кожного дня. Будь ласка, оберіть інший час сповіщень за потреби.");
            createNotificationTimeKeyboard(message);
        }
    }
}
