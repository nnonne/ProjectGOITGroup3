package BotAPI;

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

    public static void createDigitsKeyboard(SendMessage message) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>();
        List<InlineKeyboardButton> firstRowInLine = createButton(TWO_DIGITS_BUTTON);
        List<InlineKeyboardButton> secondRowInLine = createButton(THREE_DIGITS_BUTTON);
        List<InlineKeyboardButton> thirdRowInLine = createButton(FOUR_DIGITS_BUTTON);
        rowsInLine.add(firstRowInLine);
        rowsInLine.add(secondRowInLine);
        rowsInLine.add(thirdRowInLine);
        inlineKeyboardMarkup.setKeyboard(rowsInLine);
        message.setReplyMarkup(inlineKeyboardMarkup);
    }

    public static InlineKeyboardMarkup changeDigitsKeyboard(String callBackData) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>();
        List<InlineKeyboardButton> firstRowInLine = new ArrayList<>();
        List<InlineKeyboardButton> secondRowInLine = new ArrayList<>();
        List<InlineKeyboardButton> thirdRowInLine = new ArrayList<>();
        if (callBackData.equals(TWO_DIGITS_BUTTON)) {
            firstRowInLine = createButton("✅ " + TWO_DIGITS_BUTTON);
            secondRowInLine = createButton(THREE_DIGITS_BUTTON);
            thirdRowInLine = createButton(FOUR_DIGITS_BUTTON);
        }
        if (callBackData.equals(THREE_DIGITS_BUTTON)) {
            firstRowInLine = createButton(TWO_DIGITS_BUTTON);
            secondRowInLine = createButton("✅ " + THREE_DIGITS_BUTTON);
            thirdRowInLine = createButton(FOUR_DIGITS_BUTTON);
        }
        if (callBackData.equals(FOUR_DIGITS_BUTTON)) {
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

    public static void createBankKeyboard(SendMessage message) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>();
        List<InlineKeyboardButton> firstRowInLine = createButton(NBU_BUTTON);
        List<InlineKeyboardButton> secondRowInLine = createButton(PRIVATBANK_BUTTON);
        List<InlineKeyboardButton> thirdRowInLine = createButton(MONOBANK_BUTTON);
        rowsInLine.add(firstRowInLine);
        rowsInLine.add(secondRowInLine);
        rowsInLine.add(thirdRowInLine);
        inlineKeyboardMarkup.setKeyboard(rowsInLine);
        message.setReplyMarkup(inlineKeyboardMarkup);
    }

    public static InlineKeyboardMarkup changeBankKeyboard(String callBackData) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>();
        List<InlineKeyboardButton> firstRowInLine = new ArrayList<>();
        List<InlineKeyboardButton> secondRowInLine = new ArrayList<>();
        List<InlineKeyboardButton> thirdRowInLine = new ArrayList<>();
        if (callBackData.equals(NBU_BUTTON)) {
            firstRowInLine = createButton("✅ " + NBU_BUTTON);
            secondRowInLine = createButton(PRIVATBANK_BUTTON);
            thirdRowInLine = createButton(MONOBANK_BUTTON);
        }
        if (callBackData.equals(PRIVATBANK_BUTTON)) {
            firstRowInLine = createButton(NBU_BUTTON);
            secondRowInLine = createButton("✅ " + PRIVATBANK_BUTTON);
            thirdRowInLine = createButton(MONOBANK_BUTTON);
        }
        if (callBackData.equals(MONOBANK_BUTTON)) {
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

    public static void createCurrencyKeyboard(SendMessage message) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>();
        List<InlineKeyboardButton> firstRowInLine = createButton(USD_BUTTON);
        List<InlineKeyboardButton> secondRowInLine = createButton(EUR_BUTTON);
        rowsInLine.add(firstRowInLine);
        rowsInLine.add(secondRowInLine);
        inlineKeyboardMarkup.setKeyboard(rowsInLine);
        message.setReplyMarkup(inlineKeyboardMarkup);
    }

    public static List<InlineKeyboardButton> createButton(String buttonName) {
        List<InlineKeyboardButton> inlineKeyboardButtons = new ArrayList<>();
        InlineKeyboardButton inlineKeyboardButton = new InlineKeyboardButton();
        inlineKeyboardButton.setText(buttonName);
        inlineKeyboardButton.setCallbackData(buttonName);
        inlineKeyboardButtons.add(inlineKeyboardButton);
        return inlineKeyboardButtons;
    }

    public static EditMessageReplyMarkup placeCheckMark(String callBackData, Update update) {
        EditMessageReplyMarkup newMessage = new EditMessageReplyMarkup();
        newMessage.setChatId(update.getCallbackQuery().getMessage().getChatId().toString());
        newMessage.setMessageId(update.getCallbackQuery().getMessage().getMessageId());
        newMessage.setInlineMessageId(update.getCallbackQuery().getInlineMessageId());
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        if (callBackData.equals(NBU_BUTTON) || callBackData.equals(PRIVATBANK_BUTTON) || callBackData.equals(MONOBANK_BUTTON)) {
            inlineKeyboardMarkup = changeBankKeyboard(callBackData);
        }
        if (callBackData.equals(TWO_DIGITS_BUTTON) || callBackData.equals(THREE_DIGITS_BUTTON) || callBackData.equals(FOUR_DIGITS_BUTTON)) {
            inlineKeyboardMarkup = changeDigitsKeyboard(callBackData);
        }
        newMessage.setReplyMarkup(inlineKeyboardMarkup);
        return newMessage;
    }
}
