/*
 * @Autor: Zver
 * @Date: 2022-07-27 04:45:33
 * @LastEditTime: 2022-07-27 07:04:22
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
#define SERV_PORT 123110
#define DEST_PORT 123123
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
    struct sockaddr_in server;
    socklen_t len = sizeof(server);
    memset(&server, 0, sizeof(struct sockaddr_in)); 
    server.sin_family = AF_INET; 
    server.sin_port = htons(SERV_PORT); 
    server.sin_addr.s_addr = htonl(INADDR_ANY); //自动获取IP地址
    // 接受方必须bind
    if (bind(sock_fd, (struct sockaddr*)&server, sizeof(server)) < 0) {
        perror("bind error:");
        exit(1);
    }
    printf("i am server ，我监听端口号为%d,IP地址为任何\n", SERV_PORT);
    // client
    // printf("i an server，我朝着ip = %s,port = %d 发送message\n",DEST_IP_ADDRESS,DEST_PORT);
    // struct sockaddr_in client;
    // socklen_t len = sizeof(client);
    // memset(&client, 0, sizeof(client));
    // client.sin_family = AF_INET;       
    // client.sin_port   = htons(DEST_PORT);    
    // client.sin_addr.s_addr = inet_addr(DEST_IP_ADDRESS); 



    //timeout
    struct timeval tv;
    tv.tv_sec =0;
    tv.tv_usec = 200000; //200ms
    setsockopt(sock_fd,SOL_SOCKET,SO_RCVTIMEO,(const char *)&tv,sizeof(struct timeval));

    int counter = 0;
    char send_buf[1024] ="i am server";
    char recv_buf[1024] ;
    int send_num;
    int recv_num;
    int count=0;
    while (1) {
        
        recv_num = recvfrom(sock_fd,recv_buf,sizeof(recv_buf),0,(struct sockaddr*)&server,&len);
        if(recv_num >0){
            count++;
            printf("接收到了 %d 个字节，message = %s\n", recv_num, recv_buf);
            // send_num=sendto(sock_fd, send_buf, strlen(send_buf), 0, (sockaddr*)&client, len);
            // printf("发送 %d 字节,message = %s\n",send_num,send_buf);
        }    
        printf("接收到%d个message\n",count);
    }
    close(sock_fd);

    return 0;
}
