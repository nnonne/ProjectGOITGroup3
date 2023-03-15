import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.util.*;

public class UserSettings {
    private static final String PATH = "src/main/resources/users.json";

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
    private synchronized static Map<String, SettingsUserDto> getUsersSettingsFromJson() {
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
    private static Map<String, NotificationTimeDto> getUserByNotificationTime() {
        Map<String, NotificationTimeDto> result = new HashMap<>();
        Set<Map.Entry<String, SettingsUserDto>> setUsers = getUsersSettingsFromJson().entrySet();
        for (Map.Entry<String, SettingsUserDto> set : setUsers)
            result.put(set.getKey(), set.getValue().getNotificationTime());
        return result;
    }

    // Тест
    public static void main(String[] args) {
        SettingsUserDto settingsUserDto = new SettingsUserDto("111111", BankNames.NBU,Currency.EUR,NotificationTimeDto.NINE, 2);
        saveUserSettings(settingsUserDto);
        SettingsUserDto settingsUserDto2 = new SettingsUserDto("22222", BankNames.NBU,Currency.EUR,NotificationTimeDto.FIFTEEN, 2);
        saveUserSettings(settingsUserDto2);
        //settingsUserDto2.setCurrency(Currency.USD);
        saveUserSettings(settingsUserDto2);
        SettingsUserDto settingsUserDto3 = new SettingsUserDto("33333", BankNames.NBU,Currency.EUR,NotificationTimeDto.TEN, 2);
        saveUserSettings(settingsUserDto3);
        System.out.println("getUserById(\"22222\") = " + getUserById("22222"));
        System.out.println("getUserByNotificationTime() = " + getUserByNotificationTime());
        System.out.println("existUserById(\"5555\") = " + existUserById("22222"));

    }
}
