package botapi;

import settings.*;
import enums.Currency;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

import static botapi.Buttons.*;

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
        KeyboardRow firstRow = new KeyboardRow();
        firstRow.addAll(List.of("9:00", "10:00", "11:00"));
        KeyboardRow secondRow = new KeyboardRow();
        secondRow.addAll(List.of("12:00", "13:00", "14:00"));
        KeyboardRow thirdRow = new KeyboardRow();
        thirdRow.addAll(List.of("15:00", "16:00", "17:00"));
        KeyboardRow fourthRow = new KeyboardRow();
        fourthRow.addAll(List.of("18:00", "Вимкнути сповіщення", "Назад"));
        List<KeyboardRow> keyboardRows = new ArrayList<>(List.of(firstRow, secondRow, thirdRow, fourthRow));
        replyKeyboardMarkup.setKeyboard(keyboardRows);
        message.setReplyMarkup(replyKeyboardMarkup);
    }

    public static void createSettingsKeyboard(SendMessage message) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<InlineKeyboardButton> firstRow = createButton(DIGITS_AFTER_DECIMAL_POINT_BUTTON);
        List<InlineKeyboardButton> secondRow = createButton(BANK_BUTTON);
        List<InlineKeyboardButton> thirdRow = createButton(CURRENCY_RATE_BUTTON);
        List<InlineKeyboardButton> fourthRow = createButton(NOTIFICATION_TIME_BUTTON);
        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>(List.of(firstRow, secondRow, thirdRow, fourthRow));
        inlineKeyboardMarkup.setKeyboard(rowsInLine);
        message.setReplyMarkup(inlineKeyboardMarkup);
    }

    public static InlineKeyboardMarkup createDigitsKeyboard(SettingsUserDto settingsUserDto) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
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
        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>(List.of(firstRowInLine, secondRowInLine, thirdRowInLine));
        inlineKeyboardMarkup.setKeyboard(rowsInLine);
        return inlineKeyboardMarkup;
    }

    public static InlineKeyboardMarkup createBankKeyboard(SettingsUserDto settingsUserDto) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
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
        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>(List.of(firstRowInLine, secondRowInLine, thirdRowInLine));
        inlineKeyboardMarkup.setKeyboard(rowsInLine);
        return inlineKeyboardMarkup;
    }

    public static void createCurrencyKeyboard(SendMessage message, SettingsUserDto settingsUserDto) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
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
        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>(List.of(firstRowInLine, secondRowInLine));
        inlineKeyboardMarkup.setKeyboard(rowsInLine);
        message.setReplyMarkup(inlineKeyboardMarkup);
    }

    public static InlineKeyboardMarkup changeCurrencyKeyboard(String callBackData, SettingsUserDto settingsUserDto) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>();
        if (settingsUserDto.getCurrency().size() == 1) {
            replaceCheckMarkWhenOneWasChosen(callBackData, settingsUserDto, rowsInLine);
        }
        if (settingsUserDto.getCurrency().size() == 2) {
            deleteCheckMark(callBackData, settingsUserDto, rowsInLine);
        }
        inlineKeyboardMarkup.setKeyboard(rowsInLine);
        return inlineKeyboardMarkup;
    }

    private static void deleteCheckMark(String callBackData, SettingsUserDto settingsUserDto, List<List<InlineKeyboardButton>> rowsInLine) {
        List<InlineKeyboardButton> firstRowInLine = new ArrayList<>();
        List<InlineKeyboardButton> secondRowInLine = new ArrayList<>();
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
        rowsInLine.addAll(List.of(firstRowInLine, secondRowInLine));
    }

    private static void replaceCheckMarkWhenOneWasChosen(String callBackData, SettingsUserDto settingsUserDto, List<List<InlineKeyboardButton>> rowsInLine) {
        List<InlineKeyboardButton> firstRowInLine = new ArrayList<>();
        List<InlineKeyboardButton> secondRowInLine = new ArrayList<>();
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
        rowsInLine.addAll(List.of(firstRowInLine, secondRowInLine));
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
