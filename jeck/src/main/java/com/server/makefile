## 对 CuckooServer 服务端调用 c++ 的cukoo filter 服务
## 如果要使用，需要修改几处地方
CC=gcc
CCFLAG =-shared -I

## 此处需要修改成自己的位置
INCLUDE_PATH=/Users/mrl/Library/Java/JavaVirtualMachines/corretto-11.0.16.1/Contents/Home/include

CUCKOO_PATH=CuckooFilter
SHA_O=${CUCKOO_PATH}/mozilla-sha1/sha1.o
SHA_H=${CUCKOO_PATH}/mozilla-sha1/sha1.h

SERVER_GOAL=libCuckooServer.jnilib


SERVER_PROS= server_CuckooServer.c \
	  ${CUCKOO_PATH}/cuckoo_filter.o \
	  ${SHA_O} \
	  ${CUCKOO_PATH}/cuckoo_run.o


link:${SERVER_PROS}
	${CC} ${CCFLAG} ${INCLUDE_PATH} ${SERVER_PROS} -o ${SERVER_GOAL}


${SHA_O}: ${SHA_H}

${CUCKOO_PATH}/cuckoo_run.o: ${CUCKOO_PATH}/cuckoo_filter.h ${SHA_H} ${CUCKOO_PATH}/cuckoo_run.h

server_CuckooServer.o: ${CUCKOO_PATH}/cuckoo_run.h server_CuckooServer.h

${CUCKOO_PATH}/cuckoo_filter.o: ${CUCKOO_PATH}/cuckoo_filter.h






build:
	javac CuckooServer.java -h .
clean:
	rm -rf ${CUCKOO_PATH}/cuckoo_run.o
	rm -rf ${SHA_O}
	rm -rf ${CUCKOO_PATH}/cuckoo_filter.o

