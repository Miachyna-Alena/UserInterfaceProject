package utils;

import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;
import org.apache.commons.lang3.RandomStringUtils;

public class RandomUtils {
    protected static final ISettingsFile CONFIG_DATA = new JsonSettingsFile("data/config-data.json");
    private static final String PATTERN = CONFIG_DATA.getValue("/PATTERN").toString();

    public static String generateRandomString(int length) {
        return RandomStringUtils.randomAlphanumeric(length);
    }

    public static String generateRandomPassword(int length, String email) {
        String password;
        do {
            password = RandomStringUtils.randomAlphanumeric(length);
        } while (checkRandomPassword(password, email));
        return password;
    }

    private static boolean checkRandomPassword(String password, String email) {
        for (int i = 0; i < password.length(); i++) {
            for (int j = 0; j < email.length(); j++) {
                if (password.toCharArray()[i] == email.toCharArray()[j] && password.matches(PATTERN)) {
                    return true;
                }
            }
        }
        return false;
    }
}
