package utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class Password {

    public static final String encryptMd5(String password) throws Exception {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        md5.update(password.getBytes(StandardCharsets.UTF_8));
        byte byteArr[] = md5.digest();
        return new String(byteArr, StandardCharsets.UTF_8);
    }
}
