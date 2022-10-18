package com.common.DigestUtili;

import com.google.common.base.Suppliers;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.function.Supplier;

/**
 * @author mrl
 * @data 2022/10/11
 */
public class Digest {
    private static final String SHA_ALG = "SHA-1";

    private static final Supplier<MessageDigest> SHA_SUPPLIER =
            Suppliers.memoize(() -> messageDigest(SHA_ALG));
    public static String formatOutput(byte[] hash){
        String d="";
        for(int i=0;i<hash.length;i++){
            int v = hash[i] & 0xFF;
            if( v < 16 ){
                d +="0";
            }
            d += Integer.toString(v,16).toUpperCase() + "" ;
        }
        return d;
    }

    /**
     *
     * @param algorithm
     * @return 生成对应的哈希算法
     */
    private static MessageDigest messageDigest(final String algorithm) {
        try {
            return MessageDigestFactory.create(algorithm);
        } catch (final NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     *
     * @param input
     * @param digestSupplier
     * @return 根据不同哈希算法返回不同加密结果
     */
    private static byte[] digestUsingAlgorithm(final byte[] input,final Supplier<MessageDigest> digestSupplier) {
        try {
            final MessageDigest alg = (MessageDigest) digestSupplier.get().clone();
            alg.update(input);
            return alg.digest();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     *
     * @param input
     * @return SHA 加密后的数据
     */
    public static byte[] sha(final byte[] input) {
        return digestUsingAlgorithm(input, SHA_SUPPLIER);
    }


}
