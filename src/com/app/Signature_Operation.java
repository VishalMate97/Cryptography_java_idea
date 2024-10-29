package com.app;

import java.nio.charset.StandardCharsets;
import java.security.*;
import java.util.Base64;

public class Signature_Operation {

    static PublicKey publicKey = null;
    static PrivateKey privateKey = null;

    public static void init() throws Exception {
        SecureRandom secureRandom = new SecureRandom();

//        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("DSA");
        keyPairGenerator.initialize(2048, secureRandom);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();

        publicKey = keyPair.getPublic();
        privateKey = keyPair.getPrivate();

//        System.out.println("Signature Operation - public key : " + publicKey);
//        System.out.println("Signature Operation - private key : " + publicKey);

    }

    public static String sign(String message) throws Exception {
        SecureRandom secureRandom = new SecureRandom();

        Signature signature =  Signature.getInstance("SHA256WithDSA");
//        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initSign(privateKey, secureRandom);

        signature.update(message.getBytes(StandardCharsets.UTF_8));
        byte[] signatureData = signature.sign();

        return encode(signatureData);
    }

    public static Boolean verify(String message, String signatureData) throws Exception {
        Signature signature =  Signature.getInstance("SHA256WithDSA");
//        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initVerify(publicKey);

        signature.update(message.getBytes(StandardCharsets.UTF_8));
        return signature.verify(decode(signatureData));
    }

    public static String encode(byte[] data) {
        return Base64.getEncoder().encodeToString(data);
    }

    public static byte[] decode(String data) {
        return Base64.getDecoder().decode(data);
    }

}
