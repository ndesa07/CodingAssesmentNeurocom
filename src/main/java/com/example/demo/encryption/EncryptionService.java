package com.example.demo.encryption;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.util.Base64;

//AES encryption 
@Component
public class EncryptionService 
{
  @Value("${card.aes.key-base64:}") private String keyB64;
  private SecretKey key;
  private final SecureRandom rnd = new SecureRandom();

  @PostConstruct
  void init() throws Exception 
  {
    if (keyB64 == null || keyB64.isBlank()) 
    {
      // Key Generator library to get AES
      KeyGenerator kg = KeyGenerator.getInstance("AES");
      kg.init(256); key = kg.generateKey();       // dev fallback only
    }
    else 
    {
      key = new SecretKeySpec(Base64.getDecoder().decode(keyB64), "AES");
    }
  }

  public record EncResult(byte[] iv, byte[] cipher) 
  {}

  // Encrypts the given plaintext using AES in GCM mode with a random IV.
  public EncResult encrypt(String plaintext) throws Exception 
  {
    byte[] iv = new byte[12]; rnd.nextBytes(iv);

    // Create and initialize the AES cipher in GCM mode using the shared secret key and IV.
    Cipher c = Cipher.getInstance("AES/GCM/NoPadding");
    c.init(Cipher.ENCRYPT_MODE, key, new GCMParameterSpec(128, iv));
    return new EncResult(iv, c.doFinal(plaintext.getBytes()));
  }
    
}
