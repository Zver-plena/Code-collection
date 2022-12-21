package com.common.DigestUtili;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author mrl
 * @data 2022/10/11
 */
public class MessageDigestFactory {

        static MessageDigest create(final String algorithm) throws NoSuchAlgorithmException {
            return MessageDigest.getInstance(algorithm);
        }
}
