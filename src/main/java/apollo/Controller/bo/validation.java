package apollo.Controller.bo;

import apollo.Model.beans.Account;
import java.util.*;
import java.util.regex.*;

public class validation {
    public boolean isInactiveAfterOneYear(Account account) {
        Date startDate = account.getDateStart();
        Date endDate = account.getDateEnd();

        if (startDate != null && endDate != null) {
            long differenceInMillis = endDate.getTime() - startDate.getTime();
            long differenceInYears = differenceInMillis / (1000L * 60L * 60L * 24L * 365L);

            return differenceInYears >= 1;
        }
        return false;
    }

    public boolean isValidEmail(String email) {
        String regex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }

    public boolean validatePassword(String password) {
        if (password.length() < 8) return false;

        String specialCharacters = "!@#$%^&*()-+/|";
        boolean hasSpecialCharacter = false;
        for (char c : password.toCharArray()) {
            if (specialCharacters.contains(String.valueOf(c))) {
                hasSpecialCharacter = true;
                break;
            }
        }
        return hasSpecialCharacter;
    }
}
