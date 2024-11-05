package ReuseTests;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import java.util.HashMap;
import java.util.Map;

public class OpenaiAPI {
    private static final String OPENAI_API_SECRET_KEY = "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";
    //private static final String API_URL = "https://api.openai.com/v1/chat/completions";
    private static final String API_URL = "http://127.0.0.1:5000/ask";

    public static String GenerateAPIRequest(String question) {
        try {


            // Set up the request to the local Python server
            URL url = new URL("http://localhost:5000/ask");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json; utf-8");
            connection.setRequestProperty("Accept", "application/json");
            connection.setDoOutput(true);

            // Create JSON payload
            JSONObject jsonInput = new JSONObject();
            jsonInput.put("question", question);

            // Write JSON to the request body
            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = jsonInput.toString().getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            // Read the response
            StringBuilder response = new StringBuilder();
            try (BufferedReader br = new BufferedReader(
                    new InputStreamReader(connection.getInputStream(), "utf-8"))) {
                String responseLine;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
            }

            // Parse and print the response
            JSONObject jsonResponse = new JSONObject(response.toString());
            System.out.println("Answer: " + jsonResponse.getString("answer"));
            return jsonResponse.getString("answer");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return " ";
    }
}
