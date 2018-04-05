package com.kepai.base.encrypt;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

/**
 * @author huang
 */
public class PasswordUtils {

    private static final Random RANDOM = new Random();

    private static MessageDigest md;
    private static final String EncodeType = "SHA-256";

    private static final char[] hexDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'A', 'B', 'C', 'D', 'E', 'F'};

    private static final char[] randomChars = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
            'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

    static {
        try {
            md = MessageDigest.getInstance(EncodeType);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    private static String encode(String str) {
        md.update(str.getBytes());
        byte[] bytes = md.digest();
        StringBuilder sb = new StringBuilder(bytes.length * 2);
        for (int i = 0; i < bytes.length; ++i) {
            char c0 = hexDigits[(bytes[i] & 0xF0) >> 4];
            char c1 = hexDigits[bytes[i] & 0x0F];
            sb.append(c0);
            sb.append(c1);

        }
        return sb.toString();
    }

    /**
     * 密码加密
     *
     * @param password
     * @return
     */
    public static String encodePwd(String password) {
        String tempKey = random(10, randomChars);
        password = tempKey + password;
        return tempKey + encode(password);
    }

    /**
     * 密码验证
     *
     * @param passwordSys
     * @param passwordUsr
     * @return
     */
    public static boolean validatePwd(String passwordSys, String passwordUsr) {
        try {
            String tempKey = passwordSys.substring(0, 10);
            return passwordSys.equals(tempKey + encode(tempKey + passwordUsr));
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Md5加密
     *
     * @param text
     * @return
     */
    public static String encodeMd5(String text) {
        String s = null;
        byte[] source = text.getBytes();
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(source);
            char str[] = new char[16 * 2];
            byte tmp[] = md.digest();
            int k = 0;
            for (int i = 0; i < 16; i++) {
                byte byte0 = tmp[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            s = new String(str);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return s;
    }


    private static String random(int count, char[] chars) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < count; i++) {
            builder.append(chars[RANDOM.nextInt(chars.length)]);
        }
        return builder.toString();
    }


}
