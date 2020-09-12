import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;

public class Weather {

    public static String getWeather(String message, Model model) throws IOException {

        URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q=" + message + "&units=metric&appid=2f54b2bf955e7285ea700053c756c08a");

        Scanner sc = new Scanner((InputStream) url.getContent());
        String result = "";


        while (sc.hasNext()) {
            result += sc.nextLine();
        }

        JSONObject object = new JSONObject(result);
        model.setName(object.getString("name"));

        JSONObject object1 = object.getJSONObject("main");

        model.setTemp(object1.getDouble("temp"));
        model.setHumidity(object1.getDouble("humidity"));

        JSONArray jsonArray = object.getJSONArray("weather");

        for (int i = 0; i < jsonArray.length(); i++) {

            JSONObject obj = jsonArray.getJSONObject(i);
            model.setIcon((String) obj.get("icon"));
            model.setMain((String) obj.get("main"));
        }

        return "City: " + model.getName() + "\n" +
                "temperature: " + model.getTemp() + "C" + "\n" +
                "humidity: " + model.getHumidity() + "%" + "\n" +
                "main" + model.getMain() + "\n" +
                "http://openweathermap.org/img/wn/" + model.getIcon() + ".png";
    }
}
