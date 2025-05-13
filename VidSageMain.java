import java.util.Scanner;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import org.json.JSONObject;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class VidSageMain{
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your research topic: ");
        String topic = scanner.nextLine();

        System.out.println("Processing... Researching topic: " + topic);

        //Wikipedia API URL   
        String encodedTopic = "";
        try{
            encodedTopic = URLEncoder.encode(topic, "UTF-8"); 
        }catch(UnsupportedEncodingException e){
            System.out.println("UTF-8 encoding not supported. This should never happen.");
            e.printStackTrace();
        }
    
        String urlString ="https://en.wikipedia.org/w/api.php?action=query&format=json&titles=" + encodedTopic +"&prop=extracts&exintro=true";

        try{
            URL url = new URL(urlString);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            
            System.out.println("HTTP Status Code: " + connection.getResponseCode());

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while((inputLine = in.readLine()) != null){
                response.append(inputLine);
            }
            in.close();

            // Parse the response as JSON
            JSONObject myResponse = new JSONObject(response.toString());
            
            // Extract the "extract" text from the response. We need to navigate through the JSON structure to get to the text we want.
            JSONObject pages = myResponse.getJSONObject("query").getJSONObject("pages");
            String pageID = pages.keys().next();
            String extract = pages.getJSONObject(pageID).getString("extract");

            // Print the extract
            String cleanExtract = stringHTMLTags(extract);
            System.out.println("Extract: " + cleanExtract);
            System.out.println("Research complete!");
            System.out.println("");

        }catch(Exception e){
            e.printStackTrace();
        }

        scanner.close();
    }

    public static String stringHTMLTags(String html){
        return html.replaceAll("\\<.*?\\>", "");
    }
}