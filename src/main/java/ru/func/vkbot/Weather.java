package ru.func.vkbot;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Weather {

    private final String USER_AGENT = "Mozilla/5.0";

    // HTTP GET request
    protected String sendGet(String city) throws Exception {

        String url = "https://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + ApiKey.getApiKey();

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // optional default is GET
        con.setRequestMethod("GET");

        //add request header
        con.setRequestProperty("User-Agent", USER_AGENT);

        int responseCode = con.getResponseCode();

        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //print result
        System.out.println(response.toString());

        return response.toString();
    }

    protected String res(String response) {

        JSONObject jsonObject = new JSONObject(response.toString());
            String city = jsonObject.getString("name");
            double temp = Math.round(jsonObject.getJSONObject("main").getDouble("temp") - 273.15);
            double humidity = jsonObject.getJSONObject("main").getDouble("humidity");
            double wind = jsonObject.getJSONObject("wind").getDouble("speed");
            String overcast = jsonObject.getJSONArray("weather").getJSONObject(0).getString("description");
            return "City: "
                    + city + "\n"
                    + "Current temperature: "
                    + temp + "\n"
                    + "Current humidity: "
                    + humidity + "\n"
                    + "Wind speed: "
                    + wind + "\n"
                    + "Description: "
                    + overcast;
    }
}

