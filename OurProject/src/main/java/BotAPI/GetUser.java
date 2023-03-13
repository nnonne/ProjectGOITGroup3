package BotAPI;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class GetUser {
    public void getUsetTGId(String messageText, String idUser ){
        try (InputStream word=new FileInputStream("userID.json.txt");
             Scanner scanner = new Scanner(word)) {

            boolean bool=true;
            while (scanner.hasNextLine()){
                String line=scanner.nextLine();
                if (line.contains(idUser)){
                    bool=false;
                }
            }
            if (bool==true){
                Map<String,String> base=new HashMap<>();
                Gson gson=new GsonBuilder().setPrettyPrinting().create();
                System.out.println("idUser = " + idUser);
                if (messageText.equals("/start")){
                    base.put("idUser",idUser);
                    base.put("DecimalPoint","2");
                    base.put("Bank","Privat");
                    base.put("Currency-USD","Ok");
                    base.put("Currency-EUR","Ok");
                    base.put("HourOfAwakening","10");
                    FileWriter fileWriter;
                    try {
                        fileWriter = new FileWriter("userID.json.txt",true);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    gson.toJson(base, fileWriter);
                    try {
                        fileWriter.close();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                }
                scanner.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
