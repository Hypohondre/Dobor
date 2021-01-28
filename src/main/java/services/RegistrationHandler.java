package services;

import DAO.Repositories.UsersDaoImpl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegistrationHandler {
    public ArrayList<String> errors;
    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
                    "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private Pattern pattern;
    private Matcher matcher;

    public ArrayList<String> validate(String name, String password, String email, String birth) {
        this.handleName(name);
        this.handlePassword(password);
        this.handleEmail(email);
        this.handleDate(birth);
        return errors;
    }

    private void handleName(String name) {
        if (name.equals("")) {
            errors.add("Incorrect name");
        }
    }

    private void handlePassword(String password) {
        if (password.length() < 8) {
            errors.add("The password must contain 8 or more characters");
        }
    }

    private void handleEmail(String email) {
        UsersDaoImpl bd = new UsersDaoImpl();
        if(bd.findByEmail(email) != null) {
            errors.add("This email exist");
            return;
        }
        matcher = pattern.matcher(email);
        if (!matcher.matches()) {
            errors.add("Incorrect email");
        }
    }

    private void handleDate(String birth) {
        if (birth.equals("")) {
            errors.add("Date is not entered");
        }
    }

    public RegistrationHandler() {
        errors = new ArrayList<>();
        pattern = Pattern.compile(EMAIL_PATTERN);
    }
}
