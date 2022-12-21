package com.common.DigestUtili;


import org.junit.Test;

import javax.crypto.*;
import java.io.*;
import java.security.*;

import static javax.crypto.Cipher.*;

/**
 * @author mrl
 * @data 2022/10/16
 */
public class RSA {

    private static final int KEYSIZE = 512;
    private static KeyPair keyPair;
    /**
     * -genkey : 生成私钥和公钥
     *      java RSATest -genkey public.key private.key
     * -encrypt : 加密
     *      // java RSATest -encrypt plaintextFile encryptedFile public.key
     * -decrypt : 解密
     *      java RSATest -decrypt encryptedFile decryptedFile private.key
     * @param args
     *
     *  可行。
     */
    public void main(String[] args) throws NoSuchAlgorithmException, IOException, ClassNotFoundException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, ShortBufferException, BadPaddingException {
        //TODO：将AES对称加密更换为SHA加密

        //java RSATest -genkey public.key private.key
        if(("-genkey").equals(args[0])){
            System.out.println("执行-genkey");
            if(keyPair == null){
                keyPair = getRSA();
            }
            ObjectOutputStream out=null;
            try {
                out = new ObjectOutputStream(new FileOutputStream(args[1]));
                out.writeObject(keyPair.getPublic());
                out = new ObjectOutputStream(new FileOutputStream(args[2]));
                out.writeObject(keyPair.getPrivate());
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                out.close();

            }
        }else{
            // java RSATest -encrypt plaintextFile encryptedFile public.key
            if(("-encrypt").equals(args[0])){
                // 用AES生成密钥
                KeyGenerator keygen = KeyGenerator.getInstance("AES");
                keygen.init(getRandomNumber());
                SecretKey key = keygen.generateKey();

                FileInputStream dataIn = new FileInputStream(args[1]);
                DataOutputStream dataOut = new DataOutputStream(new FileOutputStream(args[2]));
                ObjectInputStream keyIn = new ObjectInputStream(new FileInputStream(args[3]));
                
                Key publicKey = (Key)keyIn.readObject();
                // 用public key 对 AES key
                Cipher rsa = getInstance("RSA");
                rsa.init(WRAP_MODE,publicKey);
                byte[] wrapKey = rsa.wrap(key);
                //密钥长度 + 密钥字节码
                dataOut.writeInt(wrapKey.length);
                dataOut.write(wrapKey);
                //AES密钥加密明文
                Cipher aes = getInstance("AES");
                aes.init(ENCRYPT_MODE,key);
                Crypt.run(dataIn,dataOut,aes);

                //关闭资源
                dataIn.close();
                dataOut.close();
                keyIn.close();
            }else if(("-decrypt".equals(args[0]))){
                // java RSATest -decrypt encryptedFile decryptedFile private.key
                DataInputStream dataIn = new DataInputStream(new FileInputStream(args[1]));
                FileOutputStream dataOut = new FileOutputStream(args[2]);
                ObjectInputStream keyIn = new ObjectInputStream(new FileInputStream(args[3]));

                // 读取加密文件内容
                int len = dataIn.readInt();
                byte[] wrapKey = new byte[len];
                dataIn.read(wrapKey,0,len);

                // private
                Key privateKey = (Key)keyIn.readObject();

                // 用private key 给 aes对称加密算法 解密
                Cipher rsa = Cipher.getInstance("RSA");
                rsa.init(Cipher.UNWRAP_MODE,privateKey);
                Key key = rsa.unwrap(wrapKey, "AES", SECRET_KEY);

                // 获得aes的解密密钥
                Cipher aes = Cipher.getInstance("AES");
                aes.init(DECRYPT_MODE,key);
                Crypt.run(dataIn,dataOut,aes);


                // 关闭资源
                dataIn.close();
                dataOut.close();
                keyIn.close();
            }


        }
    }

    /**
     *
     * @return RSA实例
     * @throws NoSuchAlgorithmException
     */
    public static KeyPair getRSA() throws NoSuchAlgorithmException {
        KeyPairGenerator rsa = KeyPairGenerator.getInstance("RSA");
        rsa.initialize(KEYSIZE, getRandomNumber());
        KeyPair keyPair = rsa.generateKeyPair();
        return keyPair;
    }

    /**
     *
     * @return 随机数
     */
    public static SecureRandom getRandomNumber() {
        SecureRandom secureRandom = new SecureRandom();
//        byte[] b = new byte[20];
//        secureRandom.setSeed(b);
        return secureRandom;
    }
}
