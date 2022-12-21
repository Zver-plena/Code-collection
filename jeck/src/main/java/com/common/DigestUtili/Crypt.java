package com.common.DigestUtili;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.ShortBufferException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author mrl
 * @data 2022/10/16
 */
public class Crypt {

    public static void run(InputStream in, OutputStream out, Cipher cipher) throws IOException, ShortBufferException, IllegalBlockSizeException, BadPaddingException {
        //返回密码块的大小
        int blockSize = cipher.getBlockSize();
        //缓冲区大小
        int outputSize = cipher.getOutputSize(blockSize);
        byte[] inBuf = new byte[blockSize];
        byte[] outBuf = new byte[outputSize];
        int len = 0;
        boolean more = true;
        while(more){
            len = in.read(inBuf);
            if(len  == blockSize){
                int outLen = cipher.update(inBuf,0,blockSize,outBuf);
                out.write(outBuf,0,outLen);
            }else{
                more = false;
            }
        }
        // 如果内容不够填充密码块，通过doFinal进行填充加密
        if( len  > 0 ) {
            outBuf = cipher.doFinal(inBuf,0,len);
        }else{
            outBuf = cipher.doFinal();
        }
        out.write(outBuf);
    }

}
