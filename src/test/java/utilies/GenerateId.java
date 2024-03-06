package utilies;

import org.apache.commons.lang3.RandomStringUtils;

public class GenerateId {
    public static String generateSessionId() {
// Генерация sessionID 24 знака буквенночисловая
        return RandomStringUtils.random(24, true, true);
    }
}
