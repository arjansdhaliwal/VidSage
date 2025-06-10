import java.net.URL;
import java.net.URLConnection;

public class Test {
    public static void main(String[] args) throws Exception {
        URL url = new URL("https://en.wikipedia.org/wiki/Sadhguru");

        URLConnection conn = url.openConnection();  // This is 'generic'
        
        System.out.println("What is really inside?");
        System.out.println(conn.getClass().getName());
    }
}
