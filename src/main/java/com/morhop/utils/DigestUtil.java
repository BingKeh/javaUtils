package com.morhop.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * <p>calculate digest tool use java build in MessageDigest Algorithm</p>
 * <p>there are shortcut ways to use md5 and sha algorithm,
 * just invoke <code>getMD5</code> or <code>getSha..</code> to get results</p>
 * <p>if you want to use specified algorithm just invoke <code>getDigest</code></p>
 * @author BingKeh
 * 2018/2/3 11:14
 */
public class DigestUtil {
    private static final String DEFAULT_CHARACTER_SET = "UTF-8";
    private static final String SHA_512 = "SHA-512";
    private static final String SHA_256 = "SHA-256";

    /**
     * @param bytes bytes to hash
     * @return hashed bytes
     * @throws NoSuchAlgorithmException throw it when there is no md5 algorithm
     */
    public static byte[] getMD5(byte... bytes) throws NoSuchAlgorithmException {
        return getDigest("MD5", bytes);
    }

    /**
     * @param str string used to calculate
     * @param charsetName character set name of the string, utf-8 is default if it is null
     * @return the result string formatted in hex
     * @throws UnsupportedEncodingException throw unsupported character set
     * @throws NoSuchAlgorithmException throw if there is no md5 algorithm
     */
    public static String getMD5(String str, String charsetName) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        if (charsetName == null)
            charsetName = DEFAULT_CHARACTER_SET;
        return toHex(getMD5(str.getBytes(charsetName)));
    }

    /**
     * @param str string used to calculate
     * @return the digest of the string using sha-256
     * @throws NoSuchAlgorithmException no sha-256 algorithm
     */
    public static String getSha256(String str) throws NoSuchAlgorithmException {
        return toHex(getDigest(SHA_256, str.getBytes()));
    }

    /**
     * @param str string used to calculate
     * @return the digest of the string using sha-512
     * @throws NoSuchAlgorithmException no sha-512 algorithm
     */
    public static String getSha512(String str) throws NoSuchAlgorithmException {
        return toHex(getDigest(SHA_512, str.getBytes()));
    }

    /**
     * @param type the algorithm
     * @param bytes bytes to hash
     * @return hashed bytes
     * @throws NoSuchAlgorithmException throw it when there is no 'type' algorithm
     */
    public static byte[] getDigest(String type, byte... bytes) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance(type);
        return digest.digest(bytes);
    }

    /**
     * @param bytes bytes to be formatted
     * @return the formatted string in hex
     */
    protected static String toHex(byte... bytes) {
        StringBuilder sBuilder = new StringBuilder();
        for (byte b : bytes) {
            sBuilder.append(String.format("%02X", b));
        }
        return sBuilder.toString();
    }
}
