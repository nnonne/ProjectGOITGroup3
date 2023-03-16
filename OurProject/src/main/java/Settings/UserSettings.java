package Settings;

import Dto.SettingsUserDto;
import Enums.BankName;
import Enums.Currency;
import Enums.DigitsAfterDecimalPoint;
import Enums.NotificationTime;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.util.*;

public class UserSettings {
    private static final String PATH = "OurProject/src/main/resources/users.json";

    // Перевірка, що файл "users.json" існує та створення якщо він відсутній
    private static File checkFileAvailability() {
        File file = new File(PATH);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return file;
    }

    // Формується Map<String, SettingsUserDto> усіх користувачів з json файлу, key - це Id користувача, value - це SettingsUserDto
    public synchronized static Map<String, SettingsUserDto> getUsersSettingsFromJson() {
        List<SettingsUserDto> allUserlist = new ArrayList<>();
        try (BufferedReader buff = new BufferedReader(new FileReader(checkFileAvailability()))) {
            allUserlist = new Gson().fromJson(buff, new TypeToken<List<SettingsUserDto>>() {
            }.getType());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Map<String, SettingsUserDto> allUsersMap = new HashMap<>();
        if (allUserlist != null) {
            for (SettingsUserDto element : allUserlist) {
                allUsersMap.put(element.getIdUser(), element);
            }
        }
        return allUsersMap;
    }

    // Збереження в файл нового/зміненого користувача, виконується перезапис всього json файлу
    private synchronized static void saveUsersSettingsToJson(Map<String, SettingsUserDto> usersSettings) {
        try (FileWriter fileWriter = new FileWriter(checkFileAvailability())) {
            Gson gson = new Gson();
            gson.toJson(usersSettings.values(), fileWriter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //  Збереження нового/зміненого користувача, запис виконується лише тоді коли користувач в файлі json відсутній або він змінений
    public static void saveUserSettings(SettingsUserDto userSettings) {
        Map<String, SettingsUserDto> mapUsersSettings = getUsersSettingsFromJson();

        if (mapUsersSettings.get(userSettings.getIdUser()) == null) {
            mapUsersSettings.put(userSettings.getIdUser(), userSettings);
            saveUsersSettingsToJson(mapUsersSettings);
        }
        if (!Objects.equals(userSettings, mapUsersSettings.get(userSettings.getIdUser()))) {
            mapUsersSettings.put(userSettings.getIdUser(), userSettings);
            saveUsersSettingsToJson(mapUsersSettings);
        }
    }
    // Наявність користувача SettingsUserDto в файлі .json за його Id
    public static boolean existUserById (String UserId) {
        return getUserById(UserId) != null;
    }

    // Видача користувача SettingsUserDto за його Id
    public static SettingsUserDto getUserById(String UserId) {
        return getUsersSettingsFromJson().get(UserId);
    }

    // Формується Map<String, NotificationTimeDto> де key - це Id користувача, value - це NotificationTimeDto
    private static Map<String, NotificationTime> getUserByNotificationTime() {
        Map<String, NotificationTime> result = new HashMap<>();
        Set<Map.Entry<String, SettingsUserDto>> setUsers = getUsersSettingsFromJson().entrySet();
        for (Map.Entry<String, SettingsUserDto> set : setUsers)
            result.put(set.getKey(), set.getValue().getNotificationTime());
        return result;
    }

    // Тест
    public static void main(String[] args) {
        SettingsUserDto settingsUserDto = new SettingsUserDto("111111", BankName.MONOBANK, List.of(Currency.EUR, Currency.USD), NotificationTime.ELEVEN, DigitsAfterDecimalPoint.TWO);
        saveUserSettings(settingsUserDto);
        SettingsUserDto settingsUserDto2 = new SettingsUserDto("222222", BankName.PRIVATBANK, List.of(Currency.EUR), NotificationTime.TEN, DigitsAfterDecimalPoint.TWO);
        saveUserSettings(settingsUserDto2);
        settingsUserDto2.setCurrency(List.of(Currency.EUR, Currency.USD));
        saveUserSettings(settingsUserDto2);
        SettingsUserDto settingsUserDto3 = new SettingsUserDto("333333", BankName.NBU, List.of(Currency.USD), NotificationTime.OFFNOTIFICATIONS, DigitsAfterDecimalPoint.THREE);
        saveUserSettings(settingsUserDto3);
        System.out.println("getUserById(\"222222\") = " + getUserById("222222"));
        System.out.println("getUserByNotificationTime() = " + getUserByNotificationTime());
        System.out.println("existUserById(\"333333\") = " + existUserById("333333"));
        System.out.println("existUserById(\"555555\") = " + existUserById("555555"));

    }
}
