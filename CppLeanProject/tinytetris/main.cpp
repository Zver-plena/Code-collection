/*
 * @Autor: Zver
 * @Date: 2022-07-10 06:11:01
 * @LastEditTime: 2022-07-10 06:33:50
 */
#include<time.h>
#include<curses.h>
#include<unistd.h>
#include<stdlib.h>
#include<string.h>
#include<iostream>

int main(){
    srand(time(0));
    initscr();
    start_color();
    
    endwin();
}

