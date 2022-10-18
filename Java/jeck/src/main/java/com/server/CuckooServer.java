package com.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author mrl
 * @data 2022/10/8
 */
public class CuckooServer {
    static{
        System.load("/Users/mrl/Macbook/JavaProject/jeck/src/main/java/server/libCuckooServer.jnilib");
    }
    private native void  run();
    private void recvMessage() {
        String ser = "Server:";
        ServerSocket server = null;
        BufferedWriter writer = null;
        try {
            server = new ServerSocket(9000);
            Socket accept = server.accept();
            System.out.println("连接到客户端");
            InputStream is = accept.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String readline = "";
            StringBuilder sb = new StringBuilder();
            while(!readline.trim().equals("over")){
                readline = reader.readLine();
                sb.append(readline);
                System.out.println("接收到数据为  = " + readline);
            }
            System.out.println("数据 = " + sb.toString());
            System.out.println("数据接受完成,开始写入文件");
            //TODO:固定位置
            String target = "/Users/mrl/Macbook/JavaProject/jeck/src/main/java/server/input.txt";
            File file = new File(target);
            writer = new BufferedWriter(new FileWriter(file));
            writer.write(sb.toString());
            writer.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                if(writer!= null){
                    writer.close();
                }
                if(server != null){
                    server.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
    public static void main(String[] args) {

        CuckooServer server = new CuckooServer();
        while(true){
            server.recvMessage();
            System.out.println("正在准备执行run方法");
            server.run();
        }


//            server.run();


    }
}
