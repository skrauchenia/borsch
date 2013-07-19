package com.exadel.borsch.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


/**
 * @author Andrey Zhilka
 */
public final class Encoder {
    private static final int HASH_RADIX = 16;
    private static final Logger LOGGER = LoggerFactory.getLogger(Encoder.class);


    private Encoder() {
    }

    public static String encodeWithMD5(String toEncode, String salt) {
        String forHash = salt + toEncode;
        String encoding = "UTF-8";
        try {
            MessageDigest m = MessageDigest.getInstance("MD5");
            m.update(forHash.getBytes(encoding), 0, forHash.length());
            return new BigInteger(1, m.digest()).toString(HASH_RADIX);
        } catch (NoSuchAlgorithmException e) {
            LOGGER.trace("MD5 algorithm was not found", e);
            throw new RuntimeException();
        } catch (UnsupportedEncodingException e) {
            LOGGER.trace("UTF-8 is not supportred by the system", e);
            throw new RuntimeException();
        }
    }
}

