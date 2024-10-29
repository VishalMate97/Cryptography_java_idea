package com.app;

import java.io.*;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.util.Base64;

public class Certificate_Operation {

    public static PublicKey getPublicKeyFromCertificate(String certificate_file_path) throws CertificateException, IOException {
    // Read the entire file content as a Base64-encoded string
        String base64Certificate = readCertificateFile(certificate_file_path);

        // Decode the Base64 encoded certificate to a byte array
        byte[] decodedBytes = Base64.getMimeDecoder().decode(base64Certificate);

        // Use the decoded bytes to create an InputStream for the CertificateFactory
        InputStream inputStream = new ByteArrayInputStream(decodedBytes);
        CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
        Certificate certificate = certificateFactory.generateCertificate(inputStream);

        return certificate.getPublicKey();
    }

    private static String readCertificateFile(String filePath) throws IOException {
        StringBuilder certificateContent = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                certificateContent.append(line.trim());  // Trim whitespace and append
            }
        }
        return certificateContent.toString();
    }

}
