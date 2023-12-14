package de.srh.library.util;
import java.util.Random;

public class RandomCodeGenerator {

    public static String generateVerificationCode(int length) {
        String digits = "0123456789";

        StringBuilder codeBuilder = new StringBuilder(length);
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(digits.length());
            char digit = digits.charAt(index);
            codeBuilder.append(digit);
        }
        return codeBuilder.toString();
    }
}
