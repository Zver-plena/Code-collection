/*
 * @Autor: Zver
 * @Date: 2022-07-27 04:45:33
 * @LastEditTime: 2022-07-27 07:05:21
 */
#include<sys/select.h>
#include<unistd.h>
#include<sys/types.h>
#include<sys/socket.h>
#include<arpa/inet.h>
#include<netinet/in.h>
#include <cstdlib>
#include <cstdio>
#include <cstring>
#include <iostream>
#define SERV_PORT 123123
#define DEST_PORT 123110
#define DEST_IP_ADDRESS "127.0.0.1"
void server(int socketServer)
{
 
 
}
int main()
{
    int sock_fd = socket(AF_INET, SOCK_DGRAM, 0);
    if (sock_fd < 0) {
        perror("socket");
        exit(1);
    }
    // server
    // struct sockaddr_in server;
    // memset(&server, 0, sizeof(struct sockaddr_in)); 
    // server.sin_family = AF_INET; 
    // server.sin_port = htons(SERV_PORT); 
    // server.sin_addr.s_addr = htonl(INADDR_ANY); //自动获取IP地址
    // if (bind(sock_fd, (struct sockaddr*)&server, sizeof(server)) < 0) {
    //     perror("bind error:");
    //     exit(1);
    // }
    // printf("i am client ，我监听端口号为%d,IP地址为任何\n", SERV_PORT);

    //client 
    printf("i an client，我朝着ip = %s,port = %d 发送message\n",DEST_IP_ADDRESS,DEST_PORT);
    struct sockaddr_in client;
    socklen_t len = sizeof(client);
    memset(&client, 0, sizeof(client));
    client.sin_family = AF_INET;       
    client.sin_port   = htons(DEST_PORT);    
    client.sin_addr.s_addr = inet_addr(DEST_IP_ADDRESS); 

    int counter = 0;
    char send_buf[1024] ="i am client";
    char recv_buf[1024] ;
    int send_num;
    int recv_num;

    int count=0;
    while (count < 1e5){
        send_num=sendto(sock_fd, send_buf, strlen(send_buf), 0, (sockaddr*)&client, len);
        printf("发送 %d 字节,message = %s\n",send_num,send_buf);
        count++;
        // memset(recv_buf, 0, sizeof(recv_buf));
        // recv_num = recvfrom(sock_fd,recv_buf,sizeof(recv_buf),0,(struct sockaddr*)&server,&len);
        // if(recv_num >0){
        //     printf("接收到了 %d 个字节，message = %s\n", recv_num, recv_buf);
           
        // }
    }
    printf("发送了%d个message\n",count);
    close(sock_fd);

    return 0;
}
