package org.aneeshsid.urlshortener.utils;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashUtils {
    private static final String ALPHABET = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static String base62(byte[] bytes, int length) {
        BigInteger bi = new BigInteger(1, bytes);
        StringBuilder sb = new StringBuilder();
        while (sb.length() < length) {
            BigInteger[] divmod = bi.divideAndRemainder(BigInteger.valueOf(62));
            sb.append(ALPHABET.charAt(divmod[1].intValue()));
            bi = divmod[0];
        }
        return sb.toString();
    }

    public static String deterministicAlias(String url, int length) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            int salt = (int) (Math.ceil(Math.random()*100)*23*7);
            md.update((url).getBytes(StandardCharsets.UTF_8));
            byte[] hash = md.digest();
            return base62(hash, length);
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException(e);
        }
    }
}
