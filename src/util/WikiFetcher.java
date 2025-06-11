package util;

import java.net.URL;
import java.net.URLConnection;
import java.net.HttpURLConnection;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import org.json.JSONObject;

public class WikiFetcher{
    public static String fetchData(String topic){
        try {
        String normalizedTopic = topic.substring(0, 1).toUpperCase() + topic.substring(1).toLowerCase();
        //Wikipedia's public RESTful API.
        String encodedTopic = java.net.URLEncoder.encode(normalizedTopic, "UTF-8"); 
        String url = "https://en.wikipedia.org/w/api.php?action=query&format=json&titles=" + encodedTopic + "&prop=extracts&exintro=true";
            URL wikiURL = new URL(url);
            URLConnection genericConnection = wikiURL.openConnection();
            HttpURLConnection connection = (HttpURLConnection) genericConnection;
            connection.setRequestMethod("GET");
            //Java connects automatically also when used getInputStream()
            connection.connect();

            /*
             * Wikipedia Server
                ↓ (raw data)
               InputStream         → raw bytes
                ↓
               InputStreamReader   → decoded text characters
                ↓
               BufferedReader      → efficient line-by-line reading
             */
            InputStream inputStream = connection.getInputStream();
            InputStreamReader inputStreamRead = new InputStreamReader (inputStream);
            BufferedReader inputReader = new BufferedReader(inputStreamRead);

            StringBuilder responseBuilder = new StringBuilder();

            String line;
            //.readLine(): It reads one line of text from the input — up to the next \n (newline character) — and returns it as a String.
            //.append(): It does not let it overwrite the previous line. It is used when to write just at the end of the previoud String.
            while((line = inputReader.readLine()) != null){
                responseBuilder.append(line);
            }
            inputReader.close();
            inputStreamRead.close();
            inputStream.close();

            String response = responseBuilder.toString();

            JSONObject fullJSON = new JSONObject(response);
            JSONObject queryObject = fullJSON.getJSONObject("query");
            JSONObject pagesObject = queryObject.getJSONObject("pages");
            String pageId = pagesObject.keys().next();
            JSONObject page = pagesObject.getJSONObject(pageId);
            if(!page.has("extract")){
                return "No such topic as '" + encodedTopic +"'.";
            }
            String extractText = page.getString("extract");

            String plainText = stripHTML(extractText).stripLeading();
            System.out.println("\nGot the Data! Nice Topic!! " + encodedTopic);
            System.out.println("\nInternal Print: \n" + plainText);
            return plainText;
        }catch(Exception e){
            return "Error with URL: " + e.getMessage();
        }
    }
    public static String stripHTML(String htmlString){
            return htmlString
                .replaceAll("<[^>]*>", "")
                .replaceAll("\\n{2,}", "\n")
                .strip();
    }
}

