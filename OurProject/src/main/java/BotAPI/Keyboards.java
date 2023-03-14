package BotAPI;

import Dto.SettingsUserDto;
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

    public static InlineKeyboardMarkup createDigitsKeyboard(SettingsUserDto settingsUserDto) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>();
        List<InlineKeyboardButton> firstRowInLine = new ArrayList<>();
        List<InlineKeyboardButton> secondRowInLine = new ArrayList<>();
        List<InlineKeyboardButton> thirdRowInLine = new ArrayList<>();
        if (TWO_DIGITS_BUTTON.equals(settingsUserDto.getDecimalPoint().name())) {
            firstRowInLine = createButton("✅ " + TWO_DIGITS_BUTTON);
            secondRowInLine = createButton(THREE_DIGITS_BUTTON);
            thirdRowInLine = createButton(FOUR_DIGITS_BUTTON);
        }
        if (THREE_DIGITS_BUTTON.equals(settingsUserDto.getDecimalPoint().name())) {
            firstRowInLine = createButton(TWO_DIGITS_BUTTON);
            secondRowInLine = createButton("✅ " + THREE_DIGITS_BUTTON);
            thirdRowInLine = createButton(FOUR_DIGITS_BUTTON);
        }
        if (FOUR_DIGITS_BUTTON.equals(settingsUserDto.getDecimalPoint().name())) {
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
        if (NBU_BUTTON.equals(settingsUserDto.getBankName().name())) {
            firstRowInLine = createButton("✅ " + NBU_BUTTON);
            secondRowInLine = createButton(PRIVATBANK_BUTTON);
            thirdRowInLine = createButton(MONOBANK_BUTTON);
        }
        if (PRIVATBANK_BUTTON.equals(settingsUserDto.getBankName().name())) {
            firstRowInLine = createButton(NBU_BUTTON);
            secondRowInLine = createButton("✅ " + PRIVATBANK_BUTTON);
            thirdRowInLine = createButton(MONOBANK_BUTTON);
        }
        if (MONOBANK_BUTTON.equals(settingsUserDto.getBankName().name())) {
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
//                deleteCurrencyFromJson(settingsUserDto.getIdUser(), "Currency", USD_BUTTON);
//                updateUserSettings(settingsUserDto.getIdUser(), "Currency", EUR_BUTTON);
            }
            if (USD_BUTTON.equals(callBackData) || ("✅ " + EUR_BUTTON).equals(callBackData)) {
                firstRowInLine = createButton("✅ " + USD_BUTTON);
                secondRowInLine = createButton(EUR_BUTTON);
//                deleteCurrencyFromJson(settingsUserDto.getIdUser(), "Currency", EUR_BUTTON);
//                updateUserSettings(settingsUserDto.getIdUser(), "Currency", USD_BUTTON);
            }
            if (USD_BUTTON.equals(callBackData) || EUR_BUTTON.equals(callBackData)) {
                firstRowInLine = createButton("✅ " + USD_BUTTON);
                secondRowInLine = createButton("✅ " + EUR_BUTTON);
//                updateUserSettings(settingsUserDto.getIdUser(), "Currency", callBackData);
            }
        }
        if (settingsUserDto.getCurrency().size() == 2) {
            if (("✅ " + EUR_BUTTON).equals(callBackData)) {
                firstRowInLine = createButton("✅ " + USD_BUTTON);
                secondRowInLine = createButton(EUR_BUTTON);
//                deleteCurrencyFromJson(settingsUserDto.getIdUser(), "Currency", EUR_BUTTON);
            }
            if (("✅ " + USD_BUTTON).equals(callBackData)) {
                firstRowInLine = createButton(USD_BUTTON);
                secondRowInLine = createButton("✅ " + EUR_BUTTON);
//                deleteCurrencyFromJson(settingsUserDto.getIdUser(), "Currency", USD_BUTTON);
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

    public static EditMessageReplyMarkup placeCheckMark(String callBackData, Update update, SettingsUserDto settingsUserDto) {
        EditMessageReplyMarkup newMessage = new EditMessageReplyMarkup();
        newMessage.setChatId(update.getCallbackQuery().getMessage().getChatId().toString());
        newMessage.setMessageId(update.getCallbackQuery().getMessage().getMessageId());
        newMessage.setInlineMessageId(update.getCallbackQuery().getInlineMessageId());
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        if (NBU_BUTTON.equals(callBackData) || PRIVATBANK_BUTTON.equals(callBackData) || MONOBANK_BUTTON.equals(callBackData)) {
//            updateUserSettings(settingsUserDto.getIdUser(), "Bank", callBackData);
            inlineKeyboardMarkup = createBankKeyboard(settingsUserDto);
        }
        if (TWO_DIGITS_BUTTON.equals(callBackData) || THREE_DIGITS_BUTTON.equals(callBackData) || FOUR_DIGITS_BUTTON.equals(callBackData)) {
//            updateUserSettings(settingsUserDto.getIdUser(), "Decimal", callBackData);
            inlineKeyboardMarkup = createDigitsKeyboard(settingsUserDto);
        }
        if (USD_BUTTON.equals(callBackData) || EUR_BUTTON.equals(callBackData) || ("✅ " + USD_BUTTON).equals(callBackData) || ("✅ " + EUR_BUTTON).equals(callBackData)) {
            inlineKeyboardMarkup = changeCurrencyKeyboard(callBackData, settingsUserDto);
        }
        newMessage.setReplyMarkup(inlineKeyboardMarkup);
        return newMessage;
    }
}
