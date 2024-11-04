package com.app;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.KeyStore;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.util.Enumeration;

public class KeyStore_Operations {

    public static void create_keyStore(String keyStore_path, String keyStore_pass) throws Exception {

        KeyStore keyStore = KeyStore.getInstance("JKS");
        keyStore.load(null, null);

        try (FileOutputStream fileOutputStream = new FileOutputStream(keyStore_path)) {
            keyStore.store(fileOutputStream, keyStore_pass.toCharArray());
        } catch (Exception e) {
            //noinspection CallToPrintStackTrace
            e.printStackTrace();
        }
    }

    public static void store_external_certificate(String keyStore_path, String keyStore_pass, String certificateFilePath) throws Exception {
        KeyStore keyStore = KeyStore.getInstance("JKS");

        try (FileInputStream fileInputStream = new FileInputStream(keyStore_path)) {
            keyStore.load(fileInputStream, keyStore_pass.toCharArray());
        } catch (Exception e) {
            //noinspection CallToPrintStackTrace
            e.printStackTrace();
        }

        Certificate certificate = Certificate_Operation.getCertificate(certificateFilePath);

        keyStore.setCertificateEntry("external_certificate", certificate);

        try (FileOutputStream fileOutputStream = new FileOutputStream(keyStore_path)) {
            keyStore.store(fileOutputStream, keyStore_pass.toCharArray());
        } catch (Exception e) {
            //noinspection CallToPrintStackTrace
            e.printStackTrace();
        }
    }

    public static PublicKey get_external_certificate_publicKey(String keyStore_path, String keyStore_pass) throws Exception {
        KeyStore keyStore = KeyStore.getInstance("JKS");

        try (FileInputStream fileInputStream = new FileInputStream(keyStore_path)) {
            keyStore.load(fileInputStream, keyStore_pass.toCharArray());
        } catch (Exception e) {
            //noinspection CallToPrintStackTrace
            e.printStackTrace();
        }

        Certificate certificate = keyStore.getCertificate("external_certificate");

        PublicKey publicKey;
        publicKey = certificate != null ? certificate.getPublicKey() : null;

        return publicKey;

    }

    public static void get_content_of_keyStore(String keyStore_path, String keyStore_pass) throws Exception {
        KeyStore keyStore = KeyStore.getInstance("JKS");

        try (FileInputStream fileInputStream = new FileInputStream(keyStore_path)) {
            keyStore.load(fileInputStream, keyStore_pass.toCharArray());
        } catch (Exception e) {
            //noinspection CallToPrintStackTrace

            e.printStackTrace();
        }

        // List the aliases and their types
        System.out.println("Contents of the keystore:");
        Enumeration<String> aliases = keyStore.aliases();
        while (aliases.hasMoreElements()) {
            String alias = aliases.nextElement();
            System.out.println("Alias: " + alias);
            // Print the entry type (certificate or private key)
            if (keyStore.isCertificateEntry(alias)) {
                System.out.println("  Type: Certificate Entry");
            } else if (keyStore.isKeyEntry(alias)) {
                System.out.println("  Type: Key Entry");
            }
        }

    }

    public static void delete_keyStore(String keyStore_path, String keyStore_pass) throws Exception {
        KeyStore keyStore = KeyStore.getInstance("JKS");

        try (FileInputStream fileInputStream = new FileInputStream(keyStore_path)) {
            keyStore.load(fileInputStream, keyStore_pass.toCharArray());
        } catch (Exception e) {
            //noinspection CallToPrintStackTrace

            e.printStackTrace();
        }

        keyStore.deleteEntry("external_certificate");

        try (FileOutputStream fileOutputStream = new FileOutputStream(keyStore_path)) {
            keyStore.store(fileOutputStream, keyStore_pass.toCharArray());
        } catch (Exception e) {
            //noinspection CallToPrintStackTrace

            e.printStackTrace();
        }
    }

}
