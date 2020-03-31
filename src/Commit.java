import java.io.IOException;
import java.net.URL;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Commit {
    public int getCommits() throws IOException {
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
            tmp = tmp.replace("SignupAbasicSlick2djavaproject", "");
            tmp = tmp.replace("commits", "");
        }
        return Integer.parseInt(tmp);
    }

    public String[] getControls() throws IOException {
        URL url = new URL("https://github.com/wertons/Slick2d_Basic_Game/blob/master/Rules.md");
        Scanner sc = new Scanner(url.openStream());
        StringBuffer sb = new StringBuffer();
        while (sc.hasNext()) {
            sb.append(sc.next());
        }
        String result = sb.toString();
        result = result.replaceAll("<[^>]*>", "");
        String tmp = "";
        Pattern p = Pattern.compile("RawBlameHistory.*->Go&copy;");
        Matcher m = p.matcher(result);
        if (m.find()) {
            tmp = m.group();
            tmp = tmp.replace("RawBlameHistory", "");
            tmp = tmp.replace("-->Go&copy;", "");
            tmp = tmp.replace("Controls", "");

        }
        return tmp.split("[.]");
    }
}