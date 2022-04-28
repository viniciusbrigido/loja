package util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validate {

    public static boolean isEmailValido(String email) {
        Pattern pattern = Pattern.compile("^[\\w-]+(\\.[\\w-]+)*@([\\w-]+\\.)+[a-zA-Z]{2,7}$");
        Matcher matcher = pattern.matcher(email.trim());
        return matcher.find();
    }
}
