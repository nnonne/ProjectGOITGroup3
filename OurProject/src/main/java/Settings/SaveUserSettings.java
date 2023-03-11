package Settings;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;

public class SaveUserSettings {
    public void saveUser(SettingsUserDto user){

        String nameFile = "user" + String.valueOf(user.getId()) + ".json";
        File file = new File(nameFile);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String toJson = gson.toJson(user);
        try (OutputStream fos = Files.newOutputStream(file.toPath())) {
            fos.write(toJson.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
