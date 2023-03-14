package BotAPI;

import org.apache.http.ParseException;
import org.json.JSONObject;
import org.json.JSONWriter;
import org.json.simple.parser.JSONParser;

import java.io.*;
import java.util.List;

public class UpdateUserSettings {
    public void updateUsersSettings(String idUser, String fieldName, String value) throws IOException, ParseException, org.json.simple.parser.ParseException {

        Object obj = new JSONParser().parse(new FileReader("userID.json"));
        JSONObject jsonObject = (JSONObject) obj;
        JSONObject userSettings = (JSONObject) jsonObject.get(idUser);

        switch (fieldName) {
            case "BankNames":
                userSettings.remove("BankNames");
                userSettings.put("BankNames", value);
                break;
            case "Currency":
                String oldValue = (String) userSettings.get("Currency");
                userSettings.put("Currency", /*oldValue + ", " + value*/ List.of(oldValue, value));
                break;
            case "DigitsAfterDecimalPoint":
                userSettings.remove("DigitsAfterDecimalPoint");
                userSettings.put("DigitsAfterDecimalPoint", value);
                break;
            case "NotificationTime":
                userSettings.remove("NotificationTime");
                userSettings.put("NotificationTime", value);
                break;
            default:
                throw new IllegalArgumentException("Невірна назва поля: " + fieldName);
        }

        try (FileWriter file = new FileWriter("userID.json")) {
            JSONWriter jsonWriter = new JSONWriter(file);
            jsonWriter.object();
            for (Object key : jsonObject.keySet()) {
                String userIdKey = (String) key;
                jsonWriter.key(userIdKey);
                if (userIdKey.equals(idUser)) {
                    jsonWriter.value(userSettings);
                } else {
                    jsonWriter.value(jsonObject.get(userIdKey));
                }
            }
            jsonWriter.endObject();
        }
    }

}
