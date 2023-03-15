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

import static Enums.BankName.*;
import static Enums.Currency.EUR;
import static Enums.Currency.USD;
import static Enums.DigitsAfterDecimalPoint.TWO;
import static Enums.NotificationTime.TEN;


public class GetUser {
    public void getUsetTGId(String messageText, String idUser ){
        try (InputStream word=new FileInputStream("OurProject/src/main/java/BotAPI/users.json");
             Scanner scanner = new Scanner(word)) {

            boolean bool=true;
            while (scanner.hasNext()){
                String line=scanner.nextLine();
                if (line.contains(idUser)){
                    bool=false;
                }
            }
            if (bool){
                Map<String,EnumsGetUser> base=new HashMap<>();
                Gson gson=new GsonBuilder().setPrettyPrinting().create();
                System.out.println("idUser = " + idUser);
                if (messageText.equals("/start")){
                    base.put("idUser",new EnumsGetUser(idUser));
                    base.put("Currency",new EnumsGetUser(USD,EUR));
                    base.put("DigitsAfterDecimalPoint",new EnumsGetUser(TWO));
                    base.put("BankNames",new EnumsGetUser(PRIVATBANK,MONOBANK,NBU) );
                    base.put("NotificationTime", new EnumsGetUser(TEN));
                    FileWriter fileWriter;
                    try {
                        fileWriter = new FileWriter("OurProject/src/main/java/BotAPI/users.json",true);
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
