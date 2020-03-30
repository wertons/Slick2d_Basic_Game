import java.io.IOException;
import java.net.URL;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Commit {
    public static void main(String[] args) throws IOException {
        Commit c = new Commit();
        c.generateScores();
    }

    public void generateScores() throws IOException {
        URL url = new URL("https://github.com/wertons/Slick2d_Basic_Game");
        Scanner sc = new Scanner(url.openStream());
        StringBuffer sb = new StringBuffer();
        while (sc.hasNext()) {
            sb.append(sc.next());
        }
        String result = sb.toString();
        result = result.replaceAll("<[^>]*>", "");

        String tmp = "";
        Pattern p = Pattern.compile("SignupAbasicSlick2djavaproject.*commits");
        Matcher m = p.matcher(result);
        if (m.find()) {
            tmp = m.group();
          tmp =   tmp.replace("SignupAbasicSlick2djavaproject","");
            tmp =   tmp.replace("commits","");

        }
        System.out.println(tmp);


    }
}