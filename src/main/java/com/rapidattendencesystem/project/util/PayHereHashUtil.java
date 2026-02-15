package com.rapidattendencesystem.project.util;

import java.security.MessageDigest;

public class PayHereHashUtil {
    public static String md5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digest = md.digest(input.getBytes());

            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (Exception e) {
            throw new RuntimeException("MD5 error", e);
        }
    }

    public static String generateHash(
            String merchantId,
            String merchantSecret,
            String orderId,
            String amount,
            String currency
    ) {
        String secretHash = md5(merchantSecret).toUpperCase();

        String rawString =
                merchantId +
                        orderId +
                        amount +
                        currency +
                        secretHash;

        return md5(rawString).toUpperCase();
    }
}
