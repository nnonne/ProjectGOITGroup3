package botapi;

import bankservice.HourCurrencyRatesUpdate;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;
import settings.SettingsUserDto;
import enums.NotificationTime;
import settings.UserSettings;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static botapi.BotFunctions.*;
import static botapi.Buttons.*;
import static botapi.Keyboards.*;
import static settings.UserSettings.getUserById;
import static settings.UserSettings.getUserByNotificationTime;

public class TelegramBot extends TelegramLongPollingBot {

    Properties property = new Properties();
    public static final String FILE_NAME = "./src/main/resources/botsettings.properties";
    SettingsUserDto settingsUserDto;

    public TelegramBot() {

        List<BotCommand> listofCommands = new ArrayList<>();
        listofCommands.add(new BotCommand("/start", "Початок роботи з MyFirstBot"));
        listofCommands.add(new BotCommand("/help", "Допомога в орієнтації по MyFirstBot"));
        listofCommands.add(new BotCommand("/mysettings", "Подивитись свої налаштування"));

        try {
            this.execute(new SetMyCommands(listofCommands, new BotCommandScopeDefault(), null));
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }

    }

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
            respondForTextMessage(update, message);
        } else if (update.hasCallbackQuery()) {
            respondForCallbackQuery(update, message);
        }
        try {
            execute(message);
        } catch (
                TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void respondForCallbackQuery(Update update, SendMessage message) {
        String callBackData = update.getCallbackQuery().getData();
        String userID = update.getCallbackQuery().getMessage().getChatId().toString();
        message.setChatId(update.getCallbackQuery().getMessage().getChatId().toString());
        settingsUserDto = getUserById(userID);
        handlePressedButton(update, message, callBackData);
    }

    private void handlePressedButton(Update update, SendMessage message, String callBackData) {
        if (GET_INFO_BUTTON.equals(callBackData)) {
            message.setText(MessageUserInfo.showInfo(settingsUserDto));
            createStartKeyboard(message);
        } else if (SETTINGS_BUTTON.equals(callBackData)) {
            message.setText(SETTINGS_BUTTON);
            createSettingsKeyboard(message);
        } else if (DIGITS_AFTER_DECIMAL_POINT_BUTTON.equals(callBackData)) {
            message.setText(DIGITS_AFTER_DECIMAL_POINT_BUTTON);
            message.setReplyMarkup(createDigitsKeyboard(settingsUserDto));
        } else if (TWO_DIGITS_BUTTON.equals(callBackData) ||
                THREE_DIGITS_BUTTON.equals(callBackData) ||
                FOUR_DIGITS_BUTTON.equals(callBackData) ||
                NBU_BUTTON.equals(callBackData) ||
                PRIVATBANK_BUTTON.equals(callBackData) ||
                MONOBANK_BUTTON.equals(callBackData) ||
                USD_BUTTON.equals(callBackData) ||
                EUR_BUTTON.equals(callBackData) ||
                ("✅ " + EUR_BUTTON).equals(callBackData) ||
                ("✅ " + USD_BUTTON).equals(callBackData)) {
            executeChangedMessage(placeCheckMark(callBackData, update, settingsUserDto));
        } else if (BANK_BUTTON.equals(callBackData)) {
            message.setText(BANK_BUTTON);
            message.setReplyMarkup(createBankKeyboard(settingsUserDto));
        } else if (CURRENCY_RATE_BUTTON.equals(callBackData)) {
            message.setText(CURRENCY_RATE_BUTTON);
            createCurrencyKeyboard(message, settingsUserDto);
        } else if (NOTIFICATION_TIME_BUTTON.equals(callBackData)) {
            pressNotificationTime(message, settingsUserDto);
        }
    }

    private void respondForTextMessage(Update update, SendMessage message) {
        ResponseMysettingsAndHelp messSettings = new ResponseMysettingsAndHelp();
        String userID = update.getMessage().getChatId().toString();
        String messageText = update.getMessage().getText();
        String output = messSettings.mysettingsAndHelp(messageText, userID);
        message.setText(output);
        message.setChatId(update.getMessage().getChatId().toString());
        settingsUserDto = getUserById(userID);
        handleReceivedText(message, userID, messageText);
    }

    private void handleReceivedText(SendMessage message, String userID, String messageText) {
        if (messageText.equals("/start")) {
            settingsUserDto = pressStart(message, userID);
        } else if (SETTINGS_BUTTON.equals(messageText)) {
            message.setText(SETTINGS_BUTTON);
            createSettingsKeyboard(message);
        } else if (GET_INFO_BUTTON.equals(messageText)) {
            message.setText(MessageUserInfo.showInfo(settingsUserDto));
            createStartKeyboard(message);
        } else if (Arrays.stream(NotificationTime.values()).anyMatch(element -> Objects.equals(element.getValue(),
                messageText))) {
            settingsUserDto.setNotificationTime(NotificationTime.getByValue(messageText));
            UserSettings.saveUserSettings(settingsUserDto);
            message.setText("Обраний час сповіщень: " + messageText);
            createDefaultKeyboard(message);
        } else if ("Назад".equals(messageText)) {
            message.setText("Ви повернулися в початкове меню.");
            createDefaultKeyboard(message);
        }
    }

    public void executeChangedMessage(EditMessageReplyMarkup editMessageReplyMarkup) {
        try {
            execute(editMessageReplyMarkup);

        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void sendDailyNotificationMessage() {
        ScheduledExecutorService service = Executors.newScheduledThreadPool(1);
        service.scheduleAtFixedRate(new Timer(), 0, 1, TimeUnit.MINUTES);
    }

    public void currencyUpdate(){
        ScheduledExecutorService service = Executors.newScheduledThreadPool(1);
        service.scheduleAtFixedRate(new HourCurrencyRatesUpdate(), 0, 5, TimeUnit.MINUTES);
    }

    public class Timer implements Runnable {
        SendMessage message = new SendMessage();
        private boolean sendMassage = false;
        private boolean blockSendMessage = false;

        @Override
        public void run() {
            ZonedDateTime userDateTime = ZonedDateTime.now(ZoneId.systemDefault());
            int hour = userDateTime.getHour();
            int minute = userDateTime.getMinute();
            if (hour >= 9 && hour <= 18) {
                if (minute <= 1 && !sendMassage && !blockSendMessage) {
                    sendMassage = true;
                    blockSendMessage = true;
                    hourly(hour);
                }
                if (minute > 2) blockSendMessage = false;
            } else blockSendMessage = false;
        }

        private void hourly(int hour) {
            SendMessage message = new SendMessage();
            Set<Map.Entry<String, NotificationTime>> userByNotification = getUserByNotificationTime().entrySet();

            for (Map.Entry<String, NotificationTime> allUser : userByNotification) {
                if (hour == allUser.getValue().getIntValue()) {
                    message.setChatId(allUser.getKey());
                    message.setText(botapi.MessageUserInfo.showInfo(getUserById(allUser.getKey())));
                    try {
                        execute(message);
                    } catch (
                            TelegramApiException e) {
                        e.printStackTrace();
                    }
                }
            }
            sendMassage = false;
        }
    }
}
