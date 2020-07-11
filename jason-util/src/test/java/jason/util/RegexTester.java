package jason.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexTester {
    public static void main(String[] args) {
        Pattern pattern = Pattern.compile("\\{[a-zA-Z_0-9.\\[\\]?&@!/|%'*#:()><=\\-, ]+\\}", java.util.regex.Pattern.CASE_INSENSITIVE);
        Matcher matches = pattern.matcher("{ \"claimType\": [ \"{{originalTag}}\" ] }");
        boolean result = matches.find();

        System.out.println(result);
    }
}
