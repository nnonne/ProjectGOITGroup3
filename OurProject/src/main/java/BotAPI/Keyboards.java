package BotAPI;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
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
        List<InlineKeyboardButton> firstRowInLine = createButton("Отримати інфо", GET_INFO_BUTTON);
        List<InlineKeyboardButton> secondRowInLine = createButton("Налаштування", SETTINGS_BUTTON);
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
        message.setReplyMarkup(replyKeyboardMarkup);
    }

    public static void createNotificationTimeKeyboard(SendMessage message) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        List<KeyboardRow> keyboardRows = new ArrayList<>();
        KeyboardRow firstRow = new KeyboardRow();
        firstRow.add("9");
        firstRow.add("10");
        firstRow.add("11");
        KeyboardRow secondRow = new KeyboardRow();
        secondRow.add("12");
        secondRow.add("13");
        secondRow.add("14");
        KeyboardRow thirdRow = new KeyboardRow();
        thirdRow.add("15");
        thirdRow.add("16");
        thirdRow.add("17");
        KeyboardRow fourthRow = new KeyboardRow();
        fourthRow.add("18");
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
        List<InlineKeyboardButton> firstRowInLine = createButton("К-сть знаків після коми", DIGITS_AFTER_DECIMAL_POINT_BUTTON);
        List<InlineKeyboardButton> secondRowInLine = createButton("Банк", BANK_BUTTON);
        List<InlineKeyboardButton> thirdRowInLine = createButton("Валюти", CURRENCY_RATE_BUTTON);
        List<InlineKeyboardButton> fourthRowInLine = createButton("Час сповіщень", NOTIFICATION_TIME_BUTTON);
        rowsInLine.add(firstRowInLine);
        rowsInLine.add(secondRowInLine);
        rowsInLine.add(thirdRowInLine);
        rowsInLine.add(fourthRowInLine);
        inlineKeyboardMarkup.setKeyboard(rowsInLine);
        message.setReplyMarkup(inlineKeyboardMarkup);
    }

    public static void createDigitsKeyboard(SendMessage message) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>();
        List<InlineKeyboardButton> firstRowInLine = createButton("2", TWO_DIGITS_BUTTON);
        List<InlineKeyboardButton> secondRowInLine = createButton("3", THREE_DIGITS_BUTTON);
        List<InlineKeyboardButton> thirdRowInLine = createButton("4", FOUR_DIGITS_BUTTON);
        rowsInLine.add(firstRowInLine);
        rowsInLine.add(secondRowInLine);
        rowsInLine.add(thirdRowInLine);
        inlineKeyboardMarkup.setKeyboard(rowsInLine);
        message.setReplyMarkup(inlineKeyboardMarkup);
    }

    public static void createBankKeyboard(SendMessage message) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>();
        List<InlineKeyboardButton> firstRowInLine = createButton("НБУ", NBU_BUTTON);
        List<InlineKeyboardButton> secondRowInLine = createButton("ПриватБанк", PRIVATBANK_BUTTON);
        List<InlineKeyboardButton> thirdRowInLine = createButton("Монобанк", MONOBANK_BUTTON);
        rowsInLine.add(firstRowInLine);
        rowsInLine.add(secondRowInLine);
        rowsInLine.add(thirdRowInLine);
        inlineKeyboardMarkup.setKeyboard(rowsInLine);
        message.setReplyMarkup(inlineKeyboardMarkup);
    }

    public static void createCurrencyKeyboard(SendMessage message) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>();
        List<InlineKeyboardButton> firstRowInLine = createButton("USD", USD_BUTTON);
        List<InlineKeyboardButton> secondRowInLine = createButton("EUR", EUR_BUTTON);
        rowsInLine.add(firstRowInLine);
        rowsInLine.add(secondRowInLine);
        inlineKeyboardMarkup.setKeyboard(rowsInLine);
        message.setReplyMarkup(inlineKeyboardMarkup);
    }

    public static List<InlineKeyboardButton> createButton(String text, String buttonName) {
        List<InlineKeyboardButton> inlineKeyboardButtons = new ArrayList<>();
        InlineKeyboardButton inlineKeyboardButton = new InlineKeyboardButton();
        inlineKeyboardButton.setText(text);
        inlineKeyboardButton.setCallbackData(buttonName);
        inlineKeyboardButtons.add(inlineKeyboardButton);
        return inlineKeyboardButtons;
    }
}
