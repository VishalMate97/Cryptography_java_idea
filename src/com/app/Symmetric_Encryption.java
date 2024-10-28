package com.app;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Base64;

public class Symmetric_Encryption {
    static SecretKey secretKey = null;

    public static void init() throws Exception
    {
        SecureRandom secureRandom = new SecureRandom();
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(256, secureRandom);

        secretKey = keyGenerator.generateKey();
    }

    public static String encrypt(String message) throws Exception{
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(1, secretKey);

        return encode(cipher.doFinal(message.getBytes()));
    }

    public static String decrypt(String encryptedMessageString) throws Exception{
        byte[] encryptedMessageBytes = decode(encryptedMessageString);

        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(2, secretKey);

        return new String(cipher.doFinal(encryptedMessageBytes), StandardCharsets.UTF_8);
    }

    public static String encode(byte[] data){
        return Base64.getEncoder().encodeToString(data);
    }

    public static byte[] decode(String data){
        return Base64.getDecoder().decode(data);
    }
}
