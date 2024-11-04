import com.app.*;

import java.security.PublicKey;

public class ApplicationStart {

    public static void main(String[] args) throws Exception {
        String plainText = "Hello Im Vishal, How are you If you are free let me know 4534 @233243";

        // Symmetric Encryption ---------------------------------------------------

        System.out.println("Symmetric Key Encryption");
        Symmetric_Encryption.init();
        System.out.println("Plaintext Message: " + plainText);

        String encryptedMessage = Symmetric_Encryption.encrypt(plainText);
        System.out.println("Encrypted Message: " + encryptedMessage);

        String decryptedMessage = Symmetric_Encryption.decrypt(encryptedMessage);
        System.out.println("Decrypted Message: " + decryptedMessage);

        System.out.println("------------------------------------------------");

        // Asymmetric Encryption ---------------------------------------------------

        System.out.println("Asymmetric Key Encryption");
        Asymmetric_Encryption.init();
        System.out.println("Plaintext Message: " + plainText);

        encryptedMessage = Asymmetric_Encryption.encrypt(plainText);
        System.out.println("Encrypted Message: " + encryptedMessage);

        decryptedMessage = Asymmetric_Encryption.decrypt(encryptedMessage);
        System.out.println("Decrypted Message: " + decryptedMessage);

        System.out.println("------------------------------------------------");

        // Signature ---------------------------------------------------

        System.out.println("Signature Operation");

        Signature_Operation.init();
        System.out.println("Plaintext Message: " + plainText);

        String signatureData = Signature_Operation.sign(plainText);
        System.out.println("signature Data: " + signatureData);

        Boolean verifySignature = Signature_Operation.verify(plainText, signatureData);
        System.out.println("Signature Verification: " + verifySignature);

        System.out.println("------------------------------------------------");

        // Certificate ---------------------------------------------------

        String certificateFilePath = "D:\\OneDrive - Bizsolindia IT Services Pvt. Ltd\\Desktop\\dgftPublicCertificate.txt";
        System.out.println("Certificate Operation");

        PublicKey publicKey = Certificate_Operation.getPublicKeyFromCertificate(certificateFilePath);
        System.out.println("Public Key from Certificate: " + publicKey);

        System.out.println("------------------------------------------------");

        // KeyStore - Store the Certificate and then load Certificate and public key --------------------

        String keyStore_path = "D:\\practice setup\\IntelliJ\\Cryptography_java_idea\\other_files\\keyStores\\truststore.jks";
        String keyStore_pass = "123";
        System.out.println("-------------------KeyStore Operations-------------------");

        KeyStore_Operations.create_keyStore(keyStore_path, keyStore_pass);

        KeyStore_Operations.store_external_certificate(keyStore_path, keyStore_pass, certificateFilePath);
        PublicKey publicKey2 = KeyStore_Operations.get_external_certificate_publicKey(keyStore_path, keyStore_pass);
        System.out.println("Public Key from Certificate from key Store: " + publicKey2);

        System.out.println("-------------------KeyStore Delete Operations-------------------");

        System.out.println("Before Delete");
        KeyStore_Operations.get_content_of_keyStore(keyStore_path, keyStore_pass);

        KeyStore_Operations.delete_keyStore(keyStore_path, keyStore_pass);
        System.out.println("----------");

        System.out.println("After Delete");
        KeyStore_Operations.get_content_of_keyStore(keyStore_path, keyStore_pass);

        System.out.println("------------------------------------------------");

    }
}
