package com.ukream.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA256Util {

    /**
     * 주어진 문자열에 대한 SHA-256 해시를 생성합니다.
     *
     * @param input 입력 문자열
     * @return SHA-256 해시값 (16진수 문자열)
     */
    public static String generateSha256(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedHash = digest.digest(input.getBytes(StandardCharsets.UTF_8));

            return bytesToHex(encodedHash);
        } catch (NoSuchAlgorithmException e) {
            // 예외 처리: 지원하지 않는 알고리즘일 경우
            throw new RuntimeException("지원하지 않는 알고리즘 입니다.");
        }
    }

    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
