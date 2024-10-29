import com.app.Asymmetric_Encryption;
import com.app.Certificate_Operation;
import com.app.Signature_Operation;
import com.app.Symmetric_Encryption;
import java.security.PublicKey;

public class ApplicationStart {

    public static void main(String [] args) throws Exception {
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

    }
}
