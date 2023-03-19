package settings;

import enums.NotificationTime;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.util.*;

public class UserSettings {
    private static final String PATH = "src/main/resources/users.json";

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

    private synchronized static void saveUsersSettingsToJson(Map<String, SettingsUserDto> usersSettings) {
        try (FileWriter fileWriter = new FileWriter(checkFileAvailability())) {
            Gson gson = new Gson();
            gson.toJson(usersSettings.values(), fileWriter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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
    public static boolean existUserById (String UserId) {
        return getUserById(UserId) != null;
    }

    public static SettingsUserDto getUserById(String UserId) {
        return getUsersSettingsFromJson().get(UserId);
    }

    public static Map<String, NotificationTime> getUserByNotificationTime() {
        Map<String, NotificationTime> result = new HashMap<>();
        Set<Map.Entry<String, SettingsUserDto>> setUsers = getUsersSettingsFromJson().entrySet();
        for (Map.Entry<String, SettingsUserDto> set : setUsers)
            if (set.getValue().getNotificationTime() != NotificationTime.OFFNOTIFICATIONS)
                result.put(set.getKey(), set.getValue().getNotificationTime());
        return result;
    }

}
