package botapi;

import settings.SettingsUserDto;
import settings.UserSettings;


public class ResponseMysettingsAndHelp {
    public String mysettingsAndHelp(String textMessage, String id) {

        SettingsUserDto settingsUser = UserSettings.getUserById(id);

        String responseMysettings = "";
        if (textMessage.equals("/mysettings")) {
            responseMysettings = "Якщо необхідно отримати додаткову інформацію з приводу налаштувань напишіть: /help "+"\n"
                    +"Ваші налаштування:"+"\n"
                    +"Банк: "+ settingsUser.getBankName()+"\n"
                    +"Валюта: "+ settingsUser.getCurrency()+"\n"
                    +"Кількість знаків після коми: "+ settingsUser.getDecimalPoint().getValue()+"\n"
                    +"Час сповіщення: "+ settingsUser.getNotificationTime().getValue()+"\n";
        }
        else if (textMessage.equals("/help")) {
            responseMysettings =
                    "1. Для отримання інформацій про перелік банків, необхідно вибрати (Налаштування ->Банк);\n" +
                            "2. Для встановлення формату кількість після коми, необхідно вибрати (Налаштування -> К-сть знаків після коми);\n"+
                            "3. Для отримання інформацій про валюту, необхідно вибрати (Налаштування ->Валюти);\n"+
                            "4. Для отримання інформацій про час сповіщення, необхідно вибрати (Налаштування ->Час сповіщень).\n" +
                            "команди:\n" +
                            "/mysettings - подивитись свої налаштування.\n" +
                            "/start - розпочати роботу з ботом.\n" +
                            "/help - подивитись на це повідомлення знову.\n";
        }
        else {
            responseMysettings = "Невірна команда, виберіть одну з команд або зайдить в меню.\n" +
                    "Список доступних команд:\n" +
                    "/help \n"+
                    "/mysettings \n"+
                    "/start \n";
        }
        return responseMysettings;
    }
}
