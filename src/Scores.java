import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Scores {
    public static void main(String args[]) throws IOException {
        URL url = new URL("https://docs.google.com/document/d/1Z34CvJUmyeqNoGZefY04hrNwrfwOeCrGkrY2c4XiEuM/edit?usp=sharing");
        Scanner sc = new Scanner(url.openStream());
        StringBuffer sb = new StringBuffer();
        while (sc.hasNext()) {
            sb.append(sc.next());
        }
        String result = sb.toString();
        result = result.replaceAll("<[^>]*>", "");

        String tmp;
        Pattern p = Pattern.compile("ñçñ.*ñçñ");
        Matcher m = p.matcher(result);
        if (m.find())
        {
            result = m.group();
        }
         p = Pattern.compile("çWç.*çWç");
         m = p.matcher(result);
        String[] scores = new String[10];
        if (m.find())
        {
            tmp = m.group();
            tmp = tmp.replaceAll("çWç","\n");
            scores = tmp.split("\n");
        }
        p = Pattern.compile("ñWñ.*ñWñ");
        m = p.matcher(result);
        String[] names = new String[10];
        if (m.find())
        {
            tmp = m.group();
            tmp = tmp.replaceAll("ñWñ","\n");
            names = tmp.split("\n");
            System.out.format("'%s'\n", result);
        }
        Score[] scs = new Score[10];
        for (int i = 0; i <  scores.length/2; i++) {
            scs[i] = new Score(scores[(i*2)+1],names[(i*2)+1],i);
        }
        int s = 0;

    }
}
class  Score{
    String score;
    String name;
    int position;
    Score(String s,String m,int p){
        score = s;
        name = m;
        position = p+1;
    }
}