package com.visa.service.util;

import com.visa.service.exception.ProcessingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.Charsets;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;

/**
 * @author Abdussamad
 */
public class SecurityUtil {

  public static String hashWithMd5(String rawKey) throws ProcessingException {
    try {
      MessageDigest md = MessageDigest.getInstance("MD5");
      byte[] encrypted = md.digest(rawKey.getBytes());
      return new String(Hex.encodeHex(encrypted));
    } catch (NoSuchAlgorithmException ex) {
      String errorMessage = "Unable to hash this string";
      throw new ProcessingException(errorMessage);
    }
  }

  public static String hashWithSha256(String rawKey) throws ProcessingException {
    try {
      MessageDigest md = MessageDigest.getInstance("SHA-256");
      byte[] encrypted = md.digest(rawKey.getBytes());
      return new String(Hex.encodeHex(encrypted));
    } catch (NoSuchAlgorithmException ex) {
      String errorMessage = "Unable to hash this string";
      throw new ProcessingException(errorMessage);
    }
  }

  public static String hashWithHMACSHA1(String key, String data) throws ProcessingException {
    try {
      SecretKeySpec signingKey = new SecretKeySpec(key.getBytes(), "HmacSHA1");
      Mac mac = Mac.getInstance("HmacSHA1");
      mac.init(signingKey);
      return new String(Hex.encodeHex(mac.doFinal(data.getBytes(
          Charsets.UTF_8))));
    } catch (NoSuchAlgorithmException ex) {
      String errorMessage = "Unable to hash this string";
      throw new ProcessingException(errorMessage);
    } catch (Exception ex) {
      String errorMessage = "Unable to hash this string";
      throw new ProcessingException(errorMessage);
    }
  }

  public static String hashWithHmacSha256(String key, String data) throws ProcessingException {
    try {
      Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
      byte[] decodedPrivateKey = Hex.decodeHex(key.toCharArray());
      SecretKeySpec secret_key = new SecretKeySpec(decodedPrivateKey, "HmacSHA256");
      sha256_HMAC.init(secret_key);
      return Hex.encodeHexString(sha256_HMAC.doFinal(data.getBytes("UTF-8")));
    } catch (Exception ex) {
      String errorMessage = "Unable to hash this string";
      throw new ProcessingException(errorMessage);
    }
  }

  public static String base64Converter(String input) {
    Base64 base64 = new Base64();
    String encodedString = new String(base64.encode(input.getBytes()));
    return encodedString;
  }

}
