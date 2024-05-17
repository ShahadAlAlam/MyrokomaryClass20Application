package org.saa.myrokomary_class20.config.security.encriptions;

import org.saa.myrokomary_class20.utils.AESUtil;
import org.springframework.stereotype.Service;
import javax.crypto.SecretKey;
import java.util.HashMap;

@Service
public class EncryptionService {

    private final SecretKey key;

    public EncryptionService() throws Exception {
        // Generate and store the key
        this.key = AESUtil.generateKey();
        System.out.println("AES KEY"+this.key);
    }

    public String getEncodedKey() {
        return AESUtil.encodeKey(key);
    }

    public byte[] encryptHashMap(HashMap<String, Object> map) throws Exception {
        // Serialize the HashMap to a byte array
        byte[] serializedData = AESUtil.serialize(map);

        // Encrypt the serialized byte array
        return AESUtil.encrypt(serializedData, key);
    }

    public HashMap<String, String> decryptHashMap(byte[] encryptedData) throws Exception {
        // Decrypt the byte array
        byte[] decryptedData = AESUtil.decrypt(encryptedData, key);

        // Deserialize the byte array back to a HashMap
        return AESUtil.deserialize(decryptedData);
    }
}
