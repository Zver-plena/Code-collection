package com.client;

import java.io.*;
import java.net.Socket;
import java.nio.file.FileSystemNotFoundException;
import java.util.Scanner;

/**
 * @author mrl
 * @data 2022/10/8
 * 客户端value给服务端存储按照k,v
 */
public class Client {

    public static void main(String[] args) throws IOException {

        Socket socket = new Socket("127.0.0.1", 9000);
        System.out.println("成功连接服务器");
        OutputStream out = socket.getOutputStream();
        PrintWriter writer = new PrintWriter(socket.getOutputStream());
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String readline = "";
        String client = "Client:";
        while(true){
            System.out.println(client);
            readline = reader.readLine();
            if("over".equals(readline.trim())){
                writer.println(readline);
                writer.flush();
                break;
            }
            for(int i=1;i<=3;i++){
                readline += "abc";
            }
            writer.println(readline);
            writer.flush();
        }
        reader.close();
        writer.close();
        out.close();
        socket.close();

    }
}
