package com.orangehrm.crypto.run;

import com.orangehrm.configuration.environments.EnvironmentConfigConstants;
import com.orangehrm.crypto.services.EnvironmentCryptoManager;
import com.orangehrm.crypto.services.SecureKeyGenerator;
import com.orangehrm.utils.Base64Utils;
import com.orangehrm.utils.LoggerUtils;
import org.apache.logging.log4j.Logger;
import org.bouncycastle.crypto.CryptoException;

import javax.crypto.SecretKey;
import java.io.IOException;

public class EncryptionFlowTests {

    private static final Logger logger = LoggerUtils.getLogger(EncryptionFlowTests.class);
    private static final String USERNAME = "PORTAL_USERNAME";
    private static final String PASSWORD = "PORTAL_PASSWORD";

    public static void main(String[] args) throws CryptoException, IOException {
        generateSecretKey();
        encryptCredentials();
    }

    public static void generateSecretKey() throws IOException {
        SecretKey generatedSecretKey = SecureKeyGenerator.generateSecretKey();
        EnvironmentCryptoManager.saveSecretKeyInBaseEnvironment(
                EnvironmentConfigConstants.EnvironmentFilePath.BASE.getFullPath(),
                EnvironmentConfigConstants.EnvironmentSecretKey.UAT.getKeyName(),
                Base64Utils.encodeSecretKey(generatedSecretKey)
        );
        logger.info("Secret key generation process completed");
    }

    public static void encryptCredentials() throws CryptoException {
        EnvironmentCryptoManager.encryptEnvironmentVariables(
                EnvironmentConfigConstants.Environment.UAT.getDisplayName(),
                EnvironmentConfigConstants.EnvironmentFilePath.UAT.getFilename(),
                EnvironmentConfigConstants.EnvironmentSecretKey.UAT.getKeyName(),
                USERNAME, PASSWORD
        );
        logger.info("Encryption process completed");
    }
}
