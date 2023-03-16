package BotAPI;

import Dto.SettingsUserDto;
import Enums.BankName;
import Enums.Currency;
import Enums.DigitsAfterDecimalPoint;
import Settings.UserSettings;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

import static BotAPI.Buttons.*;

public class Keyboards {

    public static void createStartKeyboard(SendMessage message) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>();
        List<InlineKeyboardButton> firstRowInLine = createButton(GET_INFO_BUTTON);
        List<InlineKeyboardButton> secondRowInLine = createButton(SETTINGS_BUTTON);
        rowsInLine.add(firstRowInLine);
        rowsInLine.add(secondRowInLine);
        inlineKeyboardMarkup.setKeyboard(rowsInLine);
        message.setReplyMarkup(inlineKeyboardMarkup);
    }

    public static void createDefaultKeyboard(SendMessage message) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        List<KeyboardRow> keyboardRows = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();
        row.add("Отримати інфо");
        row.add("Налаштування");
        keyboardRows.add(row);
        replyKeyboardMarkup.setKeyboard(keyboardRows);
        replyKeyboardMarkup.setResizeKeyboard(true);
        message.setReplyMarkup(replyKeyboardMarkup);
    }

    public static void createNotificationTimeKeyboard(SendMessage message) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        List<KeyboardRow> keyboardRows = new ArrayList<>();
        KeyboardRow firstRow = new KeyboardRow();
        firstRow.add("9:00");
        firstRow.add("10:00");
        firstRow.add("11:00");
        KeyboardRow secondRow = new KeyboardRow();
        secondRow.add("12:00");
        secondRow.add("13:00");
        secondRow.add("14:00");
        KeyboardRow thirdRow = new KeyboardRow();
        thirdRow.add("15:00");
        thirdRow.add("16:00");
        thirdRow.add("17:00");
        KeyboardRow fourthRow = new KeyboardRow();
        fourthRow.add("18:00");
        fourthRow.add("Вимкнути повідомлення");
        keyboardRows.add(firstRow);
        keyboardRows.add(secondRow);
        keyboardRows.add(thirdRow);
        keyboardRows.add(fourthRow);
        replyKeyboardMarkup.setKeyboard(keyboardRows);
        message.setReplyMarkup(replyKeyboardMarkup);
    }

    public static void createSettingsKeyboard(SendMessage message) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>();
        List<InlineKeyboardButton> firstRowInLine = createButton(DIGITS_AFTER_DECIMAL_POINT_BUTTON);
        List<InlineKeyboardButton> secondRowInLine = createButton(BANK_BUTTON);
        List<InlineKeyboardButton> thirdRowInLine = createButton(CURRENCY_RATE_BUTTON);
        List<InlineKeyboardButton> fourthRowInLine = createButton(NOTIFICATION_TIME_BUTTON);
        rowsInLine.add(firstRowInLine);
        rowsInLine.add(secondRowInLine);
        rowsInLine.add(thirdRowInLine);
        rowsInLine.add(fourthRowInLine);
        inlineKeyboardMarkup.setKeyboard(rowsInLine);
        message.setReplyMarkup(inlineKeyboardMarkup);
    }

    public static InlineKeyboardMarkup createDigitsKeyboard(SettingsUserDto settingsUserDto) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>();
        List<InlineKeyboardButton> firstRowInLine = new ArrayList<>();
        List<InlineKeyboardButton> secondRowInLine = new ArrayList<>();
        List<InlineKeyboardButton> thirdRowInLine = new ArrayList<>();
        String userDigits = settingsUserDto.getDecimalPoint().getValue();
        if (TWO_DIGITS_BUTTON.equals(userDigits)) {
            firstRowInLine = createButton("✅ " + TWO_DIGITS_BUTTON);
            secondRowInLine = createButton(THREE_DIGITS_BUTTON);
            thirdRowInLine = createButton(FOUR_DIGITS_BUTTON);
        }
        if (THREE_DIGITS_BUTTON.equals(userDigits)) {
            firstRowInLine = createButton(TWO_DIGITS_BUTTON);
            secondRowInLine = createButton("✅ " + THREE_DIGITS_BUTTON);
            thirdRowInLine = createButton(FOUR_DIGITS_BUTTON);
        }
        if (FOUR_DIGITS_BUTTON.equals(userDigits)) {
            firstRowInLine = createButton(TWO_DIGITS_BUTTON);
            secondRowInLine = createButton(THREE_DIGITS_BUTTON);
            thirdRowInLine = createButton("✅ " + FOUR_DIGITS_BUTTON);
        }
        rowsInLine.add(firstRowInLine);
        rowsInLine.add(secondRowInLine);
        rowsInLine.add(thirdRowInLine);
        inlineKeyboardMarkup.setKeyboard(rowsInLine);
        return inlineKeyboardMarkup;
    }

    public static InlineKeyboardMarkup createBankKeyboard(SettingsUserDto settingsUserDto) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>();
        List<InlineKeyboardButton> firstRowInLine = new ArrayList<>();
        List<InlineKeyboardButton> secondRowInLine = new ArrayList<>();
        List<InlineKeyboardButton> thirdRowInLine = new ArrayList<>();
        String userBank = settingsUserDto.getBankName().getValue();
        if (NBU_BUTTON.equals(userBank)) {
            firstRowInLine = createButton("✅ " + NBU_BUTTON);
            secondRowInLine = createButton(PRIVATBANK_BUTTON);
            thirdRowInLine = createButton(MONOBANK_BUTTON);
        }
        if (PRIVATBANK_BUTTON.equals(userBank)) {
            firstRowInLine = createButton(NBU_BUTTON);
            secondRowInLine = createButton("✅ " + PRIVATBANK_BUTTON);
            thirdRowInLine = createButton(MONOBANK_BUTTON);
        }
        if (MONOBANK_BUTTON.equals(userBank)) {
            firstRowInLine = createButton(NBU_BUTTON);
            secondRowInLine = createButton(PRIVATBANK_BUTTON);
            thirdRowInLine = createButton("✅ " + MONOBANK_BUTTON);
        }
        rowsInLine.add(firstRowInLine);
        rowsInLine.add(secondRowInLine);
        rowsInLine.add(thirdRowInLine);
        inlineKeyboardMarkup.setKeyboard(rowsInLine);
        return inlineKeyboardMarkup;
    }

    public static void createCurrencyKeyboard(SendMessage message, SettingsUserDto settingsUserDto) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>();
        List<InlineKeyboardButton> firstRowInLine = new ArrayList<>();
        List<InlineKeyboardButton> secondRowInLine = new ArrayList<>();
        String selectedCurrency = settingsUserDto.getCurrency().get(0).name();
        if (settingsUserDto.getCurrency().size() == 1) {
            if (USD_BUTTON.equals(selectedCurrency)) {
                firstRowInLine = createButton("✅ " + USD_BUTTON);
                secondRowInLine = createButton(EUR_BUTTON);
            }
            if (EUR_BUTTON.equals(selectedCurrency)) {
                firstRowInLine = createButton(USD_BUTTON);
                secondRowInLine = createButton("✅ " + EUR_BUTTON);
            }
        }
        if (settingsUserDto.getCurrency().size() == 2) {
            firstRowInLine = createButton("✅ " + USD_BUTTON);
            secondRowInLine = createButton("✅ " + EUR_BUTTON);
        }
        rowsInLine.add(firstRowInLine);
        rowsInLine.add(secondRowInLine);
        inlineKeyboardMarkup.setKeyboard(rowsInLine);
        message.setReplyMarkup(inlineKeyboardMarkup);
    }

    public static InlineKeyboardMarkup changeCurrencyKeyboard(String callBackData, SettingsUserDto settingsUserDto) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>();
        List<InlineKeyboardButton> firstRowInLine = new ArrayList<>();
        List<InlineKeyboardButton> secondRowInLine = new ArrayList<>();
        if (settingsUserDto.getCurrency().size() == 1) {
            if (("✅ " + USD_BUTTON).equals(callBackData) || EUR_BUTTON.equals(callBackData)) {
                firstRowInLine = createButton(USD_BUTTON);
                secondRowInLine = createButton("✅ " + EUR_BUTTON);
                settingsUserDto.setCurrency(List.of(Currency.EUR));
                UserSettings.saveUserSettings(settingsUserDto);

            }
            if (USD_BUTTON.equals(callBackData) || ("✅ " + EUR_BUTTON).equals(callBackData)) {
                firstRowInLine = createButton("✅ " + USD_BUTTON);
                secondRowInLine = createButton(EUR_BUTTON);
                settingsUserDto.setCurrency(List.of(Currency.USD));
                UserSettings.saveUserSettings(settingsUserDto);
            }
            if (USD_BUTTON.equals(callBackData) || EUR_BUTTON.equals(callBackData)) {
                firstRowInLine = createButton("✅ " + USD_BUTTON);
                secondRowInLine = createButton("✅ " + EUR_BUTTON);
                settingsUserDto.setCurrency(List.of(Currency.USD, Currency.EUR));
                UserSettings.saveUserSettings(settingsUserDto);
            }
        }
        if (settingsUserDto.getCurrency().size() == 2) {
            if (("✅ " + EUR_BUTTON).equals(callBackData)) {
                firstRowInLine = createButton("✅ " + USD_BUTTON);
                secondRowInLine = createButton(EUR_BUTTON);
                settingsUserDto.setCurrency(List.of(Currency.USD));
                UserSettings.saveUserSettings(settingsUserDto);
            }
            if (("✅ " + USD_BUTTON).equals(callBackData)) {
                firstRowInLine = createButton(USD_BUTTON);
                secondRowInLine = createButton("✅ " + EUR_BUTTON);
                settingsUserDto.setCurrency(List.of(Currency.EUR));
                UserSettings.saveUserSettings(settingsUserDto);
            }
        }
        rowsInLine.add(firstRowInLine);
        rowsInLine.add(secondRowInLine);
        inlineKeyboardMarkup.setKeyboard(rowsInLine);
        return inlineKeyboardMarkup;
    }

    public static List<InlineKeyboardButton> createButton(String buttonName) {
        List<InlineKeyboardButton> inlineKeyboardButtons = new ArrayList<>();
        InlineKeyboardButton inlineKeyboardButton = new InlineKeyboardButton();
        inlineKeyboardButton.setText(buttonName);
        inlineKeyboardButton.setCallbackData(buttonName);
        inlineKeyboardButtons.add(inlineKeyboardButton);
        return inlineKeyboardButtons;
    }
}
