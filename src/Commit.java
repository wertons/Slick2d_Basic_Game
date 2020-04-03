import java.io.IOException;
import java.net.URL;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Commit {
    URL urlPath;
    Scanner sc;
    StringBuffer strBuff;
    String result;
    String tmpStr;
    Pattern ptrn;
    Matcher mtchr;

    public int getCommits() throws IOException {
        //Commit Scanner Start-----------------
        urlPath = new URL("https://github.com/wertons/Slick2d_Basic_Game");
        sc = new Scanner(urlPath.openStream());
        strBuff = new StringBuffer();
        while (sc.hasNext()) {
            strBuff.append(sc.next());
        }
        result = strBuff.toString();
        result = result.replaceAll("<[^>]*>", "");
        tmpStr = "";
        ptrn = Pattern.compile("SignupAbasicSlick2djavaproject.*commits");
        mtchr = ptrn.matcher(result);
        if (mtchr.find()) {
            tmpStr = mtchr.group();
            tmpStr = tmpStr.replace("SignupAbasicSlick2djavaproject", "");
            tmpStr = tmpStr.replace("commits", "");
        }
        return Integer.parseInt(tmpStr);
        //-----------------Commit Scanner End

    }

    public String[] getControls() throws IOException {
        //Controls Scanner Start-----------------
        urlPath = new URL("https://github.com/wertons/Slick2d_Basic_Game/blob/master/Rules.md");
        sc = new Scanner(urlPath.openStream());
        strBuff = new StringBuffer();
        while (sc.hasNext()) {
            strBuff.append(sc.next());
        }
        result = strBuff.toString();
        result = result.replaceAll("<[^>]*>", "");
        tmpStr = "";
        ptrn = Pattern.compile("RawBlameHistory.*->Go&copy;");
        mtchr = ptrn.matcher(result);
        if (mtchr.find()) {
            tmpStr = mtchr.group();
            tmpStr = tmpStr.replace("RawBlameHistory", "");
            tmpStr = tmpStr.replace("-->Go&copy;", "");
            tmpStr = tmpStr.replace("Controls", "");

        }
        return tmpStr.split("[.]");
        //-----------------Controls Scanner End

    }
}